package miprimeraapp.android.teaching.com.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import miprimeraapp.android.teaching.com.myapplication.interactors.GamesFirebaseInteractor;
import miprimeraapp.android.teaching.com.myapplication.interactors.GamesInteractor;
import miprimeraapp.android.teaching.com.myapplication.interactors.GamesInteractorCallback;
import miprimeraapp.android.teaching.com.myapplication.model.GameModel;

public class ListActivity extends BaseActivity {
    private MyAdapter myAdapter;
    private ListView listview;
    private GamesFirebaseInteractor gamesFirebaseInteractor;
    private MyConnectivtyBroadcastReceiver myConnectivityReceiver;

    //Asigno los nombres e iconos a los atributos





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

            obtenerUbicacion();
// Tenemos permisos
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                    100);
// No tenemos permisos
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        myConnectivityReceiver = new MyConnectivtyBroadcastReceiver();
        registerReceiver(myConnectivityReceiver, intentFilter);

        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
        if (isConnected == false) {
            findViewById(R.id.loading).setVisibility(View.GONE);
            Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show();

        } else {
            //token para mensajes push de Firebase
            String token = FirebaseInstanceId.getInstance().getToken();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("device_push_token");
            databaseReference.setValue(token);


            //carga la lista con cosas de la base de datos de firebase
            gamesFirebaseInteractor = new GamesFirebaseInteractor();
            gamesFirebaseInteractor.getGames(new GamesInteractorCallback() {
                @Override
                public void onGamesAvailable() {
                    //barra de carga
                    findViewById(R.id.loading).setVisibility(View.GONE);
                    myAdapter = new MyAdapter();
                    listview.setAdapter (new MyAdapter());
                }
            });
        }


        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);

        getSupportActionBar().setTitle("eSports");
        File directoriointerno = getFilesDir();
        File directorioexterno = getCacheDir();
        Log.d("ListActivity", "Interno:" + directoriointerno);
        Log.d("ListActivity", "Externo:" + directorioexterno);
        //Esto crea la carpeta privada sdcard
        getExternalFilesDir(null);

        //Encuentro los items en la lista XML y ejecuto el adapter

        listview = findViewById(R.id.list_View);


        //lista, set on click listener es para que ejecute algo al hacer click
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position , long id) {
                    //En gameDetailActivity estan los datos con los que relleno la lista.. mediante el adaptador.
                    Intent intent = new Intent (ListActivity.this, GameDetailActivity.class);
                    int gameId = new GamesInteractor().getGames().get(position).getId(); //obtener juegos, posicion de array, y el ID
                    intent.putExtra("position", position);
                    startActivity(intent);

            }
        });
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);


    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    &&  grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myConnectivityReceiver);
    }
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;

    }
    //inflar menu de opciones de toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu_main_activity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int identificador = item.getItemId();
        switch (identificador) {
            case R.id.actionbuttom:
                Intent intent = new Intent(this, LoginActivity.class);

                                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
        //Clase para crear el adaptador de la listview
    private class MyAdapter extends BaseAdapter {

        //Con ésta clase creo el adaptador, que hereda de Baseadapter
        @Override
        public int getCount() {
            return gamesFirebaseInteractor.getGames().size();
        }
        //Count es el numero de items que tendrá, si tiene mas de la cuenta la aplicación se cierra
        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Éste método "infla" la XML con los datos
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_item, parent, false);
            //Situo los iconos y los nombres y les añado una posición en la lista
            ImageView icon = rowView.findViewById(R.id.imagentest);
            Glide.with(ListActivity.this).load(gamesFirebaseInteractor.getGames().get(position).getIcon()).into(icon);
           // GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);
            //icon.setImageResource(gameIcons[position]);
            //este textview se saca de la firebase
            TextView textView = rowView.findViewById(R.id.texttest);
            textView.setText(gamesFirebaseInteractor.getGames().get(position).getName());

            return rowView;

        }
    }
        //para el gps, consumo de bateria alto
    @SuppressLint("MissingPermission")
    private void obtenerUbicacion() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location changed", "location is" + location.toString());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,999999,999999,listener);
    }
}

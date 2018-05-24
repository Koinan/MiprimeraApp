package miprimeraapp.android.teaching.com.myapplication;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import miprimeraapp.android.teaching.com.myapplication.interactors.GamesInteractor;
import miprimeraapp.android.teaching.com.myapplication.model.GameModel;

public class ListActivity extends BaseActivity {
    //Asigno los nombres e iconos a los atributos

    String[] gameNames = {"World of Warcraft", "Starcraft"};
    int[] gameIcons = {R.drawable.wow1, R.drawable.sc1};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Nuevo juego");
        GameModel game = new GameModel(700, "Parchis", "Descripcion", "www.asdasd.com", 0, 0
        );
        myRef.setValue (game);

        StringRequest myStringRequest = new StringRequest(Request.Method.GET, "https://miprimeraapp-db818.firebaseio.com/games.json",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("ListActivity", "Response is : " + response);
                        try {
                            JSONArray myArray = new JSONArray(response);
                            for (int i = 0; i < myArray.length();i++) {
                                JSONObject object = myArray.getJSONObject(i);
                                GameModel game = new GameModel(i,
                                        object.getString("name"),
                                        object.getString("description"),
                                        object.getString("officialWebsiteUrl"),
                                        0, 0
                                        );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue myQueue = Volley.newRequestQueue(this);
        myQueue.add(myStringRequest);

                setContentView(R.layout.activity_list);
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

        ListView listView = findViewById(R.id.list_View);
        listView.setAdapter (new MyAdapter());


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position , long id) {

                    Intent intent = new Intent (ListActivity.this, GameDetailActivity.class);
                    int gameId = new GamesInteractor().getGames().get(position).getId();
                    intent.putExtra("position", position);
                    startActivity(intent);

            }
        });
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

    private class MyAdapter extends BaseAdapter {

        //Con esta clase creo el adaptador, que hereda de Baseadapter
        @Override
        public int getCount() {
            return 2;
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
            icon.setImageResource(gameIcons[position]);

            TextView textView = rowView.findViewById(R.id.texttest);
            textView.setText(gameNames[position]);

            return rowView;

        }
    }
}

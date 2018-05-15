package miprimeraapp.android.teaching.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.util.List;

import miprimeraapp.android.teaching.com.myapplication.interactors.GamesInteractor;

public class ListActivity extends BaseActivity {
    //Asigno los nombres e iconos a los atributos

    String[] gameNames = {"World of Warcraft", "Starcraft"};
    int[] gameIcons = {R.drawable.wow1, R.drawable.sc1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);

        getSupportActionBar().setTitle("eSports");



        //Encuentro los items en la lista XML y ejecuto el adapter

        ListView listView = findViewById(R.id.list_View);
        listView.setAdapter (new MyAdapter());


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position , long id) {

                    Intent intent = new Intent (ListActivity.this, GameDetailActivity.class);
                    int gameId = new GamesInteractor().getGames().get(position).getId();
                    intent.putExtra("iddeljuego", gameId);
                    startActivity(intent);

            }
        });
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

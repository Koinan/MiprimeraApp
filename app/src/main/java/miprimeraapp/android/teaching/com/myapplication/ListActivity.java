package miprimeraapp.android.teaching.com.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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

public class ListActivity extends AppCompatActivity {
    //Asigno los nombres e iconos a los atributos

    String[] gameNames = {"Juego1", "World of Warcraft"};
    int[] gameIcons = {R.drawable.newwindow1, R.drawable.wow1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Encuentro los items en la lista XML y ejecuto el adapter

        ListView listView = findViewById(R.id.list_View);
        listView.setAdapter (new MyAdapter());

        //Al clicar los items de la lista ejecuto una Toast

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position , long id) {
                    Toast.makeText(ListActivity.this, getString(R.string.Position) + position,
                            Toast.LENGTH_LONG).show();

            }
        });
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
            //Pongo los iconos y los nombres y les añado una posicion en la lista
            ImageView icon = rowView.findViewById(R.id.imagentest);
            icon.setImageResource(gameIcons[position]);

            TextView textView = rowView.findViewById(R.id.texttest);
            textView.setText(gameNames[position]);

            return rowView;

        }
    }
}

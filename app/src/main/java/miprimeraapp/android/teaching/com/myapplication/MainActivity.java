package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate curso");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart curso");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Mainactivity", "onResume curso");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause curso");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop curso");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart curso");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy curso");
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class); //creo la intención de ejecutar la segunda activity en la memoria
        intent.putExtra("paco", "VALOR DE PACO");
        startActivity(intent); //ejecuto la intención
    }

    public void onClick2(View view) {
        String url = "https://WWW.GOOGLE.COM"; //creo la url que quiera
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); //ejecuto la accion de Ver algo, y ejecuto el comando Uri.parse para ejecutar la URL

        startActivity(intent); //ejecuto la intencion
    }

    public void onClick3(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 1234"));



        startActivity(intent);
    }
}

package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent(); //recibo el extra
        String valor = intent.getStringExtra("paco"); //lo traduzco
        Log.d("SecondActivity", "valor de paco: " + valor); //lo imprimo

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

}

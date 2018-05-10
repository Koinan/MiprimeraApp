package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.net.URLEncoder;

public class ProfileActivity extends BaseActivity {
    //Asigno los atributos
    private EditText usernameEditText;
    private EditText EmailEditText;
    private EditText passwordEditText;
    private EditText ageEditText;
    private RadioButton radiobuttonM;
    private RadioButton radioButtonF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        //obtengo el Id con findViewById
        usernameEditText = findViewById(R.id.username);
        EmailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        ageEditText = findViewById((R.id.age));
        radioButtonF = findViewById(R.id.genderf);
        radiobuttonM = findViewById(R.id.genderm);
        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);

        getSupportActionBar().setTitle(" ");
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu_main_activityprofile, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int identificador = item.getItemId();
        switch (identificador) {
            case R.id.actionbuttom:
                botonguardar();
                break;
            case R.id.actionbuttom2:
                borrarmethod();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void borrarmethod () {
        usernameEditText.setText("");
        passwordEditText.setText("");
        EmailEditText.setText("");
        ageEditText.setText("");
        radioButtonF.setChecked(false);
        radiobuttonM.setChecked(false);
    }
    public void onClick5(View view) {
        //Imprimo los datos con un get.text en Logcat
        Intent intent = new Intent(this, LoginActivity.class);
        Log.d("ProfileActivity", "Username:" + usernameEditText.getText());
        Log.d("ProfileActivity", "Email : " + EmailEditText.getText());
        Log.d("ProfileActivity", "Password : " + passwordEditText.getText());
        Log.d("ProfileActivity", "Age : " + ageEditText.getText());
        //Radio button
        if (radiobuttonM.isChecked()) {
            Log.d("ProfileActivity", "Gender male");
        } else if(radioButtonF.isChecked()) {
            Log.d("ProfileActivity", "Gender female");

        }
        startActivity(intent);


        }
        private void botonguardar() {
            //Imprimo los datos con un get.text en Logcat
            Intent intent = new Intent(this, LoginActivity.class);
            Log.d("ProfileActivity", "Username:" + usernameEditText.getText());
            Log.d("ProfileActivity", "Email : " + EmailEditText.getText());
            Log.d("ProfileActivity", "Password : " + passwordEditText.getText());
            Log.d("ProfileActivity", "Age : " + ageEditText.getText());
            //Radio button
            if (radiobuttonM.isChecked()) {
                Log.d("ProfileActivity", "Gender male");
            } else if(radioButtonF.isChecked()) {
                Log.d("ProfileActivity", "Gender female");

            }
            startActivity(intent);


        }


    public void onClickexit(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }


}

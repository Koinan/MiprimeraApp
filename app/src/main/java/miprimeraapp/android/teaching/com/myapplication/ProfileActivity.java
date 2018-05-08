package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.net.URLEncoder;

public class ProfileActivity extends AppCompatActivity {
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

    }
    public void onClick5(View view) {
        //Imprimo los datos con un get.text en Logcat
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

        }


    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }


}

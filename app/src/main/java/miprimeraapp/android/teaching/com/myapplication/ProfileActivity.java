package miprimeraapp.android.teaching.com.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

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
        ageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayofMonth    ) {
                            //Para calcular la edad a partir de dos fechas
                            int anoactual = Calendar.getInstance().get(Calendar.YEAR);
                            int edad = anoactual - year;
                            final int selectedDate = dayofMonth + (month+1) + year;
                           ageEditText.setText(String.valueOf(edad));
                        }
                    },1970,1,1).show();
                }
            }
        });
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
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_registry), Context.MODE_PRIVATE);
        String saveduser = sharedPref.getString("username_key", "no-user");
        String saveduser3 = sharedPref.getString("email_key", "no-email");
        String saveduser4 = sharedPref.getString("age_key", "no-age");
        usernameEditText.setText(saveduser);
        EmailEditText.setText(saveduser3);
        ageEditText.setText(saveduser4);

    }
    @Override
    protected void onStop() {
        super.onStop();
        String username = usernameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String email = EmailEditText.getText().toString();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_registry), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPref.edit();
        myEditor.putString("username_key", username);
        myEditor.putString("age_key", age);
        myEditor.putString("email_key", email);

        myEditor.apply();




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
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);


        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                usernameEditText.setText("");
                passwordEditText.setText("");
                EmailEditText.setText("");
                ageEditText.setText("");
                radioButtonF.setChecked(false);
                radiobuttonM.setChecked(false);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setNeutralButton(R.string.neutral, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

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

            Intent intent = new Intent(this, LoginActivity.class);
            Log.d("ProfileActivity", "Username:" + usernameEditText.getText());
            Log.d("ProfileActivity", "Email : " + EmailEditText.getText());
            Log.d("ProfileActivity", "Password : " + passwordEditText.getText());
            Log.d("ProfileActivity", "Age : " + ageEditText.getText());
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_registry), Context.MODE_PRIVATE);
            SharedPreferences.Editor myEditor = sharedPref.edit();
            myEditor.remove("username_key");
            myEditor.remove("email_key");
            myEditor.remove("age_key");
            myEditor.apply();

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
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_registry), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPref.edit();
        myEditor.remove("username_key");
        myEditor.remove("email_key");
        myEditor.remove("age_key");
        myEditor.apply();

        startActivity(intent);
    }


}

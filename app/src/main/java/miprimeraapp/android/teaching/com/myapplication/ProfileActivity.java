    package miprimeraapp.android.teaching.com.myapplication;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.net.URLEncoder;
import java.sql.SQLClientInfoException;
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
    private ImageView imageview;

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

        File imgFile = new File(getExternalFilesDir(null), "descarga.png");
        if(imgFile.exists()) {
            ImageView myImage= findViewById(R.id.imageview34);
            myImage.setImageURI(Uri.fromFile(imgFile));

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_registry), Context.MODE_PRIVATE);
        String saveduser0 = sharedPref.getString("usuario", "no-user");
        String saveduser = sharedPref.getString("usuariologin", "no-user");
        String saveduser3 = sharedPref.getString("usuarioemail", "no-email");
        String saveduser4 = sharedPref.getString("usuarioage", "no-age");
        String saveduser5 = sharedPref.getString("usuariogender", "no-gender");
        AppDatabase myDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "dblogin").allowMainThreadQueries().build();
        user myUser = myDatabase.userDao().findByUsername(saveduser0);
        if (myUser != null) {
            usernameEditText.setText(myUser.getUsername());
            EmailEditText.setText(myUser.getEmail());
            ageEditText.setText(myUser.getAge());
            if (myUser.getGender().equals(("H"))) {
                radiobuttonM.setChecked(true);
            } else if (myUser.getGender().equals("M")) {
                radioButtonF.setChecked(true);
            }

        }
        /*usernameEditText.setText(saveduser);
        EmailEditText.setText(saveduser3);
        ageEditText.setText(saveduser4);*/


    }
    @Override
    protected void onStop() {
        super.onStop();
        /*String username = usernameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String email = EmailEditText.getText().toString();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_registry), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPref.edit();
        myEditor.putString("username_key", username);
        myEditor.putString("age_key", age);
        myEditor.putString("email_key", email);

        myEditor.apply();*/




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
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dblogin").allowMainThreadQueries().build();
        try {
            user myNewUser = new user();
            myNewUser.setUsername(usernameEditText.getText().toString());
            myNewUser.setPassword(passwordEditText.getText().toString());
            myNewUser.setEmail(EmailEditText.getText().toString());
            myNewUser.setAge(ageEditText.getText().toString());
            if (radiobuttonM.isChecked()) {
                myNewUser.setGender("Male");
            } else if(radioButtonF.isChecked()) {
                myNewUser.setGender("Female");
            }
            db.userDao().insert(myNewUser);
            startActivity(intent);
        }catch (SQLiteConstraintException ex) {
            Toast.makeText(this, "Username already taken", Toast.LENGTH_LONG).show();
            //Algún error ha ocurrido al insertar
        }



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


            startActivity(intent);


        }


    public void onClickexit(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_registry), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPref.edit();
        myEditor.remove("usuariologin");
        myEditor.remove("usuarioemail");
        myEditor.remove("usuarioage");
        myEditor.apply();

        startActivity(intent);
    }

    public void clickprofileimage (View v) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())!= null) {

            File photoFile = createImageFile();
            Uri photoURI = FileProvider.getUriForFile(this, "miprimeraapp.android.teaching.com.myapplication", photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, 100);
        }




    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           imageview = findViewById(R.id.imageview34);
        if (requestCode == 100 && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageview.setImageBitmap(imageBitmap);
            createImageFile();
        } else if (requestCode == 100 && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "New profile pic canceled", Toast.LENGTH_LONG).show();
        }
    }*/
    private File createImageFile() {
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return new File(storageDir,"profile.jpg");
    }
    @Override
    protected void onResume() {
        super.onResume();

        File myFile = createImageFile();
        if (myFile.exists()) {
            ImageView imageView = findViewById(R.id.imageview34);
            imageView.setImageBitmap(BitmapFactory.decodeFile(myFile.getAbsolutePath()));
        }
    }

}



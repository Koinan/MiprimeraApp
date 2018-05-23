package miprimeraapp.android.teaching.com.myapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.ConnectException;

public class LoginActivity extends BaseActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setTitle(R.string.Login);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_preferences),Context.MODE_PRIVATE);
        String saveduser = sharedPref.getString("username_key", "no-user");
        usernameEditText.setText(saveduser);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.menu_main_activitylogin, menu);
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
    public void onClickexit2 (View view) {
        usernameEditText.setText("");
        passwordEditText.setText("");
       // Intent intent = new Intent (this, MainActivity.class);
        // startActivity(intent);
    }
    public void doRegister (View view) {
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }
    public void doLogin (View view) {


        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dblogin").allowMainThreadQueries().build();
            user usuariologin = db.userDao().findByUsername(username);


        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError(getString(R.string.usernameerror));
        } else if (TextUtils.isEmpty(password)) {
            passwordEditText.setError(getString(R.string.passworderror));
        } if (usuariologin == null) {
            Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();
        }else if (password.equals(usuariologin.getPassword())) {
                 Toast.makeText(this, "Login OK", Toast.LENGTH_LONG).show();
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.user_registry), Context.MODE_PRIVATE);
            SharedPreferences.Editor myEditor = sharedPref.edit();
            myEditor.putString("usuario", username);
            myEditor.putString("usuariologin", usuariologin.getUsername());
            myEditor.putString("usuarioemail", usuariologin.getEmail());
            myEditor.putString("usuarioage", usuariologin.getAge());
            myEditor.putString("usuariogender", usuariologin.getGender());
            myEditor.apply();


            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);


        }else {
            Toast.makeText(this, "Invalid Login", Toast.LENGTH_LONG).show();

        }
    }
}

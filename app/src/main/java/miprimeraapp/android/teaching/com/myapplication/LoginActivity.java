package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        Toast.makeText(this, getString(R.string.LoginToast),
                Toast.LENGTH_LONG).show();
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
        String password = usernameEditText.getText().toString();
        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError(getString(R.string.usernameerror));
        } else if (TextUtils.isEmpty(password)) {
            passwordEditText.setError(getString(R.string.passworderror));
        } else {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}

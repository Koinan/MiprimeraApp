package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Toast.makeText(this, getString(R.string.LoginToast),
                Toast.LENGTH_LONG).show();
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

package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        EditText usernameEDitText = findViewById(R.id.username);
        String usuario = usernameEDitText.getText().toString();
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }


}

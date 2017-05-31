package b05studio.com.order_boss.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import b05studio.com.order_boss.R;

/**
 * Created by young on 2017-05-31.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initLoginView();
    }

    private void initLoginView() {
        final EditText loginEditIdText = (EditText)findViewById(R.id.loginIdTextView);
        final EditText loginEditPassText = (EditText)findViewById(R.id.loginPassTextView);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(loginEditIdText.getText().toString(),loginEditPassText.getText().toString());
            }
        });
    }

    private void login(String id, String pass) {
        moveActivity();
    }

    private void moveActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}

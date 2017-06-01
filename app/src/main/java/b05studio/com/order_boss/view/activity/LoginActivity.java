package b05studio.com.order_boss.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.ReservationInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.model.User;

/**
 * Created by young on 2017-05-31.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //TODO:2017.05.29 유저정보를 데이터베이스에서 가져오는 프로세스 필요
        User.setCurrentUser(new User("1", "", "김만수", "010-0000-0000", new ArrayList<Review>(), new ArrayList<ReservationInfo>()));

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

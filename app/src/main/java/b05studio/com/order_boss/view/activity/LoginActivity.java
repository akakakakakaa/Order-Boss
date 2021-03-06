package b05studio.com.order_boss.view.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

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

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                sendNotification();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask,600000);
        //sendNotification();
    }

    private void login(String id, String pass) {
        moveActivity();
    }

    private void moveActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void sendNotification() {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.icon_jungyeon)
                    .setContentTitle("순번대장")
                    .setContentText("고객님의 주문하신 \"브리또\"가 앞으로 5분뒤면 완성됩니다!")
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
    }




}





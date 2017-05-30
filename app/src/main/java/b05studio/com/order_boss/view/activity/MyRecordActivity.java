package b05studio.com.order_boss.view.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.view.fragment.MyRecordRestrauantFragment;
import b05studio.com.order_boss.view.fragment.MyRecordReviewFragment;


public class MyRecordActivity extends AppCompatActivity {

    private Fragment currentSelectedFragment;
    private MyRecordRestrauantFragment myRecordRestrauantFragment;
    private MyRecordReviewFragment myRecordReviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_record);

        initFragment();
        initButton();
    }

    private void initFragment()  {
        myRecordRestrauantFragment = new MyRecordRestrauantFragment();
        myRecordReviewFragment = new MyRecordReviewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.myRecordFragment, myRecordRestrauantFragment).commit();
    }

    private void initButton() {
        final ImageView myRestaruantButton = (ImageView) findViewById(R.id.myRecordRestauratButton);
        final View myRestaruantButtonBottom = findViewById(R.id.myRecordRestauratButtonBottomView);

        final ImageView myReviewButton = (ImageView) findViewById(R.id.myRecordReviewButton);
        final View myReviewButtonBottom = findViewById(R.id.myRecordReviewButtonBottomView);

        myRestaruantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRestaruantButton.setImageResource(R.drawable.icon_my_restaurant_checked);
                myRestaruantButtonBottom.setBackgroundColor(Color.parseColor("#FFA133"));

                myReviewButton.setImageResource(R.drawable.icon_my_review_unchecked);
                myReviewButtonBottom.setBackgroundColor(Color.parseColor("#FFFFFF"));

                getSupportFragmentManager().beginTransaction().replace(R.id.myRecordFragment, myRecordRestrauantFragment).commit();
            }
        });

        myReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRestaruantButton.setImageResource(R.drawable.icon_my_restaurant_unchecked);
                myRestaruantButtonBottom.setBackgroundColor(Color.parseColor("#FFFFFF"));

                myReviewButton.setImageResource(R.drawable.icon_my_review_checked);
                myReviewButtonBottom.setBackgroundColor(Color.parseColor("#FFA133"));

                getSupportFragmentManager().beginTransaction().replace(R.id.myRecordFragment, myRecordReviewFragment).commit();
            }
        });

    }


}

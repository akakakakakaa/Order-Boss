package b05studio.com.order_boss.view.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        initBotttomBar();
    }

    private void initFragment()  {
        myRecordRestrauantFragment = new MyRecordRestrauantFragment();
        myRecordReviewFragment = new MyRecordReviewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.myRecordFragment, myRecordRestrauantFragment).commit();
    }

    private void initBotttomBar() {
        BottomNavigationBar bottomNavigationBar;
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.profileNavigationBar);

        BottomNavigationItem firstBottomItem = new BottomNavigationItem(R.drawable.icon_my_restaurant_checked);
        Drawable inActiveIcon1 = getResources().getDrawable(R.drawable.icon_my_restaurant_unchecked);
        inActiveIcon1.setTint(Color.parseColor("#888888"));
        firstBottomItem.setInactiveIcon(inActiveIcon1);

        BottomNavigationItem secondBottomItem = new BottomNavigationItem(R.drawable.icon_my_review_checked);
        Drawable inActiveIcon2 = getResources().getDrawable(R.drawable.icon_my_review_unchecked);
        inActiveIcon2.setTint(Color.parseColor("#888888"));
        secondBottomItem.setInactiveIcon(inActiveIcon2);

        bottomNavigationBar
                .addItem(firstBottomItem)
                .addItem(secondBottomItem).initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        currentSelectedFragment = myRecordRestrauantFragment;
                        break;
                    case 1:
                        currentSelectedFragment = myRecordReviewFragment;
                        break;
                    default:
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.myRecordFragment,currentSelectedFragment).commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }


}

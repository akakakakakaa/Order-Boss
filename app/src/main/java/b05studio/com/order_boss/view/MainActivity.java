package b05studio.com.order_boss.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tsengvn.typekit.TypekitContextWrapper;

import b05studio.com.order_boss.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initBottomNaviBar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void initBottomNaviBar() {
        BottomNavigationBar bottomNavigationBar;
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.mainBottomNavigationBar);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable .icon_main_checked));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_order_list_checked));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_profile_checked)).initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }

            @Override
              public void onTabUnselected(int position) {

            }


            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


}

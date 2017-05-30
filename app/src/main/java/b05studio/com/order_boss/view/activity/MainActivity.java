package b05studio.com.order_boss.view.activity;

import android.app.Activity;
import android.content.Context;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.content.Intent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.ReservationInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.model.User;
import b05studio.com.order_boss.view.fragment.MyReservationFragment;
import b05studio.com.order_boss.view.fragment.ProfileFragment;
import b05studio.com.order_boss.view.fragment.MapFragment;
import b05studio.com.order_boss.view.fragment.RestaurantListFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment currentSelectedFragment;
    private static int firstTabSwitching;
    private static RestaurantListFragment restaurantListFragment; //firstTabSwitching 0 inittoolbar 0
    private static MapFragment mapFragment;                         //firstTabSwitching 1 inittoolbar 1
    private MyReservationFragment myReservationFragment;   //inittoolbar 2
    private ProfileFragment profileFragment;                //inittoolbar 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* for test create user*/
        //TODO:2017.05.29 유저정보를 데이터베이스에서 가져오는 프로세스 필요
        User.setCurrentUser(new User("1", "", "김만수", new ArrayList<Review>(), new ArrayList<ReservationInfo>()));

        initFragment();
        initBottomNaviBar();
    }

    private void initFragment() {
        restaurantListFragment = new RestaurantListFragment();
        mapFragment = new MapFragment();
        myReservationFragment = new MyReservationFragment();
        profileFragment = new ProfileFragment();

        firstTabSwitching = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, restaurantListFragment).commit();
    }

    private void initBottomNaviBar() {
        BottomNavigationBar bottomNavigationBar;
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.mainBottomNavigationBar);

        BottomNavigationItem firstBottomItem = new BottomNavigationItem(R.drawable.icon_main_checked);
        Drawable inActiveIcon1 = getResources().getDrawable(R.drawable.icon_main_unchecked);
        inActiveIcon1.setTint(Color.parseColor("#888888"));
        firstBottomItem.setInactiveIcon(inActiveIcon1);
        //firstBottomItem.setInactiveIcon(getResources().getDrawable(R.drawable.icon_main_empty));
        //firstBottomItem.setInActiveColor(Color.parseColor("#888888"));


        BottomNavigationItem secondBottomItem = new BottomNavigationItem(R.drawable.icon_order_list_checked);
        Drawable inActiveIcon2 = getResources().getDrawable(R.drawable.icon_order_list_unckecked);
        inActiveIcon2.setTint(Color.parseColor("#888888"));
        secondBottomItem.setInactiveIcon(inActiveIcon2);
       // secondBottomItem.setInActiveColor(Color.parseColor("#888888"));

        BottomNavigationItem thirdBottomItem = new BottomNavigationItem(R.drawable.icon_profile_checked);
        Drawable inActiveIcon3 = getResources().getDrawable(R.drawable.icon_profile_unchecked);
        inActiveIcon3.setTint(Color.parseColor("#888888"));
        thirdBottomItem.setInactiveIcon(inActiveIcon3);
       // thirdBottomItem.setInactiveIcon(getResources().getDrawable(R.drawable.icon_profile_unchecked));
       // thirdBottomItem.setInActiveColor(Color.parseColor("#888888"));

       // firstBottomItem.setInactiveIcon()
        bottomNavigationBar
                .addItem(firstBottomItem)
                .addItem(secondBottomItem)
                .addItem(thirdBottomItem).initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        //initToolbar(position);
                        if(firstTabSwitching == 0)
                            currentSelectedFragment = mapFragment;
                        else
                            currentSelectedFragment = restaurantListFragment;
                        break;
                    case 1:
                        //initToolbar(position);
                        currentSelectedFragment = myReservationFragment;
                        break;
                    case 2:
                        //initToolbar(position);
                        currentSelectedFragment = profileFragment;
                        break;
                    default:
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,currentSelectedFragment).commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }


            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    public static void moveToMapFragment(FragmentManager fm, String title) {
        fm.beginTransaction().replace(R.id.container, mapFragment).commit();
        mapFragment.setTitle(title);
        firstTabSwitching = 0;
    }

    public static void moveToRestaurantListFragment(FragmentManager fm) {
        fm.beginTransaction().replace(R.id.container, restaurantListFragment).commit();
        firstTabSwitching = 1;
    }

    /*
    private void initToolbar(final int position) {
        if(position == 0 || position == 1) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            final EditText searchText = (EditText) findViewById(R.id.mainSearchView);
            TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String queryString = searchText.getText().toString();
                            switch (position) {
                                case 0:
                                    getSupportFragmentManager().beginTransaction().replace(R.id.container,restaurantListFragment).commit();
                                    restaurantListFragment.searchKeyword(queryString);
                                    break;
                                case 1:
                                    restaurantListFragment.searchKeyword(queryString);
                                    break;
                            }
                        }
                    }).start();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };
            searchText.addTextChangedListener(watcher);
        }
    }
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RestaurantListFragment.SEARCH) {
            if(resultCode == Activity.RESULT_OK)
                restaurantListFragment.searchKeyword(data.getStringExtra("keyword"));
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
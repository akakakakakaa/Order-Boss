package b05studio.com.order_boss.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tsengvn.typekit.TypekitContextWrapper;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.view.fragment.ProfileFragment;
import b05studio.com.order_boss.view.fragment.MapFragment;
import b05studio.com.order_boss.view.fragment.RestaurantListFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment currentSelectedFragment;
    private MapFragment mapFragment;
    private RestaurantListFragment restaurantListFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initBottomNaviBar();
    }

    private void initFragment() {
        mapFragment = new MapFragment();
        restaurantListFragment = new RestaurantListFragment();
        profileFragment = new ProfileFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,mapFragment).commit();
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
                        initToolbar(position);
                        currentSelectedFragment = mapFragment;
                        break;
                    case 1:
                        initToolbar(position);
                        currentSelectedFragment = restaurantListFragment;
                        break;
                    case 2:
                        initToolbar(position);
                        currentSelectedFragment = profileFragment;
                        break;
                    default:
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,currentSelectedFragment).addToBackStack(null).commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }


            @Override
            public void onTabReselected(int position) {

            }
        });
    }

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


}

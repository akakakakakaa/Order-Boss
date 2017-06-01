package b05studio.com.order_boss.view.activity;

import android.app.Activity;
import android.content.Context;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.content.Intent;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.jar.Manifest;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.ReservationInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.model.User;
import b05studio.com.order_boss.util.LocationTracker;
import b05studio.com.order_boss.view.fragment.MyReservationFragment;
import b05studio.com.order_boss.view.fragment.ProfileFragment;
import b05studio.com.order_boss.view.fragment.MapFragment;
import b05studio.com.order_boss.view.fragment.RestaurantListFragment;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private static final int RC_LOCATION_REQUEST = 8001;
    private Fragment currentSelectedFragment;
    private static int firstTabSwitching;
    private static RestaurantListFragment restaurantListFragment; //firstTabSwitching 0 inittoolbar 0
    private static MapFragment mapFragment;                         //firstTabSwitching 1 inittoolbar 1
    private MyReservationFragment myReservationFragment;   //inittoolbar 2
    private ProfileFragment profileFragment;                //inittoolbar 3

   LocationManager locationManager;
    private Location currentLocation;

    private LocationListener mLocationListner = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            // TODO: 2017-06-01 정확도를 빅해서 하는 방법도 있는데 고려를 해보아야됨.
            LocationTracker.setCurLoc(location);

            Log.d("현재 위도와 경도", location.getLatitude() + " " + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* for test create user*/
        //TODO:2017.05.29 유저정보를 데이터베이스에서 가져오는 프로세스 필요
        User.setCurrentUser(new User("1", "", "김만수", new ArrayList<Review>(), new ArrayList<ReservationInfo>()));

        requestLocationPermission();
        initFragment();
        initBottomNaviBar();
    }

    private void requestGetMyLocation() {

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Log.d("네트워크 사용여부",String.valueOf(isNetworkEnabled));

            boolean isGPSEnaabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Log.d("GPS 사용여부", String.valueOf(isGPSEnaabled));

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, RC_LOCATION_REQUEST);
            } else {
                if (isGPSEnaabled) { // gps 가 켜져있다면
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mLocationListner);
                    currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                if(isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, mLocationListner);
                    if (currentLocation == null) {
                        currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }

                if (currentLocation != null) {
                    Log.d("현재 위도와 경도", currentLocation.getLatitude() + " " + currentLocation.getLongitude());
                    LocationTracker.setCurLoc(currentLocation);
                }

                if(!isGPSEnaabled && !isNetworkEnabled ) {
                    //show dialog to allow user to enable location settings
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("위치 정보 설정 ");
                    dialog.setMessage("위치 정보 권한을 활성화 시켜주세요.");

                    dialog.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
                        }
                    });

                    dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            //nothing to do
                        }
                    });
                    dialog.show();
                }
            }
        }
    }

    @AfterPermissionGranted(RC_LOCATION_REQUEST)
    private void requestLocationPermission() {
        if(EasyPermissions.hasPermissions(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            // TODO: 2017-06-01 위치 트랙킹
            requestGetMyLocation();

        } else {
            EasyPermissions.requestPermissions(this, "정확한 정보 제공을 위하여 위치 정보가 필요합니다.",
                    RC_LOCATION_REQUEST, android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
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
        setTint(inActiveIcon1, Color.parseColor("#888888"));
        firstBottomItem.setInactiveIcon(inActiveIcon1);
        //firstBottomItem.setInactiveIcon(getResources().getDrawable(R.drawable.icon_main_empty));
        //firstBottomItem.setInActiveColor(Color.parseColor("#888888"));


        BottomNavigationItem secondBottomItem = new BottomNavigationItem(R.drawable.icon_order_list_checked);
        Drawable inActiveIcon2 = getResources().getDrawable(R.drawable.icon_order_list_unckecked);
        setTint(inActiveIcon2, Color.parseColor("#888888"));
        secondBottomItem.setInactiveIcon(inActiveIcon2);
       // secondBottomItem.setInActiveColor(Color.parseColor("#888888"));

        BottomNavigationItem thirdBottomItem = new BottomNavigationItem(R.drawable.icon_profile_checked);
        Drawable inActiveIcon3 = getResources().getDrawable(R.drawable.icon_profile_unchecked);
        setTint(inActiveIcon3, Color.parseColor("#888888"));
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

    private void setTint(Drawable d, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(d);
        DrawableCompat.setTint(wrappedDrawable, color);
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            requestGetMyLocation();
        }
    }
}
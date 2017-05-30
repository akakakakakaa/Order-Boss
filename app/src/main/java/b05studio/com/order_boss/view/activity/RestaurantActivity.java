package b05studio.com.order_boss.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Toast;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.User;
import b05studio.com.order_boss.view.WrapContentViewPager;
import b05studio.com.order_boss.view.fragment.RestaurantInfoFragment;
import b05studio.com.order_boss.view.fragment.RestaurantMenuFragment;
import b05studio.com.order_boss.view.fragment.RestaurantReviewFragment;

/**
 * Created by mansu on 2017-05-18.
 */

public class RestaurantActivity extends AppCompatActivity {
    private WrapContentViewPager viewPager;
    public static PopupWindow popupWindow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        initScrollView();
        initToolbar();
        initTabWithViewPager();
        initButtons();
    }

    private void initScrollView() {
        ScrollView scrollView = (ScrollView)findViewById(R.id.restaurantScrollView);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.restaurantToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initTabWithViewPager() {
        TabLayout tabLayout = (TabLayout)findViewById(R.id.restaurantTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("메뉴"));
        tabLayout.addTab(tabLayout.newTab().setText("정보"));
        tabLayout.addTab(tabLayout.newTab().setText("리뷰"));

        viewPager = (WrapContentViewPager)findViewById(R.id.restaurantViewPager);
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                viewPager.reMeasureCurrentPage(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initButtons() {
        Button reservationBtn = (Button)findViewById(R.id.restaurantReservationButton);
        reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(User.getCurrentUser().getCurrentOrderInfos().size() == 0)
                    Toast.makeText(RestaurantActivity.this, "메뉴를 선택해 주세요.", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(RestaurantActivity.this, ReservationActivity.class);
                    startActivity(intent);
                }
            }
        });

        ImageButton backBtn = (ImageButton)findViewById(R.id.restaurantBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button restaurantShoppingBtn = (Button)findViewById(R.id.restaurantShoppingButton);
        restaurantShoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(User.getCurrentUser().getCurrentOrderInfos().size() == 0)
                    Toast.makeText(RestaurantActivity.this, "메뉴를 선택해 주세요.", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(RestaurantActivity.this, BasketActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private class TabPagerAdapter extends FragmentStatePagerAdapter {
        private int tabCount;

        public TabPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = new RestaurantMenuFragment();
                    break;
                case 1:
                    fragment = new RestaurantInfoFragment();
                    break;
                case 2:
                    fragment = new RestaurantReviewFragment();
                    break;
                default:
                    return null;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    };

    @Override
    public void onBackPressed() {
        if(popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        else
            finish();
    }
}

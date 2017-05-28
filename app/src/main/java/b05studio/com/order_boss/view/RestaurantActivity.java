package b05studio.com.order_boss.view;

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
import android.widget.ScrollView;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.Restaurant;
import b05studio.com.order_boss.view.fragment.RestaurantInfoFragment;
import b05studio.com.order_boss.view.fragment.RestaurantMenuFragment;
import b05studio.com.order_boss.view.fragment.RestaurantReviewFragment;

/**
 * Created by mansu on 2017-05-18.
 */

public class RestaurantActivity extends AppCompatActivity {
    private WrapContentViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        setScrollView();
        initToolbar();
        initTabWithViewPager();
        initReservationButton();
    }

    private void setScrollView() {
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

    private void initReservationButton() {
        Button reservationBtn = (Button)findViewById(R.id.restaurantReservation);
        reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ReservationActivity.class);
                startActivity(intent);
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
            switch (position) {
                case 0:
                    RestaurantMenuFragment fragment = new RestaurantMenuFragment();
                    return fragment;
                case 1:
                    RestaurantInfoFragment fragment2 = new RestaurantInfoFragment();
                    return fragment2;
                case 2:
                    RestaurantReviewFragment fragment3 = new RestaurantReviewFragment();
                    return fragment3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    };
}

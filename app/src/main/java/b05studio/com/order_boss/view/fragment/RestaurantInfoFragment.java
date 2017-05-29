package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.RestaurantInfo;

/**
 * Created by mansu on 2017-05-26.
 */

public class RestaurantInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_info, container, false);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.restaurantInfoRecyclerView);
        recyclerView.setHasFixedSize(true);
        //get MenuInfo
        RestaurantInfoFragment.RestaurantInfoAdatper restaurantMenuAdatper = new RestaurantInfoFragment.RestaurantInfoAdatper(RestaurantInfo.getCurrentRestaurantInfo().getMenuInfos(), getContext(), inflater);
        recyclerView.setAdapter(restaurantMenuAdatper);

        initRestaurantInfo(rootView);
        return rootView;
    }

    private void initRestaurantInfo(View rootView) {
        RestaurantInfo info = RestaurantInfo.getCurrentRestaurantInfo();

        //TODO:2017-05-29 지도 가져오는것 필요
        FrameLayout mapLayout = (FrameLayout)rootView.findViewById(R.id.restaurantInfoMap);
        TextView address = (TextView)rootView.findViewById(R.id.restaurantInfoAddress);
        TextView phoneNum = (TextView)rootView.findViewById(R.id.restaurantInfoPhoneNum);
        TextView businessHours = (TextView)rootView.findViewById(R.id.restaurantInfoTime);
        TextView holiday = (TextView)rootView.findViewById(R.id.restaurantInfoHoliday);
        TextView avgPrice = (TextView)rootView.findViewById(R.id.restaurantInfoPrice);

        address.setText(info.getAddress());
        phoneNum.setText(info.getPhoneNum());
        businessHours.setText(info.getStartHour() + ":" + info.getStartMinute() + " ~ " + info.getEndHour() + ":" + info.getEndMinute());
        holiday.setText(info.getHolidayString());
        avgPrice.setText(info.getAvgPrice());
    }

    private class RestaurantInfoAdatper extends RecyclerView.Adapter<RestaurantInfoFragment.RestaurantInfoAdatper.ViewHolder> {
        private ArrayList<MenuInfo> menuInfos;
        private Context context;
        private LayoutInflater inflater;

        public RestaurantInfoAdatper(ArrayList<MenuInfo> menuInfos, Context context, LayoutInflater inflater) {
            this.menuInfos = menuInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public RestaurantInfoFragment.RestaurantInfoAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_restaurant_info, parent, false);
            RestaurantInfoFragment.RestaurantInfoAdatper.ViewHolder holder = new RestaurantInfoFragment.RestaurantInfoAdatper.ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(RestaurantInfoFragment.RestaurantInfoAdatper.ViewHolder holder, int position) {
            MenuInfo menuInfo = menuInfos.get(position);

            //Picasso.with(context).load(menuInfo.getUrl()).into(holder.restaurantInfoMenuImage);
            holder.restaurantInfoMenuName.setText(menuInfo.getName());
            holder.restaurantInfoMenuPrice.setText(menuInfo.getPrice() + "원");
        }

        @Override
        public int getItemCount() {
            return menuInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView restaurantInfoMenuName;
            public TextView restaurantInfoMenuPrice;

            public ViewHolder(View view) {
                super(view);
                restaurantInfoMenuName = (TextView)view.findViewById(R.id.restaurantInfoMenuName);
                restaurantInfoMenuPrice = (TextView)view.findViewById(R.id.restaurantInfoMenuPrice);
            }
        }
    }
}
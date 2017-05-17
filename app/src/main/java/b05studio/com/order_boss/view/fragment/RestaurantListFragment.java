package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.Restaurant;
import b05studio.com.order_boss.model.RestaurantInfo;

/**
 * Created by mansu on 2017-05-17.
 */

public class RestaurantListFragment extends Fragment {
    private RecyclerView restaurantListRecyclerView;
    private RecyclerView.Adapter restaurantListAdapter;
    private final static Comparator<RestaurantInfo> timeComparator = new Comparator<RestaurantInfo>() {
        @Override
        public int compare(RestaurantInfo o1, RestaurantInfo o2) {
            return (o1.getTime() >= o2.getTime()) ? 1 : 0;
        }
    };

    private final static Comparator<RestaurantInfo> distanceComparator = new Comparator<RestaurantInfo>() {
        @Override
        public int compare(RestaurantInfo o1, RestaurantInfo o2) {
            return (o1.getRestaurant().getDistance() >= o2.getRestaurant().getDistance()) ? 1 : 0;
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        //for restaurant list
        restaurantListRecyclerView = (RecyclerView)rootView.findViewById(R.id.restaurantListRecyclerView);
        restaurantListRecyclerView.setHasFixedSize(true);
        ArrayList<RestaurantInfo> restaurantInfos = new ArrayList<>();
        //TODO: 2017-05-17 파이어베이스에서 레스토랑 데이터 들고오는 것 필요함.
        restaurantInfos.add(new RestaurantInfo(new Restaurant("1", "1. 멘무샤", "일식 > 라멘", "경기도 화성시 동탄중앙로 220", 128, "1"), 62, 12, 12));
        restaurantInfos.add(new RestaurantInfo(new Restaurant("2", "2. 사보텐", "일식 > 돈카츠", "경기도 화성시 동탄중앙로 220", 133, "1"), 62, 12, 22));
        restaurantInfos.add(new RestaurantInfo(new Restaurant("3", "3. 빈티지 컨테이너", "양식 > 주점", "경기도 화성시 동탄중앙로 220", 145, "1"), 62, 12, 26));
        Collections.sort(restaurantInfos, timeComparator);
        restaurantListAdapter = new RestaurantListAdapter(restaurantInfos, getContext(), inflater);
        restaurantListRecyclerView.setAdapter(restaurantListAdapter);
        return rootView;
    }

    private class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {
        private ArrayList<RestaurantInfo> restaurantInfos;
        private Context context;
        private LayoutInflater inflater;

        public RestaurantListAdapter(ArrayList<RestaurantInfo> restaurantInfos, Context context, LayoutInflater inflater) {
            this.restaurantInfos = restaurantInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public RestaurantListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_restaurant_list, parent, false);
            RestaurantListAdapter.ViewHolder holder = new RestaurantListAdapter.ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(RestaurantListAdapter.ViewHolder holder, int position) {
            RestaurantInfo restaurantInfo = restaurantInfos.get(position);
            Restaurant restaurant = restaurantInfo.getRestaurant();

            //Picasso.with(context).load(restaurant.getImageUrl()).into(holder.mapRestaurantImageView);
            holder.restaurantListName.setText(restaurant.getName());
            holder.restaurantListFoodTag.setText(restaurant.getFoodTag());
            holder.restaurantListLikeNumber.setText(Integer.toString(restaurantInfo.getLikeNumber()));
            holder.restaurantListReviewNumber.setText(Integer.toString(restaurantInfo.getReviewNumber()));
            holder.restaurantListTime.setText(Integer.toString(restaurantInfo.getTime()));
            holder.restaurantListDistance.setText(Integer.toString(restaurant.getDistance()));
        }

        @Override
        public int getItemCount() {
            return restaurantInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView restaurantListImageView;
            public TextView restaurantListName;
            public TextView restaurantListFoodTag;
            public TextView restaurantListLikeNumber;
            public TextView restaurantListReviewNumber;
            public TextView restaurantListTime;
            public TextView restaurantListDistance;

            public ViewHolder(View view) {
                super(view);
                restaurantListImageView = (ImageView)view.findViewById(R.id.restaurantListImageView);
                restaurantListName = (TextView)view.findViewById(R.id.restaurantListName);
                restaurantListFoodTag = (TextView)view.findViewById(R.id.restaurantListFoodTag);
                restaurantListLikeNumber = (TextView)view.findViewById(R.id.restaurantListLikeNumber);
                restaurantListReviewNumber = (TextView)view.findViewById(R.id.restaurantListReviewNumber);
                restaurantListTime = (TextView)view.findViewById(R.id.restaurantListTime);
                restaurantListDistance = (TextView)view.findViewById(R.id.restaurantListDistance);
            }
        }
    }
}

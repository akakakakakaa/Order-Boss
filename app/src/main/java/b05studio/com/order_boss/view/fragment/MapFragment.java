package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.Restaurant;
import b05studio.com.order_boss.model.RestaurantInfo;

/**
 * Created by young on 2017-05-16.
 */

public class MapFragment extends Fragment {
    RecyclerView mapRestaurantRecyclerView;
    RecyclerView.Adapter mapRestaurantAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_map, container, false);

        //for daum map
        MapView mapView = new MapView(getActivity());
        mapView.setDaumMapApiKey(getString(R.string.daum_map_api_key));
        ViewGroup mapViewContainer = (ViewGroup) rootView.findViewById(R.id.mapView);
        mapViewContainer.addView(mapView);

        //for restaurant list
        mapRestaurantRecyclerView = (RecyclerView)rootView.findViewById(R.id.mapRestaurantRecyclerView);
        mapRestaurantRecyclerView.setHasFixedSize(true);
        ArrayList<RestaurantInfo> restaurantInfos = new ArrayList<>();
        //TODO: 2017-05-17 파이어베이스에서 레스토랑 데이터 들고오는 것 필요함.
        restaurantInfos.add(new RestaurantInfo(new Restaurant("1", "3. 멘무샤", "일식, 라멘", "경기도 화성시 동탄중앙로 220", 133, "1"), 62, 12, 25));
        restaurantInfos.add(new RestaurantInfo(new Restaurant("2", "3. 멘무샤", "일식, 라멘", "경기도 화성시 동탄중앙로 220", 133, "1"), 62, 12, 25));
        restaurantInfos.add(new RestaurantInfo(new Restaurant("3", "3. 멘무샤", "일식, 라멘", "경기도 화성시 동탄중앙로 220", 133, "1"), 62, 12, 25));
        mapRestaurantAdapter = new MapRestaurantAdapter(restaurantInfos, getContext(), inflater);
        mapRestaurantRecyclerView.setAdapter(mapRestaurantAdapter);
        return rootView;
    }

     private class MapRestaurantAdapter extends RecyclerView.Adapter<MapRestaurantAdapter.ViewHolder> {
        private ArrayList<RestaurantInfo> restaurantInfos;
        private Context context;
        private LayoutInflater inflater;

        public MapRestaurantAdapter(ArrayList<RestaurantInfo> restaurantInfos, Context context, LayoutInflater inflater) {
            this.restaurantInfos = restaurantInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_map_restaurant, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RestaurantInfo restaurantInfo = restaurantInfos.get(position);
            Restaurant restaurant = restaurantInfo.getRestaurant();

            //Picasso.with(context).load(restaurant.getImageUrl()).into(holder.mapRestaurantImageView);
            holder.mapRestaurantName.setText(restaurant.getName());
            holder.mapRestaurantFoodTag.setText(restaurant.getFoodTag());
            holder.mapRestaurantAddress.setText(restaurant.getAddress());
            holder.mapRestaurantLikeNumber.setText(Integer.toString(restaurantInfo.getLikeNumber()));
            holder.mapRestaurantReviewNumber.setText(Integer.toString(restaurantInfo.getReviewNumber()));
            holder.mapRestaurantTime.setText(Integer.toString(restaurantInfo.getTime()));
            holder.mapRestaurantDistance.setText(Integer.toString(restaurant.getDistance()));
        }

        @Override
        public int getItemCount() {
            return restaurantInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView mapRestaurantImageView;
            public TextView mapRestaurantName;
            public TextView mapRestaurantFoodTag;
            public TextView mapRestaurantAddress;
            public TextView mapRestaurantLikeNumber;
            public TextView mapRestaurantReviewNumber;
            public TextView mapRestaurantTime;
            public TextView mapRestaurantDistance;

            public ViewHolder(View view) {
                super(view);
                mapRestaurantImageView = (ImageView)view.findViewById(R.id.mapRestaurantImageView);
                mapRestaurantName = (TextView)view.findViewById(R.id.mapRestaurantName);
                mapRestaurantFoodTag = (TextView)view.findViewById(R.id.mapRestaurantFoodTag);
                mapRestaurantAddress = (TextView)view.findViewById(R.id.mapRestaurantAddress);
                mapRestaurantLikeNumber = (TextView)view.findViewById(R.id.mapRestaurantLikeNumber);
                mapRestaurantReviewNumber = (TextView)view.findViewById(R.id.mapRestaurantReviewNumber);
                mapRestaurantTime = (TextView)view.findViewById(R.id.mapRestaurantTime);
                mapRestaurantDistance = (TextView)view.findViewById(R.id.mapRestaurantDistance);
            }
        }
    }
}

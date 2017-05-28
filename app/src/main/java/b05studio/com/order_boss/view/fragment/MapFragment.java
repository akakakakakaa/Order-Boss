package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.view.RestaurantActivity;

/**
 * Created by young on 2017-05-16.
 */

public class MapFragment extends Fragment {
    private RecyclerView mapRestaurantRecyclerView;
    private RecyclerView.Adapter mapRestaurantAdapter;

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
        /* for test foodTag */
        ArrayList<String> foodTag = new ArrayList<>();
        foodTag.add("일식");
        foodTag.add("라멘");
        ArrayList<String> foodTag2 = new ArrayList<>();
        foodTag2.add("일식");
        foodTag2.add("돈카츠");
        ArrayList<String> foodTag3 = new ArrayList<>();
        foodTag3.add("양식");
        foodTag3.add("주점");
        ArrayList<String> foodTag4 = new ArrayList<>();
        foodTag4.add("한식");
        foodTag4.add("분식");
        ArrayList<String> foodTag5 = new ArrayList<>();
        foodTag5.add("일식");
        ArrayList<String> foodTag6 = new ArrayList<>();
        foodTag6.add("양식");
        foodTag6.add("주점");
        /* for test menuInfos */
        ArrayList<MenuInfo> menuInfos = new ArrayList<>();
        menuInfos.add(new MenuInfo("", "소세지 또띠아", 15000));
        menuInfos.add(new MenuInfo("", "모둠 포", 15000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        /* for test reviews */
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review("0", "임정연", "", 1, "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("1", "임정연", "", 1, "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("2", "임정연", "", 1, "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("3", "임정연", "", 1, "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));

        restaurantInfos.add(new RestaurantInfo("1", "멘무샤", foodTag, "경기도 화성시 동탄중앙로 220", 128, "1", 62, 12, 12, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("2", "사보텐", foodTag2, "경기도 화성시 동탄중앙로 220", 133, "1", 62, 12, 22, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("3", "빈티지 컨테이너", foodTag3, "경기도 화성시 동탄중앙로 220", 145, "1", 62, 12, 26, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("4", "스쿨푸드", foodTag4, "경기도 화성시 동탄중앙로 220", 128, "1", 62, 12, 36, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("5", "사보텐", foodTag5, "경기도 화성시 동탄중앙로 220", 133, "1", 62, 12, 40, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("6", "빈티지 컨테이너", foodTag6, "경기도 화성시 동탄중앙로 220", 145, "1", 62, 12, 47, reviews, menuInfos));
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
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_map, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final RestaurantInfo restaurantInfo = restaurantInfos.get(position);
            ArrayList<String> foodTag = restaurantInfo.getFoodTag();

            holder.mapRestaurantWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantInfo.setCurrentRestaurantInfo(restaurantInfo);
                    Intent intent = new Intent(getActivity(), RestaurantActivity.class);
                    startActivity(intent);
                }
            });
            //Picasso.with(context).load(restaurant.getImageUrl()).into(holder.mapRestaurantImageView);
            holder.mapRestaurantName.setText(restaurantInfo.getName());
            String foodTagStr = "";
            for(int i=0; i<foodTag.size()-1; i++)
                foodTagStr += foodTag.get(i) + ", ";
            foodTagStr += foodTag.get(foodTag.size() - 1);
            holder.mapRestaurantFoodTag.setText(foodTagStr);
            holder.mapRestaurantAddress.setText(restaurantInfo.getAddress());
            holder.mapRestaurantLikeNumber.setText(Integer.toString(restaurantInfo.getLikeNumber()));
            holder.mapRestaurantReviewNumber.setText(Integer.toString(restaurantInfo.getReviewNumber()));
            holder.mapRestaurantTime.setText(Integer.toString(restaurantInfo.getTime()));
            holder.mapRestaurantDistance.setText(Integer.toString(restaurantInfo.getDistance()));
        }

        @Override
        public int getItemCount() {
            return restaurantInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ConstraintLayout mapRestaurantWrapper;
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
                mapRestaurantWrapper = (ConstraintLayout)view.findViewById(R.id.mapRestaurantWrapper);
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
package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import net.daum.mf.map.api.MapLayout;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.DaumLocalInfo;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.network.DaumService;
import b05studio.com.order_boss.network.DaumServiceGenerator;
import b05studio.com.order_boss.util.LocationTracker;
import b05studio.com.order_boss.view.activity.MainActivity;
import b05studio.com.order_boss.view.activity.RestaurantActivity;
import b05studio.com.order_boss.view.activity.SearchActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by young on 2017-05-16.
 */

public class MapFragment extends Fragment implements MapView.MapViewEventListener, MapView.POIItemEventListener{
    private static final String TAG = "MAPFRAGMENT";
    private RecyclerView mapRestaurantRecyclerView;
    private RecyclerView.Adapter mapRestaurantAdapter;
    private TextView mapTitle;
    private String mapTitleString;
    private DaumService daumService;
    private ViewGroup rootView;

    ArrayList<RestaurantInfo> restaurantInfos;
    boolean[] holiday;
    ArrayList<Review> reviews;
    ArrayList<MenuInfo> menuInfos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_map, container, false);
        Log.d("dddddddd",LocationTracker.getCurLoc().getLatitude() + "");
        //myLocation =
        mapTitle = (TextView)rootView.findViewById(R.id.mapTitle);
        if(mapTitleString != null)
            mapTitle.setText(mapTitleString);

        initMapView();

        //for restaurant list
        mapRestaurantRecyclerView = (RecyclerView)rootView.findViewById(R.id.mapRestaurantRecyclerView);
        mapRestaurantRecyclerView.setHasFixedSize(true);
        restaurantInfos = new ArrayList<>();
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
        menuInfos = new ArrayList<>();
        menuInfos.add(new MenuInfo("", "소세지 또띠아", 15000));
        menuInfos.add(new MenuInfo("", "모둠 포", 15000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        /* for test reviews */
        reviews = new ArrayList<>();
        reviews.add(new Review("0", "임정연", "", 1,"", "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("1", "임정연", "", 1, "","동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("2", "임정연", "", 1, "","동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("3", "임정연", "", 1, "","동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        holiday = new boolean[28];
        for(int i=0; i<28; i++)
            holiday[i] = false;
        //첫째 주 일요일
        holiday[6] = true;
        //셋째 주 일요일
        holiday[20] = true;

        // TODO: 2017-05-30 GPS 받아와야함.
       // restaurantInfos.add(new RestaurantInfo("1", "멘무샤", foodTag, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 128, "1", 62, 12, 12, reviews, menuInfos));
       // restaurantInfos.add(new RestaurantInfo("2", "사보텐", foodTag2, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 133, "1", 62, 12, 22, reviews, menuInfos));
       // restaurantInfos.add(new RestaurantInfo("3", "빈티지 컨테이너", foodTag3, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 145, "1", 62, 12, 26, reviews, menuInfos));
       // restaurantInfos.add(new RestaurantInfo("4", "스쿨푸드", foodTag4, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 128, "1", 62, 12, 36, reviews, menuInfos));
       // restaurantInfos.add(new RestaurantInfo("5", "사보텐", foodTag5, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 133, "1", 62, 12, 40, reviews, menuInfos));
       // restaurantInfos.add(new RestaurantInfo("6", "빈티지 컨테이너", foodTag6, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 145, "1", 62, 12, 47, reviews, menuInfos));
        mapRestaurantAdapter = new MapRestaurantAdapter(restaurantInfos, getContext(), inflater);
        mapRestaurantRecyclerView.setAdapter(mapRestaurantAdapter);
        return rootView;
    }


    private void initMapView() {
        MapView mapView = new MapView(getActivity());
        mapView.setDaumMapApiKey(getString(R.string.daum_map_api_key));
        mapView.setMapViewEventListener(this);
        mapView.setPOIItemEventListener(this);
        ViewGroup mapViewContainer = (ViewGroup) rootView.findViewById(R.id.mapView);
        mapViewContainer.addView(mapView);
    }

    public void setTitle(String title) {
        if(mapTitle != null)
            mapTitle.setText("내 주변 " + title);
    }

    private void initButton(View rootView) {
        ImageButton mapBackBtn = (ImageButton)rootView.findViewById(R.id.mapBackButton);
        mapBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.moveToRestaurantListFragment(getActivity().getSupportFragmentManager());
            }
        });

        ImageButton filterButton = (ImageButton)rootView.findViewById(R.id.mapFilterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                //startActivityForResult(intent);
            }
        });
    }

    @Override
    public void onMapViewInitialized(final MapView mapView) {
        Double lat = LocationTracker.getCurLoc().getLatitude();
        Double lon = LocationTracker.getCurLoc().getLongitude();
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lon) , true);

        // 내거 띄우기
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("내 위치");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(lat, lon));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        mapView.addPOIItem(marker);

        // 내 주위 맛집 띄우기.
        daumService = DaumServiceGenerator.createService(DaumService.class);
        String daumLocationString = lat + "," + lon;
        Call<DaumLocalInfo> daumLocalInfos = daumService.listKeywordRestaurant(getString(R.string.daum_map_api_key),"맛집",daumLocationString);
        daumLocalInfos.enqueue(new Callback<DaumLocalInfo>() {
            @Override
            public void onResponse(Call<DaumLocalInfo> call, Response<DaumLocalInfo> response) {
                if(response.isSuccessful()) {
                    int count = 0;
                    for (DaumLocalInfo.Item item : response.body().getChannel().getItem()) {
                        MapPOIItem mapPOIItem = new MapPOIItem();
                        mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(item.getLatitude()),Double.parseDouble(item.getLongitude())));
                        mapPOIItem.setItemName(item.getTitle());
                        mapPOIItem.setTag(count);
                        mapPOIItem.setMarkerType(MapPOIItem.MarkerType.RedPin);
                        //restaurantInfos.add(new RestaurantInfo("1", "멘무샤", foodTag, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 128, "1", 62, 12, 12, reviews, menuInfos));
                        restaurantInfos.add(new RestaurantInfo(item.getId(), item.getTitle(), item.getCategory(), item.getNewAddress(),item.getPhone(),
                                17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 128, item.getImageUrl(), 62, 12, 12, reviews, menuInfos));

                        mapView.addPOIItem(mapPOIItem);
                        count++;
                    }
                    mapRestaurantAdapter.notifyDataSetChanged();

                } else {
                    Log.d(TAG, response.code() + "");
                }

            }

            @Override
            public void onFailure(Call<DaumLocalInfo> call, Throwable t) {
                Log.d(TAG,call.toString());
            }
        });


    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

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
            String foodTag = restaurantInfo.getFoodTag();

            holder.mapRestaurantWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantInfo.setCurrentRestaurantInfo(restaurantInfo);
                    Intent intent = new Intent(getActivity(), RestaurantActivity.class);
                    startActivity(intent);
                }
            });
            Picasso.with(context).load(restaurantInfo.getImageUrl()).into(holder.mapRestaurantImageView);
            holder.mapRestaurantName.setText(restaurantInfo.getName());
            holder.mapRestaurantFoodTag.setText(foodTag);
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
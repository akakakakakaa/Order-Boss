package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import java.util.ArrayList;

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

/**
 * Created by mansu on 2017-05-17.
 */

public class RestaurantListFragment extends Fragment {
    public static final String TAG = "RestaurantListFragment";
    public static final int SEARCH = 9999;
    private RecyclerView restaurantListRecyclerView;
    private RestaurantListAdapter restaurantListAdapter;

    private static ArrayList<RestaurantInfo> restaurantInfos;
    public static ArrayList<RestaurantInfo> getRestaurantInfos() {
        return restaurantInfos;
    }

    private ArrayList<RestaurantInfo> keywordInfos;
    private String currentDistance = "500m";
    private Button choosedBtn;
    private Button searchBtn;

    private DaumService daumService;
    boolean[] holiday;
    ArrayList<Review> reviews;
    ArrayList<MenuInfo> menuInfos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        initButton(rootView);
        //for restaurant list
        restaurantListRecyclerView = (RecyclerView)rootView.findViewById(R.id.restaurantListRecyclerView);
        restaurantListRecyclerView.setHasFixedSize(true);
        restaurantListRecyclerView.scrollToPosition(0);

        restaurantInfos = new ArrayList<>();
        restaurantListAdapter = new RestaurantListAdapter(restaurantInfos, getContext(), inflater);
        restaurantListRecyclerView.setAdapter(restaurantListAdapter);

        requestArroundRestaurantInfos();

        return rootView;
    }

    private void requestArroundRestaurantInfos() {

        Double lat = LocationTracker.getCurLoc().getLatitude();
        Double lon = LocationTracker.getCurLoc().getLongitude();

        // 내 주위 맛집 띄우기.
        daumService = DaumServiceGenerator.createService(DaumService.class);
        String daumLocationString = lat + "," + lon;
        Call<DaumLocalInfo> daumLocalInfos = daumService.listKeywordRestaurant(getString(R.string.daum_map_api_key),"내주변맛집",daumLocationString);
        daumLocalInfos.enqueue(new Callback<DaumLocalInfo>() {
            @Override
            public void onResponse(Call<DaumLocalInfo> call, Response<DaumLocalInfo> response) {
                if(response.isSuccessful()) {
                    int count = 0;
                    restaurantInfos.add(new RestaurantInfo("", "멕시모부리또", "음식점 > 양식 > 멕시칸,브라질", "경기 수원시 영통구 덕영대로1681번길 14", "031-202-9976", 132,
                            0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "삼천원 ~ 만원", 128, "https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20161103_49%2F1478156297147Fl06K_PNG%2F177053558837052_0.png&type=l&size=1056x624&quality=95&autoRotate=true", 62, 12, 12, reviews, menuInfos));
                    for (DaumLocalInfo.Item item : response.body().getChannel().getItem()) {

                        //restaurantInfos.add(new RestaurantInfo("1", "멘무샤", foodTag, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 128, "1", 62, 12, 12, reviews, menuInfos));
                        restaurantInfos.add(new RestaurantInfo(item.getId(), item.getTitle(), item.getCategory(), item.getNewAddress(),item.getPhone(), Integer.parseInt(item.getDistance()),
                                0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 128, item.getImageUrl(), 62, 12, 12, reviews, menuInfos));

                        count++;
                    }
                    RestaurantInfo.setRestaurantInfosCache(restaurantInfos);
                    restaurantListAdapter.notifyDataSetChanged();

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

    private void initButton(View rootView) {
        Button showMap = (Button)rootView.findViewById(R.id.restaurantListShowMap);
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.moveToMapFragment(getActivity().getSupportFragmentManager(), currentDistance);
            }
        });

        final Button oneThirdKilo = (Button)rootView.findViewById(R.id.restaurantList300m);
        oneThirdKilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processButtonEvent(oneThirdKilo, "300m");
            }
        });
        final Button halfKilo = (Button)rootView.findViewById(R.id.restaurantList500m);
        halfKilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processButtonEvent(halfKilo, "500m");
            }
        });
        processButtonEvent(halfKilo, "500m");
        final Button oneKilo = (Button)rootView.findViewById(R.id.restaurantList1km);
        oneKilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processButtonEvent(oneKilo, "1km");
            }
        });
        final Button oneHalfKilo = (Button)rootView.findViewById(R.id.restaurantList1_5km);
        oneHalfKilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processButtonEvent(oneHalfKilo, "1.5km");
            }
        });
        final Button threeKilo = (Button)rootView.findViewById(R.id.restaurantList3km);
        threeKilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processButtonEvent(threeKilo, "3km");
            }
        });
        final Button fiveKilo = (Button)rootView.findViewById(R.id.restaurantList5km);
        fiveKilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processButtonEvent(fiveKilo, "5km");
            }
        });

        searchBtn = (Button)rootView.findViewById(R.id.restaurantListSearch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void processButtonEvent(Button button, String currentDistance){
        if(choosedBtn != null)
            choosedBtn.setTextColor(Color.parseColor("#000000"));
        this.currentDistance = currentDistance;
        button.setTextColor(getResources().getColor(R.color.colorPrimary));
        choosedBtn = button;
    }

    private class RestaurantListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<RestaurantInfo> restaurantInfos;
        private Context context;
        private LayoutInflater inflater;

        public RestaurantListAdapter(ArrayList<RestaurantInfo> restaurantInfos, Context context, LayoutInflater inflater) {
            this.restaurantInfos = restaurantInfos;
            this.context = context;
            this.inflater = inflater;
        }

        public void setInfos(ArrayList<RestaurantInfo> restaurantInfos) {
            this.restaurantInfos = restaurantInfos;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder");
            View card = inflater.from(parent.getContext()).inflate(R.layout.cardview_restaurant_list, parent, false);
            RecyclerView.ViewHolder holder = new RestaurantListAdapter.ViewHolder(card);
            return holder;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder");
            final RestaurantInfo restaurantInfo = restaurantInfos.get(position);

            final ViewHolder viewHolder = (ViewHolder)holder;
            viewHolder.restaurantListCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantInfo.setCurrentRestaurantInfo(restaurantInfo);
                    Intent intent = new Intent(getActivity(), RestaurantActivity.class);
                    startActivity(intent);
                }
            });

            Picasso.with(context).load(restaurantInfo.getImageUrl()).into(viewHolder.restaurantListImageView);
            viewHolder.restaurantListName.setText((position + 1)+". "+restaurantInfo.getName());
            viewHolder.restaurantListFoodTag.setText(restaurantInfo.getFoodTag());
            viewHolder.restaurantListLikeNumber.setText(Integer.toString(restaurantInfo.getLikeNumber()));
            viewHolder.restaurantListReviewNumber.setText(Integer.toString(restaurantInfo.getReviewNumber()));
            String distance;
            if(restaurantInfo.getDistance() >= 1000)
                distance = (restaurantInfo.getDistance() / 1000) + "." + (restaurantInfo.getDistance() % 1000)/10 + "km";
            else
                distance = restaurantInfo.getDistance() + "m";
            viewHolder.restaurantListDistance.setText(distance);
        }

        @Override
        public int getItemCount() {
            return restaurantInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ConstraintLayout restaurantListCard;
            public ImageView restaurantListImageView;
            public TextView restaurantListName;
            public TextView restaurantListFoodTag;
            public TextView restaurantListLikeNumber;
            public TextView restaurantListReviewNumber;
            public TextView restaurantListDistance;

            public ViewHolder(View view) {
                super(view);
                restaurantListCard = (ConstraintLayout)view.findViewById(R.id.restaurantListCard);
                restaurantListImageView = (ImageView)view.findViewById(R.id.restaurantListImageView);
                restaurantListName = (TextView)view.findViewById(R.id.restaurantListName);
                restaurantListFoodTag = (TextView)view.findViewById(R.id.restaurantListFoodTag);
                restaurantListLikeNumber = (TextView)view.findViewById(R.id.restaurantListLikeNumber);
                restaurantListReviewNumber = (TextView)view.findViewById(R.id.restaurantListReviewNumber);
                restaurantListDistance = (TextView)view.findViewById(R.id.restaurantListDistance);
            }
        }
    }
}

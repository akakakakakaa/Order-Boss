package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.view.activity.MainActivity;
import b05studio.com.order_boss.view.activity.RestaurantActivity;
import b05studio.com.order_boss.view.activity.SearchActivity;

/**
 * Created by mansu on 2017-05-17.
 */

public class RestaurantListFragment extends Fragment {
    public static final String TAG = "RestaurantListFragment";
    public static final int SEARCH = 9999;
    private RecyclerView restaurantListRecyclerView;
    private RestaurantListAdapter restaurantListAdapter;
    private ArrayList<RestaurantInfo> restaurantInfos;
    private ArrayList<RestaurantInfo> keywordInfos;
    private String currentDistance = "500m";
    private Button choosedBtn;
    private Button searchBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        initButton(rootView);
        //for restaurant list
        restaurantListRecyclerView = (RecyclerView)rootView.findViewById(R.id.restaurantListRecyclerView);
        restaurantListRecyclerView.setHasFixedSize(true);

        restaurantInfos = new ArrayList<>();
        //TODO: 2017-05-17 파이어베이스에서 레스토랑 데이터 들고오는 것 필요함.
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
        reviews.add(new Review("0", "임정연", "", 1,"", "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("1", "임정연", "", 1,"", "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("2", "임정연", "", 1, "","동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("3", "임정연", "", 1, "","동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        boolean[] holiday = new boolean[28];
        for(int i=0; i<28; i++)
            holiday[i] = false;
        //첫째 주 일요일
        holiday[6] = true;
        //셋째 주 일요일
        holiday[20] = true;

        restaurantInfos.add(new RestaurantInfo("1", "멘무샤", foodTag, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1280, "1", 62, 12, 12, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("2", "사보텐", foodTag2, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1330, "1", 62, 12, 22, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("3", "빈티지 컨테이너", foodTag3, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1450, "1", 62, 12, 26, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("4", "스쿨푸드", foodTag4, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1280, "1", 62, 12, 36, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("5", "사보텐", foodTag5, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1330, "1", 62, 12, 40, reviews, menuInfos));
        restaurantInfos.add(new RestaurantInfo("6", "빈티지 컨테이너", foodTag6, "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1450, "1", 62, 12, 47, reviews, menuInfos));

        restaurantListAdapter = new RestaurantListAdapter(restaurantInfos, getContext(), inflater);
        restaurantListRecyclerView.setAdapter(restaurantListAdapter);

        return rootView;
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
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder");
            final RestaurantInfo restaurantInfo = restaurantInfos.get(position);
            ArrayList<String> foodTag = restaurantInfo.getFoodTag();

            String foodTagStr = "";
            ViewHolder viewHolder = (ViewHolder)holder;
            viewHolder.restaurantListCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantInfo.setCurrentRestaurantInfo(restaurantInfo);
                    Intent intent = new Intent(getActivity(), RestaurantActivity.class);
                    startActivity(intent);
                }
            });
            //Picasso.with(context).load(restaurant.getImageUrl()).into(holder.mapRestaurantImageView);
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(context.getResources(), R.drawable.restaurant_list_test_image2));
            dr.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
            viewHolder.restaurantListImageView.setImageDrawable(dr);
            viewHolder.restaurantListName.setText((position + 1)+". "+restaurantInfo.getName());
            for(int i=0; i<foodTag.size()-1; i++)
                foodTagStr += foodTag.get(i) + " > ";
            foodTagStr += foodTag.get(foodTag.size()-1);
            viewHolder.restaurantListFoodTag.setText(foodTagStr);
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

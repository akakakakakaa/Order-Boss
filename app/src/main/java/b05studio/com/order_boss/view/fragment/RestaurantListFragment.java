package b05studio.com.order_boss.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.view.RestaurantActivity;

/**
 * Created by mansu on 2017-05-17.
 */

public class RestaurantListFragment extends Fragment {
    public static final String TAG = "RestaurantListFragment";
    private RecyclerView restaurantListRecyclerView;
    private RestaurantListAdapter restaurantListAdapter;
    private ArrayList<RestaurantInfo> restaurantInfos;
    private ArrayList<RestaurantInfo> keywordInfos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        //for restaurant list
        restaurantListRecyclerView = (RecyclerView)rootView.findViewById(R.id.restaurantListRecyclerView);
        restaurantListRecyclerView.setHasFixedSize(true);

        if(restaurantInfos == null) {
            restaurantInfos = new ArrayList<>();
            //TODO: 2017-05-17 파이어베이스에서 레스토랑 데이터 들고오는 것 필요함.
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
            restaurantListAdapter = new RestaurantListAdapter(restaurantInfos, getContext(), inflater);
        }
        else
            restaurantListAdapter = new RestaurantListAdapter(keywordInfos, getContext(), inflater);
        restaurantListRecyclerView.setAdapter(restaurantListAdapter);
        return rootView;
    }

    public void searchKeyword(String keyword) {
        keywordInfos = new ArrayList<>();
        for(int i=0; i<restaurantInfos.size(); i++) {
            ArrayList<String> foodTag = restaurantInfos.get(i).getFoodTag();
            for(int j=0; j<foodTag.size(); j++)
                if(foodTag.get(j).indexOf(keyword) != -1) {
                    keywordInfos.add((restaurantInfos.get(i)));
                    break;
                }
        }

        restaurantListAdapter.setInfos(keywordInfos);
    }

    private class RestaurantListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<RestaurantInfo> restaurantInfos;
        private Context context;
        private LayoutInflater inflater;
        private int arrangeType;
        private SharedPreferences pref;
        private final Comparator<RestaurantInfo> comparator = new Comparator<RestaurantInfo>() {
            @Override
            public int compare(RestaurantInfo o1, RestaurantInfo o2) {
                switch (arrangeType) {
                    case 0: //by time
                        return (o1.getTime() >= o2.getTime()) ? 1 : 0;
                    case 1: //by distance
                        return (o1.getDistance() >= o2.getDistance()) ? 1 : 0;
                    case 2: //by popularity
                        return (o1.getLikeNumber() >= o2.getLikeNumber()) ? 1 : 0;
                    default:
                        return 0;
                }
            }
        };

        public RestaurantListAdapter(ArrayList<RestaurantInfo> restaurantInfos, Context context, LayoutInflater inflater) {
            this.restaurantInfos = restaurantInfos;
            this.context = context;
            this.inflater = inflater;

            pref = context.getSharedPreferences("listFilter", Activity.MODE_PRIVATE);
            arrangeType = pref.getInt("filter", 0);
            Collections.sort(restaurantInfos, comparator);
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

        //0: card, 1: card with text divider
        @Override
        public int getItemViewType(int position) {
            if(position == 0)
                return 1;
            else {
                RestaurantInfo prev = restaurantInfos.get(position - 1);
                RestaurantInfo next = restaurantInfos.get(position);

                switch (arrangeType) {
                    case 0:
                        if (next.getTime()/10 - prev.getTime()/10 >= 1)
                            return 1;
                        else
                            return 0;
                    case 1:
                        if (next.getDistance() - prev.getDistance() >= 10)
                            return 1;
                        else
                            return 0;
                    case 2:
                        if (next.getLikeNumber() - prev.getLikeNumber() >= 10)
                            return 1;
                        else
                            return 0;
                    default:
                            return 0;
                }
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder");
            View card = inflater.from(parent.getContext()).inflate(R.layout.cardview_restaurant_list, parent, false);

            switch (viewType) {
                case 0:
                    RecyclerView.ViewHolder holder = new RestaurantListAdapter.ViewHolder(card);
                    return holder;
                default:
                    View divider = inflater.from(parent.getContext()).inflate(R.layout.cardview_restaurant_list_divider, parent, false);
                    ConstraintLayout dividerLayout = (ConstraintLayout)divider.findViewById(R.id.restaurantListDivider);
                    ConstraintSet set= new ConstraintSet();
                    set.clone(dividerLayout);
                    dividerLayout.addView(card);

                    ConstraintLayout cardLayout = (ConstraintLayout)card.findViewById(R.id.restaurantListCard);
                    set.connect(cardLayout.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                    set.connect(cardLayout.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                    set.connect(cardLayout.getId(), ConstraintSet.TOP, R.id.restaurantListDividerTitle, ConstraintSet.BOTTOM, 0);
                    set.setMargin(cardLayout.getId(), ConstraintSet.TOP, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, getResources().getDisplayMetrics()));
                    set.constrainWidth(cardLayout.getId(), 0);
                    set.constrainHeight(cardLayout.getId(), (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 157, getResources().getDisplayMetrics()));
                    set.applyTo(dividerLayout);
                    /*
                    //생각대로 안돌아간다.
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) cardLayout.getLayoutParams();
                    params.width = 0;
                    params.startToStart = R.id.restaurantListDivider;
                    params.endToEnd = R.id.restaurantListDivider;
                    params.leftToLeft = R.id.restaurantListDivider;
                    params.topToBottom = R.id.restaurantListDividerTitle;
                    params.rightToRight = R.id.restaurantListDivider;
                    cardLayout.setLayoutParams(params);
                    */
                    RecyclerView.ViewHolder holder2 = new RestaurantListAdapter.ViewHolder2(divider);
                    return holder2;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder");
            final RestaurantInfo restaurantInfo = restaurantInfos.get(position);
            ArrayList<String> foodTag = restaurantInfo.getFoodTag();

            String foodTagStr = "";
            switch (holder.getItemViewType()) {
                case 0:
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
                    viewHolder.restaurantListName.setText((position + 1)+". "+restaurantInfo.getName());
                    for(int i=0; i<foodTag.size()-1; i++)
                        foodTagStr += foodTag.get(i) + " > ";
                    foodTagStr += foodTag.get(foodTag.size()-1);
                    viewHolder.restaurantListFoodTag.setText(foodTagStr);
                    viewHolder.restaurantListLikeNumber.setText(Integer.toString(restaurantInfo.getLikeNumber()));
                    viewHolder.restaurantListReviewNumber.setText(Integer.toString(restaurantInfo.getReviewNumber()));
                    viewHolder.restaurantListTime.setText(Integer.toString(restaurantInfo.getTime()));
                    viewHolder.restaurantListDistance.setText(Integer.toString(restaurantInfo.getDistance()));
                    break;
                case 1:
                    ViewHolder2 viewHolder2 = (ViewHolder2)holder;
                    viewHolder2.restaurantListCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RestaurantInfo.setCurrentRestaurantInfo(restaurantInfo);
                            Intent intent = new Intent(getActivity(), RestaurantActivity.class);
                            startActivity(intent);
                        }
                    });
                    viewHolder2.restaurantListDividerTitle.setText(((restaurantInfo.getTime()/10 + 1) * 10) + "분이내");
                    //Picasso.with(context).load(restaurant.getImageUrl()).into(holder.mapRestaurantImageView);
                    viewHolder2.restaurantListName.setText((position + 1) +". "+restaurantInfo.getName());
                    for(int i=0; i<foodTag.size()-1; i++)
                        foodTagStr += foodTag.get(i) + " > ";
                    foodTagStr += foodTag.get(foodTag.size()-1);
                    viewHolder2.restaurantListFoodTag.setText(foodTagStr);
                    viewHolder2.restaurantListLikeNumber.setText(Integer.toString(restaurantInfo.getLikeNumber()));
                    viewHolder2.restaurantListReviewNumber.setText(Integer.toString(restaurantInfo.getReviewNumber()));
                    viewHolder2.restaurantListTime.setText(Integer.toString(restaurantInfo.getTime()));
                    viewHolder2.restaurantListDistance.setText(Integer.toString(restaurantInfo.getDistance()));
                    break;
            }
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
            public TextView restaurantListTime;
            public TextView restaurantListDistance;

            public ViewHolder(View view) {
                super(view);
                restaurantListCard = (ConstraintLayout)view.findViewById(R.id.restaurantListCard);
                restaurantListImageView = (ImageView)view.findViewById(R.id.restaurantListImageView);
                restaurantListName = (TextView)view.findViewById(R.id.restaurantListName);
                restaurantListFoodTag = (TextView)view.findViewById(R.id.restaurantListFoodTag);
                restaurantListLikeNumber = (TextView)view.findViewById(R.id.restaurantListLikeNumber);
                restaurantListReviewNumber = (TextView)view.findViewById(R.id.restaurantListReviewNumber);
                restaurantListTime = (TextView)view.findViewById(R.id.restaurantListTime);
                restaurantListDistance = (TextView)view.findViewById(R.id.restaurantListDistance);
            }
        }

        public class ViewHolder2 extends RecyclerView.ViewHolder {
            public TextView restaurantListDividerTitle;
            public ConstraintLayout restaurantListCard;
            public ImageView restaurantListImageView;
            public TextView restaurantListName;
            public TextView restaurantListFoodTag;
            public TextView restaurantListLikeNumber;
            public TextView restaurantListReviewNumber;
            public TextView restaurantListTime;
            public TextView restaurantListDistance;

            public ViewHolder2(View view) {
                super(view);
                restaurantListDividerTitle = (TextView)view.findViewById(R.id.restaurantListDividerTitle);
                restaurantListCard = (ConstraintLayout)view.findViewById(R.id.restaurantListCard);
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

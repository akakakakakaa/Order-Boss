package b05studio.com.order_boss.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.User;

/**
 * Created by mansu on 2017-05-31.
 */

public class SearchResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        TextView title = (TextView)findViewById(R.id.searchResultTitle);
        title.setText("'" + getIntent().getStringExtra("keyword") + "' 검색 결과");
        ImageButton backBtn = (ImageButton)findViewById(R.id.searchResultBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView searchResultRecyclerView = (RecyclerView)findViewById(R.id.searchResultRecyclerView);
        searchResultRecyclerView.setHasFixedSize(true);
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(getMatchingRestaurantInfo(getIntent().getStringExtra("keyword")), this, getLayoutInflater());
        searchResultRecyclerView.setAdapter(searchResultAdapter);

    }

    // TODO: 2017-06-01  여기작업 해야됨,
    public ArrayList<RestaurantInfo> getMatchingRestaurantInfo(String keyword) {
       // //get restaurant infos
        ArrayList<RestaurantInfo> restaurantInfos = RestaurantInfo.getRestaurantInfosCache();
        ArrayList<RestaurantInfo> matchingInfos = new ArrayList<>();
        for(int i=0; i<restaurantInfos.size(); i++) {
            String foodTag = restaurantInfos.get(i).getFoodTag();
            String name = restaurantInfos.get(i).getName();
            if(foodTag.indexOf(keyword) != -1 || name.indexOf(keyword) != 1) {
                matchingInfos.add((restaurantInfos.get(i)));
                break;
            }
        }

        if(matchingInfos.size() == 0)
            ((TextView)findViewById(R.id.searchResultNoResult)).setVisibility(View.VISIBLE);
        return matchingInfos;
    }

    private class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<RestaurantInfo> restaurantInfos;
        private Context context;
        private LayoutInflater inflater;

        public SearchResultAdapter(ArrayList<RestaurantInfo> restaurantInfos, Context context, LayoutInflater inflater) {
            this.restaurantInfos = restaurantInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View card = inflater.from(parent.getContext()).inflate(R.layout.cardview_search_result, parent, false);
            RecyclerView.ViewHolder holder = new SearchResultAdapter.ViewHolder(card);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final RestaurantInfo restaurantInfo = restaurantInfos.get(position);

            final ViewHolder viewHolder = (ViewHolder)holder;
            viewHolder.searchResultCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantInfo.setCurrentRestaurantInfo(restaurantInfo);
                    Intent intent = new Intent(SearchResultActivity.this, RestaurantActivity.class);
                    startActivity(intent);
                }
            });

            Picasso.with(context).load(restaurantInfo.getImageUrl()).into(viewHolder.searchResultImageView);
            viewHolder.searchResultName.setText((position + 1)+". "+restaurantInfo.getName());
            viewHolder.searchResultFoodTag.setText(restaurantInfo.getFoodTag());
            viewHolder.searchResultLikeNumber.setText(Integer.toString(restaurantInfo.getLikeNumber()));
            viewHolder.searchResultReviewNumber.setText(Integer.toString(restaurantInfo.getReviewNumber()));
            String distance;
            if(restaurantInfo.getDistance() >= 1000)
                distance = (restaurantInfo.getDistance() / 1000) + "." + (restaurantInfo.getDistance() % 1000)/10 + "km";
            else
                distance = restaurantInfo.getDistance() + "m";
            viewHolder.searchResultDistance.setText(distance);
        }

        @Override
        public int getItemCount() {
            return restaurantInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ConstraintLayout searchResultCard;
            public ImageView searchResultImageView;
            public TextView searchResultName;
            public TextView searchResultFoodTag;
            public TextView searchResultLikeNumber;
            public TextView searchResultReviewNumber;
            public TextView searchResultDistance;

            public ViewHolder(View view) {
                super(view);
                searchResultCard = (ConstraintLayout)view.findViewById(R.id.searchResultCard);
                searchResultImageView = (ImageView)view.findViewById(R.id.searchResultImageView);
                searchResultName = (TextView)view.findViewById(R.id.searchResultName);
                searchResultFoodTag = (TextView)view.findViewById(R.id.searchResultFoodTag);
                searchResultLikeNumber = (TextView)view.findViewById(R.id.searchResultLikeNumber);
                searchResultReviewNumber = (TextView)view.findViewById(R.id.searchResultReviewNumber);
                searchResultDistance = (TextView)view.findViewById(R.id.searchResultDistance);
            }
        }
    }
}

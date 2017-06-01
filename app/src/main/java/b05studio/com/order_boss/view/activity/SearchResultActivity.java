package b05studio.com.order_boss.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.RestaurantInfo;

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

    public ArrayList<RestaurantInfo> getMatchingRestaurantInfo(String keyword) {
        //get restaurant infos
        ArrayList<RestaurantInfo> restaurantInfos = new ArrayList<>();
        ArrayList<RestaurantInfo> matchingInfos = new ArrayList<>();
        for(int i=0; i<restaurantInfos.size(); i++) {
            ArrayList<String> foodTag = restaurantInfos.get(i).getFoodTag();
            for(int j=0; j<foodTag.size(); j++)
                if(foodTag.get(j).indexOf(keyword) != -1) {
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
        }

        @Override
        public int getItemCount() {
            return restaurantInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView searchResultImageView;
            public TextView searchResultName;
            public TextView searchResultFoodTag;
            public TextView searchResultLikeNumber;
            public TextView searchResultReviewNumber;
            public TextView searchResultDistance;

            public ViewHolder(View view) {
                super(view);
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

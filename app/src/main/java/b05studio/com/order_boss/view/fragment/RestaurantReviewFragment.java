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

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.Review;

/**
 * Created by mansu on 2017-05-26.
 */

public class RestaurantReviewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_review, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.restaurantReviewRecyclerView);
        recyclerView.setHasFixedSize(true);
        //get MenuInfo
        RestaurantReviewAdatper restaurantReviewAdatper = new RestaurantReviewAdatper(RestaurantInfo.getCurrentRestaurantInfo().getReviews(), getContext(), inflater);
        recyclerView.setAdapter(restaurantReviewAdatper);
        return rootView;
    }

    private class RestaurantReviewAdatper extends RecyclerView.Adapter<RestaurantReviewFragment.RestaurantReviewAdatper.ViewHolder> {
        private ArrayList<Review> reviews;
        private Context context;
        private LayoutInflater inflater;

        public RestaurantReviewAdatper(ArrayList<Review> reviews, Context context, LayoutInflater inflater) {
            this.reviews = reviews;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public RestaurantReviewFragment.RestaurantReviewAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_restaurant_review, parent, false);
            RestaurantReviewFragment.RestaurantReviewAdatper.ViewHolder holder = new RestaurantReviewFragment.RestaurantReviewAdatper.ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(RestaurantReviewFragment.RestaurantReviewAdatper.ViewHolder holder, int position) {
            Review review = reviews.get(position);

            holder.restaurantReviewName.setText(review.getUserName());
            holder.restaurantReviewDate.setText(review.getTimestamp()+"");
            holder.restaurantReviewContents.setText(review.getReviewContent());
            //Picasso.with(context).load(review.getProfileUrl()).into(holder.restaurantReviewProfileImg);
            //Picasso.with(context).load(review.reviewImgUrl()).into(holder.restaurantReviewContentsImg);
            holder.restaurantReviewContentsImg.setImageResource(R.drawable.restaurant_info_test_image1);
            switch(review.getLikeNumber()) {
                case 5:
                    holder.restaurantReviewRate.setImageResource(R.drawable.icon_review_rate_five);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return reviews.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView restaurantReviewName;
            public TextView restaurantReviewDate;
            public TextView restaurantReviewContents;
            public ImageView restaurantReviewContentsImg;
            public ImageView restaurantReviewProfileImg;
            public ImageView restaurantReviewRate;

            public ViewHolder(View view) {
                super(view);
                restaurantReviewName = (TextView)view.findViewById(R.id.restaurantReviewName);
                restaurantReviewDate = (TextView)view.findViewById(R.id.restaurantReviewDate);
                restaurantReviewContents = (TextView)view.findViewById(R.id.restaurantReviewContents);
                restaurantReviewContentsImg = (ImageView)view.findViewById(R.id.restaurantReviewContentsImg);
                restaurantReviewProfileImg = (ImageView)view.findViewById(R.id.restaurantReviewProfileImg);
                restaurantReviewRate = (ImageView)view.findViewById(R.id.restaurantReviewRate);
            }
        }
    }
}
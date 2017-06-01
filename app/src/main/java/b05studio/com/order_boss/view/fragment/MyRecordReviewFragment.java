package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.OrderInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.model.User;
import b05studio.com.order_boss.view.activity.BasketActivity;

/**
 * Created by young on 2017-05-31.
 */

public class MyRecordReviewFragment extends Fragment {

    ViewGroup rootView;
    private RecyclerView.LayoutManager mLayoutManager;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_myrecord_review, container, false);
        initRecordReviewRecyclerView();
        return rootView;
    }

    private void initRecordReviewRecyclerView() {
        RecyclerView reviewRecyclerView = (RecyclerView)rootView.findViewById(R.id.reviewRecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        reviewRecyclerView.setLayoutManager(mLayoutManager);
        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.scrollToPosition(0);

        ArrayList<Review> reviews = new ArrayList<>();
        /*
        reviews.add(new Review("0", "임정연", "", 1,"코코야찌방야", "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("1", "임정연", "", 1,"남대감", "동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        reviews.add(new Review("2", "임정연", "", 1, "동대감","동네에 이런 분위기의 술집이 있는 줄 몰랐어요!\n" +
                " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" +
                "단골해야겠습니다!!!!!!", "", 5));
        */
        ReviewAdapter reviewAdapter = new ReviewAdapter(reviews);
        reviewRecyclerView.setAdapter(reviewAdapter);
        reviewRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
        private ArrayList<Review> reviews;

        public ReviewAdapter(ArrayList<Review> reviews) {
            this.reviews = reviews;
        }

        @Override
        public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_myrecord_review, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Review reviewInfo = reviews.get(position);
            holder.reviewTitle.setText(reviewInfo.getRestarauntName());
            holder.reviewDescription.setText(reviewInfo.getReviewContent());
        }

        @Override
        public int getItemCount() {
            return reviews.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView reviewTitle;
            public TextView reviewDescription;

            public ViewHolder(View view) {
                super(view);
                reviewTitle = (TextView)view.findViewById(R.id.recordReviewTitle);
                reviewDescription = (TextView)view.findViewById(R.id.recordReviewDescription);
            }
        }



    }

}

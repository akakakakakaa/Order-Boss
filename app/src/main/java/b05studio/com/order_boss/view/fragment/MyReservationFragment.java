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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.OrderInfo;
import b05studio.com.order_boss.model.ReservationInfo;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.model.User;

/**
 * Created by Mansu on 2017-05-29.
 */

public class MyReservationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_myreservation, container, false);


        //for restaurant list
        RecyclerView myReservationRecyclerView = (RecyclerView)rootView.findViewById(R.id.myReservationRecyclerView);
        myReservationRecyclerView.setHasFixedSize(true);
        ArrayList<ReservationInfo> reservationInfos = User.getCurrentUser().getReservationInfos();
        //TODO:2017.06.01 ReservationInfo 가져오는것 필요함
        ArrayList<MenuInfo> menuInfos = new ArrayList<>();
        menuInfos.add(new MenuInfo("", "소세지 또띠아", 15000));
        menuInfos.add(new MenuInfo("", "모둠 포", 15000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        ArrayList<OrderInfo> orderInfos = new ArrayList<>();
        orderInfos.add(new OrderInfo("1", menuInfos.get(0), 1));
        orderInfos.add(new OrderInfo("1", menuInfos.get(1), 2));
        orderInfos.add(new OrderInfo("1", menuInfos.get(2), 3));
        orderInfos.add(new OrderInfo("1", menuInfos.get(3), 4));
        boolean[] holiday = new boolean[28];
        for(int i=0; i<28; i++)
            holiday[i] = false;
        //첫째 주 일요일
        holiday[6] = true;
        //셋째 주 일요일
        holiday[20] = true;

        ReservationInfo reservationInfo = new ReservationInfo(new RestaurantInfo("1", "멘무샤", "일식 > 라멘", "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1280, "1", 62, 12, 12, new ArrayList<Review>(), menuInfos), orderInfos, 25, Calendar.getInstance());
        reservationInfos.add(reservationInfo);
        if(reservationInfos.size() == 0)
            rootView.findViewById(R.id.myReservationNoResult).setVisibility(View.VISIBLE);
        else {
            MyReservationAdapter mapRestaurantAdapter = new MyReservationAdapter(reservationInfos, getContext(), inflater);
            myReservationRecyclerView.setAdapter(mapRestaurantAdapter);
        }
        return rootView;
    }

    private class MyReservationAdapter extends RecyclerView.Adapter<MyReservationAdapter.ViewHolder> {
        private ArrayList<ReservationInfo> reservationInfos;
        private Context context;
        private LayoutInflater inflater;
        private ViewGroup parent;

        public MyReservationAdapter(ArrayList<ReservationInfo> reservationInfos, Context context, LayoutInflater inflater) {
            this.reservationInfos = reservationInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            this.parent = parent;
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_myreservation, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ReservationInfo reservationInfo = reservationInfos.get(position);

            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd / aa HH:mm");
            holder.myReservationDate.setText(format.format(reservationInfo.getOrderTime().getTime()));
            holder.myReservationMenuImage.setImageResource(R.drawable.restaurant_info_test_image1);
            holder.myReservationRestaurantName.setText(reservationInfo.getRestaurantInfo().getName());

            holder.myReservationFoodTag.setText(reservationInfo.getRestaurantInfo().getFoodTag());
            String distance;
            if(reservationInfo.getRestaurantInfo().getDistance() >= 1000)
                distance = (reservationInfo.getRestaurantInfo().getDistance() / 1000) + "." + (reservationInfo.getRestaurantInfo().getDistance() % 1000)/10 + "km";
            else
                distance = reservationInfo.getRestaurantInfo().getDistance() + "m";
            holder.myReservationDistance.setText(distance);
            holder.myReservationTime.setText(""+reservationInfo.getRemainTime());
            int totalPrice = 0;
            for(int i=0; i<reservationInfo.getOrderInfos().size(); i++)
                totalPrice += reservationInfo.getOrderInfos().get(i).getMenuInfo().getPrice() * reservationInfo.getOrderInfos().get(i).getMenuNum();
            holder.myReservationTotalPrice.setText(""+totalPrice);

            for(int i=0; i<reservationInfo.getOrderInfos().size(); i++) {
                View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_myreservation_order, parent, false);
                TextView myReservationOrderName = (TextView)v.findViewById(R.id.myReservationOrderName);
                TextView myReservationOrderPrice = (TextView)v.findViewById(R.id.myReservationOrderPrice);
                myReservationOrderName.setText(reservationInfo.getOrderInfos().get(i).getMenuInfo().getName());
                myReservationOrderPrice.setText(reservationInfo.getOrderInfos().get(i).getMenuNum()+"*"+reservationInfo.getOrderInfos().get(i).getMenuInfo().getPrice());
                holder.myReservationOrderList.addView(v);
            }
        }

        @Override
        public int getItemCount() {
            return reservationInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myReservationDate;
            ImageView myReservationMenuImage;
            TextView myReservationRestaurantName;
            TextView myReservationFoodTag;
            TextView myReservationDistance;
            TextView myReservationTotalPrice;
            TextView myReservationTime;
            LinearLayout myReservationOrderList;

            public ViewHolder(View view) {
                super(view);
                myReservationDate = (TextView)view.findViewById(R.id.myReservationDate);
                myReservationMenuImage = (ImageView)view.findViewById(R.id.myReservationMenuImage);
                myReservationRestaurantName = (TextView)view.findViewById(R.id.myReservationRestaurantName);
                myReservationFoodTag = (TextView)view.findViewById(R.id.myReservationFoodTag);
                myReservationDistance = (TextView)view.findViewById(R.id.myReservationDistance);
                myReservationTotalPrice = (TextView)view.findViewById(R.id.myReservationTotalPrice);
                myReservationTime = (TextView)view.findViewById(R.id.myReservationTime);
                myReservationOrderList = (LinearLayout)view.findViewById(R.id.myReservationOrderList);
            }
        }

        public class ViewHolder2 extends RecyclerView.ViewHolder {
            TextView myReservationOrderName;
            TextView myReservationOrderPrice;

            public ViewHolder2(View view) {
                super(view);
                myReservationOrderName = (TextView)view.findViewById(R.id.myReservationOrderName);
                myReservationOrderPrice = (TextView)view.findViewById(R.id.myReservationOrderPrice);
            }
        }
    }
}

package b05studio.com.order_boss.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import b05studio.com.order_boss.model.User;

/**
 * Created by mansu on 2017-05-28.
 */

public class ReservationActivity extends AppCompatActivity {
    private TextView totalPriceText;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        initReservationRecyclerView();
        initButtons();
        initTotalPrice();
    }

    private void initReservationRecyclerView() {
        RecyclerView reservationRecyclerView = (RecyclerView)findViewById(R.id.reservationRecyclerView);
        reservationRecyclerView.setHasFixedSize(true);

        ReservationActivity.reservationMenuAdapter restaurantMenuAdatper = new ReservationActivity.reservationMenuAdapter(User.getCurrentUser().getCurrentOrderInfos(), this, getLayoutInflater());
        reservationRecyclerView.setAdapter(restaurantMenuAdatper);
    }

    private void initButtons() {
        ImageButton backBtn = (ImageButton)findViewById(R.id.reservationBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initTotalPrice() {
        totalPriceText = (TextView)findViewById(R.id.reservationTotalPrice);
        ArrayList<OrderInfo> orderInfos = User.getCurrentUser().getCurrentOrderInfos();

        totalPrice = 0;
        for(int i=0; i<orderInfos.size(); i++)
            totalPrice += orderInfos.get(i).getMenuInfo().getPrice() * orderInfos.get(i).getMenuNum();
        totalPriceText.setText(totalPrice+"");
    }

    private class reservationMenuAdapter extends RecyclerView.Adapter<ReservationActivity.reservationMenuAdapter.ViewHolder> {
        private ArrayList<OrderInfo> orderInfos;
        private Context context;
        private LayoutInflater inflater;

        public reservationMenuAdapter(ArrayList<OrderInfo> orderInfos, Context context, LayoutInflater inflater) {
            this.orderInfos = orderInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public ReservationActivity.reservationMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_reservation_menu, parent, false);
            ReservationActivity.reservationMenuAdapter.ViewHolder holder = new ReservationActivity.reservationMenuAdapter.ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ReservationActivity.reservationMenuAdapter.ViewHolder holder, int position) {
            final OrderInfo orderInfo = orderInfos.get(position);

            //need Picasso
            holder.reservationMenuImage.setImageResource(R.drawable.restaurant_info_test_image1);
            holder.reservationMenuName.setText(orderInfo.getMenuInfo().getName());
            holder.reservationMenuNum.setText(orderInfo.getMenuNum()+"");
            holder.reservationMenuPrice.setText(orderInfo.getMenuInfo().getPrice()*orderInfo.getMenuNum()+"");
        }

        @Override
        public int getItemCount() {
            return orderInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView reservationMenuImage;
            public TextView reservationMenuName;
            public TextView reservationMenuNum;
            public TextView reservationMenuPrice;

            public ViewHolder(View view) {
                super(view);
                reservationMenuImage = (ImageView)view.findViewById(R.id.reservationMenuImage);
                reservationMenuName = (TextView)view.findViewById(R.id.reservationMenuName);
                reservationMenuNum = (TextView)view.findViewById(R.id.reservationMenuNum);
                reservationMenuPrice = (TextView)view.findViewById(R.id.reservationMenuPrice);
            }
        }
    }
}

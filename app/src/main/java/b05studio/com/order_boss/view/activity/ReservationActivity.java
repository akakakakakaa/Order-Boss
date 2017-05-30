package b05studio.com.order_boss.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.OrderInfo;
import b05studio.com.order_boss.model.ReservationInfo;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.User;

/**
 * Created by mansu on 2017-05-28.
 */

public class ReservationActivity extends AppCompatActivity {
    private TextView totalPriceText;
    private int totalPrice;
    private int peopleNum = -1;
    private Button btnTakeOut;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnChooseNum;

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

        Button reservationBtn = (Button)findViewById(R.id.reservationButton);
        reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 2017.05.29 예약이 끝나면 예약정보를 서버로 보내는 프로세스 필요
                ReservationInfo reservationInfo = new ReservationInfo(RestaurantInfo.getCurrentRestaurantInfo(), User.getCurrentUser().getCurrentOrderInfos(), -1, Calendar.getInstance());
                User.getCurrentUser().getReservationInfos().add(reservationInfo);
                User.getCurrentUser().getCurrentOrderInfos().clear();

                Intent intent = new Intent(ReservationActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        initChooseNumButtons();
    }

    private void initChooseNumButtons() {
        btnTakeOut = (Button)findViewById(R.id.reservationPersonnelButtonTakeOut);
        btnOne = (Button)findViewById(R.id.reservationPersonnelButtonOne);
        btnTwo = (Button)findViewById(R.id.reservationPersonnelButtonTwo);
        btnThree = (Button)findViewById(R.id.reservationPersonnelButtonThree);
        btnFour = (Button)findViewById(R.id.reservationPersonnelButtonFour);
        btnChooseNum = (Button)findViewById(R.id.reservationPersonnelButtonChooseNum);

        btnTakeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(0);
            }
        });
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(1);
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(2);
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(3);
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(4);
            }
        });
    }

    private void clickButton(int num) {
        Button disableBtn = null;
        switch (peopleNum) {
            case -1:
                break;
            case 0:
                disableBtn = btnTakeOut;
                break;
            case 1:
                disableBtn = btnOne;
                break;
            case 2:
                disableBtn = btnTwo;
                break;
            case 3:
                disableBtn = btnThree;
                break;
            case 4:
                disableBtn = btnFour;
                break;
            default:
                disableBtn = btnChooseNum;
                break;
        }
        if(disableBtn != null) {
            disableBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            disableBtn.setTextColor(Color.parseColor("#000000"));
        }

        if(peopleNum != num) {
            Button enableBtn;
            switch (num) {
                case 0:
                    enableBtn = btnTakeOut;
                    break;
                case 1:
                    enableBtn = btnOne;
                    break;
                case 2:
                    enableBtn = btnTwo;
                    break;
                case 3:
                    enableBtn = btnThree;
                    break;
                case 4:
                    enableBtn = btnFour;
                    break;
                default:
                    enableBtn = btnChooseNum;
                    break;
            }
            enableBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            enableBtn.setTextColor(Color.parseColor("#FFFFFF"));
            peopleNum = num;
        }
        else
            peopleNum = -1;
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

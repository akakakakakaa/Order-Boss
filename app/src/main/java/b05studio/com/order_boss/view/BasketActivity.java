package b05studio.com.order_boss.view;

import android.content.Context;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.OrderInfo;
import b05studio.com.order_boss.model.Review;
import b05studio.com.order_boss.model.User;
import b05studio.com.order_boss.view.fragment.RestaurantReviewFragment;

/**
 * Created by mansu on 2017-05-28.
 */

public class BasketActivity extends AppCompatActivity {
    private TextView totalPriceText;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        initBasketRecyclerView();
        initButtons();
        initTotalPrice();
    }

    private void initBasketRecyclerView() {
        RecyclerView basketRecyclerView = (RecyclerView)findViewById(R.id.basketRecyclerView);
        basketRecyclerView.setHasFixedSize(true);

        BasketActivity.basketMenuAdapter restaurantMenuAdatper = new BasketActivity.basketMenuAdapter(User.getCurrentUser().getCurrentOrderInfos(), this, getLayoutInflater());
        basketRecyclerView.setAdapter(restaurantMenuAdatper);
    }

    private void initButtons() {
        ImageButton backBtn = (ImageButton)findViewById(R.id.basketBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initTotalPrice() {
        totalPriceText = (TextView)findViewById(R.id.basketTotalPrice);
        ArrayList<OrderInfo> orderInfos = User.getCurrentUser().getCurrentOrderInfos();

        totalPrice = 0;
        for(int i=0; i<orderInfos.size(); i++)
            totalPrice += orderInfos.get(i).getMenuInfo().getPrice() * orderInfos.get(i).getMenuNum();
        totalPriceText.setText(totalPrice+"");
    }

    public class basketMenuAdapter extends RecyclerView.Adapter<BasketActivity.basketMenuAdapter.ViewHolder> {
        private ArrayList<OrderInfo> orderInfos;
        private Context context;
        private LayoutInflater inflater;

        public basketMenuAdapter(ArrayList<OrderInfo> orderInfos, Context context, LayoutInflater inflater) {
            this.orderInfos = orderInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public BasketActivity.basketMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_basket_menu, parent, false);
            BasketActivity.basketMenuAdapter.ViewHolder holder = new BasketActivity.basketMenuAdapter.ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(BasketActivity.basketMenuAdapter.ViewHolder holder, int position) {
            final OrderInfo orderInfo = orderInfos.get(position);

            //need Picasso
            holder.basketMenuImage.setImageResource(R.drawable.restaurant_info_test_image1);
            holder.basketMenuName.setText(orderInfo.getMenuInfo().getName());
            holder.basketMenuNum.setText(orderInfo.getMenuNum()+"");
            holder.basketMenuPrice.setText(orderInfo.getMenuInfo().getPrice()*orderInfo.getMenuNum()+"");
            holder.basketMenuDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orderInfos.remove(orderInfo);
                    initTotalPrice();
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return orderInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView basketMenuImage;
            public TextView basketMenuName;
            public TextView basketMenuNum;
            public TextView basketMenuPrice;
            public ImageButton basketMenuDeleteButton;

            public ViewHolder(View view) {
                super(view);
                basketMenuImage = (ImageView)view.findViewById(R.id.basketMenuImage);
                basketMenuName = (TextView)view.findViewById(R.id.basketMenuName);
                basketMenuNum = (TextView)view.findViewById(R.id.basketMenuNum);
                basketMenuPrice = (TextView)view.findViewById(R.id.basketMenuPrice);
                basketMenuDeleteButton = (ImageButton)view.findViewById(R.id.basketMenuDeleteButton);
            }
        }
    }
}
package b05studio.com.order_boss.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.OrderInfo;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.User;
import b05studio.com.order_boss.view.activity.RestaurantActivity;

/**
 * Created by mansu on 2017-05-26.
 */

public class RestaurantMenuFragment extends Fragment {
    private int selectedNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_menu, container, false);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.restaurantMenuRecyclerView);
        recyclerView.setHasFixedSize(true);

        RestaurantMenuAdatper restaurantMenuAdatper = new RestaurantMenuAdatper(RestaurantInfo.getCurrentRestaurantInfo().getMenuInfos(), getContext(), inflater);
        recyclerView.setAdapter(restaurantMenuAdatper);

        return rootView;
    }

    private class RestaurantMenuAdatper extends RecyclerView.Adapter<RestaurantMenuFragment.RestaurantMenuAdatper.ViewHolder> {
        private ArrayList<MenuInfo> menuInfos;
        private Context context;
        private LayoutInflater inflater;

        public RestaurantMenuAdatper(ArrayList<MenuInfo> menuInfos, Context context, LayoutInflater inflater) {
            this.menuInfos = menuInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public RestaurantMenuFragment.RestaurantMenuAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_restaurant_menu, parent, false);
            RestaurantMenuFragment.RestaurantMenuAdatper.ViewHolder holder = new RestaurantMenuFragment.RestaurantMenuAdatper.ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(RestaurantMenuFragment.RestaurantMenuAdatper.ViewHolder holder, int position) {
            final MenuInfo menuInfo = menuInfos.get(position);

            //Picasso.with(context).load(menuInfo.getUrl()).into(holder.restaurantInfoMenuImage);
            holder.restaurantMenuWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View popupView = inflater.inflate(R.layout.popup_restaurant_menu_choose, null);
                    RestaurantActivity.popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);

                    ConstraintLayout background = (ConstraintLayout)popupView.findViewById(R.id.restaurantMenuChooseBackground);
                    ImageView menuImg = (ImageView)popupView.findViewById(R.id.restaurantMenuChooseImg);
                    TextView menuName = (TextView)popupView.findViewById(R.id.restaurantMenuChooseName);
                    final TextView menuPrice = (TextView)popupView.findViewById(R.id.restaurantMenuChoosePrice);
                    final TextView menuNum = (TextView)popupView.findViewById(R.id.restaurantMenuChooseNum);
                    ImageButton addBtn = (ImageButton)popupView.findViewById(R.id.restaurantMenuChooseAdd);
                    ImageButton subtractionBtn = (ImageButton)popupView.findViewById(R.id.restaurantMenuChooseSubtraction);
                    Button addToBasketBtn = (Button)popupView.findViewById(R.id.restaurantMenuChooseAddToBasketButton);

                    background.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RestaurantActivity.popupWindow.dismiss();
                        }
                    });
                    menuImg.setImageResource(R.drawable.restaurant_info_test_image1);
                    menuName.setText(menuInfo.getName());
                    menuPrice.setText(menuInfo.getPrice()+"원");
                    menuNum.setText("1개");
                    selectedNum = 1;
                    addBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            selectedNum++;
                            menuNum.setText(selectedNum+"개");
                            menuPrice.setText(menuInfo.getPrice()*selectedNum+"원");
                        }
                    });
                   subtractionBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(selectedNum > 1) {
                                selectedNum--;
                                menuNum.setText(selectedNum+"개");
                                menuPrice.setText(menuInfo.getPrice()*selectedNum+"원");
                            }
                        }
                    });
                    addToBasketBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            User.getCurrentUser().getCurrentOrderInfos().add(new OrderInfo(RestaurantInfo.getCurrentRestaurantInfo().getId(), menuInfo, selectedNum));
                        }
                    });

                    RestaurantActivity.popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                }
            });
            holder.restaurantMenuImage.setImageResource(R.drawable.restaurant_info_test_image1);
            holder.restaurantMenuName.setText(menuInfo.getName());
            holder.restaurantMenuPrice.setText(menuInfo.getPrice() + "원");
        }

        @Override
        public int getItemCount() {
            return menuInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ConstraintLayout restaurantMenuWrapper;
            public ImageView restaurantMenuImage;
            public TextView restaurantMenuName;
            public TextView restaurantMenuPrice;

            public ViewHolder(View view) {
                super(view);
                restaurantMenuWrapper = (ConstraintLayout)view.findViewById(R.id.restaurantMenuWrapper);
                restaurantMenuImage = (ImageView)view.findViewById(R.id.restaurantMenuImage);
                restaurantMenuName = (TextView)view.findViewById(R.id.restaurantMenuName);
                restaurantMenuPrice = (TextView)view.findViewById(R.id.restaurantMenuPrice);
            }
        }
    }
}

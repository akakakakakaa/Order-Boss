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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.model.OrderInfo;
import b05studio.com.order_boss.model.RestaurantInfo;
import b05studio.com.order_boss.model.Review;
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

        //get MenuInfo
        RestaurantInfo.getCurrentRestaurantInfo().setMenuInfos(new ArrayList<MenuInfo>());
        RestaurantInfo.getCurrentRestaurantInfo().getMenuInfos().add(new MenuInfo("https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20161103_62%2F1478156185473F4uoW_JPEG%2Fc96b9ebb-4489-4928-972c-c822ed20d6f1.jpeg&type=l&size=210x140&quality=95&autoRotate=true", "부리또", 3300));
        RestaurantInfo.getCurrentRestaurantInfo().getMenuInfos().add(new MenuInfo("https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20161103_65%2F14781561857769RCXJ_PNG%2F95adfb1e-eec4-41da-8932-53d643947820.png&type=l&size=210x140&quality=95&autoRotate=true", "타코", 3800));
        RestaurantInfo.getCurrentRestaurantInfo().getMenuInfos().add(new MenuInfo("https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20161103_289%2F1478156185857om83R_JPEG%2F496e1f2e-7bb6-4c79-a1c4-71c1cc79e5cd.jpeg&type=l&size=210x140&quality=95&autoRotate=true", "퀘사디아", 8800));
        RestaurantInfo.getCurrentRestaurantInfo().getMenuInfos().add(new MenuInfo("https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20161103_169%2F1478156186036VETIU_PNG%2F9b4424da-5c66-43b7-b34c-a38a6a6843bb.png&type=l&size=210x140&quality=95&autoRotate=true", "모둠감자튀김", 4800));
        //get reviewInfo
        RestaurantInfo.getCurrentRestaurantInfo().setReviews(new ArrayList<Review>());
        RestaurantInfo.getCurrentRestaurantInfo().getReviews().add(new Review("2", "임정연", "", Calendar.getInstance(), RestaurantInfo.getCurrentRestaurantInfo().getName(), "동네에 이렇게 맛있는 부리또가 있는줄 몰랐어요!\n" + " 분위기 너무나 좋고 음식들도 다 맛있어요ㅎㅎ\n" + "단골해야겠습니다!!!!!!", "", 5));

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

            Picasso.with(context).load(menuInfo.getUrl()).into(holder.restaurantMenuImage);
            holder.restaurantMenuWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<OrderInfo> orderInfos = User.getCurrentUser().getCurrentOrderInfos();
                    if(orderInfos.size() == 0 || orderInfos.get(0).getRestaurantId().compareTo(RestaurantInfo.getCurrentRestaurantInfo().getId()) == 0)
                        showMenuChoosePopup(menuInfo);
                    else
                        showDeleteYesOrNoPopupView(menuInfo);
                }
            });
            holder.restaurantMenuName.setText(menuInfo.getName());
            holder.restaurantMenuPrice.setText(menuInfo.getPrice() + "원");
        }

        private void showMenuChoosePopup(final MenuInfo menuInfo) {
            View popupView = inflater.inflate(R.layout.popup_restaurant_menu_choose, null);
            RestaurantActivity.popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);

            ConstraintLayout background = (ConstraintLayout) popupView.findViewById(R.id.restaurantMenuChooseBackground);
            ImageView menuImg = (ImageView) popupView.findViewById(R.id.restaurantMenuChooseImg);
            TextView menuName = (TextView) popupView.findViewById(R.id.restaurantMenuChooseName);
            final TextView menuPrice = (TextView) popupView.findViewById(R.id.restaurantMenuChoosePrice);
            final TextView menuNum = (TextView) popupView.findViewById(R.id.restaurantMenuChooseNum);
            ImageButton addBtn = (ImageButton) popupView.findViewById(R.id.restaurantMenuChooseAdd);
            ImageButton subtractionBtn = (ImageButton) popupView.findViewById(R.id.restaurantMenuChooseSubtraction);
            Button addToBasketBtn = (Button) popupView.findViewById(R.id.restaurantMenuChooseAddToBasketButton);

            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantActivity.popupWindow.dismiss();
                    RestaurantActivity.popupWindow = null;
                }
            });
            Picasso.with(context).load(menuInfo.getUrl()).into(menuImg);
            menuName.setText(menuInfo.getName());
            menuPrice.setText(menuInfo.getPrice() + "원");
            menuNum.setText("1개");
            selectedNum = 1;
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedNum++;
                    menuNum.setText(selectedNum + "개");
                    menuPrice.setText(menuInfo.getPrice() * selectedNum + "원");
                }
            });
            subtractionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedNum > 1) {
                        selectedNum--;
                        menuNum.setText(selectedNum + "개");
                        menuPrice.setText(menuInfo.getPrice() * selectedNum + "원");
                    }
                }
            });
            addToBasketBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User.getCurrentUser().getCurrentOrderInfos().add(new OrderInfo(RestaurantInfo.getCurrentRestaurantInfo().getId(), menuInfo, selectedNum));
                    RestaurantActivity.popupWindow.dismiss();
                    RestaurantActivity.popupWindow = null;
                }
            });

            RestaurantActivity.popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        }

        private void showDeleteYesOrNoPopupView(final MenuInfo menuInfo) {
            View popupView = inflater.inflate(R.layout.popup_restaurant_menu_delete, null);
            RestaurantActivity.popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);

            ConstraintLayout background = (ConstraintLayout)popupView.findViewById(R.id.restaurantMenuDeleteBackground);
            Button deleteOkBtn = (Button)popupView.findViewById(R.id.restaurantMenuDeleteOk);
            Button deleteCancelBtn = (Button)popupView.findViewById(R.id.restaurantMenuDeleteCancel);

            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantActivity.popupWindow.dismiss();
                    RestaurantActivity.popupWindow = null;
                }
            });
            deleteOkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User.getCurrentUser().getCurrentOrderInfos().clear();
                    RestaurantActivity.popupWindow.dismiss();
                    RestaurantActivity.popupWindow = null;
                    showMenuChoosePopup(menuInfo);
                }
            });
            deleteCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantActivity.popupWindow.dismiss();
                    RestaurantActivity.popupWindow = null;
                }
            });
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

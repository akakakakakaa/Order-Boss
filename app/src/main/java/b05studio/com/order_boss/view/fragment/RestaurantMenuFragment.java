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
import b05studio.com.order_boss.model.MenuInfo;
import b05studio.com.order_boss.view.RestaurantActivity;

/**
 * Created by mansu on 2017-05-26.
 */

public class RestaurantMenuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_menu, container, false);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.restaurantMenuRecyclerView);
        recyclerView.setHasFixedSize(true);
        //get MenuInfo
        ArrayList<MenuInfo> menuInfos = new ArrayList<>();
        menuInfos.add(new MenuInfo("", "소세지 또띠아", 15000));
        menuInfos.add(new MenuInfo("", "모둠 포", 15000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        RestaurantMenuAdatper restaurantMenuAdatper = new RestaurantMenuAdatper(menuInfos, getContext(), inflater);
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
            MenuInfo menuInfo = menuInfos.get(position);

            //Picasso.with(context).load(menuInfo.getUrl()).into(holder.restaurantInfoMenuImage);
            holder.restaurantMenuImage.setImageResource(R.drawable.restaurant_info_test_image1);
            holder.restaurantMenuName.setText(menuInfo.getName());
            holder.restaurantMenuPrice.setText(menuInfo.getPrice() + "원");
        }

        @Override
        public int getItemCount() {
            return menuInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView restaurantMenuImage;
            public TextView restaurantMenuName;
            public TextView restaurantMenuPrice;

            public ViewHolder(View view) {
                super(view);
                restaurantMenuImage = (ImageView)view.findViewById(R.id.restaurantMenuImage);
                restaurantMenuName = (TextView)view.findViewById(R.id.restaurantMenuName);
                restaurantMenuPrice = (TextView)view.findViewById(R.id.restaurantMenuPrice);
            }
        }
    }
}

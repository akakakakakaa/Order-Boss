package b05studio.com.order_boss.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Mansu on 2017-05-29.
 */

public class ReservationInfo {
    private RestaurantInfo restaurantInfo;
    private ArrayList<OrderInfo> orderInfos;
    private int remainTime;
    private Calendar orderTime;

    public ReservationInfo(RestaurantInfo restaurantInfo, ArrayList<OrderInfo> orderInfos, int remainTime, Calendar orderTime) {
        this.restaurantInfo = restaurantInfo;
        this.orderInfos = orderInfos;
        this.remainTime = remainTime;
        this.orderTime = orderTime;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public ArrayList<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(ArrayList<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public Calendar getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Calendar orderTime) {
        this.orderTime = orderTime;
    }
}

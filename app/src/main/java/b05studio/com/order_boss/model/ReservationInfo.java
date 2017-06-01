package b05studio.com.order_boss.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Mansu on 2017-05-29.
 */

public class ReservationInfo {
    private String userId;
    private String userName;
    private String userPhoneNum;
    private RestaurantInfo restaurantInfo;
    private ArrayList<OrderInfo> orderInfos;
    private int remainTime;
    private Calendar orderTime;
    private String requestContent;

    public ReservationInfo(String userId, String userName, String userPhoneNum, RestaurantInfo restaurantInfo, ArrayList<OrderInfo> orderInfos, int remainTime, Calendar orderTime, String requestContent) {
        this.userId = userId;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.restaurantInfo = restaurantInfo;
        this.orderInfos = orderInfos;
        this.remainTime = remainTime;
        this.orderTime = orderTime;
        this.requestContent = requestContent;
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

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }
}

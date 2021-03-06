package b05studio.com.order_boss.model;

import java.util.ArrayList;

/**
 * Created by mansu on 2017-05-28.
 */

public class User {
    private String userId;
    private String userProfileUrl;
    private String userName;
    private String userPhoneNum;
    private ArrayList<Review> myReviews;
    //파이어베이스에서 가져오는 정보가 아님. 나의 현재 장바구니 정보를 담는 객체
    private ArrayList<OrderInfo> currentOrderInfos = new ArrayList<>();
    private ArrayList<ReservationInfo> reservationInfos = new ArrayList<>();

    public static User currentUser;
    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public User(String userId, String userProfileUrl, String userName, String userPhoneNum, ArrayList<Review> myReviews, ArrayList<ReservationInfo> reservationInfos) {
        this.userId = userId;
        this.userProfileUrl = userProfileUrl;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.myReviews = myReviews;
        this.reservationInfos = reservationInfos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Review> getMyReviews() {
        return myReviews;
    }

    public void setMyReviews(ArrayList<Review> myReviews) {
        this.myReviews = myReviews;
    }

    public ArrayList<OrderInfo> getCurrentOrderInfos() {
        return currentOrderInfos;
    }

    public void setCurrentOrderInfos(ArrayList<OrderInfo> currentOrderInfos) {
        this.currentOrderInfos = currentOrderInfos;
    }

    public ArrayList<ReservationInfo> getReservationInfos() {
        return reservationInfos;
    }

    public void setReservationInfos(ArrayList<ReservationInfo> reservationInfos) {
        this.reservationInfos = reservationInfos;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }
}

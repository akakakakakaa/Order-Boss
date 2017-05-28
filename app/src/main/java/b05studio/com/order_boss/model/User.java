package b05studio.com.order_boss.model;

import java.util.ArrayList;

/**
 * Created by mansu on 2017-05-28.
 */

public class User {
    private String userId;
    private String userProfileUrl;
    private String userName;
    private ArrayList<String> myReviewids;
    //파이어베이스에서 가져오는 정보가 아님. 나의 현재 장바구니 정보를 담는 객체
    private ArrayList<OrderInfo> currentOrderInfos = new ArrayList<>();

    public static User currentUser;
    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public User(String userId, String userProfileUrl, String userName, ArrayList<String> myReviewids) {
        this.userId = userId;
        this.userProfileUrl = userProfileUrl;
        this.userName = userName;
        this.myReviewids = myReviewids;
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

    public ArrayList<String> getMyReviewids() {
        return myReviewids;
    }

    public void setMyReviewids(ArrayList<String> myReviewids) {
        this.myReviewids = myReviewids;
    }

    public ArrayList<OrderInfo> getCurrentOrderInfos() {
        return currentOrderInfos;
    }

    public void setCurrentOrderInfos(ArrayList<OrderInfo> currentOrderInfos) {
        this.currentOrderInfos = currentOrderInfos;
    }
}
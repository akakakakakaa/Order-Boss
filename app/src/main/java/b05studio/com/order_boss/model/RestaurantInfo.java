package b05studio.com.order_boss.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by mansu on 2017-05-17.
 */

public class RestaurantInfo {
    private String id;
    private String name;
    private String foodTag;
    private String address;
    private String phoneNum;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    //첫째주 월 ~ 넷째 주 일 배열크기 28
    private boolean[] holiday;
    private String holidayString;
    private String avgPrice;
    private int distance;
    private String imageUrl;
    private int likeNumber;
    private int reviewNumber;
    private int time;
    private ArrayList<Review> reviews;
    private ArrayList<MenuInfo> menuInfos;

    public static RestaurantInfo currentRestaurantInfo;
    public static RestaurantInfo getCurrentRestaurantInfo() {
        return currentRestaurantInfo;
    }
    public static void setCurrentRestaurantInfo(RestaurantInfo restaurantInfo) {
        currentRestaurantInfo = restaurantInfo;
    }

    public RestaurantInfo(String id, String name, String foodTag, String address, String phoneNum, int startHour, int startMinute, int endHour, int endMinute, boolean[] holiday, String holidayString, String avgPrice, int distance, String imageUrl, int likeNumber, int reviewNumber, int time, ArrayList<Review> reviews, ArrayList<MenuInfo> menuInfos) {
        this.id = id;
        this.name = name;
        this.foodTag = foodTag;
        this.address = address;
        this.phoneNum = phoneNum;
        this.distance = distance;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.holiday = holiday;
        this.holidayString = holidayString;
        this.avgPrice = avgPrice;
        this.imageUrl = imageUrl;
        this.likeNumber = likeNumber;
        this.reviewNumber = reviewNumber;
        this.time = time;
        this.reviews = reviews;
        this.menuInfos = menuInfos;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public int getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodTag() {
        return foodTag;
    }

    public void setFoodTag(String foodTag) {
        this.foodTag = foodTag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<MenuInfo> getMenuInfos() {
        return menuInfos;
    }

    public void setMenuInfos(ArrayList<MenuInfo> menuInfos) {
        this.menuInfos = menuInfos;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public boolean[] getHoliday() {
        return holiday;
    }

    public void setHoliday(boolean[] holiday) {
        this.holiday = holiday;
    }

    public String getHolidayString() {
        return holidayString;
    }

    public void setHolidayString(String holidayString) {
        this.holidayString = holidayString;
    }
}

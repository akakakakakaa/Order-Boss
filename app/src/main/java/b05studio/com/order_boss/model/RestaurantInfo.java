package b05studio.com.order_boss.model;

import java.util.ArrayList;

/**
 * Created by mansu on 2017-05-17.
 */

public class RestaurantInfo {
    private String id;
    private String name;
    private ArrayList<String> foodTag;
    private String address;
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

    public RestaurantInfo(String id, String name, ArrayList<String> foodTag, String address, int distance, String imageUrl, int likeNumber, int reviewNumber, int time, ArrayList<Review> reviews, ArrayList<MenuInfo> menuInfos) {
        this.id = id;
        this.name = name;
        this.foodTag = foodTag;
        this.address = address;
        this.distance = distance;
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

    public ArrayList<String> getFoodTag() {
        return foodTag;
    }

    public void setFoodTag(ArrayList<String> foodTag) {
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
}

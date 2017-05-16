package b05studio.com.order_boss.model;

/**
 * Created by mansu on 2017-05-17.
 */

public class RestaurantInfo {
    private Restaurant restaurant;
    private int likeNumber;
    private int reviewNumber;
    private int time;

    public RestaurantInfo(Restaurant restaurant, int likeNumber, int reviewNumber, int time) {
        this.restaurant = restaurant;
        this.likeNumber = likeNumber;
        this.reviewNumber = reviewNumber;
        this.time = time;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
}

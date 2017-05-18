package b05studio.com.order_boss.model;

import java.util.ArrayList;

/**
 * Created by young on 2017-05-16.
 */

public class Restaurant {

    private String id;
    private String name;
    private ArrayList<String> foodTag;
    private String address;
    private int distance;
    private String imageUrl;

    public Restaurant(String id, String name, ArrayList<String> foodTag, String address, int distance, String imageUrl) {
        this.id = id;
        this.name = name;
        this.foodTag = foodTag;
        this.address = address;
        this.distance = distance;
        this.imageUrl = imageUrl;
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
    // 좋아요랑 후기작성은 id 를 기준으로 따로 가져와야 할것 같다.


}

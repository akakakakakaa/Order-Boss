package b05studio.com.order_boss.model;

import java.util.ArrayList;

/**
 * Created by mansu on 2017-05-20.
 */

public class Review {
    private String userName;
    private long timestamp;
    private String reviewContent;
    private ArrayList<String> reviewImgUrls;
    private int likeNumber;

    public Review(String userName, long timestamp, String reviewContent, ArrayList<String> reviewImgUrls, int likeNumber) {
        this.userName = userName;
        this.timestamp = timestamp;
        this.reviewContent = reviewContent;
        this.reviewImgUrls = reviewImgUrls;
        this.likeNumber = likeNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public ArrayList<String> getReviewImgUrls() {
        return reviewImgUrls;
    }

    public void setReviewImgUrls(ArrayList<String> reviewImgUrls) {
        this.reviewImgUrls = reviewImgUrls;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }
}

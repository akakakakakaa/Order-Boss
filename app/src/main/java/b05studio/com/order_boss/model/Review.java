package b05studio.com.order_boss.model;

import java.util.ArrayList;

/**
 * Created by mansu on 2017-05-20.
 */

public class Review {
    private String userName;
    private String userProfileUrl;
    private long timestamp;
    private String reviewContent;
    private String reviewImgUrl;
    private int likeNumber;

    public Review(String userName, String userProfileUrl, long timestamp, String reviewContent, String reviewImgUrl, int likeNumber) {
        this.userName = userName;
        this.userProfileUrl = userProfileUrl;
        this.timestamp = timestamp;
        this.reviewContent = reviewContent;
        this.reviewImgUrl = reviewImgUrl;
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

    public String getReviewImgUrls() {
        return reviewImgUrl;
    }

    public void setReviewImgUrls(String reviewImgUrl) {
        this.reviewImgUrl = reviewImgUrl;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }
}

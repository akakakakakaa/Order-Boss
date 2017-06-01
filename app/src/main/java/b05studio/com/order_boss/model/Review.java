package b05studio.com.order_boss.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by mansu on 2017-05-20.
 */

public class Review {
    private String reviewId;

    private String userName;
    private String userProfileUrl;
    private Calendar calendar;
    private String restarauntName;
    private String reviewContent;
    private String reviewImgUrl;
    private int likeNumber;

    public Review(String reviewId, String userName, String userProfileUrl, Calendar calendar, String restarauntName, String reviewContent, String reviewImgUrl, int likeNumber) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.userProfileUrl = userProfileUrl;
        this.calendar = calendar;
        this.restarauntName = restarauntName;
        this.reviewContent = reviewContent;
        this.reviewImgUrl = reviewImgUrl;
        this.likeNumber = likeNumber;

    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRestarauntName() {
        return restarauntName;
    }

    public void setRestarauntName(String restarauntName) {
        this.restarauntName = restarauntName;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}

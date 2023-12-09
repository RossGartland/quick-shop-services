package com.cn.partnerservice.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="PRODUCT_REVIEWS")
public class ProductReview {

    @Id
    @Column(name="ProductReviewID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productReviewID;
    @NotNull
    @Column(name="productid")
    private long productID;
    @NotNull
    @Column(name="UserID")
    private long userID;
    @NotNull
    @Column(name="Username", length=50)
    private String username;
    @NotNull
    @Lob
    @Column(name="Content", length=512)
    private String content;
    @NotNull
    @Column(name="Rating")
    private int rating;
    @Column(name="crtdTimeStamp", updatable = false)
    @CreationTimestamp
    private Date crtdTimeStamp;
    @UpdateTimestamp
    private Date uptdTimeStamp;

    public ProductReview() {
    }

    public ProductReview(long productReviewID, long productID, long userID, String username, String content, int rating, Date crtdTimeStamp, Date uptdTimeStamp) {
        this.productReviewID = productReviewID;
        this.productID = productID;
        this.userID = userID;
        this.username = username;
        this.content = content;
        this.rating = rating;
        this.crtdTimeStamp = crtdTimeStamp;
        this.uptdTimeStamp = uptdTimeStamp;
    }

    public long getProductReviewID() {
        return productReviewID;
    }

    public void setProductReviewID(long productReviewID) {
        this.productReviewID = productReviewID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCrtdTimeStamp() {
        return crtdTimeStamp;
    }

    public void setCrtdTimeStamp(Date crtdTimeStamp) {
        this.crtdTimeStamp = crtdTimeStamp;
    }

    public Date getUptdTimeStamp() {
        return uptdTimeStamp;
    }

    public void setUptdTimeStamp(Date uptdTimeStamp) {
        this.uptdTimeStamp = uptdTimeStamp;
    }
}

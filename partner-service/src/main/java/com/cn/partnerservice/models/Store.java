package com.cn.partnerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents a store.
 * A store can have many owners and owners can have many stores.
 */
@Entity
@Builder
@Table(name="STORES")
public class Store {

    @Id
    @Column(name="StoreID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeID;
    @NotNull
    @Column(name="StoreName", length=50)
    private String storeName;
    @NotNull
    @Column(name="PhoneNumber", length=50)
    private String phoneNumber;
    @NotNull
    @Column(name="EmailAddress")
    private String emailAddress;
    @NotNull
    @Column(name="Address")
    private String address;
    @Column(name="owners")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.REMOVE })
    @JoinTable(name = "STORE_OWNERS_MAPPING", joinColumns = @JoinColumn(name ="StoreID"),
            inverseJoinColumns = @JoinColumn(name="OwnerID"))
    @JsonIgnore
    private Set<Owner> owners = new HashSet<>();
    @NotNull
    @Column(name="latitude")
    private double latitude;
    @NotNull
    @Column(name="longitude")
    private double longitude;
    @Column(name="DeliveryFee")
    private BigDecimal deliveryFee;
    @Column(name="StoreImagePath")
    private String storeImagePath;
    @NotNull
    @Column(name="IsActive")
    private boolean isActive;
    @Column(name="StoreRating")
    private Integer storeRating;
    @Column(name="crtdTimeStamp", updatable = false)
    @CreationTimestamp
    private Date crtdTimeStamp;
    @UpdateTimestamp
    private Date uptdTimeStamp;

    public Store() {

    }


    public Store(long storeID, String storeName, String phoneNumber, String emailAddress, String address, Set<Owner> owners,
                 double latitude, double longitude,BigDecimal deliveryFee, String storeImagePath, boolean isActive,
                 Integer storeRating,Date crtdTimeStamp, Date uptdTimeStamp) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.owners = owners;
        this.deliveryFee = deliveryFee;
        this.storeImagePath = storeImagePath;
        this.isActive = isActive;
        this.storeRating = storeRating;
        this.crtdTimeStamp = crtdTimeStamp;
        this.uptdTimeStamp = uptdTimeStamp;
    }

    public String getStoreImagePath() {
        return storeImagePath;
    }

    public void setStoreImagePath(String storeImagePath) {
        this.storeImagePath = storeImagePath;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getStoreRating() {
        return storeRating;
    }

    public void setStoreRating(Integer storeRating) {
        this.storeRating = storeRating;
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

package com.qsorderservice.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Represents an order.
 */
@Entity
@Table(name="ORDERS")
public class Order {
    @Id
    @Column(name="OrderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;
    @Column(name="customerID")
    private long customerID;
    @Column(name="CustomerEmail")
    private String customerEmail;
    @Column(name="StoreID")
    private long storeID;
    @Column(name="TotalItemCost")
    private BigDecimal totalItemCost;
    @Column(name="IsDelivery")
    private boolean isDelivery;
    @Column(name="IsCollection")
    private boolean isCollection;
    @Column(name="DeliveryFee")
    private BigDecimal deliveryFee;
    @Column(name="DeliveryAddress")
    private String deliveryAddress;
    @Column(name="TotalCost")
    private BigDecimal totalCost;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Column(name="CustomerLat")
    private double customerLat;
    @Column(name="CustomerLng")
    private double customerLng;
    @CreationTimestamp
    private Date crtdTimeStamp;
    @UpdateTimestamp
    private Date uptdTimeStamp;


    public Order(long orderID, long customerID, long storeID, String customerEmail, BigDecimal totalItemCost, boolean isDelivery,
                 BigDecimal deliveryFee, BigDecimal totalCost, EStatus status, double customerLat, double customerLng, boolean isCollection,
                 String deliveryAddress,Date crtdTimeStamp, Date uptdTimeStamp) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerEmail = customerEmail;
        this.storeID = storeID;
        this.totalItemCost = totalItemCost;
        this.isCollection = isCollection;
        this.isDelivery = isDelivery;
        this.deliveryFee = deliveryFee;
        this.totalCost = totalCost;
        this.status = status;
        this.customerLat = customerLat;
        this.customerLng = customerLng;
        this.deliveryAddress = deliveryAddress;
        this.crtdTimeStamp = crtdTimeStamp;
        this.uptdTimeStamp = uptdTimeStamp;
    }

    public Order() {
    }

    public double getCustomerLat() {
        return customerLat;
    }

    public void setCustomerLat(double customerLat) {
        this.customerLat = customerLat;
    }

    public double getCustomerLng() {
        return customerLng;
    }

    public void setCustomerLng(double customerLng) {
        this.customerLng = customerLng;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalItemCost() {
        return totalItemCost;
    }

    public void setTotalItemCost(BigDecimal totalItemCost) {
        this.totalItemCost = totalItemCost;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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

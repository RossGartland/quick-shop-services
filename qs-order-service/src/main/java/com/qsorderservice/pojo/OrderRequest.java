package com.qsorderservice.pojo;

import com.qsorderservice.models.EStatus;
import com.qsorderservice.models.Item;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Set;


/**
 * Represents a request to the order repository.
 */
public class OrderRequest {

    public long orderID;
    public long customerID;
    public String customerEmail;
    public long storeID;
    public BigDecimal totalItemCost;
    public boolean isDelivery;
    public boolean isCollection;
    public String deliveryAddress;
    public BigDecimal deliveryFee;
    public BigDecimal totalCost;
    public EStatus status;
    public Set<Item> items;
    public double customerLat;
    public double customerLng;


    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
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

    public BigDecimal getTotalItemCost() {
        return totalItemCost;
    }

    public void setTotalItemCost(BigDecimal totalItemCost) {
        this.totalItemCost = totalItemCost;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}

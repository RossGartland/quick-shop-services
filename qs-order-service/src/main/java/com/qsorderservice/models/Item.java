package com.qsorderservice.models;

import javax.persistence.*;

/**
 * Represents Item entity.
 */
@Entity
@Table(name="ITEMS")
public class Item {
    @Id
    @Column(name="ItemID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemID;
    @Column(name="ProductID") //Provided by product service.
    private int productID;
    @Column(name="Name") //Provided by product service.
    private String name;
    @Column(name="OrderID")
    private long orderID;
    @Column(name="qty")
    private int qty;

    public Item(long itemID, int productID, String name, long orderID, int qty) {
        this.itemID = itemID;
        this.productID = productID;
        this.name = name;
        this.orderID = orderID;
        this.qty = qty;
    }

    public Item() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

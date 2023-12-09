package com.cn.partnerservice.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
/**
 * Represents a product.
 * A product can have only 1 category however a category can have many products.
 */
@Entity
@Table(name="PRODUCTS")
public class Product {
    @Id
    @Column(name="ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productID;
    @NotNull
    @Column(name="Name", length=50)
    private String name;
    @NotNull
    @Column(name="BrandName", length=50)
    private String brandName;
    @NotNull
    @Column(name="Description")
    private String description;
    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinColumn(name="CategoryID", updatable=true)
    private Category categoryID;
    @NotNull
    @Column(name="Price")
    private BigDecimal price;
    @Column(name="Volume")
    private long volume;
    @Column(name="storeid")
    private long storeID;
    @Column(name="gtin14")
    private String gtin14;
    @Column(name="ProductImagePath")
    private String productImagePath;
    @NotNull
    @Column(name="IsActive")
    private boolean isActive;
    @Column(name="IsOnSale")
    private boolean isOnSale;
    @Column(name="SalePrice")
    private BigDecimal salePrice;
    @Column(name="ProductRating")
    private Integer productRating;
    @Column(name="crtdTimeStamp", updatable = false)
    @CreationTimestamp
    private Date crtdTimeStamp;
    @UpdateTimestamp
    private Date uptdTimeStamp;

    public Product() {
    }

    public Product(long productID, String name, String brandName, String description, Category categoryID, BigDecimal price, long volume, long storeID,
                   String gtin14, String productImagePath, boolean isActive, boolean isOnSale,BigDecimal salePrice, Integer productRating,
                   Date crtdTimeStamp, Date uptdTimeStamp) {
        this.productID = productID;
        this.name = name;
        this.brandName = brandName;
        this.description = description;
        this.categoryID = categoryID;
        this.price = price;
        this.volume = volume;
        this.storeID = storeID;
        this.gtin14 = gtin14;
        this.productImagePath = productImagePath;
        this.isActive = isActive;
        this.isOnSale = isOnSale;
        this.salePrice = salePrice;
        this.productRating = productRating;
        this.crtdTimeStamp = crtdTimeStamp;
        this.uptdTimeStamp = uptdTimeStamp;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGtin14() {
        return gtin14;
    }

    public void setGtin14(String gtin14) {
        this.gtin14 = gtin14;
    }

    public String getProductImagePath() {
        return productImagePath;
    }

    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getProductRating() {
        return productRating;
    }

    public void setProductRating(Integer productRating) {
        this.productRating = productRating;
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

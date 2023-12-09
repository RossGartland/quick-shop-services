package com.cn.partnerservice.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Represents categories.
 * A category can have many products.
 */
@Entity
@Table(name="CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryID;
    @NotNull
    @Column(name="CategoryType", length = 50)
    private String categoryType;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="CategoryID")
    private Set<Product> products;

    public Category(long categoryID, String categoryType) {
        this.categoryID = categoryID;
        this.categoryType = categoryType;
    }

    public Category() {
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}

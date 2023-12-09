package com.cn.partnerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
/**
 * Represents an owner.
 * An owner can have many stores.
 */
@Entity
@Table(name="OWNERS")
public class Owner {
    @Id
    @Column(name="OwnerID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerID;
    @NotNull
    @Column(name="Forename", length=50)
    private String forename;
    @NotNull
    @Column(name="Surname", length=50)
    private String surname;
    @NotNull
    @Column(name="PhoneNumber", length=50)
    private String phoneNumber;
    @NotNull
    @Column(name="EmailAddress")
    private String emailAddress;
    @NotNull
    @Column(name="Address")
    private String address;
    @Column(name="stores")
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH
            },mappedBy = "owners")//Set relationship between owner and store.
    private Set<Store> stores = new HashSet<>();
    @Column(name="AccountRef")
    private int accountRef;
    @Column(name="crtdTimeStamp", updatable = false)
    @CreationTimestamp
    private Date crtdTimeStamp;
    @UpdateTimestamp
    private Date uptdTimeStamp;

    public Owner() {
    }

    public Owner(long ownerID, String forename, String surname, String phoneNumber, String emailAddress, String address, Set<Store> stores,
                 Date crtdTimeStamp, int accountRef, Date uptdTimeStamp) {
        this.ownerID = ownerID;
        this.forename = forename;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.stores = stores;
        this.crtdTimeStamp = crtdTimeStamp;
        this.accountRef = accountRef;
        this.uptdTimeStamp = uptdTimeStamp;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
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

    public int getAccountRef() {
        return accountRef;
    }

    public void setAccountRef(int accountRef) {
        this.accountRef = accountRef;
    }

    public void addStore(Store store) {

        this.stores.add(store);
        store.getOwners().add(this);
    }
}

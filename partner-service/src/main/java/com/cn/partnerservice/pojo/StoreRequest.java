package com.cn.partnerservice.pojo;

import com.cn.partnerservice.models.Owner;

import java.math.BigDecimal;
import java.util.Set;
/**
 * Represents a request to the store repository.
 */
public class StoreRequest {

    public String storeName;
    public String phoneNumber;
    public String emailAddress;
    public String address;
    public Set<Owner> owners;
    public double latitude;
    public double longitude;
    public BigDecimal deliveryFee;
}

package com.cn.partnerservice.pojo;

/**
 * Represents a request for latitude and longitude.
 */
public class LocationRequest {
    public double lat;
    public double lng;

    public LocationRequest(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}



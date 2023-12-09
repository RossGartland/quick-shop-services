package com.qsorderservice.pojo;

import java.math.BigDecimal;

/**
 * Represents statistics relating to a stores orders.
 */
public class StatRequest {
    public BigDecimal revenue;
    public int totalOrders;
    public int productsSold;

    public StatRequest(BigDecimal revenue, int totalOrders, int productsSold) {
        this.revenue = revenue;
        this.totalOrders = totalOrders;
        this.productsSold = productsSold;
    }
}

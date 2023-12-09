package com.cn.partnerservice.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceHelperTest {

    /**
     * Calculates the distance between a 2 given longitude and latitude.
     */
    @Test
    void distanceCalculation() {
        DistanceHelper distanceHelper = new DistanceHelper();
        assertEquals(79, Math.round(distanceHelper.distanceCalculation(55, 56, -6, -7)));
    }
}
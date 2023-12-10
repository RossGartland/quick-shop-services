package com.cn.partnerservice.service;

import com.cn.partnerservice.models.TradingHours;
import com.cn.partnerservice.pojo.TradingHoursRequest;
import com.cn.partnerservice.repositories.TradingHoursRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TradingHoursServiceTest {

    static TradingHours tradingHour;

    @Mock
    private TradingHoursRepository tradingHoursRepository;

    @InjectMocks
    private TradingHoursService tradingHoursService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        tradingHour = new TradingHours(1, 0, DayOfWeek.MONDAY, LocalTime.NOON, LocalTime.MIDNIGHT);
    }

    @Test
    void getTradingHoursTest() {
        Long storeID = 1L;
        List<TradingHours> mockTradingHours = Arrays.asList(
                new TradingHours(/* details */),
                new TradingHours(/* details */)
        );
        when(tradingHoursRepository.findAllByStoreID(storeID)).thenReturn(mockTradingHours);

        // Test the method
        ResponseEntity response = tradingHoursService.getTradingHours(storeID);

        // Verify that the repository method was called with the store ID
        verify(tradingHoursRepository, times(1)).findAllByStoreID(storeID);

        // Assert that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the response body contains the expected trading hours
        assertEquals(mockTradingHours, response.getBody());
    }
}
package com.cn.partnerservice.controllers;

import com.cn.partnerservice.models.Store;
import com.cn.partnerservice.models.TradingHours;
import com.cn.partnerservice.pojo.StoreRequest;
import com.cn.partnerservice.pojo.TradingHoursRequest;
import com.cn.partnerservice.service.TradingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API Endpoints for trading hours entity.
 */
@RestController
@RequestMapping("/stores")
public class TradingHoursController {
    @Autowired
    TradingHoursService tradingHoursService;

    /**
     * Creates new trading hours for a given store.
     * @param storeID
     * @param tradingHoursRequest
     * @return
     */
    @PostMapping("/{storeID}/tradinghours")
    public ResponseEntity setTradingHours(@PathVariable long storeID, @RequestBody TradingHoursRequest tradingHoursRequest) {
        return tradingHoursService.setTradingHours(storeID,tradingHoursRequest);
    }

    /**
     * Updates trading hours for a given store.
     * @param storeID
     * @param tradingHoursRequest
     * @return
     */
    @PutMapping("/{storeID}/tradinghours")
    public ResponseEntity editTradingHours(@PathVariable long storeID, @RequestBody TradingHoursRequest tradingHoursRequest) {
        return tradingHoursService.editTradingHours(storeID,tradingHoursRequest);
    }

    /**
     * Returns trading hours for a given store.
     * @param storeID
     * @return
     */
    @GetMapping("/{storeID}/tradinghours")
    public ResponseEntity getTradingHours(@PathVariable long storeID) {
        return tradingHoursService.getTradingHours(storeID);
    }

}

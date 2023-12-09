package com.cn.partnerservice.service;

import com.cn.partnerservice.models.TradingHours;
import com.cn.partnerservice.pojo.TradingHoursRequest;
import com.cn.partnerservice.repositories.TradingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for trading hours entity.
 * Contains business logic.
 */
@Service
public class TradingHoursService {

    @Autowired
    TradingHoursRepository tradingHoursRepository;

    /**
     * Creates trading hours for a given store entity.
     * @param storeID
     * @param tradingHoursRequest
     * @return
     */
    public ResponseEntity setTradingHours(Long storeID, TradingHoursRequest tradingHoursRequest) {
        for (TradingHours i: tradingHoursRequest.tradingHours
             ) {
            tradingHoursRepository.save(i);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tradingHoursRequest);
    }

    /**
     * Updates trading hours for a given store entity.
     * @param storeID
     * @param tradingHoursRequest
     * @return
     */
    public ResponseEntity editTradingHours(Long storeID, TradingHoursRequest tradingHoursRequest) {
        List<TradingHours> tradingHours = tradingHoursRepository.findAllByStoreID(storeID);

        for (TradingHours i: tradingHoursRequest.tradingHours
        ) {
           tradingHoursRepository.updateTradingHours(i.getStoreID(), i.getWeekday(), i.getStartHour(), i.getEndHour());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(tradingHoursRequest);
    }

    /**
     * Gets trading hours for a given store entity.
     * @param storeID
     * @return
     */
    public ResponseEntity getTradingHours(Long storeID) {
        List<TradingHours> tradingHours = tradingHoursRepository.findAllByStoreID(storeID);

        return ResponseEntity.status(HttpStatus.OK)
                .body(tradingHours);
    }
}

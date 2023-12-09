package com.cn.partnerservice.repositories;

import com.cn.partnerservice.models.TradingHours;
import com.cn.partnerservice.pojo.TradingHoursRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 * Data access interface for trading hours entity.
 */
public interface TradingHoursRepository extends JpaRepository<TradingHours, Long> {
    List<TradingHours> findAllByStoreID (Long storeID);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE TradingHours t SET t.startHour = :startHour, t.endHour = :endHour WHERE t.storeID = :storeID AND t.weekday = :weekday")
    void updateTradingHours(@Param("storeID") Long storeID, @Param("weekday") DayOfWeek weekday, @Param("startHour") LocalTime startHour, @Param("endHour") LocalTime endHour);
}

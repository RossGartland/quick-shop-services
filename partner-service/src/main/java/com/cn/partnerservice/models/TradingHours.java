package com.cn.partnerservice.models;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Represents a stores opening and closing hours.
 */
@Entity
@Table(name="TRADING_HOURS")
public class TradingHours {
    @Id
    @Column(name="TradingHourID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tradingHourID;
    @Column(name="storeid")
    private long storeID;
    @Column(name="Weekday")
    private DayOfWeek weekday;
    @Column(name="StartHour", columnDefinition = "TIME")
    private LocalTime startHour;
    @Column(name="End_Hour", columnDefinition = "TIME")
    private LocalTime endHour;

    public TradingHours() {
    }

    public TradingHours(long tradingHourID, long storeID, DayOfWeek weekday, LocalTime startHour, LocalTime endHour) {
        this.tradingHourID = tradingHourID;
        this.storeID = storeID;
        this.weekday = weekday;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public long getTradingHourID() {
        return tradingHourID;
    }

    public void setTradingHourID(long businessHourID) {
        this.tradingHourID = businessHourID;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public DayOfWeek getWeekday() {
        return weekday;
    }
    public int getWeekdayValue() {
        return weekday.getValue();
    }

    public void setWeekday(DayOfWeek weekday) {
        this.weekday = weekday;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }
}

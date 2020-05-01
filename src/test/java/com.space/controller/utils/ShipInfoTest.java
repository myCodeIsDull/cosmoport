package com.space.controller.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.space.model.ShipType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipInfoTest {


    public Long id;
    public String name;
    public String planet;
    public ShipType shipType;
    public Long prodDate;
    public Boolean isUsed;
    public Double speed;
    public Integer crewSize;
    public Double rating;

    public ShipInfoTest() {
    }

    public ShipInfoTest(Long id, String name, String planet, ShipType shipType, Long prodDate, Boolean isUsed, Double speed, Integer crewSize, Double rating) {
        this.id = id;
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
        this.rating = rating;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipInfoTest that = (ShipInfoTest) o;
        Calendar calendarThis = Calendar.getInstance();
        calendarThis.setTimeInMillis(prodDate);
        int prodYearThis = calendarThis.get(Calendar.YEAR);
        Calendar calendarThat = Calendar.getInstance();
        calendarThat.setTimeInMillis(that.prodDate);
        int prodYearThat = calendarThat.get(Calendar.YEAR);
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(planet, that.planet) &&
                shipType == that.shipType &&
                Objects.equals(prodYearThis, prodYearThat) &&
                Objects.equals(isUsed, that.isUsed) &&
                Objects.equals(speed, that.speed) &&
                Objects.equals(crewSize, that.crewSize) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, planet, shipType, prodDate, isUsed, speed, crewSize, rating);
    }




private SimpleDateFormat stf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(prodDate);
        int prodYear = calendar.get(Calendar.YEAR);
        return "ShipInfoTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prodYear=" + stf.format(prodDate) +
                ", speed=" +speed + //DELETE PRIOR PRODUCTION!
                ", isUsed=" +isUsed+
                ", rating=" +rating+
                '}';
    }
}
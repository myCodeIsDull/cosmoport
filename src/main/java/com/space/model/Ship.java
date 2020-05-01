package com.space.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.space.util.CustomDoubleSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Stream;


@Entity
@Table(name = "ship")
public class Ship {
    @Transient
    @JsonIgnore
    public static final Integer CURRENT_YEAR = 3019;

    public Ship() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ctrl+v from:https://stackoverflow.com/questions/39807483/sequence-hibernate-sequence-not-found-sql-statement
    private Long id;
    private String name;
    private String planet;
    @Enumerated(EnumType.STRING)
    private ShipType shipType;
    private Date prodDate;
    private Boolean isUsed;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double speed;
    private Integer crewSize;
    private Double rating;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double calculateRating() {
        double rating = (80*speed*(isUsed?0.5:1))/((CURRENT_YEAR-getProdYear())+1);
        BigDecimal bd = BigDecimal.valueOf(rating).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    public boolean isAnyParticularVariableNull() {
        return particularVariableStream().anyMatch(Objects::isNull);
    }

    public Stream<Object>particularVariableStream() {
        return Stream.of(name,planet,shipType,prodDate,speed,crewSize);
    }

    public int getProdYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prodDate);
        return calendar.get(Calendar.YEAR);
    }

    public void setPropertiesFromProbe(Ship probe) {
        if(probe.name !=null && !name.equals(probe.name)) this.setName(probe.name);
        if(probe.planet!=null && !planet.equals((probe.planet))) this.setPlanet(probe.planet);
        if(probe.shipType!=null && !shipType.equals(probe.shipType)) this.setShipType(probe.shipType);
        if(probe.prodDate!=null && !prodDate.equals(probe.prodDate)) this.setProdDate(probe.prodDate);
        if(probe.isUsed!=null && !isUsed.equals(probe.isUsed))this.setUsed(probe.isUsed);
        if(probe.crewSize!=null && !crewSize.equals(probe.crewSize))this.setCrewSize(probe.crewSize);
        if(probe.speed!=null && !speed.equals(probe.speed))this.setSpeed(probe.speed);
    }


    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                '}';
    }
}

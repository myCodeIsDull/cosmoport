package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;


import java.util.List;


public interface ShipService {

    List<Ship> getByQuery(String name, String planet, ShipType shipType, Long after, Long before,
                          Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize,
                          Double minRating, Double maxRating, ShipOrder order, Integer pageNumber, Integer pageSize);

    Integer getShipsCount(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed, Double minSpeed,
                          Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating);

    Ship createAndSave(Ship ship);

    Ship getShip(String id);

    Ship updateAndSave(String id, Ship probe);

    void deleteShip(String id);
}

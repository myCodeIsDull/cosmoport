package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.JpaShipRepository;
import com.space.util.exception.BadRequestException;
import com.space.util.exception.ShipNotFoundException;
import com.space.util.Validator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service("shipService")
public class ShipServiceImpl implements ShipService {

    private JpaShipRepository jpaShipRepository;

    public ShipServiceImpl(JpaShipRepository jpaShipRepository) {
        this.jpaShipRepository = jpaShipRepository;
    }



    @Override
    public List<Ship> getByQuery(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
                                 Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating,
                                 Double maxRating, ShipOrder order, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber==null?0:pageNumber, pageSize==null?3:pageSize, order==null? Sort.unsorted():Sort.by(Sort.Order.asc(order.getFieldName())));
        Date dateAfter = after==null?null:new Date(after);
        Date dateBefore = before==null?null:new Date(before);

        return jpaShipRepository.queryByShip(name,
                planet, shipType, dateAfter, dateBefore, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating,
                maxRating, pageable);
    }

    @Override
    public Integer getShipsCount(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
                                 Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating,
                                 Double maxRating) {

        Date dateAfter = after==null?null:new Date(after);
        Date dateBefore = before==null?null:new Date(before);

        return jpaShipRepository.queryByShip(name, planet, shipType, dateAfter, dateBefore, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating, Pageable.unpaged()).size();
    }


    @Transactional
    @Override
    public Ship updateAndSave(String id, Ship probe) {
        Ship ship = getShip(id);
        ship.setPropertiesFromProbe(probe);
        createAndSave(ship);
        return ship;
    }

    @Transactional
    @Override
    public Ship createAndSave(Ship ship) {
        if(!Validator.isShipEntityCorrect(ship)) throw new BadRequestException(ship);
        if(ship.getUsed()==null) ship.setUsed(false);
        ship.setRating(ship.calculateRating());
        return jpaShipRepository.saveAndFlush(ship);
    }

    @Override
    public Ship getShip(String id) {
        if(!Validator.isIDCorrect(id)) throw new BadRequestException(id);
        Ship ship = jpaShipRepository.findById(Long.parseLong(id)).orElse(null);
        if(ship==null) throw new ShipNotFoundException(id);
        return ship;
    }

    @Transactional
    @Override
    public void deleteShip(String id) {
        if(!Validator.isIDCorrect(id)) throw new BadRequestException(id);
        Long parsedID = Long.parseLong(id);
        if(!jpaShipRepository.existsById(parsedID)) throw new ShipNotFoundException(id);
        jpaShipRepository.deleteById(parsedID);
    }
}

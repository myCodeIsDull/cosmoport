package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @RequestMapping(value = "/ships",method = RequestMethod.GET)
    public List<Ship> getShips(@RequestParam(name = "name",required = false)String name,
                                             @RequestParam(name = "planet", required = false) String planet,
                                            @RequestParam(name = "shipType", required = false)ShipType shipType,
                                            @RequestParam(name = "after", required = false)Long after,
                                            @RequestParam(name = "before",required = false)Long before,
                                            @RequestParam(name = "isUsed", required = false)Boolean isUsed,
                                            @RequestParam(name = "minSpeed",required = false)Double minSpeed,
                                            @RequestParam(name = "maxSpeed", required = false)Double maxSpeed,
                                            @RequestParam(name = "minCrewSize", required = false)Integer minCrewSize,
                                            @RequestParam(name = "maxCrewSize", required = false)Integer maxCrewSize,
                                            @RequestParam(name = "minRating", required = false)Double minRating,
                                            @RequestParam(name = "maxRating",required = false)Double maxRating,
                                            @RequestParam(name = "order", required = false)ShipOrder order,
                                            @RequestParam(name = "pageNumber", required = false)Integer pageNumber,
                                            @RequestParam(name = "pageSize",required = false)Integer pageSize) {


                            return shipService.getByQuery(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                                    maxCrewSize, minRating, maxRating, order,pageNumber,pageSize);
                        }


    @RequestMapping(value = "/ships/count",method = RequestMethod.GET)
    public Integer getShipsCount(@RequestParam(name = "name",required = false)String name,
                                             @RequestParam(name = "planet", required = false) String planet,
                                             @RequestParam(name = "shipType", required = false)ShipType shipType,
                                             @RequestParam(name = "after", required = false)Long after,
                                             @RequestParam(name = "before",required = false)Long before,
                                             @RequestParam(name = "isUsed", required = false)Boolean isUsed,
                                             @RequestParam(name = "minSpeed",required = false)Double minSpeed,
                                             @RequestParam(name = "maxSpeed", required = false)Double maxSpeed,
                                             @RequestParam(name = "minCrewSize", required = false)Integer minCrewSize,
                                             @RequestParam(name = "maxCrewSize", required = false)Integer maxCrewSize,
                                             @RequestParam(name = "minRating", required = false)Double minRating,
                                             @RequestParam(name = "maxRating",required = false)Double maxRating) {

        return shipService.getShipsCount(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                                        maxCrewSize, minRating, maxRating);
    }

    @RequestMapping(value = "/ships", method = RequestMethod.POST)
    public Ship createShip(@RequestBody Ship ship) {
        return shipService.createAndSave(ship);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET)
    public Ship getShip(@PathVariable("id")String id) {
        return shipService.getShip(id);
    }


    @RequestMapping(value = "/ships/{id}",method = RequestMethod.POST)
    public Ship updateShip(@PathVariable("id") String id, @RequestBody Ship probe) {
        return shipService.updateAndSave(id, probe);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.DELETE)
    public void deleteShip(@PathVariable("id")String id) {
        shipService.deleteShip(id);
    }

}

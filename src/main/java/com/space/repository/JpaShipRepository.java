package com.space.repository;


import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository("shipRepository")
public interface JpaShipRepository extends JpaRepository<Ship,Long> {


    @Query("select s from Ship s where "+
            "(:name is null or upper(s.name) like concat('%', upper(:name), '%')) "+
            "and (:planet is null or upper(s.planet) like concat('%', upper(:planet), '%')) "+
            "and (:shipType is null or s.shipType = :shipType) "+
            "and (:after is null or s.prodDate >= :after) "+
            "and (:before is null or s.prodDate <= :before) " +
            "and (:isUsed is null or s.isUsed = :isUsed) "+
            "and (:minSpeed is null or s.speed >= :minSpeed) " +
            "and (:maxSpeed is null or s.speed <= :maxSpeed) " +
            "and (:minCrewSize is null or s.crewSize >= :minCrewSize) "+
            "and (:maxCrewSize is null or s.crewSize <= :maxCrewSize) "+
            "and (:minRating is null or s.rating >= :minRating) "+
            "and (:maxRating is null or s.rating <= :maxRating)")

    List<Ship> queryByShip(@Param("name")String name, @Param("planet")String planet, @Param("shipType") ShipType shipType,
                           @Param("after")  Date after, @Param("before") Date before,
                           @Param("isUsed")Boolean isUsed, @Param("minSpeed")Double minSpeed, @Param("maxSpeed")Double maxSpeed,
                           @Param("minCrewSize")Integer minCrewSize, @Param("maxCrewSize")Integer maxCrewSize,
                           @Param("minRating")Double minRating, @Param("maxRating")Double maxRating, Pageable pageable);



}

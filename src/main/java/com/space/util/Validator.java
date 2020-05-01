package com.space.util;

import com.space.model.Ship;

public class Validator {

    public static boolean isIDCorrect(String id) {
        if(id.matches("-?\\d+")) {
            long value = Long.parseLong(id);
            return value > 0;
        }
        return false;
    }

    public static boolean isShipEntityCorrect(Ship entity) {
        if(entity.isAnyParticularVariableNull() ||
                entity.getName().isEmpty() ||
                entity.getName().length()>50||
                entity.getPlanet().length()>50 ||
                entity.getPlanet().isEmpty() ||
                entity.getCrewSize()<1 ||
                entity.getCrewSize()>9999 ||
                entity.getProdDate().getTime()<0 ||
                entity.getProdYear()< 2800 ||
                entity.getProdYear()>Ship.CURRENT_YEAR ||
                entity.getSpeed()<0.01 ||
                entity.getSpeed()>0.99) return false;
        return true;
    }
}

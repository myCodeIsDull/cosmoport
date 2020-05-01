package com.space.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.PersistenceException;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShipNotFoundException extends RuntimeException {

    public ShipNotFoundException(String id) {
        super("Could not find any ship with ID: "+id);
    }
}

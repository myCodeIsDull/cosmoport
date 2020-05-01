package com.space.util.exception;

import com.space.model.Ship;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String id) {
        super(String.format("Passed ID[%s] is not correct!",id));
    }

    public BadRequestException(Ship ship) {
        super("Can't update the entity: necessary conditions aren't match. "+ship);
    }
}

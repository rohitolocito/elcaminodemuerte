package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Driver is not the owner of the car..")
public class DriverNotOwnerOfCarException extends Exception {

	
	private static final long serialVersionUID = 6970596882527624878L;

	public DriverNotOwnerOfCarException(String message) {
		super(message);
	}
}

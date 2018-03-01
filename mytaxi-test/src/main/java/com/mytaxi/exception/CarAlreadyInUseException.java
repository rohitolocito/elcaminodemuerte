package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This car is already in use ...")
public class CarAlreadyInUseException extends Exception 
{


	private static final long serialVersionUID = 3859780152447089226L;

	public CarAlreadyInUseException(String message) 
	{
		super(message);
	}
}

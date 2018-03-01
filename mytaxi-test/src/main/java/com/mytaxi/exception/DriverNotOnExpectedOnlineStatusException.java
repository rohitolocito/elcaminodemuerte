package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Driver does not match required online status")
public class DriverNotOnExpectedOnlineStatusException extends Exception {
	

	private static final long serialVersionUID = 6211575836692277653L;

	public DriverNotOnExpectedOnlineStatusException(String message) {
		super(message);
	}
}

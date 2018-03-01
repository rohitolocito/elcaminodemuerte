package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This driver account has been deleted.")
public class DriverAccountDeletedException extends Exception {


	private static final long serialVersionUID = -8944618997923102433L;

	public DriverAccountDeletedException(String message) {
		super(message);
	}
}

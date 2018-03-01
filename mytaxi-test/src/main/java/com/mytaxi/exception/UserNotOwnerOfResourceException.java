package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "User is not the owner of resource.. ")
public class UserNotOwnerOfResourceException extends Exception {

	public UserNotOwnerOfResourceException(String message) {
		super(message);
	}
}

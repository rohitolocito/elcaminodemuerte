package com.mytaxi.service.driver;

import java.security.Principal;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.UserNotOwnerOfResourceException;

public interface DriverAuthorizationService {
	
	void authorize(DriverDO driverDO, Principal principal) throws UserNotOwnerOfResourceException;
}

package com.mytaxi.service.driver;

import java.security.Principal;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.UserNotOwnerOfResourceException;

@Service
public class DefaultDriverAuthorizationService implements DriverAuthorizationService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverAuthorizationService.class);

	@Override
	public void authorize(DriverDO driverDO, Principal principal) throws UserNotOwnerOfResourceException {
		
		LOG.info("Authorizing for "+driverDO.getUsername());
		
		if (!driverDO.getUsername().equals(principal.getName())) {
			throw new UserNotOwnerOfResourceException("Illegal Access !");
		}
		
	}

}

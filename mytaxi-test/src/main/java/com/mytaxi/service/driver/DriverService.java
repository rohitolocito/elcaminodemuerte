package com.mytaxi.service.driver;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverAccountDeletedException;
import com.mytaxi.exception.DriverNotOnExpectedOnlineStatusException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException, DriverAccountDeletedException;

    List<DriverDO> find(OnlineStatus onlineStatus);

	DriverDO find(long driverId, OnlineStatus online) throws EntityNotFoundException, DriverNotOnExpectedOnlineStatusException, DriverAccountDeletedException;

	void activateDriver(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException;

	void deactivateDriver(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException;

	DriverDO verify(DriverDO driverDO, OnlineStatus online) throws DriverNotOnExpectedOnlineStatusException;

}

package com.mytaxi.service.driver;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverAccountDeletedException;
import com.mytaxi.exception.DriverNotOnExpectedOnlineStatusException;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService, UserDetailsService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    public DefaultDriverService(final DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     * @throws DriverAccountDeletedException 
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
        	driverDO.setPassword(new BCryptPasswordEncoder().encode(driverDO.getPassword()));
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     * @throws DriverAccountDeletedException 
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setOnlineStatus(OnlineStatus.OFFLINE);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     * @throws DriverAccountDeletedException 
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException, DriverAccountDeletedException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException
    {
        DriverDO driverDO = driverRepository.findOne(driverId);
        if (driverDO == null)
        {
            throw new EntityNotFoundException("Could not find driver with id: " + driverId);
        }
        if (driverDO.isDeleted()) 
        {
        	throw new DriverAccountDeletedException("Driver account with id: "+driverId+" has been deleted.");
        }
        return driverDO;
    }


	@Override
	public DriverDO find(long driverId, OnlineStatus online) throws EntityNotFoundException, DriverNotOnExpectedOnlineStatusException, DriverAccountDeletedException 
	{
		DriverDO driverDO = findDriverChecked(driverId);
		
		return verify(driverDO, online);
	}
	
	@Override
	public DriverDO verify(DriverDO driverDO, OnlineStatus online) throws DriverNotOnExpectedOnlineStatusException 
	{
		if (driverDO.getOnlineStatus().equals(online))
			return driverDO;
		
		throw new DriverNotOnExpectedOnlineStatusException("Driver with id: "+driverDO.getId()+" must be "+ online.name());
	}


	@Override
	@Transactional
	public void activateDriver(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException 
	{
		setOnlineStatus(driverId, OnlineStatus.ONLINE);
	}
	
	@Override
	@Transactional
	public void deactivateDriver(Long driverId) throws EntityNotFoundException, DriverAccountDeletedException 
	{
		setOnlineStatus(driverId, OnlineStatus.OFFLINE);
	}

	private void setOnlineStatus(Long driverId, OnlineStatus onlineStatus) throws EntityNotFoundException, DriverAccountDeletedException 
	{
		
		DriverDO driverDO = findDriverChecked(driverId);
		driverDO.setOnlineStatus(onlineStatus);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return driverRepository.findOneByUsername(username);
	}
	

}

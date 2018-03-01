package com.mytaxi.service.car;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.mytaxi.dataaccessobject.CarManufacturerRepository;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarManufacturerDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverNotOwnerOfCarException;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some Car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService {
	
	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);
	
	private final CarManufacturerRepository carManufacturerRepository;
	
	private final CarRepository carRepository;
	
	public DefaultCarService(final CarManufacturerRepository carManufacturerRepository, final CarRepository carRepository) 
	{
		this.carManufacturerRepository = carManufacturerRepository;
		this.carRepository = carRepository;
	}

	@Override
	public List<CarManufacturerDO> getAllCarManufacturers() 
	{
		List<CarManufacturerDO> manufacturers = new ArrayList<>();
		carManufacturerRepository.findAll().forEach(manufacturers :: add);
		return manufacturers;
	}

	@Override
	public CarManufacturerDO createCarManufacturer(CarManufacturerDO carManufacturerDO) throws ConstraintsViolationException
	{
		CarManufacturerDO carManufacturer;
		
		try {
			carManufacturer = carManufacturerRepository.save(carManufacturerDO);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("Some constraints have been violated due to car manufacturer creation", e);
            throw new ConstraintsViolationException(e.getMessage());
		}
		
		return carManufacturer;
	}

	@Override
	@Transactional
	public synchronized CarDO selectCarForDriver(CarDO carDO) throws ConstraintsViolationException, CarAlreadyInUseException 
	{
		CarDO currentCar = findCarByLicensePlate(carDO.getLicensePlate());
		if (currentCar != null) {
			if (!currentCar.getDriverDO().getId().equals(carDO.getDriverDO().getId())) {
				LOG.warn("CarAlreadyInUseException for Driver : "+carDO.getDriverDO().getId());
				throw new CarAlreadyInUseException("Car is already in use by another driver");
			}
		} else {
			currentCar = carRepository.save(carDO);
		}
		setAllCarsNotCurrent(carDO.getDriverDO().getId());
		currentCar.setCurrent(true);
		return currentCar;
	}
	
	private void setAllCarsNotCurrent(Long driverId) {
		List<CarDO> cars = findByDriver(driverId);
		cars.forEach(car -> {
			car.setCurrent(false);
		});
	}
	
	@Override
	@Transactional
	public void deselectCarForDriver(CarDO carDO, Long driverId) throws DriverNotOwnerOfCarException, EntityNotFoundException 
	{

		if (!carDO.getDriverDO().getId().equals(driverId)) 
		{
			LOG.warn("DriverNotOwnerOfCarException for Driver : "+driverId);
			throw new DriverNotOwnerOfCarException("Unable to delete car for driver");
		}
		carDO.setCurrent(false);
	}

	@Override
	public CarManufacturerDO findCarMaunfacturer(Long manufacturerId) throws EntityNotFoundException {
		CarManufacturerDO carManufacturerDO = carManufacturerRepository.findOne(manufacturerId);
        if (carManufacturerDO == null)
        {
        	LOG.warn("EntityNotFoundException for car manufacturer with id : "+manufacturerId);
            throw new EntityNotFoundException("Could not find car manufacturer with id: " + manufacturerId);
        }
        return carManufacturerDO;
	}

	@Override
	public CarDO findCar(CarDO carDO) throws EntityNotFoundException 
	{
		return findCarChecked(carDO.getId());
	}
	
	private CarDO findCarByLicensePlate(String licensePlate) {
		return carRepository.findByLicensePlate(licensePlate);
	}
	
	
	 private CarDO findCarChecked(Long carId) throws EntityNotFoundException
	 {
        CarDO carDO = carRepository.findOne(carId);
        if (carDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + carId);
        }
        return carDO;
	 }

	@Override
	public List<CarDO> findByDriver(Long driverId) {
		return carRepository.findByDriverDO_Id(driverId);
	}

	@Override
	public CarDO findCar(Long carId) throws EntityNotFoundException {
		return findCarChecked(carId);
	}

	@Override
	public List<CarDO> getAllCars() {
		return Lists.newArrayList(carRepository.findAll());
	}
}

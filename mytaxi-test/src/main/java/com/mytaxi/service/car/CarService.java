package com.mytaxi.service.car;

import java.util.Collection;
import java.util.List;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarManufacturerDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverNotOwnerOfCarException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService {
	
	Collection<CarManufacturerDO> getAllCarManufacturers();

	CarManufacturerDO createCarManufacturer(CarManufacturerDO carManufacturerDO) throws ConstraintsViolationException;
	
	CarManufacturerDO findCarMaunfacturer(Long id) throws EntityNotFoundException;
	
	CarDO findCar(CarDO carDO) throws EntityNotFoundException;
	
	CarDO findCar(Long carId) throws EntityNotFoundException;
	
	CarDO selectCarForDriver(CarDO carDO) throws ConstraintsViolationException, CarAlreadyInUseException;

	void deselectCarForDriver(CarDO carDO, Long driverId) throws DriverNotOwnerOfCarException, EntityNotFoundException;
	
	List<CarDO> findByDriver(Long driverId);
	
	List<CarDO> getAllCars();

}

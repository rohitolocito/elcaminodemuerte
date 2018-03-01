package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.CarDO;

public interface CarRepository extends CrudRepository<CarDO, Long>{

	List<CarDO> findByDriverDO_Id(Long id);
	
	CarDO findByLicensePlate(String licensePlate);
}

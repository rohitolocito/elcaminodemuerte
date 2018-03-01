package com.mytaxi.controller.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;

public class CarMapper {
	
	public static CarDO makeCarDO(CarDTO carDTO) 
	{
		CarDO carDO = new CarDO(carDTO.getLicensePlate(), 
							carDTO.getSeatCount(), 
								DriverMapper.makeDriverDO(carDTO.getDriver()), 
									CarManufacturerMapper.makeCarManufacturerDO(carDTO.getManufacturer()));
		if (carDTO.getId() != null) 
		{
			carDO.setId(carDTO.getId());
		}
		
		return carDO;
	}
	
	public static CarDTO makeCarDTO(CarDO carDO) {
		CarDTO.CarDTOBuilder builder = CarDTO.newBuilder()
										.setId(carDO.getId())
										.setLicensePlate(carDO.getLicensePlate())
										.setCurrent(carDO.isCurrent())
										.setSeatCount(carDO.getSeatCount())
										.setManufacturer(CarManufacturerMapper.makeCarManufacturerDTO(carDO.getCarManufacturerDO()));
		
		return builder.createCarDTO();
	}
	
	public static Map<String, List<CarDTO>> makeDriverCarsMap(List<CarDO> cars) {
		return cars.stream()
				.collect(Collectors.groupingBy(car -> car.getDriverDO().getUsername(), Collectors.mapping(CarMapper::makeCarDTO, Collectors.toList())));
	}
}

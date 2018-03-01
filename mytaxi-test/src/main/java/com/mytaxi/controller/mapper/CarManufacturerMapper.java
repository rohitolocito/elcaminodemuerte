package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarManufacturerDTO;
import com.mytaxi.domainobject.CarManufacturerDO;

public class CarManufacturerMapper {
	
	public static CarManufacturerDO makeCarManufacturerDO(CarManufacturerDTO carManufacturerDTO) {
		CarManufacturerDO carManufacturerDO =  new CarManufacturerDO(carManufacturerDTO.getManufacturer(), carManufacturerDTO.getModel(), carManufacturerDTO.getType());
		if (carManufacturerDTO.getId() != null) {
			carManufacturerDO.setId(carManufacturerDTO.getId());
		}
		if (carManufacturerDTO.getEngineType() != null) {
			carManufacturerDO.setEngineType(carManufacturerDTO.getEngineType());
		}
		return carManufacturerDO;
	}
	
	public static Map<String, List<CarManufacturerDTO>> makeManufacturerDTOMap(Collection<CarManufacturerDO> carManufacturers) 
	{
		
		return carManufacturers
				.stream()
				.map(CarManufacturerMapper::makeCarManufacturerDTO)
				.collect(Collectors.groupingBy(CarManufacturerDTO::getManufacturer, Collectors.toList()));
		
	}
	
	public static CarManufacturerDTO makeCarManufacturerDTO(CarManufacturerDO carManufacturerDO) 
	{
		CarManufacturerDTO.CarManufacturerDTOBuilder carManufacturerDTOBuilder = CarManufacturerDTO
				.newBuilder()
				.setId(carManufacturerDO.getId())
				.setManufacturer(carManufacturerDO.getManufacturer())
				.setModel(carManufacturerDO.getModel())
				.setEngineType(carManufacturerDO.getEngineType())
				.setType(carManufacturerDO.getType());
		
		return carManufacturerDTOBuilder.createCarManufacturerDTO();
	}
 }

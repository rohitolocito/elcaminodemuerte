package com.mytaxi.service.search;

import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.CarEngineType;

public class EngineTypeSearchCriteria implements SearchCriteria {

	
	private CarEngineType carEngineType;
	
	public EngineTypeSearchCriteria(CarEngineType carEngineType) {
		this.carEngineType = carEngineType;
	}
	
	@Override
	public List<CarDO> meetsCriteria(List<CarDO> list) {
		if (carEngineType == null)
			return list;
		
		return list.stream()
				.filter(carDO-> carDO.getCarManufacturerDO().getEngineType().equals(carEngineType))
				.collect(Collectors.toList());
	}

}

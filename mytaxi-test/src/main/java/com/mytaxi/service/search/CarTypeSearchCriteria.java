package com.mytaxi.service.search;

import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.CarType;

public class CarTypeSearchCriteria implements SearchCriteria {
	
	private CarType carType;
	
	public CarTypeSearchCriteria(CarType carType) 
	{
		this.carType = carType;
	}

	@Override
	public List<CarDO> meetsCriteria(List<CarDO> list) 
	{
		if (carType == null)
			return list;
		
		return list.stream()
				.filter(carDO -> carDO.getCarManufacturerDO().getType().equals(carType))
				.collect(Collectors.toList());
	}

}

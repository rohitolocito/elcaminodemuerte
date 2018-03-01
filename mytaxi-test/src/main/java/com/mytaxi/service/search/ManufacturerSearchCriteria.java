package com.mytaxi.service.search;

import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.domainobject.CarDO;

public class ManufacturerSearchCriteria implements SearchCriteria {
	
	private String manufacturer;
	
	public ManufacturerSearchCriteria(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public List<CarDO> meetsCriteria(List<CarDO> list) {
		if (manufacturer == null) 
			return list;
		
		return list.stream()
				.filter(carDO -> carDO.getCarManufacturerDO().getManufacturer().equals(manufacturer))
				.collect(Collectors.toList());
	}

}

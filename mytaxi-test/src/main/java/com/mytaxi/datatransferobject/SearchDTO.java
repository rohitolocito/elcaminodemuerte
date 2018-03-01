package com.mytaxi.datatransferobject;

import com.mytaxi.domainvalue.CarEngineType;
import com.mytaxi.domainvalue.CarType;

public class SearchDTO {

	private CarEngineType engineType;
	
	private CarType carType;
	
	private String manufacturer;
	
	private boolean andCriteria = true;

	public CarEngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(CarEngineType engineType) {
		this.engineType = engineType;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public boolean isAndCriteria() {
		return andCriteria;
	}

	public void setAndCriteria(boolean andCriteria) {
		this.andCriteria = andCriteria;
	}

}

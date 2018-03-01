package com.mytaxi.domainvalue;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CarType 
{
	CONVERTIBLE, COUPE, ELECTRIC, HATCHBACK, HYBRID, LUXURY, SEDAN, SUV, WAGON;
	
	@Override
	public String toString() {
		return this.name();
	}
	
	@JsonCreator
	public static CarType create(String name) {
		name = name.toUpperCase();
		for (CarType type : CarType.values()) {
			if (name.equals(type.toString()))
				return type;
		}
		
		return null;
	}
}

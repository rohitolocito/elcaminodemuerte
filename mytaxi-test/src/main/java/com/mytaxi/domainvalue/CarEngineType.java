package com.mytaxi.domainvalue;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CarEngineType {
	GAS, ELECTRIC, HYBRID;
	
	@Override
	public String toString() {
		return this.name();
	}
	
	@JsonCreator
	public static CarEngineType create(String name) {
		name = name.toUpperCase();
		for (CarEngineType type : CarEngineType.values()) {
			if (name.equals(type.toString()))
				return type;
		}
		
		return null;
	}
}

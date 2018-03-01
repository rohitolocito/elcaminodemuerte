package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mytaxi.domainvalue.CarEngineType;
import com.mytaxi.domainvalue.CarType;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarManufacturerDTO {
	
	@ApiModelProperty(hidden=true)
	@JsonProperty(access=Access.READ_ONLY)
    private Long id;
	
	@ApiModelProperty(required=true)
    @NotNull(message = "Car Manufacturer can not be null!")
    private String manufacturer;
	
	@ApiModelProperty(required=true)
    @NotNull(message = "Car Model can not be null!")
    private String model;

	@ApiModelProperty(required=true)
    @NotNull(message = "Car type can not be null!")
	private CarType type;
	
	private CarEngineType engineType;
	
	private CarManufacturerDTO() 
	{
	}
	
	private CarManufacturerDTO(Long id, String manufacturer, String model, CarType type, CarEngineType engineType) 
	{
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.type = type;
		this.engineType = engineType;
	}
	
	
	public Long getId() 
	{
		return id;
	}

	public String getManufacturer() 
	{
		return manufacturer;
	}

	public String getModel() 
	{
		return model;
	}

	public CarType getType() 
	{
		return type;
	}
	
	public CarEngineType getEngineType() 
	{
		return engineType;
	}
	
	public static CarManufacturerDTOBuilder newBuilder() 
	{
		return new CarManufacturerDTOBuilder();
	}
	
	public static class CarManufacturerDTOBuilder 
	{
		
		private Long id;
		private String manufacturer;
		private String model;
		private CarType type;
		private CarEngineType engineType;
		
		
		public CarManufacturerDTOBuilder setId(Long id) 
		{
			this.id = id;
			return this;
		}


		public CarManufacturerDTOBuilder setManufacturer(String manufacturer) 
		{
			this.manufacturer = manufacturer;
			return this;
		}


		public CarManufacturerDTOBuilder setModel(String model) 
		{
			this.model = model;
			return this;
		}


		public CarManufacturerDTOBuilder setType(CarType type) 
		{
			this.type = type;
			return this;
		}
		
		public CarManufacturerDTOBuilder setEngineType(CarEngineType engineType) 
		{
			this.engineType = engineType;
			return this;
		}
		
		public CarManufacturerDTO createCarManufacturerDTO() 
		{
			return new CarManufacturerDTO(id, manufacturer, model, type, engineType);
		}
 
	}
}

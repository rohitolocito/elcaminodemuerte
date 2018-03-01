package com.mytaxi.datatransferobject;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
	
	@ApiModelProperty(hidden=true)
	@JsonProperty(access=Access.READ_ONLY)
    private Long id;
	
	@ApiModelProperty(hidden=true)
	@JsonProperty(access=Access.READ_ONLY)
	private DriverDTO driver;
	
	@ApiModelProperty(hidden=true)
	@JsonProperty(access=Access.READ_ONLY)
	private CarManufacturerDTO manufacturer;
	
	@ApiModelProperty(required=true)
	@NotNull(message = "Car license plate can not be null!")
	private String licensePlate;
	
	@ApiModelProperty(required=true)
	@NotNull(message = "Car Manufacturer Id can not be null!")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Long carManufacturerId;

	@Max(8)
	private int seatCount;
	
	@ApiModelProperty(hidden=true)
	@JsonProperty(access=Access.READ_ONLY)
	private boolean current;
	
	
	private CarDTO() 
	{
	}

	private CarDTO(Long id, String licensePlate, int seatCount, CarManufacturerDTO manufacturer, boolean current) {
		this.id = id;
		this.licensePlate = licensePlate;
		this.seatCount = seatCount;
		this.manufacturer = manufacturer;
		this.current = current;
	}
	
	public static CarDTOBuilder newBuilder()
	{
        return new CarDTOBuilder();
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public DriverDTO getDriver() 
	{
		return driver;
	}

	@JsonIgnore
	public void setDriver(DriverDTO driver) 
	{
		this.driver = driver;
	}

	public CarManufacturerDTO getManufacturer() 
	{
		return manufacturer;
	}

	@JsonIgnore
	public void setManufacturer(CarManufacturerDTO manufacturer) 
	{
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() 
	{
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) 
	{
		this.licensePlate = licensePlate;
	}

	public int getSeatCount() 
	{
		return seatCount;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	
	public void setCarManufacturerId(Long carManufacturerId) {
		this.carManufacturerId = carManufacturerId;
	}
	
	@JsonIgnore
	public Long getCarManufacturerId() {
		return carManufacturerId;
	}

	public static class CarDTOBuilder {
		private CarManufacturerDTO manufacturer;
		private String licensePlate;
		private int seatCount;
		private Long id; 
		private boolean current;
		
		public CarDTOBuilder setManufacturer(CarManufacturerDTO carManufacturerDTO) {
			this.manufacturer = carManufacturerDTO;
			return this;
		}
		
		public CarDTOBuilder setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
			return this;
		}
		
		public CarDTOBuilder setSeatCount(int seatCount) {
			this.seatCount = seatCount;
			return this;
		}
		
		public CarDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public CarDTOBuilder setCurrent(boolean current) {
			this.current = current;
			return this;
		}
		
		public CarDTO createCarDTO() {
			return new CarDTO(id, licensePlate, seatCount, manufacturer, current);
		}
		
	}
	

}

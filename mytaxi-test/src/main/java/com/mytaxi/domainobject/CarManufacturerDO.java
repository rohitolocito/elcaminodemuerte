package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.mytaxi.domainvalue.CarEngineType;
import com.mytaxi.domainvalue.CarType;

@Entity
@Table(
    name = "carmanufacturer",
    uniqueConstraints = @UniqueConstraint(name = "uc_manufacturer", columnNames = {"manufacturer", "model"})
)
public class CarManufacturerDO {
	
    @Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
    @NotNull(message = "Car Manufacturer can not be null!")
    private String manufacturer;
	
	@Column(nullable = false)
    @NotNull(message = "Car Model can not be null!")
    private String model;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull(message = "Car Type is null or unknown!")
	private CarType type;
	
	@Column(columnDefinition = "int default 6")
	private int maxPassengersAllowed;
	
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    
    @Enumerated(EnumType.STRING)
    @Column(name= "engine_type", nullable = false, columnDefinition="varchar(32) default 'GAS'")
    private CarEngineType engineType = CarEngineType.GAS;
    
    public CarManufacturerDO() 
    {
    	
    }
    
    public CarManufacturerDO(String manufacturer, String model, CarType type) 
    {
		this.manufacturer = manufacturer;
		this.model = model;
		this.type = type;
	}
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getManufacturer() 
	{
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) 
	{
		this.manufacturer = manufacturer;
	}

	public String getModel() 
	{
		return model;
	}

	public void setModel(String model) 
	{
		this.model = model;
	}

	public CarType getType() 
	{
		return type;
	}

	public void setType(CarType type) 
	{
		this.type = type;
	}

	public int getMaxPassengersAllowed() 
	{
		return maxPassengersAllowed;
	}

	public void setMaxPassengersAllowed(int maxPassengersAllowed) 
	{
		this.maxPassengersAllowed = maxPassengersAllowed;
	}

	public ZonedDateTime getDateCreated() 
	{
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) 
	{
		this.dateCreated = dateCreated;
	}

	public CarEngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(CarEngineType engineType) {
		this.engineType = engineType;
	}	
	
}

package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_car", columnNames = {"license_plate"})
)
public class CarDO {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(name="license_plate", nullable=false)
	private String licensePlate;

	@Column(name="seat_count", columnDefinition="tinyint(1) default 4")
	private Integer seatCount;

	@ManyToOne
	@JoinColumn(name = "driver_id", referencedColumnName= "id", nullable=false)
	private DriverDO driverDO;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id", referencedColumnName= "id", nullable=false)
	private CarManufacturerDO carManufacturerDO;
	
	@Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
	
	@Column(columnDefinition="boolean default false")
	private boolean current=false;
	
	public CarDO() 
	{
	}
	
	public CarDO(String licensePlate, Integer seatCount, DriverDO driverDO, CarManufacturerDO carManufacturerDO) 
	{
		this.licensePlate = licensePlate;
		this.seatCount = seatCount;
		this.driverDO = driverDO;
		this.carManufacturerDO = carManufacturerDO;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getLicensePlate() 
	{
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) 
	{
		this.licensePlate = licensePlate;
	}

	public CarManufacturerDO getCarManufacturerDO() 
	{
		return carManufacturerDO;
	}

	public void setCarManufacturerDO(CarManufacturerDO carManufacturerDO) 
	{
		this.carManufacturerDO = carManufacturerDO;
	}

	public int getSeatCount() 
	{
		return seatCount;
	}

	public void setSeatCount(int seatCount) 
	{
		this.seatCount = seatCount;
	}

	public DriverDO getDriverDO() 
	{
		return driverDO;
	}

	public void setDriverDO(DriverDO driverDO) 
	{
		this.driverDO = driverDO;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

}

package com.mytaxi.controller;


import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.CarManufacturerMapper;
import com.mytaxi.datatransferobject.CarManufacturerDTO;
import com.mytaxi.domainobject.CarManufacturerDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;

/**
 * All operations associated with a car will be routed by this controller.
 * 
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {
	
	private final CarService carService;
	
	@Autowired
	public CarController(CarService carService) 
	{
		this.carService = carService;
	}
	
	@GetMapping("/manufacturers")
    public Map<String, List<CarManufacturerDTO>> getCarManufacturers() throws EntityNotFoundException
    {
		return CarManufacturerMapper.makeManufacturerDTOMap(carService.getAllCarManufacturers());
    }
	
	@PostMapping("/manufacturer")
	@ResponseStatus(HttpStatus.CREATED)
	public CarManufacturerDTO createCarManufacturer(@Valid @RequestBody CarManufacturerDTO carManufacturerDTO) throws ConstraintsViolationException {
		CarManufacturerDO carManufacturerDO = carService.createCarManufacturer(CarManufacturerMapper.makeCarManufacturerDO(carManufacturerDTO)); 
		return CarManufacturerMapper.makeCarManufacturerDTO(carManufacturerDO);
	}
}

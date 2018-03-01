package com.mytaxi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.mytaxi.controller.mapper.CarManufacturerMapper;
import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.CarManufacturerDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.datatransferobject.SearchDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverAccountDeletedException;
import com.mytaxi.exception.DriverNotOnExpectedOnlineStatusException;
import com.mytaxi.exception.DriverNotOwnerOfCarException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.UserNotOwnerOfResourceException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverAuthorizationService;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.service.search.AndSearchCriteria;
import com.mytaxi.service.search.CarTypeSearchCriteria;
import com.mytaxi.service.search.EngineTypeSearchCriteria;
import com.mytaxi.service.search.ManufacturerSearchCriteria;
import com.mytaxi.service.search.OnlineDriverSearchCriteria;
import com.mytaxi.service.search.OrSearchCriteria;
import com.mytaxi.service.search.SearchCriteria;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;
    
    private final CarService carService;
    
    private final DriverAuthorizationService driverAuthorizationService;


    @Autowired
    public DriverController(final DriverService driverService, final CarService carService, DriverAuthorizationService driverAuthorizationService)
    {
        this.driverService = driverService;
        this.carService = carService;
        this.driverAuthorizationService = driverAuthorizationService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId, Principal principal) throws EntityNotFoundException, DriverAccountDeletedException, UserNotOwnerOfResourceException
    {    
    	DriverDO driverDO = driverService.find(driverId);
    	driverAuthorizationService.authorize(driverDO, principal);
        return DriverMapper.makeDriverDTO(driverDO);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId, Principal principal) throws EntityNotFoundException, DriverAccountDeletedException, UserNotOwnerOfResourceException
    {
    	DriverDO driverDO = driverService.find(driverId);
    	driverAuthorizationService.authorize(driverDO, principal);
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude, Principal principal)
        throws ConstraintsViolationException, EntityNotFoundException, DriverAccountDeletedException, UserNotOwnerOfResourceException
    {
    	DriverDO driverDO = driverService.find(driverId);
    	driverAuthorizationService.authorize(driverDO, principal);
        driverService.updateLocation(driverId, longitude, latitude);
    }
    
    @ApiOperation(value="Activates the driver account", notes="This is meant to be called after all the driver documents have been verified. Only Admin can active the driver account. All ADMIN role APIs can only be accessed from 127.0.0.1:8080 ")
    @PutMapping("/{driverId}/activate")
    public void activateDriver(@Valid @PathVariable long driverId) throws ConstraintsViolationException, EntityNotFoundException, DriverAccountDeletedException
    {
        driverService.activateDriver(driverId);
    }
    
    @ApiOperation(value="Deactivates the driver account", notes="This is meant to be called after the driver has consistently got low ratings. Only Admin can deactivate the driver. All ADMIN role APIs can only be accessed from 127.0.0.1:8080")
    @PutMapping("/{driverId}/deactivate")
    public void deactivateDriver(@Valid @PathVariable long driverId) throws ConstraintsViolationException, EntityNotFoundException, DriverAccountDeletedException
    {
        driverService.deactivateDriver(driverId);
    }

    @ApiOperation(value="Returns all the ONLINE drivers", notes="Only Admin can access this API. All ADMIN role APIs can only be accessed from 127.0.0.1:8080.")
    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }
    
    @PostMapping("/{driverId}/car")
    @ResponseStatus(HttpStatus.CREATED)
    public void selectCar(@Valid @PathVariable long driverId, @RequestBody CarDTO carDTO, Principal principal) throws EntityNotFoundException, ConstraintsViolationException, 
    		CarAlreadyInUseException, DriverNotOnExpectedOnlineStatusException, 
    		DriverAccountDeletedException, UserNotOwnerOfResourceException 
    {
    	DriverDO driverDO = driverService.find(driverId);
    	driverAuthorizationService.authorize(driverDO, principal);
    	driverService.verify(driverDO, OnlineStatus.ONLINE); 
    	DriverDTO driverDTO = DriverMapper.makeDriverDTO(driverDO);
    	CarManufacturerDTO carManufacturerDTO = CarManufacturerMapper.makeCarManufacturerDTO(carService.findCarMaunfacturer(carDTO.getCarManufacturerId()));
    	carDTO.setDriver(driverDTO);
    	carDTO.setManufacturer(carManufacturerDTO);
    	carService.selectCarForDriver(CarMapper.makeCarDO(carDTO));
    }
    
    @DeleteMapping("/{driverId}/cars/{carId}")
    public void  deselectCar(@Valid @PathVariable long driverId, @PathVariable long carId, Principal principal) throws EntityNotFoundException, ConstraintsViolationException, 
    		DriverNotOwnerOfCarException, DriverNotOnExpectedOnlineStatusException, 
    		DriverAccountDeletedException, UserNotOwnerOfResourceException 
    {
    	DriverDO driverDO = driverService.find(driverId);
    	driverAuthorizationService.authorize(driverDO, principal);
    	driverService.verify(driverDO, OnlineStatus.ONLINE); 
    	carService.deselectCarForDriver(carService.findCar(carId), driverId);
    }
    
    @ApiOperation(value="Returns all the ONLINE drivers that match the search criteria", notes="Only Admin can access this API. All ADMIN role APIs can only be accessed from 127.0.0.1:8080.")
    @GetMapping("/online")
    public Map<String, List<CarDTO>> searchOnlineDrivers(SearchDTO searchDTO) {
    	
    	List<CarDO> cars = carService.getAllCars();
    	
    	SearchCriteria engineTypeCriteria = new EngineTypeSearchCriteria(searchDTO.getEngineType());
    	SearchCriteria carTypeCriteria = new CarTypeSearchCriteria(searchDTO.getCarType());
    	SearchCriteria manufacturerCriteria = new ManufacturerSearchCriteria(searchDTO.getManufacturer());
    	SearchCriteria onlineDriverCriteria = new OnlineDriverSearchCriteria(OnlineStatus.ONLINE); 
    	
    	List<CarDO> result ;
    	
    	if (searchDTO.isAndCriteria()) {
    		result = new AndSearchCriteria(Lists.newArrayList(engineTypeCriteria, carTypeCriteria, manufacturerCriteria, onlineDriverCriteria)).meetsCriteria(cars);
    	} else {
    		result = new OrSearchCriteria(Lists.newArrayList(engineTypeCriteria, carTypeCriteria, manufacturerCriteria, onlineDriverCriteria)).meetsCriteria(cars);
    	}
    	
    	return CarMapper.makeDriverCarsMap(result);
    }
    
    @ApiOperation(value="Returns all the OFFLNE drivers that match the search criteria", notes="Only Admin can access this API. All ADMIN role APIs can only be accessed from 127.0.0.1:8080.")
    @GetMapping("/offline")
    public Map<String, List<CarDTO>> searchOfflineDrivers(SearchDTO searchDTO) {
    	
    	List<CarDO> cars = carService.getAllCars();
    	
    	SearchCriteria engineTypeCriteria = new EngineTypeSearchCriteria(searchDTO.getEngineType());
    	SearchCriteria carTypeCriteria = new CarTypeSearchCriteria(searchDTO.getCarType());
    	SearchCriteria manufacturerCriteria = new ManufacturerSearchCriteria(searchDTO.getManufacturer());
    	SearchCriteria onlineDriverCriteria = new OnlineDriverSearchCriteria(OnlineStatus.OFFLINE); 
    	
    	List<CarDO> result ;
    	
    	if (searchDTO.isAndCriteria()) {
    		result = new AndSearchCriteria(Lists.newArrayList(engineTypeCriteria, carTypeCriteria, manufacturerCriteria, onlineDriverCriteria)).meetsCriteria(cars);
    	} else {
    		result = new OrSearchCriteria(Lists.newArrayList(engineTypeCriteria, carTypeCriteria, manufacturerCriteria, onlineDriverCriteria)).meetsCriteria(cars);
    	}
    	
    	return CarMapper.makeDriverCarsMap(result);
    }
}

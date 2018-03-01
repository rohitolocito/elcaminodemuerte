package com.mytaxi.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.common.collect.Lists;
import com.mytaxi.TestHelper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarManufacturerDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.CarEngineType;
import com.mytaxi.domainvalue.CarType;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.UserNotOwnerOfResourceException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverAuthorizationService;
import com.mytaxi.service.driver.DriverService;

@RunWith(MockitoJUnitRunner.class)
public class DriverControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private DriverService driverServiceMock;
	
	@Mock
	private CarService carServiceMock;
	
	@Mock
	private DriverAuthorizationService driverAuthorizationService;
	
	@InjectMocks
	private DriverController driverController;
	
	@Before
	public void setup() 
	{
		mockMvc = MockMvcBuilders.standaloneSetup(driverController).build();
	}
	
	@Test
	public void test_getDriver() throws Exception {
		
		DriverDO driver = new DriverDO("driver007", "driver007pw");
		
		doNothing().when(driverAuthorizationService).authorize(any(DriverDO.class), any());
		
		when(driverServiceMock.find(7L)).thenReturn(driver);
		
		mockMvc.perform(get("/v1/drivers/7"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.username", is("driver007")))
		   .andExpect(jsonPath("$.cars.size()", is(0)));
	
		verify(driverServiceMock, times(1)).find(7L);
		verifyNoMoreInteractions(driverServiceMock);
	}
	
	@Test
	public void test_getDriver_illegalAccess() throws Exception {
		
		doThrow(UserNotOwnerOfResourceException.class).when(driverAuthorizationService).authorize(any(DriverDO.class), any());

		when(driverServiceMock.find(7L)).thenReturn(new DriverDO("driver007", "driver007pw"));
		
		mockMvc.perform(get("/v1/drivers/7"))
		   .andExpect(status().isForbidden());

		verify(driverServiceMock, times(1)).find(7L);
		verifyNoMoreInteractions(driverServiceMock);
	}
	
	@Test
	public void test_createDriver() throws Exception {
				
		DriverDO driverDO = new DriverDO("driver008", "driver008pw");
		driverDO.setId(8L);
		driverDO.setDeleted(false);

		when(driverServiceMock.create(any(DriverDO.class))).thenReturn(driverDO);
		
		mockMvc.perform(post("/v1/drivers")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"driver008\",\"password\":\"driver008pw\"}"))
			   .andExpect(status().isCreated());

		verify(driverServiceMock, times(1)).create(any(DriverDO.class));
		verifyNoMoreInteractions(driverServiceMock);
	}
	
	@Test
	public void test_deleteDriver() throws Exception {
		
		DriverDO driverDO = new DriverDO("driver008", "driver008pw");
		driverDO.setId(8L);
		driverDO.setDeleted(false);
		
		doNothing().when(driverAuthorizationService).authorize(any(DriverDO.class), any());

		when(driverServiceMock.find(8L)).thenReturn(driverDO);
		
		mockMvc.perform(delete("/v1/drivers/{id}", "8")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());

		verify(driverServiceMock, times(1)).delete(8L);
	}
	
	@Test
	public void test_updateLocation() throws Exception {
		DriverDO driverDO = new DriverDO("driver008", "driver008pw");
		driverDO.setId(8L);
		driverDO.setDeleted(false);
		
		doNothing().when(driverAuthorizationService).authorize(any(DriverDO.class), any());

		when(driverServiceMock.find(8L)).thenReturn(driverDO);
		
		mockMvc.perform(put("/v1/drivers/{id}", "8")
				.param("longitude", "22.3")
				.param("latitude", "72.5")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());

		verify(driverServiceMock, times(1)).updateLocation(8L, 22.3, 72.5);
	}
	
	@Test
	public void test_activateDriver() throws Exception {
		
		doNothing().when(driverServiceMock).activateDriver(8L);
		
		mockMvc.perform(put("/v1/drivers/{id}/activate", "8")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
		
		verify(driverServiceMock, times(1)).activateDriver(8L);
	}
	
	
	@Test
	public void test_deactivateDriver() throws Exception {
		
		doNothing().when(driverServiceMock).activateDriver(8L);
		
		mockMvc.perform(put("/v1/drivers/{id}/deactivate", "8")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
		
		verify(driverServiceMock, times(1)).deactivateDriver(8L);
	}
	
	@Test
	public void test_findDrivers() throws Exception {
		
		DriverDO online = new DriverDO("driver008", "driver008pw");
		online.setId(8L);
		online.setOnlineStatus(OnlineStatus.ONLINE);
		online.setDeleted(false);
		
		DriverDO online1 = new DriverDO("driver007", "driver007pw");
		online1.setId(7L);
		online1.setOnlineStatus(OnlineStatus.ONLINE);
		online1.setDeleted(false);
		
		List<DriverDO> drivers = Lists.newArrayList(online, online1);
		
		when(driverServiceMock.find(OnlineStatus.ONLINE)).thenReturn(drivers);
		
		mockMvc.perform(get("/v1/drivers")
				.param("onlineStatus", "ONLINE"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.size()", is(2)))
		   .andExpect(jsonPath("$.[0].username", is("driver008")));
	
		verify(driverServiceMock, times(1)).find(OnlineStatus.ONLINE);
		verifyNoMoreInteractions(driverServiceMock);
		
	}
	
	@Test
	public void test_selectCar() throws Exception {
		
		CarDTO carDTO = CarDTO.newBuilder()
				.setLicensePlate("AP-1234")
				.setSeatCount(4)
				.createCarDTO();
		
		carDTO.setCarManufacturerId(1L);
		
		DriverDO driverDO = new DriverDO("driver008", "driver008pw");
		driverDO.setId(8L);
		driverDO.setOnlineStatus(OnlineStatus.ONLINE);
		driverDO.setDeleted(false);
		
		CarManufacturerDO carManufacturerDO = new CarManufacturerDO("Acura", "TSX", CarType.CONVERTIBLE);
		carManufacturerDO.setId(1L);
		
		doNothing().when(driverAuthorizationService).authorize(any(DriverDO.class), any());
		
		when(driverServiceMock.find(8L)).thenReturn(driverDO);
		
		when(carServiceMock.findCarMaunfacturer(any(Long.class))).thenReturn(carManufacturerDO);
		
		when(carServiceMock.selectCarForDriver(any(CarDO.class))).thenReturn(new CarDO());
		
		mockMvc.perform(post("/v1/drivers/{id}/car", 8L)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestHelper.stringify(carDTO)))
			   .andExpect(status().isCreated());
		
		verify(carServiceMock, times(1)).selectCarForDriver(any(CarDO.class));
		
	}
	
	
	@Test
	public void test_selectCar_CarAlreadyInUse() throws Exception {
		
		CarDTO carDTO = CarDTO.newBuilder()
				.setLicensePlate("AP-1234")
				.setSeatCount(4)
				.createCarDTO();
		
		carDTO.setCarManufacturerId(1L);
		
		DriverDO driverDO = new DriverDO("driver008", "driver008pw");
		driverDO.setId(8L);
		driverDO.setOnlineStatus(OnlineStatus.ONLINE);
		driverDO.setDeleted(false);
		
		CarManufacturerDO carManufacturerDO = new CarManufacturerDO("Acura", "TSX", CarType.CONVERTIBLE);
		carManufacturerDO.setId(1L);
		
		doNothing().when(driverAuthorizationService).authorize(any(DriverDO.class), any());
		
		when(driverServiceMock.find(8L)).thenReturn(driverDO);
		
		when(carServiceMock.findCarMaunfacturer(any(Long.class))).thenReturn(carManufacturerDO);
		
		doThrow(CarAlreadyInUseException.class).when(carServiceMock).selectCarForDriver(any(CarDO.class));
		
		mockMvc.perform(post("/v1/drivers/{id}/car", 8L)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestHelper.stringify(carDTO)))
			   .andExpect(status().isBadRequest());
		
		verify(carServiceMock, times(1)).selectCarForDriver(any(CarDO.class));
		
	}
	
	@Test
	public void test_deselectCar() throws Exception {
		
		DriverDO driverDO = new DriverDO("driver008", "driver008pw");
		driverDO.setId(8L);
		driverDO.setOnlineStatus(OnlineStatus.ONLINE);
		driverDO.setDeleted(false);
		
		CarDO carDO = new CarDO("US NY 007", 2, driverDO, new CarManufacturerDO());
		
		doNothing().when(driverAuthorizationService).authorize(any(DriverDO.class), any());

		when(driverServiceMock.find(8L)).thenReturn(driverDO);
		
		when(carServiceMock.findCar(1L)).thenReturn(carDO);
		
		mockMvc.perform(delete("/v1/drivers/{driverId}/cars/{carId}", "8", "1")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());

		verify(carServiceMock, times(1)).deselectCarForDriver(any(CarDO.class), any(Long.class));
	}
	
	@Test
	public void test_searchOnlineDrivers() throws Exception {
		
		CarManufacturerDO carManufacturerDO = new CarManufacturerDO("Acura", "TSX", CarType.SEDAN);
		
		CarManufacturerDO carManufacturerDO1 = new CarManufacturerDO("Tesla", "Model S", CarType.ELECTRIC);
		
		CarManufacturerDO carManufacturerDO2 = new CarManufacturerDO("Toyota", "Prius", CarType.ELECTRIC);
		
		DriverDO driverDO = new DriverDO("driver008", "driver008pw");
		driverDO.setId(8L);
		driverDO.setOnlineStatus(OnlineStatus.ONLINE);
		driverDO.setDeleted(false);
		
		DriverDO driverDO1 = new DriverDO("driver007", "driver007pw");
		driverDO1.setId(7L);
		driverDO1.setOnlineStatus(OnlineStatus.ONLINE);
		driverDO1.setDeleted(false);
		
		CarDO carDO = new CarDO("US NY 007", 2, driverDO1, carManufacturerDO);
		
		CarDO carDO1 = new CarDO("US NY 008", 2, driverDO, carManufacturerDO1);
		
		CarDO carDO2 = new CarDO("US NY 009", 2, driverDO1, carManufacturerDO2);
		
		when(carServiceMock.getAllCars()).thenReturn(Lists.newArrayList(carDO, carDO1, carDO2));
		
		mockMvc.perform(get("/v1/drivers/online")
				.param("type", "ELECTRIC")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.size()", is(2)));
		
		
	}
	
	@Test
	public void test_searchOnlineDrivers_AND() throws Exception {
		
		CarManufacturerDO carManufacturerDO = new CarManufacturerDO("Acura", "TSX", CarType.SEDAN);
		
		CarManufacturerDO carManufacturerDO1 = new CarManufacturerDO("Tesla", "Model S", CarType.ELECTRIC);
		
		CarManufacturerDO carManufacturerDO2 = new CarManufacturerDO("Toyota", "Prius", CarType.ELECTRIC);
		carManufacturerDO2.setEngineType(CarEngineType.HYBRID);
		
		DriverDO driverDO = new DriverDO("driver008", "driver008pw");
		driverDO.setId(8L);
		driverDO.setOnlineStatus(OnlineStatus.ONLINE);
		driverDO.setDeleted(false);
		
		DriverDO driverDO1 = new DriverDO("driver007", "driver007pw");
		driverDO1.setId(7L);
		driverDO1.setOnlineStatus(OnlineStatus.ONLINE);
		driverDO1.setDeleted(false);
		
		CarDO carDO = new CarDO("US NY 007", 2, driverDO1, carManufacturerDO);
		
		CarDO carDO1 = new CarDO("US NY 008", 2, driverDO, carManufacturerDO1);
		
		CarDO carDO2 = new CarDO("US NY 009", 2, driverDO1, carManufacturerDO2);
		
		when(carServiceMock.getAllCars()).thenReturn(Lists.newArrayList(carDO, carDO1, carDO2));
		
		mockMvc.perform(get("/v1/drivers/online")
				.param("type", "ELECTRIC")
				.param("engineType", "HYBRID")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.size()", is(1)));
		
		
	}
}

package com.mytaxi.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.common.collect.Lists;
import com.mytaxi.TestHelper;
import com.mytaxi.controller.mapper.CarManufacturerMapper;
import com.mytaxi.datatransferobject.CarManufacturerDTO;
import com.mytaxi.domainobject.CarManufacturerDO;
import com.mytaxi.domainvalue.CarType;
import com.mytaxi.service.car.CarService;


@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private CarService carServiceMock;
	
	@InjectMocks
	private CarController carController;
	
	@Before
	public void setup() 
	{
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
	}
	
	@Test
	public void test_getCarManufacturers() throws Exception 
	{
		
		List<CarManufacturerDO> manufacturers = Lists.newArrayList(
					new CarManufacturerDO("Acura", "MDX", CarType.SEDAN),
					new CarManufacturerDO("Acura", "TSX", CarType.LUXURY),
					new CarManufacturerDO("Aston Martin", "Vanquish S", CarType.CONVERTIBLE)
				);
		
		when(carServiceMock.getAllCarManufacturers()).thenReturn(manufacturers);
		
		mockMvc.perform(get("/v1/cars/manufacturers"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			   .andExpect(jsonPath("$.size()", is(2)))
			   .andExpect(jsonPath("$.Acura.size()", is(2)));
		
		verify(carServiceMock, times(1)).getAllCarManufacturers();
		verifyNoMoreInteractions(carServiceMock);
	}
	
	@Test
	public void test_createCarManufacturer() throws Exception 
	{
		CarManufacturerDTO carManufacturerDTO = CarManufacturerDTO
													.newBuilder()
													.setManufacturer("Tesla")
													.setModel("Model S")
													.setType(CarType.COUPE)
													.createCarManufacturerDTO();
		
		CarManufacturerDO carManufacturerDO = CarManufacturerMapper.makeCarManufacturerDO(carManufacturerDTO);
		carManufacturerDO.setId(1L);
		
		when(carServiceMock.createCarManufacturer(Mockito.any(CarManufacturerDO.class))).thenReturn(carManufacturerDO);
		
		mockMvc.perform(post("/v1/cars/manufacturer")
							.accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON)
							.content(TestHelper.stringify(carManufacturerDTO)))
			   .andExpect(status().isCreated())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			   .andExpect(jsonPath("$.id", is(1)));
		
	}

}

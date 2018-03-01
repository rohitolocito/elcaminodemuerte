package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;

public class DriverMapper
{
	
	public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
    	DriverDO driverDO = new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
    	if (driverDTO.getId() != null) {
    		driverDO.setId(driverDTO.getId());
    	}
    	return driverDO;
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driverDO.getId())
            .setPassword(driverDO.getPassword())
            .setDeleted(driverDO.isDeleted())
            .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }
        
        List<CarDTO> list = driverDO.getCars()
						        		.stream()
						        		.map(CarMapper::makeCarDTO)
						        		.collect(Collectors.toList());

        driverDTOBuilder.setCars(list);

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
}

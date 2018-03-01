package com.mytaxi.datatransferobject;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mytaxi.domainvalue.GeoCoordinate;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO
{
	@ApiModelProperty(hidden=true)
    @JsonIgnore
    private Long id;

    @NotNull(message = "Username can not be null!")
    private String username;

    @JsonProperty(access=Access.WRITE_ONLY)
    @NotNull(message = "Password can not be null!")
    private String password;

    private GeoCoordinate coordinate;
    
    @ApiModelProperty(hidden=true)
    @JsonProperty(access=Access.READ_ONLY)
    private List<CarDTO> cars;
    
    @ApiModelProperty(hidden=true)
    @JsonProperty(access=Access.READ_ONLY)
    private boolean deleted;


    private DriverDTO()
    {
    }


    private DriverDTO(Long id, String username, String password, GeoCoordinate coordinate, List<CarDTO> cars, boolean deleted)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.cars = cars;
        this.deleted = deleted;
    }


    public static DriverDTOBuilder newBuilder()
    {
        return new DriverDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }
    
    public List<CarDTO> getCars() 
    {
    	return cars;
    }
    
    public boolean getDeleted()
    {
    	return deleted;
    }

    public static class DriverDTOBuilder
    {
        private Long id;
        private String username;
        private String password;
        private GeoCoordinate coordinate;
        private List<CarDTO> cars;
        private boolean deleted;


        public DriverDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public DriverDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public DriverDTOBuilder setPassword(String password)
        {
            this.password = password;
            return this;
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }
        
        public DriverDTOBuilder setCars(List<CarDTO> cars) {
        	this.cars = cars;
        	return this;
        }
        
        public DriverDTOBuilder setDeleted(boolean deleted) {
        	this.deleted = deleted;
        	return this;
        }


        public DriverDTO createDriverDTO()
        {
            return new DriverDTO(id, username, password, coordinate, cars, deleted);
        }

    }
}

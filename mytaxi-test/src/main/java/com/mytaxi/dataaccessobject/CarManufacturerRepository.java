package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.CarManufacturerDO;

public interface CarManufacturerRepository  extends CrudRepository<CarManufacturerDO, Long>
{

}

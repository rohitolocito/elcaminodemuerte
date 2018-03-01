package com.mytaxi.service.search;

import java.util.List;

import com.mytaxi.domainobject.CarDO;

public interface SearchCriteria {

	List<CarDO> meetsCriteria(List<CarDO> list);
}

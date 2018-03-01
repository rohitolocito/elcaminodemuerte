package com.mytaxi.service.search;

import java.util.List;

import com.mytaxi.domainobject.CarDO;

public class AndSearchCriteria implements SearchCriteria {
	
	private List<SearchCriteria> criterias;
	
	public AndSearchCriteria(List<SearchCriteria> criterias) 
	{
		this.criterias = criterias;
	}

	@Override
	public List<CarDO> meetsCriteria(List<CarDO> list) {
		if (criterias == null) 
			return list;
		
		for(SearchCriteria criteria : criterias) {
			list = criteria.meetsCriteria(list);
		}
		
		return list;
	}

}

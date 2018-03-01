package com.mytaxi.service.search;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.mytaxi.domainobject.CarDO;

public class OrSearchCriteria implements SearchCriteria {
	
	private List<SearchCriteria> criterias;
	
	public OrSearchCriteria(List<SearchCriteria> criterias) {
		this.criterias = criterias;
	}

	@Override
	public List<CarDO> meetsCriteria(List<CarDO> list) {
		if (criterias == null)
			return list;
		
		Set<CarDO> set = new HashSet<>();
		
		for (SearchCriteria criteria : criterias) {
			set.addAll(criteria.meetsCriteria(list));
		}
		
		return Lists.newArrayList(set);
	}

}

package com.mytaxi.service.search;

import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.OnlineStatus;

public class OnlineDriverSearchCriteria implements SearchCriteria {
	
	private OnlineStatus online;
	
	public OnlineDriverSearchCriteria(OnlineStatus online) {
		this.online = online;
	}

	@Override
	public List<CarDO> meetsCriteria(List<CarDO> list) {
		
		return list.stream()
				.filter(carDO -> carDO.getDriverDO().getOnlineStatus().equals(online))
				.collect(Collectors.toList());
		
	}

}

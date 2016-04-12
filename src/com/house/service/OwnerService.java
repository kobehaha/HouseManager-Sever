package com.house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.beans.House;
import com.house.beans.Owner;
import com.house.persistence.OwnerMapper;

@Service
public class OwnerService {

	@Autowired
	private OwnerMapper ownerMapper;

	public boolean insertOwner(Owner owner) {
		boolean flag = true;
		try {
			ownerMapper.addOwner(owner);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public Owner getOwnerInfo(House house) {
		Owner owner = null;
		try {
			owner = ownerMapper.getOwnerByHouseId(house);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return owner;

	}


}

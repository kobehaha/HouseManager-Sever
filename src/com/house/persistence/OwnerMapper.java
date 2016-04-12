package com.house.persistence;

import java.util.Map;

import com.house.beans.House;
import com.house.beans.Owner;

public interface OwnerMapper {

	/*
	 * key:houseID
	 */
	public int deleteOwnerByHouseID(Map<String, Integer> map) throws Exception;

	public void addOwner(Owner owner) throws Exception;

	public Owner getOwnerByHouseId(House house) throws Exception;

	
}

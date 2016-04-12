package com.house.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.beans.House;
import com.house.persistence.HouseMapper;

@Service
public class HouseService {

	@Autowired
	private HouseMapper houseMapper = null;

	public List<House> getHousesInfo(int integer) throws Exception {
		return houseMapper.getHouses(integer);
	}

	public List<House> getAllHousesInfo(int managerAccount) throws Exception {
		return houseMapper.getAllHousesInfo(managerAccount);
	}

	public List<House> getHousesByBuyerID(int managerAccount) {
		return houseMapper.getHousesByBuyerID(managerAccount);
	}

	public List<House> getSelledHousesInfoById(int managerAccount) throws Exception {
		return houseMapper.getSelledHousesInfoById(managerAccount);
	}

	public List<House> getBuyerHouses(String houseId) {//获取每个buyer的houses
		List<House> houses = new ArrayList<House>();

		String[] houseIds = houseId.split("A");

		for (int i = 0; i < houseIds.length; i++) {

			House house = houseMapper.getHouseInfoByHouseId(houseIds[i]);
			houses.add(house);

		}
		System.out.println("house Id a 是" + houseIds.toString()+"  and house size is "+houses.size());

		return houses;

	}
}

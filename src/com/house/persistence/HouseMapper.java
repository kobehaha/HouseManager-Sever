package com.house.persistence;

import java.util.List;
import java.util.Map;

import com.house.beans.Bargain;
import com.house.beans.House;

public interface HouseMapper {

	public List<House> getHouses(int managerId) throws Exception;

	public List<House> getHousesByBuyerID(int buyerID);

	public void addHouseInfo(House house) throws Exception;

	public House getHouseByPictureUrl(House house) throws Exception;

	public int deleteHouseByHouseID(Map<String, Integer> houseIDMap) throws Exception;

	public int selleHouseOk(Bargain bargain) throws Exception;

	public List<House> getSelledHousesInfoById(int managerId) throws Exception;

	public List<House> getAllHousesInfo(int managerId);

	public House getHouseInfoByHouseId(String houseId);



}

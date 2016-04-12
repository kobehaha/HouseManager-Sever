package com.house.persistence;

import java.util.List;

import com.house.beans.Buyer;
import com.house.beans.Seller;

public interface SellerMapper {

	public List<Seller> getSellersInfo(int managerID) throws Exception;

	public int updateSellerInfo(Seller seller);

	public Seller getSellerInfoByAccount(String account) throws Exception;

	public int addSellerIdByAccount(Seller seller) throws Exception;

	public String getSellerPictureByAccount(String account) throws Exception;
	
	public List<Buyer> getAllBuyer(int account) throws Exception;
	

	
	

}
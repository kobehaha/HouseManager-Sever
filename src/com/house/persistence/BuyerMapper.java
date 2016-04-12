package com.house.persistence;

import com.house.beans.Buyer;

public interface BuyerMapper {

	public int addBuyer(Buyer buyer) throws Exception;


	public Buyer getBuyer(int buyerID) throws Exception;
	

	public Buyer getBuyerIdByBuyerInfo(Buyer buyer) throws Exception;

	public int deleteBuyerById(Buyer buyer) throws Exception;
	
	public int updateBuyerHouseIds(Buyer buyer) throws Exception;
	

}

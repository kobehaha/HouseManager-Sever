package com.house.persistence;

import java.util.List;
import java.util.Map;

import com.house.beans.Bargain;
import com.house.beans.Buyer;

public interface BargainMapper {
	public int addBargain(Map<String, String> map) throws Exception;

	public int updateBargain(Bargain bargain) throws Exception;

	public List<Bargain> selectDealProjectRecord(int account) throws Exception;

	public int deleteBargainById(Buyer buyer) throws Exception;

	public Bargain getBargainByBuyerId(Buyer buyer) throws Exception;

	public int updateBargainHouseIds(Bargain bargain) throws Exception;//更新交易buyer 交易
	
}

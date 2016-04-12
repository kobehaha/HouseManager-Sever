package com.house.persistence;

import java.util.Map;

import com.house.beans.SellerAccount;

public interface SellerAccountMapper {

	/**
	 * 根据用户名和密码查询卖者 参数是：key：sellerName、password
	 */
	public SellerAccount getSellerAccount(Map<String, String> map) throws Exception;

	public SellerAccount getSellerByName(String sellerName);

	public int addSellerAccount(SellerAccount sellerAccount) throws Exception;

	public SellerAccount getSellerByAccount(SellerAccount account);

	public SellerAccount selectSeller(SellerAccount sellerAccount);


	public int modifyPasswordSeller(SellerAccount sellerAccount);


}

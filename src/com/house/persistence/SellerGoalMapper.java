package com.house.persistence;

import java.util.List;

import com.house.beans.SellerAccount;
import com.house.beans.SellerGoal;

public interface SellerGoalMapper {

	public List<SellerGoal> getSellerGoalInfo(int managerID) throws Exception;

	public int addSellerGoalAccount(SellerAccount sellerAccount) throws Exception;

}

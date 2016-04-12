package com.house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.house.beans.Bargain;
import com.house.beans.Buyer;
import com.house.persistence.BargainMapper;
import com.house.persistence.BuyerMapper;

@Service
public class BuyerService {

	@Autowired
	// 自动依赖注入
	BuyerMapper buyerMapper = null;
	@Autowired
	BargainMapper bargainMapper = null;

	public Buyer getBuyerByID(int buyerID) {
		try {
			return buyerMapper.getBuyer(buyerID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addBuyer(Buyer buyer) {
		try {
			buyerMapper.addBuyer(buyer);
			return true;

		} catch (Exception e) {
		}
		return false;

	}

	@Transactional
	public boolean deleteBuyer(Buyer buyer) {
		boolean flag = false;
		try {
			System.out.println("开始执行删除");

			int count2 = bargainMapper.deleteBargainById(buyer);
			int count1 = buyerMapper.deleteBuyerById(buyer);
			System.out.println("count1和count2数量是" + count1 + "    " + count2);
			if (count1 == 1 && count2 == 1) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public boolean updateBuyerHouseIds(Bargain bar, Buyer buy) {
		boolean flag = false;

		try {
			int count1 = bargainMapper.updateBargainHouseIds(bar);
			int count2 = buyerMapper.updateBuyerHouseIds(buy);
			
			System.out.println("count1  "+count1 +"count2   "+count2);

			if (count1 == 1 && count2 == 1) {
				flag = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}

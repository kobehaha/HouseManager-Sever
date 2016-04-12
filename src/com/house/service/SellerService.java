package com.house.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.house.beans.Bargain;
import com.house.beans.Buyer;
import com.house.beans.Seller;
import com.house.beans.SellerGoal;
import com.house.persistence.BargainMapper;
import com.house.persistence.BuyerMapper;
import com.house.persistence.HouseMapper;
import com.house.persistence.SellerGoalMapper;
import com.house.persistence.SellerMapper;
import com.house.utils.UploadFileUtil;

@Service
public class SellerService {

	@Autowired
	private SellerGoalMapper sellerGoalMapper;
	@Autowired
	private SellerMapper sellerMapper;
	@Autowired
	private BargainMapper bargainMapper;
	@Autowired
	private BuyerMapper buyerMapper;
	@Autowired
	private HouseMapper houseMapper;

	public List<SellerGoal> getSellerGoalInfo(int managerID) {
		try {
			return sellerGoalMapper.getSellerGoalInfo(managerID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Seller getSellerInfoByAccount(String sellerAccount) {
		try {
			return sellerMapper.getSellerInfoByAccount(sellerAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Seller> getSellersInfo(int managerID) {
		List<Seller> sellers = null;
		try {
			sellers = sellerMapper.getSellersInfo(managerID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sellers;
	}

	@Transactional
	public boolean updateSellerInfo(Seller seller) {
		boolean flag2 = false;
		Seller seller1 = null;
		try {
			seller1 = sellerMapper.getSellerInfoByAccount(seller.getAccount());
			if (seller1.getUrl() == null) {
				int count = sellerMapper.updateSellerInfo(seller);
				System.out.println("原来没有图片 更新count＝" + count);
				if (count == 1) {
					flag2 = true;
				}

			} else {
				String picture = UploadFileUtil.DISK + File.separator + seller1.getUrl();
				picture = picture.replaceAll("#", "");

				System.out.println("原来seller图片的url地址＝" + picture);
				boolean flag = UploadFileUtil.deletePicture(picture);
				if (flag) {
					int count;
					try {
						count = sellerMapper.updateSellerInfo(seller);
						System.out.println("更新count＝" + count);
						if (count == 1) {
							flag2 = true;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return flag2;

	}

	@Transactional
	public boolean addBuyer(List<Map<String, String>> mapList, Buyer buyer) {
		boolean flag = true;

		int count;
		try {
			count = buyerMapper.addBuyer(buyer);
			if (count == 0)
				flag = false;
			for (Map<String, String> map : mapList) {
				Buyer buyer2;
				try {
					buyer2 = buyerMapper.getBuyerIdByBuyerInfo(buyer);
					System.out.println("buyerID = " + buyer2.getBuyerID());
					map.put("buyerID", "" + buyer2.getBuyerID());

					System.out.println("houseiD=" + map.get("houseID"));
					int i;
					try {

						i = bargainMapper.addBargain(map);
						if (i == 0) {
							flag = false;
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("查看异常");
				}

			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return flag;
	}

	@Transactional
	public boolean sellHouse(Bargain bargain) {
		boolean flag = false;

		try {
			int count = bargainMapper.updateBargain(bargain);
			int count2 = houseMapper.selleHouseOk(bargain);
			System.out.println("操作行数=" + String.valueOf(count));
			if (count == 1 && count2 == 1) {
				flag = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public List<Bargain> getDealSuccessRecord(int selleraccount) {
		List<Bargain> bargains = null;
		try {
			bargains = bargainMapper.selectDealProjectRecord(selleraccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bargains;
	}

	public String getSellerPicture(String account) {
		String picture = "";
		try {
			picture = sellerMapper.getSellerPictureByAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return picture;
	}

	public ArrayList<List<Bargain>> getSortRecord(List<Bargain> bargains) {
		ArrayList<List<Bargain>> list = new ArrayList<List<Bargain>>();
		List<Bargain> bargains2 = bargains;
		System.out.println("原始的size大小＝" + bargains2.size());
		int n = bargains2.size();
		while (n > 0) {
			int i = 0;
			Bargain temp2 = bargains2.get(i);
			List<Bargain> array = new ArrayList<Bargain>();
			array.add(temp2);
			while (i + 1 < n) {
				i++;
				System.out.println("size=" + bargains2.size());
				if (temp2.getTimeOkYear() == bargains2.get(i).getTimeOkYear()) {
					if (temp2.getTimeOkMonth() == bargains2.get(i).getTimeOkMonth()) {
						array.add(bargains2.get(i));
						System.out.println("检测到相等");
						bargains2.remove(bargains2.get(i));
					}
					System.out.println("bargain2的size=" + bargains2.size());
					System.out.println("i的大小＝" + i);
				}
				list.add(array);
				System.out.println("list中次数添加");
			}
			bargains2.remove(bargains2.get(0));
			n = bargains2.size();
			System.out.println("第二次n的大小＝" + n);
		}
		list.remove(0);

		return list;

	}

	public List<Buyer> getAllBuyer(Integer account) {
		List<Buyer> buyers = null;
		try {
			buyers = sellerMapper.getAllBuyer(account);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buyers;
	}

	public Bargain getBargainByBuyer(Buyer buyer) {//通过buyerid获取资料

		Bargain bargain=null;
		try {
			bargain = bargainMapper.getBargainByBuyerId(buyer);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return bargain;
	}
	
	
	

}

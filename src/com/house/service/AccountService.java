package com.house.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.house.beans.House;
import com.house.beans.Manager;
import com.house.beans.ManagerAccount;
import com.house.beans.Owner;
import com.house.beans.Seller;
import com.house.beans.SellerAccount;
import com.house.persistence.HouseMapper;
import com.house.persistence.ManagerAccountMapper;
import com.house.persistence.ManagerMapper;
import com.house.persistence.OwnerMapper;
import com.house.persistence.SellerAccountMapper;
import com.house.persistence.SellerGoalMapper;
import com.house.persistence.SellerMapper;
import com.house.utils.UploadFileUtil;

@Service
public class AccountService {

	@Autowired
	private SellerAccountMapper sellerAccountMapper;
	@Autowired
	private HouseMapper houseMapper;
	@Autowired
	private OwnerMapper ownerMapper;
	@Autowired
	private ManagerMapper managerMapper;
	@Autowired
	private SellerMapper sellerMapper;
	@Autowired
	private ManagerAccountMapper managerAccountMapper;
	@Autowired
	private SellerGoalMapper sellerGoalMapper;

	public boolean LoginManager(ManagerAccount managerAccount) {
		boolean flag = false;

		try {
			int count = managerAccountMapper.selectManager(managerAccount);
			if (count == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public boolean LoginSeller(SellerAccount sellerAccount) {
		boolean flag = false;
		try {
			System.out.println("开始执行");
			SellerAccount seller = sellerAccountMapper.selectSeller(sellerAccount);
			seller.getAccount();
			flag = true;
		} catch (Exception e) {
			System.out.println("登录出错");

		}

		return flag;
	}

	public SellerAccount getSellerAccount(String sellerName) {
		return sellerAccountMapper.getSellerByName(sellerName);
	}

	@Transactional
	public void insertOwnerAndHouseInfo(Owner owner, House house) throws Exception {
		System.out.println("开始插入house数据");
		houseMapper.addHouseInfo(house);
		House house2 = houseMapper.getHouseByPictureUrl(house);
		System.out.println("开始获取数据id" + house2.getHouseID());
		owner.setHouseID(String.valueOf(house2.getHouseID()));
		ownerMapper.addOwner(owner);
		System.out.println("插入数据成功");

	}

	@Transactional
	public boolean addSellerAccount(SellerAccount sellerAccount, Seller seller) {
		try {
			int count = sellerAccountMapper.addSellerAccount(sellerAccount);// 增加账号
			int count2 = sellerGoalMapper.addSellerGoalAccount(sellerAccount);

			sellerMapper.addSellerIdByAccount(seller);
			System.out.println("count1=" + count + "   " + "count2=" + count2);
			if (count == 1 && count2 == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean updateManagerInfo(Manager manager) {
		boolean flag2 = false;
		Manager manager2;
		try {
			manager2 = managerMapper.getManagerInfo(manager.getAccount());
			String picture = UploadFileUtil.DISK + File.separator + manager2.getPicture();
			picture = picture.replaceAll("#", "");

			System.out.println("原来管理员图片的url地址＝" + picture);
			boolean flag = UploadFileUtil.deletePicture(picture);

			if (flag) {
				int count;
				try {
					count = managerMapper.uploadManagerInfo(manager);
					System.out.println("更新count＝" + count);
					if (count == 1) {
						flag2 = true;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return flag2;

	}

	public Manager getManagerInfo(String managerAccount) {
		try {
			return managerMapper.getManagerInfo(managerAccount);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}

	public SellerAccount getSellerByAccount(SellerAccount sellerAccount) {
		SellerAccount account = null;
		account = sellerAccountMapper.getSellerByAccount(account);
		System.out.println("相同的账号有   " + account.getManagerAccount());
		return account;

	}

	public boolean modifyPasswordManager(ManagerAccount managerAccount) {
		boolean flag = false;
		try {
			int count = managerAccountMapper.modifyPasswordManager(managerAccount);
			System.out.println("更新数量的count＝" + count);
			if (count == 1) {
				flag = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean modifyPasswordSeller(SellerAccount sellerAccount) {
		boolean flag = false;
		try {
			int count = sellerAccountMapper.modifyPasswordSeller(sellerAccount);
			System.out.println("更改的count=" + count);
			if (count == 1) {
				flag = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Seller getSellerInfo(String sellerAccount) {
		try {
			return sellerMapper.getSellerInfoByAccount(sellerAccount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public House getHouseInfoById(String houseId) {
		try {

			House house = houseMapper.getHouseInfoByHouseId(houseId);
			System.out.println("get house info= " + house.getPicture());
			return house;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Owner getOwnerInfo(String houseId) {
		try {
			House house = new House();
			house.setHouseID(houseId);
			return ownerMapper.getOwnerByHouseId(house);
		} catch (Exception e) {
			return null;
		}

	}
}

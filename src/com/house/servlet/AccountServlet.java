package com.house.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.house.beans.House;
import com.house.beans.Manager;
import com.house.beans.ManagerAccount;
import com.house.beans.Owner;
import com.house.beans.Seller;
import com.house.beans.SellerAccount;
import com.house.service.AccountService;
import com.house.service.SellerService;
import com.house.utils.MyTimeFormat;
import com.house.utils.UploadFileUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService;
	private SellerService sellerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		accountService = webAppContext.getBean(AccountService.class);
		sellerService = webAppContext.getBean(SellerService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operate = request.getParameter("operate");
		PrintWriter writer = response.getWriter();

		System.out.println("operate = " + operate);
		if ("getSellerAccount".equals(operate)) {
			String sellerName = request.getParameter("name");
			SellerAccount sellerAccount = accountService.getSellerAccount(sellerName);
		} else if ("modifyPasswordManager".equals(operate)) {
			String managerAccount = request.getParameter("managerAccount");
			String managerPassword = request.getParameter("managerPassword");
			ManagerAccount managerAccount2 = new ManagerAccount();
			managerAccount2.setManagerAccount(managerAccount);
			managerAccount2.setManagerPassword(managerPassword);
			boolean flag = accountService.modifyPasswordManager(managerAccount2);
			JSONObject jsonObject = new JSONObject();
			System.out.println("管理员更改密码   密码=" + managerPassword + "  账号是=" + managerAccount);
			if (flag) {
				jsonObject.element("status", 1);
				System.out.println("更改成功");

			} else {
				jsonObject.element("staus", 0);
				System.out.println("更改失败，密码不正确");
			}
			writer.write(jsonObject.toString());

		} else if ("modifySellerPassword".equals(operate)) {
			String account = request.getParameter("sellerAccount");
			String password = request.getParameter("sellerPassword");
			SellerAccount sellerAccount = new SellerAccount();
			sellerAccount.setAccount(account);
			sellerAccount.setPassword(password);
			boolean flag = accountService.modifyPasswordSeller(sellerAccount);
			JSONObject jsonObject = new JSONObject();
			System.out.println("销售人员更改密码   密码=" + password + "  账号是=" + account);
			if (flag) {
				jsonObject.element("status", 1);
				System.out.println("更改成功");

			} else {
				jsonObject.element("staus", 0);
				System.out.println("更改失败，密码不正确");
			}
			writer.write(jsonObject.toString());

		}

		else if ("loginManager".equals(operate)) {
			String managerAccount = request.getParameter("managerAccount");
			String managerPassword = request.getParameter("managerPassword");
			ManagerAccount managerAccount2 = new ManagerAccount();
			managerAccount2.setManagerAccount(managerAccount);
			managerAccount2.setManagerPassword(managerPassword);
			boolean flag = accountService.LoginManager(managerAccount2);
			JSONObject jsonObject = new JSONObject();
			System.out.println("管理员登录   密码=" + managerPassword + "  账号是=" + managerAccount);
			if (flag) {
				jsonObject.element("status", 1);
				System.out.println("登录成功");

			} else {
				jsonObject.element("staus", 0);
				System.out.println("登录失败，密码不正确");
			}
			writer.write(jsonObject.toString());

		} else if ("loginSeller".equals(operate)) {
			String account = request.getParameter("sellerAccount");
			String password = request.getParameter("sellerPassword");
			SellerAccount sellerAccount = new SellerAccount();
			sellerAccount.setAccount(account);
			sellerAccount.setPassword(password);
			boolean flag = accountService.LoginSeller(sellerAccount);
			JSONObject jsonObject = new JSONObject();
			System.out.println("销售人员登录   密码=" + password + "  账号是=" + account);
			if (flag) {
				jsonObject.element("status", 1);
				System.out.println("登录成功");

			} else {
				jsonObject.element("staus", 0);
				System.out.println("登录失败，密码不正确");
			}
			writer.write(jsonObject.toString());

		} else if ("uploadOwnerAndHouseInfo".equals(operate)) {
			Map<String, String> map = UploadFileUtil.uploadFile(request, response);

			// 处理文件名
			String fileNames = map.get("fileNames");

			/**
			 * house Information
			 */
			String managerAccount = map.get("managerID");
			String price = map.get("house_price");
			String size = map.get("house_size");
			String years = map.get("house_year");
			String houseName = new String(map.get("house_name").getBytes("iso-8859-1"), "utf-8");
			String houseLocation = new String(map.get("house_location").getBytes("iso-8859-1"), "utf-8");
			String houseBrief = new String(map.get("house_brief").getBytes("iso-8859-1"), "utf-8");
			/**
			 * house Owner info
			 */
			String ownerAge = map.get("owner_age");
			String ownerName = new String(map.get("owner_name").getBytes("iso-8859-1"), "utf-8");
			String sex = new String(map.get("owner_sex").getBytes("iso-8859-1"), "utf-8");
			String phone = new String(map.get("owner_phone").getBytes("iso-8859-1"), "utf-8");
			String wonerWeichat = new String(map.get("owner_weichart").getBytes("iso-8859-1"), "utf-8");

			/**
			 * house
			 */
			System.out.println("fileName = " + fileNames);
			System.out.println("houseName = " + houseName);
			System.out.println(
					"managerID = " + managerAccount + "; houseLocation = " + houseLocation + "; price = " + price);
			System.out.println("size = " + size + "; years = " + years + "; hosueBrief = " + houseBrief);
			System.out.println("ownerName = " + ownerName + "; ownerAge = " + ownerAge + "; sex = " + sex);
			System.out.println("phone = " + phone + "; wonerWeichat = " + wonerWeichat);

			House house = new House();
			house.setManagerAccount(Integer.valueOf(managerAccount));
			house.setName(houseName);
			house.setPicture(fileNames);
			house.setLocation(houseLocation);
			house.setPrice(Double.parseDouble(price));
			house.setSize(Double.parseDouble(size));
			house.setYears(Integer.parseInt(years));
			house.setBrief(houseBrief);

			/**
			 * owner
			 */
			Owner owner = new Owner();
			owner.setName(ownerName);
			owner.setAge(Integer.parseInt(ownerAge));
			owner.setSex(sex);
			owner.setPhone(phone);
			owner.setWeiChat(wonerWeichat);
			owner.setManagerAccount(managerAccount);

			boolean runStatus = true;
			try {
				accountService.insertOwnerAndHouseInfo(owner, house);
			} catch (Exception e) {
				runStatus = false;
				e.printStackTrace();
			}
			if (runStatus) {
				writer.print(1);
			} else {
				writer.print(0);
			}

		} else if ("buildSellerAccount".equals(operate)) {
			String account = request.getParameter("seller_account");
			String sellerPassword = request.getParameter("seller_password");
			String managerID = request.getParameter("managerAccount");
			java.util.Date date = new java.util.Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String time = dateFormat.format(date);

			System.out.println(managerID + "; " + account + "; " + sellerPassword);

			SellerAccount sellerAccount = new SellerAccount();
			Seller seller = new Seller();
			seller.setTime(MyTimeFormat.changeDateToLongString(date));
			seller.setAccount(account);
			seller.setTime(time);

			if (managerID == null || "".equals(managerID)) {
				writer.write(0);
				return;
			}
			sellerAccount.setManagerAccount(Integer.parseInt(managerID));
			sellerAccount.setAccount(account);
			sellerAccount.setPassword(sellerPassword);
			// SellerAccount flag2 =
			// accountService.getSellerByAccount(sellerAccount);//处理是否注册过存在相同的账号
			// System.out.println("数据中查询过程+ " + flag2.getAccount());
			boolean flag = accountService.addSellerAccount(sellerAccount, seller);

			if (flag)
				writer.write(1);

			else
				writer.write(0);
		} else if ("updateManagerInfo".equals(operate)) {
			Map<String, String> map = UploadFileUtil.uploadManagerIco(request, response);
             
			// 处理文件名
			String managerAccount = map.get("manager_account");
			String name = map.get("manager_name");
			String sex = map.get("manager_sex");
			String age = map.get("manager_age");
			String phone = map.get("manager_phone");
			String picture = map.get("fileNames");
			String date = map.get("manager_time");

			System.out.println("managerAccount = " + managerAccount + "; name = " + name + "; sex = " + sex);
			System.out.println("age = " + age + "; phone = " + phone + "; picture = " + picture + "; time = " + date);

			Manager manager = new Manager();
			manager.setAccount(managerAccount);
			manager.setName(name);
			manager.setSex(sex);
			manager.setAge(Integer.parseInt(age));
			manager.setPhone(phone);
			manager.setPicture(picture);
			manager.setDate(MyTimeFormat.changeStringToDate(date));

			boolean flag = accountService.updateManagerInfo(manager);

			if (flag) {
				writer.write(1);
				System.out.println("更新成功");
			} else {
				writer.write(0);
				System.out.println("更新失败");
			}
		} else if ("updateSellerInfo".equals(operate)) {
			Map<String, String> map = UploadFileUtil.uploadFile(request, response);
			String fileName = map.get("fileNames");
			String account = map.get("account");
			String name = map.get("name");
			String sex = map.get("sex");
			String age = map.get("age");
			String phone = map.get("phone");
			String degree = map.get("degree");
			String weichat = map.get("weichart");
			String time = map.get("weichart");
			Seller seller = new Seller();
			seller.setAccount(account);
			seller.setDegree(degree);
			seller.setName(name);
			seller.setSex(sex);

			if (!"".equals(age)) {
				seller.setAge(Integer.parseInt(age));
			}
			seller.setPhone(phone);
			seller.setWeichat(weichat);
			seller.setTime(time);
			seller.setUrl(fileName);
			System.out.println(seller.toString());
			boolean flag = sellerService.updateSellerInfo(seller);
			if (flag) {
				writer.write(1);
			} else {
				writer.write(0);
			}
			writer.close();
		} else if ("getSellerByAccount".equals(operate)) {
			String account = request.getParameter("seller_account");

			Seller seller = sellerService.getSellerInfoByAccount(account);
			String date = seller.getTime();
			String timeString = date;
			JSONObject jo1 = JSONObject.fromObject(seller);
			jo1.element("timeString", timeString);

			JSONObject jo = new JSONObject();
			jo.element("sellerInfo", jo1);

			writer.write(jo.toString());
			writer.close();
		} else if ("getManagerInfo".equals(operate)) {
			String managerAccount = request.getParameter("managerAccount");

			Manager manager = accountService.getManagerInfo(managerAccount);

			java.util.Date date = manager.getDate();
			String dateString = MyTimeFormat.changeDateToString(date);

			JSONObject jo1 = JSONObject.fromObject(manager);
			jo1.element("dateString", dateString);

			writer.write(jo1.toString());
			writer.close();
		} else if ("getSellerInfo".equals(operate)) {
			String sellerAccount = request.getParameter("sellerAccount");
			Seller seller = accountService.getSellerInfo(sellerAccount);
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonObject3 = new JSONObject();
			JSONArray array = new JSONArray();

			if (seller != null) {

				JSONObject jsonObject2 = JSONObject.fromObject(seller);

				jsonObject.element("status", 1);
				array.add(jsonObject);
				array.add(jsonObject2);
				jsonObject3.element("jsonArray", array);
				writer.write(jsonObject3.toString());
				writer.close();
				System.out.println("获取seller消息成功");

			} else {
				jsonObject.element("status", 0);
				array.add(jsonObject);
				jsonObject3.element("jsonArray", array);
				writer.write(jsonObject3.toString());
				writer.close();
				System.out.println("获取seller消息失败");
			}

		} else if (operate.equals("getHouseByHouseId")) {
			String houseId = request.getParameter("houseId");
			System.out.println("获取房源信息" + houseId);

			House house = accountService.getHouseInfoById(houseId);
			JSONObject object;
			if (house != null) {
				object = JSONObject.fromObject(house);
				object.element("staus", "1");
				System.out.println("通过房源id获取信息成功");

			} else {
				object = new JSONObject();
				object.element("staus", "0");
				System.out.println("通过房源id获取信息失败");

			}
			writer.write(object.toString());
			writer.close();

		} else if (operate.equals("getOwnerInfoByHouseId")) {
			String houseId = request.getParameter("houseId");
			System.out.println("获取房源信息" + houseId);

			Owner owner = accountService.getOwnerInfo(houseId);
			JSONObject object;
			if (owner != null) {
				object = JSONObject.fromObject(owner);
				object.element("staus", "1");
				System.out.println("通过房源id获取信息成功");

			} else {
				object = new JSONObject();
				object.element("staus", "0");
				System.out.println("通过房源id获取信息失败");

			}
			writer.write(object.toString());
			writer.close();

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

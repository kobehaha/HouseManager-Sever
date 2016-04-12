package com.house.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.house.beans.Bargain;
import com.house.beans.Buyer;
import com.house.beans.House;
import com.house.beans.Seller;
import com.house.service.BuyerService;
import com.house.service.HouseService;
import com.house.service.SellerService;
import com.house.utils.UploadFileUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/SellerServlet")
public class SellerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SellerService sellerService;
	private HouseService houseService;
	private BuyerService buyerService;

	public void init() {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		sellerService = webAppContext.getBean(SellerService.class);
		houseService = webAppContext.getBean(HouseService.class);
		buyerService = webAppContext.getBean(BuyerService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		String operate = request.getParameter("operate");
		System.out.println("operate = " + operate);
		if ("UploadBuyerInfo".equals(operate)) {

			Map<String, String> map = UploadFileUtil.uploadBuyerIcoFile(request, response);

			// 处理文件名

			String sellerID = map.get("seller_id");
			String houseIDs = map.get("house_id");
			String name = map.get("name");
			String sex = map.get("sex");
			String age = map.get("age");
			String phone = map.get("phone");
			String weichat = map.get("weichart");
			String url = map.get("fileNames");
			Date create = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd:hh:mm:ss");
			String time = simpleDateFormat.format(create);

			System.out.println("buyer age is " + String.valueOf(age));
			Buyer buyer = new Buyer();
			buyer.setAge(Integer.parseInt(age));
			buyer.setName(name);
			buyer.setSex(sex);
			buyer.setPhone(phone);
			buyer.setWeiChat(weichat);
			buyer.setHouseId(houseIDs);
			buyer.setCreateTime(time);
			buyer.setUrl(url);

			System.out.println("buyer = " + buyer);
			System.out.println("account=" + sellerID);
			System.out.println("houseID = " + String.valueOf(houseIDs));

			// String[] houseID = houseIDs.split("#");

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			// int len = houseID.length;
			// for (int i = 0; i < len; i++) {
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("houseID", houseIDs);
			map2.put("account", sellerID);
			System.out.println("houseID === " + String.valueOf(houseIDs));
			// System.out.println("houseID = " + houseIDs[i]);
			list.add(map2);
			// }

			boolean flag = sellerService.addBuyer(list, buyer);

			System.out.println("flag = " + flag);
			if (flag) {
				writer.write(1);
			} else {
				writer.write(0);
			}
		}
		// else if ("getBuyers".equals(operate)) {
		// String sellerID = request.getParameter("seller_id");
		//
		// List<Buyer> buyers =
		// sellerService.getBuyers(Integer.parseInt(sellerID));
		//
		// JSONArray ja = JSONArray.fromObject(buyers);
		//
		// JSONObject jo = new JSONObject();
		// jo.element("buyer_info", ja);
		//
		// writer.write(jo.toString());
		// }

		else if ("getBuyersAndHouses".equals(operate)) {
			String buyerID = request.getParameter("buyer_id");

			if (buyerID == null || "".equals(buyerID))
				return;
			List<House> houses = null;
			houses = houseService.getHousesByBuyerID(Integer.parseInt(buyerID));
			Buyer buyer = null;
			buyer = buyerService.getBuyerByID(Integer.parseInt(buyerID));

			JSONObject jo = new JSONObject();
			jo.element("houses", houses);
			jo.element("buyer", buyer);

			response.getWriter().write(jo.toString());

			return;
		} else if ("deleteBuyer".equals(operate)) {
			String buyerId = request.getParameter("buyerId");

			Buyer buyer = new Buyer();
			buyer.setBuyerID(Integer.valueOf(buyerId));
			boolean flag = buyerService.deleteBuyer(buyer);

			JSONObject jsonObject = new JSONObject();
			System.out.println("要删除的buyerId=" + buyerId);
			if (flag) {
				jsonObject.element("status", 1);
				System.out.println("删除成功");

			} else {
				jsonObject.element("staus", 0);
				System.out.println("删除失败");
			}
			writer.write(jsonObject.toString());

		} else if ("dealHouseSuccess".equals(operate)) {
			String buyerId = request.getParameter("buyerId");
			String sellerAccount = request.getParameter("selleraccount");
			String houseId = request.getParameter("houseId");
			String price = request.getParameter("price");
			java.util.Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String time = simpleDateFormat.format(date);
			Bargain bargain = new Bargain();
			bargain.setTimeok(time);
			bargain.setBuyerId(buyerId);
			bargain.setHouseId(houseId);
			bargain.setPrice(price);
			bargain.setAccount(sellerAccount);
			boolean flag = sellerService.sellHouse(bargain);
			System.out.println("售卖= " + String.valueOf(flag));
			System.out.println("buyerID=" + buyerId + "  sellerAccount" + sellerAccount + "   houseId=" + houseId
					+ "price=" + price + "time ok=" + time);

			JSONObject jsonObject = new JSONObject();
			if (flag) {
				jsonObject.element("status", 1);
				System.out.println("交易成功");

			} else {
				jsonObject.element("staus", 0);
				System.out.println("交易失败");
			}
			writer.write(jsonObject.toString());

		} else if ("getDealProject".equals(operate)) {
			String account = request.getParameter("selleraccount");
			System.out.println("getdealProject sellerAccount=" + account);
			List<Bargain> bargains = sellerService.getDealSuccessRecord(Integer.valueOf(account));
			Seller seller = sellerService.getSellerInfoByAccount(account);

			String picture = seller.getUrl();
			String name = seller.getName();
			ArrayList<List<Bargain>> arrayList = sellerService.getSortRecord(bargains);

			JSONObject jsonObject = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray allArray = new JSONArray();

			for (int i = 0; i < arrayList.size(); i++) {
				List<Bargain> bargains2 = arrayList.get(i);
				JSONArray array = new JSONArray();
				for (Bargain bargain : bargains2) {
					jsonObject.element("houseId", bargain.getHouseId());
					jsonObject.element("buyerId", bargain.getBuyerId());
					jsonObject.element("price", bargain.getPrice());
					jsonObject.element("time", bargain.getTime());
					jsonObject.element("timeok", bargain.getTimeok());
					array.add(jsonObject);

				}
				allArray.add(array.toString());

			}
			jsonObject2.element("sellerName", name);
			jsonObject2.element("sellerPicture", picture);
			jsonObject2.element("getBargains", allArray.toString());
			writer.write(jsonObject2.toString());
			System.out.println(jsonObject2.toString());
			writer.close();

		} else if ("getAllBuyer".equals(operate)) {

			String account = request.getParameter("selleraccount");
			List<Buyer> buyers = sellerService.getAllBuyer(Integer.valueOf(account));
			JSONArray arry = JSONArray.fromObject(buyers);
			JSONObject jo = new JSONObject();
			jo.element("buyerInfo", arry);
			writer.write(jo.toString());

		} else if ("getBuyerRecord".equals(operate)) {// 获取买家记录

			String buyerId = request.getParameter("buyerId");

			Buyer buyer = new Buyer();
			buyer.setBuyerID(Integer.valueOf(buyerId));

			System.out.println("上传的buyerid是" + buyerId);

			Buyer buyer2 = buyerService.getBuyerByID(Integer.valueOf(buyerId));

			Bargain bargain = sellerService.getBargainByBuyer(buyer);

			String houseId = bargain.getHouseId();// 获取每个房源信息

			List<House> houses = houseService.getBuyerHouses(houseId);// 获取每个buyer的house

			@SuppressWarnings("static-access")
			JSONArray arry = new JSONArray().fromObject(houses);

			System.out.println(arry.toString());

			JSONObject json = new JSONObject();

			json.element("buyerInfo", JSONObject.fromObject(buyer2).toString());
			json.element("bargainInfo", JSONObject.fromObject(bargain).toString());

			json.element("houseInfo", arry.toString());

			writer.write(json.toString());

			System.out.println(json.toString());

		} else if ("updateBuyerHouseInfo".equals(operate)) {// 更新卖家的房源偏好记录

			String buyerId = request.getParameter("buyerId");
			String houseIds = request.getParameter("houseIds");
			Bargain bargain = new Bargain();
			Buyer buyer = new Buyer();
			bargain.setBuyerId(buyerId);
			bargain.setHouseId(houseIds);
			buyer.setBuyerID(Integer.valueOf(buyerId));
			buyer.setHouseId(houseIds);

			boolean flags = buyerService.updateBuyerHouseIds(bargain, buyer);// 执行查询服务

			JSONObject jsonObject = new JSONObject();
			if (flags) {
				jsonObject.element("status", 1);
				System.out.println("更新成功");

			} else {
				jsonObject.element("staus", 0);
				System.out.println("更新失败");
			}
			writer.write(jsonObject.toString());

		}
	}

}

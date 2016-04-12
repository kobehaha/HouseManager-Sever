package com.house.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.house.beans.House;
import com.house.beans.Notice;
import com.house.beans.Seller;
import com.house.beans.SellerGoal;
import com.house.service.HouseService;
import com.house.service.NoticeService;
import com.house.service.SellerService;
import com.house.utils.MyTimeFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoticeService noticeService = null;
	private SellerService sellerService = null;
	private HouseService houseService = null;

	public void init() {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		noticeService = webAppContext.getBean(NoticeService.class);
		sellerService = webAppContext.getBean(SellerService.class);
		houseService = webAppContext.getBean(HouseService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operate = request.getParameter("operate");

		if (!"getHousesInfo".equals(operate))
			System.out.println("operate = " + operate);

		PrintWriter writer = response.getWriter();

		if ("getAllNotices".equals(operate)) {
			String managerID = request.getParameter("manager_id");
			if (managerID == null)
				return;

			System.out.println("managerId = " + managerID);

			List<Notice> notices = noticeService.getAllNoticeByManagerID(Integer.parseInt(managerID));

			JSONObject jsonObject = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray array = new JSONArray();
			for (Notice notice : notices) {
				jsonObject.element("notice_id", notice.getId());
				jsonObject.element("content", notice.getContent());
				jsonObject.element("time", MyTimeFormat.changeDateToString(notice.getDate()));

				array.add(jsonObject);

			}
			jsonObject2.element("notice_info", array.toString());
			writer.write(jsonObject2.toString());
			System.out.println(jsonObject2.toString());

			writer.close();
		} else if ("sendNotice".equals(operate)) {
			String noticeContent = request.getParameter("content");
			String managerID = request.getParameter("manager_id");

			System.out.println("content = " + noticeContent + "; id = " + managerID);

			Notice notice = new Notice();
			notice.setContent(noticeContent);
			notice.setAccount(Integer.parseInt(managerID));

			boolean flag = noticeService.addNotice(notice);
			if (flag)
				writer.write(1);

			else
				writer.write(0);

		} else if ("deleteNoticeById".equals(operate)) {
			String id = request.getParameter("notice_id");
			System.out.println("DeleteNoticeId = " + id);
			Notice notice = new Notice();
			notice.setId(Integer.valueOf(id));
			boolean flag = noticeService.deleteNoticeById(notice);
			if (flag) {
				writer.write(1);
				System.out.println("删除成功");

			} else {
				writer.write(0);
				System.out.println("删除失败");

			}

		} else if ("getSellersGoals".equals(operate)) {
			String managerID = request.getParameter("manager_id");

			try {
				List<SellerGoal> sellerGoals = sellerService.getSellerGoalInfo(Integer.parseInt(managerID));

				JSONArray jsonArray = JSONArray.fromObject(sellerGoals);
				JSONObject jo = new JSONObject();
				jo.element("seler_goals", jsonArray);
				writer.write(jo.toString());
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("getHousesInfo".equals(operate)) {
			String managerId = request.getParameter("managerAccount");

			List<House> houses = null;
			try {
				houses = houseService.getHousesInfo(Integer.valueOf(managerId));
				JSONObject jo = new JSONObject();
				jo.element("house_info", houses);

				writer.write(jo.toString());
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("getAllHousesInfo".equals(operate)) {
			String managerId = request.getParameter("managerAccount");

			List<House> houses = null;
			try {
				houses = houseService.getAllHousesInfo(Integer.valueOf(managerId));
				JSONObject jo = new JSONObject();
				jo.element("house_info", houses);

				writer.write(jo.toString());
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("getSelledHouseInfo".equals(operate)) {
			String managerId = request.getParameter("managerAccount");
			List<House> houses = null;
			try {
				houses = houseService.getSelledHousesInfoById(Integer.valueOf(managerId));
				JSONObject jo = new JSONObject();
				jo.element("house_info", houses);

				writer.write(jo.toString());
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("getSellers".equals(operate)) {
			String managerID = request.getParameter("managerAccount");
			System.out.println("managerId is " + managerID);
			List<Seller> sellers = sellerService.getSellersInfo(Integer.parseInt(managerID));
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();

			int len = sellers.size();
			for (int i = 0; i < len; i++) {
				Seller seller = sellers.get(i);
				String dateString = seller.getTime();
				JSONObject joTemp = JSONObject.fromObject(seller);
				joTemp.element("date_string", dateString);

				ja.add(joTemp);
			}

			jo.element("seller_info", ja);

			writer.write(jo.toString());
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

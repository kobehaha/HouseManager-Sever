package com.house.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.house.beans.House;
import com.house.beans.Owner;
import com.house.service.AccountService;
import com.house.service.OwnerService;
import com.house.service.SellerService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class OwnerServlet
 */
@WebServlet("/OwnerServlet")
public class OwnerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OwnerService ownerService;

	public OwnerServlet() {
		super();

	}

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ownerService = webAppContext.getBean(OwnerService.class);

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
		if ("getOwnerByHouseId".equals(operate)) {
			int houseId = Integer.valueOf(request.getParameter("houseId"));
			House house = new House();
			house.setHouseID(String.valueOf(houseId));
			Owner owner = ownerService.getOwnerInfo(house);
			JSONObject jo1 = JSONObject.fromObject(owner);
			writer.write(jo1.toString());
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

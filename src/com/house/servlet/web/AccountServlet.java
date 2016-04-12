package com.house.servlet.web;
/*
 * 
 * 该servlet 用来判断用户请求，
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.house.beans.Manager;
import com.house.beans.web.SystemRole;
import com.house.service.web.SystemService;

@WebServlet(name = "account", urlPatterns = { "/account" }) // 通过注解向web.xml一样的注解
public class AccountServlet extends HttpServlet {
	private SystemService systemService;
	private static final long serialVersionUID = 1L;

	private SystemRole system = new SystemRole();
	private Manager manger;
	private boolean flag = false;

	@Override
	public void init() throws ServletException {

		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		systemService = webAppContext.getBean(SystemService.class);

	}

	public void service(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher;// 分发器
		String role = request.getParameter("role");
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		System.out.println("Web  登陆服务 servlet");
		if (role.equals("system")) {
			system.setAccount(account);
			system.setPasswrod(password);
			flag = systemService.login(system);

			System.out.println(system.toString() + String.valueOf(flag));
			if (flag == true) {
				request.getSession().setAttribute("role", "system");

				dispatcher = request.getRequestDispatcher("/page/log.jsp");
				
				try {
					dispatcher.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				request.setAttribute("flag", "error");
				dispatcher = request.getRequestDispatcher("/page/login.jsp");
				try {
					dispatcher.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

}

package com.house.utils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.house.beans.web.Log;
import com.house.service.web.LogService;

@WebListener
public class RequestListener implements ServletRequestListener {

	// 监控用户请求的处理过程

	private LogService logService;// 一定不能直接new 这样会出错的，不能依赖了

	public void load() {

		// ApplicationContext context = new
		// FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		// // HouseService ser = context.getBean(HouseService.class);
		// logService = context.getBean(LogService.class);
		//

	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {

		WebApplicationContext webAppContext = WebApplicationContextUtils
				.getWebApplicationContext(arg0.getServletContext());
//		ApplicationContext context=webAppContext;
		
		logService = webAppContext.getBean(LogService.class);// 食例话对象

		System.out.println("监听器开始监听请求状态");
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		HttpSession session = request.getSession();// 获取request and session
		String id = session.getId();
		String url = request.getRequestURI();
		String user = (String) session.getAttribute("role");
		if (user != null) {//必须为有角色才开始记录数据
			String ip = request.getRemoteAddr();
			Log log = new Log();
			log.setId(id);
			log.setIp(ip);
			log.setPath(url);
			log.setRole(user);
			log.setTime(System.currentTimeMillis());// 获取
			System.out.println(log.toString());
			boolean exist = logService.selectBySessionId(id);// 查询记录中是否有这条记录
			if (exist) {
				System.out.println("开始更新数据");
				boolean update = logService.update(log);
				
				if (update) {

					System.out.println("跟新记录成功");
				}

			} else {
				System.out.println("----开始插入新数据----");
				boolean insert = logService.insert(log);
				if (insert) {
					System.out.println("----插入新请求成功----" + log);
				}
			}

		}

	}

}

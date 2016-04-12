package com.house.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.beans.web.SystemRole;
import com.house.persistence.web.SystemRoleMapper;

@Service
public class SystemService {
	@Autowired
	private SystemRoleMapper system;

	public boolean login(SystemRole systemRole) {

		System.out.println("开始执行管理员登陆业务"+systemRole.toString());
		boolean result = false;
		int count = 1;
		try {
			
			if (count == system.login(systemRole)) {
				System.out.println(" 查询数量=" + String.valueOf(count));
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;

	}

}

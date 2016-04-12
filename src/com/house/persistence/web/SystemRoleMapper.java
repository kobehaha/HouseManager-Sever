package com.house.persistence.web;

import com.house.beans.web.SystemRole;

public interface SystemRoleMapper {
	void selectRole(SystemRole system) throws Exception;

	int login(SystemRole system) throws Exception;

}

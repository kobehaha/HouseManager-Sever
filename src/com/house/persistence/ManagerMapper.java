package com.house.persistence;

import com.house.beans.Manager;

public interface ManagerMapper {

	public int uploadManagerInfo(Manager manager) throws Exception;

	public Manager getManagerInfo(String managerAccount) throws Exception;

}

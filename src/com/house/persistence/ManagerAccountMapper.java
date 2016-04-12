package com.house.persistence;

import com.house.beans.ManagerAccount;

public interface ManagerAccountMapper {
	public int selectManager(ManagerAccount manager) throws Exception;

	public int modifyPasswordManager(ManagerAccount managerAccount) throws Exception;

}

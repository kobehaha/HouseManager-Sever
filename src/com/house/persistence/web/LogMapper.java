package com.house.persistence.web;

import java.util.ArrayList;

import com.house.beans.web.Log;

public interface LogMapper {
	void addLog(Log log) throws Exception;

	void updateLog(Log log) throws Exception;
	
	int findLogBySessionId(String id) throws Exception;

	ArrayList<Log> getAllLog() throws Exception;//选出所有的用户


}

package com.house.service.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.beans.web.Log;
import com.house.persistence.web.LogMapper;

@Service
public class LogService {
	@Autowired
	private LogMapper log;

	public ArrayList<Log> getAllLogs() {//获取所有的logs
		ArrayList<Log> list=new ArrayList<Log>();
		try {
			
			list = log.getAllLog();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return list;
	}

	public boolean selectBySessionId(String id) {// 通过SessionId查询
		boolean result = false;
		int count = 0;
		try {
			if (count < log.findLogBySessionId(id)) {
				result = true;
			}

		} catch (org.apache.ibatis.binding.BindingException e) {
			System.out.println("新的用户接入,此前无此记录");

		} catch (org.apache.ibatis.exceptions.TooManyResultsException e) {

			System.out.println("同一用户介入多次");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean insert(Log log2) {
		boolean result = false;
		try {
			log.addLog(log2);

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean update(Log log2) {

		boolean result = false;
		try {
			log.updateLog(log2);
			System.out.println(log2.toString());
			result = true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

}

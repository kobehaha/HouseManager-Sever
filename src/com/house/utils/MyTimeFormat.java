package com.house.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyTimeFormat {

	public static String changeDateToString(java.util.Date date){
		if(date != null) {
			String dateStr = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = sdf.format(date);
			return dateStr;
		}else {
			return "";
		}
	}
	
	/**
	 * 将date类型转化为带时分秒字符串
	 * @param date
	 * @return
	 */
	public static String changeDateToLongString(java.util.Date date) {
		if(date != null) {
			String dateStr = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			dateStr = sdf.format(date);
			return dateStr;
		}else {
			return "";
		}
	}
	
	public static java.util.Date changeStringToDate(String dateStr) {
		if(dateStr != null && !"null".equals(dateStr)&&!"".equals(dateStr)) {
			//去掉时分秒，否则报错
			//by lynn 2015.06.18
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}else {
			return null;
		}
		
	}
}

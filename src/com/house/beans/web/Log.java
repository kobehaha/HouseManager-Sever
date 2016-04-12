package com.house.beans.web;

public class Log {

	// 日志信息

	private String sessionId, role, ip, path, id;
	private Long time;

	public Log() {

	}

	public Long getTime() {
		return time;
	}

	public void setTime(long l) {
		this.time = l;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "log info id=" + String.valueOf(id) + "   ip  is " + ip + "     Role is " + role + "   Path=" + path
				+ "   time=" + time;
	}

}

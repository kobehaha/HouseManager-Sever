<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.house.utils.SpringGetBeantUtil"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.house.service.web.LogService"%>
<%@ page import="com.house.beans.web.Log"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>记录</title>
</head>
<body>
	<%
		if (request.getSession().getAttribute("role") != null) {

			if (request.getSession().getAttribute("role").equals("system") ? true : false) {
				out.println("欢迎系统管理员查看信息" + "<hr>");
				String a = new String();

				SpringGetBeantUtil context = new SpringGetBeantUtil();//获取Spring Context

				//	LogService logService = context.getBean(LogService.class);
			//	ArrayList<Log> logs = logService.getAllLogs();
	%>

<%-- 	<table width="600" border="1">
		<%
			for (int i = 0; i < logs.size(); i++) {
						Log log = logs.get(i);
		%>

		<tr>
			<td><%=log.getSessionId()%></td>
			<td><%=log.getIp()%></td>
			<td><%=log.getPath()%></td>
			<td><%=log.getRole()%></td>
			<td><%=log.getTime()%></td>
		</tr>



		<%
			}
		%> --%>
	</table>
	<%
		} else {
				out.println("欢迎销售经理查看信息" + "<hr>");
			}

		}
	%>

</body>
</html>
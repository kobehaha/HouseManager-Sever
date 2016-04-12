<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>

	<h4>登陆</h4>
	<hr>
	<form action="account" method="post" id="form1">
		账户: <input name="account" type="text"> <br> 密码: <input
			name="password" type="password"> <br /> 选择角色:<br /> <select
			name="role">
			<option value="system">系统管理员</option>
			<option value="manager">销售经理</option>
		</select> <br /> <input type="submit" value="登陆"> <input type="reset"
			value="重置">


		<%
			if (request.getAttribute("flag") != null && request.getAttribute("flag").equals("error")) {
				out.println("输入数据有错误");

			}
		%>


	</form>

</body>
</html>
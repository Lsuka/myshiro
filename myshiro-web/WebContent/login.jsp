<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	request.setCharacterEncoding("UTF-8");
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String login_url = basePath + "login.action";
%>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<shiro:authenticated>
		<h1>欢迎你老母只閪</h1>
	</shiro:authenticated>
	<shiro:notAuthenticated>
	<h1>${error }</h1>
	<form action="<%=login_url%>" method="post">
		用户名:<input type="text" name="mid" id="mid"><br> 密码:<input
			type="text" name="password" id="password"><br> <input
			type="submit" value="添加"> <input type="reset" value="重制">
	</form>
	</shiro:notAuthenticated>
</body>
</html>
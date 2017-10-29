<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	request.setCharacterEncoding("UTF-8");
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

	<h1>
		user id :
		<shiro:principal />
	</h1>
	<shiro:hasRole name="member">
	<h1>威拉威拉知你威拉权限狗</h1>
	</shiro:hasRole>
	<shiro:hasPermission name="news:add">
	<h1>我叼你老母仲没威够啊嘛臭閪</h1>
	</shiro:hasPermission>
</body>
</html>
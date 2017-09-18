<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	
	<link rel="stylesheet" href="login/css/style.css">
	
  </head>

  <body>
	<div class="login-container">
		<h1>社团活动管理系统</h1>		
		<form action="usersLogin.action" method="post">
			<div>
				<input type="text" name="username" class="username" placeholder="用户名"/>
			</div>
			<div>
				<input type="password" name="password" class="password" placeholder="密码"/>
			</div>
			<button name="loginbt" type="submit">登 录</button>
		</form>	
		<a href="registe.jsp">
			<button name="registeskipbt" type="button" class="register-tis">还有没有账号？</button>
		</a>	
	</div>
		
	<script src="login/js/jquery.min.js"></script>
	<script src="login/js/common.js"></script>
	<!--背景图片自动更换-->
	<script src="login/js/supersized.3.2.7.min.js"></script>
	<script src="login/js/supersized-init.js"></script>
	<script src="login/js/jquery.validate.min.js"></script>
  </body>
</html>

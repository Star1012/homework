<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="login/css/style.css">

  </head>
  
  <body>
	<div class="register-container">
		<h1>注册用户</h1>	
		<form action="usersCreate.action" method="post">
			<div>
				<input type="text" name="username" placeholder="您的用户名" />
			</div>
			<div>
				<input type="text" name="name" placeholder="您的昵称" />
			</div>
			<div>
				<input type="password" name="password" placeholder="输入密码" />
			</div>
			<div>
				<input type="password" name="cpassword" placeholder="再次输入密码" />
			</div>
			<div>
				<input type="text" name="suoshu" placeholder="所属学院" />
			</div>
			<button name="registebt" type="submit">注 册</button>
		</form>
		<a href="login.jsp">
			<button name="backbt" type="button" class="register-tis">已经有账号？</button>
		</a>
	</div>
	
	<script src="login/js/jquery.min.js"></script>
  	<script src="login/js/common.js"></script>
  	<!--背景图片自动更换-->
  	<script src="login/js/supersized.3.2.7.min.js"></script>
  	<script src="login/js/supersized-init.js"></script>
  	<!--表单验证-->
  	<script src="login/js/jquery.validate.min.js"></script>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.dbEntity.Permission"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限管理2</title>
<link rel="stylesheet"  type="text/css" href="css/RolePermissionManage.css"/>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/SetNewRole.js">
</script>
</script>
<script type="text/javascript" src="../js/framework.js">
</script>
<link href="../css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" />
<!--框架必需end-->
<script type="text/javascript" src="../js/nav/ddaccordion.js">
</script>
<script type="text/javascript" src="../js/text/text-overflow.js">
	
</script>

</head>
<%
	if(request.getAttribute("permission")==null)
	{
 %>
<body>
<%
	}
	else
	{
		Permission p = (Permission)request.getAttribute("permission");
 %>
 <body onload="showRole()">
 <%
 	}
  %>
<center>
<div class="context">
	<form action="addRole.action" method="post">
	<table class="tablelist" id="tb">
    		<thead>
    		<tr>
        	<th>权限名称</th>
        	<th>权限说明</th>
        	<th>赋予权限</th>
        	</tr>
       	 	</thead>
     
     		<tr>
				<td>管理员权限</td>
				<td>最高权限，使用系统所有权限</td>
				<td>
					<input type="checkbox" name="manage" value="true">
				</td>
			</tr>
			<tr>
				<td>填写征集表</td>
				<td>填写河北省重大技术需求征集表</td>
				<td>
					<input type="checkbox" name="addManage" value="true">
				</td>
			</tr>
			<tr>
				<td>形式审核</td>
				<td>情报部门审核征集表是否符合形式要求，将通过的征集表转交给部门审核处</td>
				<td>
					<input type="checkbox" name="formManage" value="true">
				</td>
			</tr>
			<tr>
				<td>部门审核</td>
				<td>各处室审核征集表是否符合技术要求</td>
				<td>
					<input type="checkbox" name="departManage" value="true">
				</td>
			</tr>
			<tr>
				<td>统计查询</td>
				<td>统计征集表的情况</td>
				<td>
					<input type="checkbox" name="sqMangae" value="true">
				</td>
			</tr>
			<tr>
				<td>需求管理</td>
				<td>编辑已提交的征集表</td>
				<td>
					<input type="checkbox" name="demandManage" value="true">
				</td>
			</tr>
			<tr>
				<td>个人信息管理</td>
				<td>修改个人信息，修改个人密码</td>
				<td>
					<input type="checkbox" name="pmManage" value="true">
				</td>
			</tr>
			<tr>
				<td>新建角色</td>
				<td>
					<input type="text" name="role">
				</td>
				<td>
					<input type="submit" value="确认">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="取消" style="font-size: medium;" onclick="ajax()">
				</td>
			</tr>
		</table>
		</form>
	</div>
</center>
</body>
</html>
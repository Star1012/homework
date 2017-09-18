<%@ page import="java.util.*,com.dbEntity.Permission" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  type="text/css" href="css/RolePermissionManage.css"/>
<script type="text/javascript" src="js/RolePermissionManage.js">
</script>
</head>
<body>
<center>
<div class="context">
	<div class="tools">
    	<ul class="toolbar">
        <a href="SetNewRole.jsp"><li class="click"><span><img src="../images/t01.png" /></span>添加</li></a>
        </ul>
        <ul class="toolbar">
        <a href="javascript:showDelete()"><li class="click"><span><img src="../images/t03.png"></span>删除</li></a>
        </ul>
	</div>
	<%
		if(request.getAttribute("role")==null)
		{
			System.out.println("!!!!!!!!!!");
		}
		List<Permission> role = (List)request.getAttribute("role");
		if(role.isEmpty())
		{
	 %>
	 <h5>暂无角色</h5>
	 <%
	 	}
	 	else
	 	{
	  %>
	<form id="form" action="deleteRoles.action" method="post">
	<table class="tablelist" id="tb">
    		<thead>
    		<tr>
    		<th id="th" style="display:none;"></th>
        	<th>角色名称</th>
        	<th>管理员权限</th>
        	<th>填写征集表</th>
        	<th>征集表管理</th>
        	<th>个人信息管理</th>
        	<th>形式审核</th>
        	<th>部门审核</th>
        	<th>统计查询</th>
        	<th>操作</th>
        	</tr>
       	 	</thead>
       	 	<%
       	 		for(int i=0;i<role.size();i++)
	 			{
	 				Permission p = (Permission)role.get(i);
       	 	 %>
       	 	<tr>
       	 		<td name="tcheck" style="display:none;"><input type="checkbox" name="roles" value="<%=p.getRole() %>"></td>
       	 		<td><%=p.getRole() %></td>
       	 		<td><%=p.getManage()?"√":"" %></td>
       	 		<td><%=p.getAddDemand()?"√":"" %></td>
       	 		<td><%=p.getDemandManage()?"√":"" %></td>
       	 		<td><%=p.getPmManage()?"√":"" %></td>
       	 		<td><%=p.getFormManage()?"√":"" %></td>
       	 		<td><%=p.getDepartManage()?"√":"" %></td>
       	 		<td><%=p.getSqMangae()?"√":"" %></td>
       	 		<td>
       	 			<a href="SetNewRole.jsp?role=<%=p.getRole() %>">修改</a>
       	 		</td>
       	 	</tr>
       	 	<%
       	 		}
       	 	 %>
       	 	 <tr id="tr" style="display:none;">
       	 	 	<td colspan="10">
       	 	 		<input type="submit" value="删除">
       	 	 	</td>
       	 	 </tr>
   </table>
   </form>
   	<%
   		} 
   	%>
</div>
</center>
</body>
</html>
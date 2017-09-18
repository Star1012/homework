<%@ page import="java.util.*,com.dbEntity.SearchResult" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
  <body>
  <center>
  <hr style="height:10px;border:none;border-top:10px groove skyblue;" />
   <div style="width:80%;height:100%">
   <%
   		
   		if(request.getAttribute("demandList")==null)
   		{
   	%>
   		<br><br><br><br>
   		<h5>未检索到任何内容，请重新确认索引条件</h5>
   	<% 
   		}
   		else
   		{
   			List<SearchResult> list = (List)request.getAttribute("demandList");
   			if(list.isEmpty())
   			{
   	%>
   		<br><br><br><br>
   		<h5>未检索到任何内容，请重新确认索引条件</h5>
   	<% 
   			}
   			else
   			{
    %>
    <table width="80%" class="tablelist">
    <thead>
    <tr>
    <th>需求表单号</th>
    <th>需求名称</th>
    <th>机构名称</th>
    <th>需求起始日期</th>
    <th>需求截止日期</th>
    <th>表单状态</th>
    <th>操作</th>
    </tr>
    </thead>
      <tbody>
      	<%
      		for(int i=0;i<list.size();i++)
      		{
      			SearchResult sr = list.get(i);
      			
      	 %>
      	 <tr>
      	 	<td><%=sr.getId() %></td>
      	 	<td><%=sr.getDname() %></td>
      	 	<td><%=sr.getOname() %></td>
      	 	<td><%=sr.getStartTime() %></td>
      	 	<td><%=sr.getEndTime() %></td>
      	 	<%
      	 		if(sr.getStatus() == 1)
      	 		{
      	 	 %>
      	 	<td>待审核</td>
      	 	<%	
      	 		}
      	 		else if(sr.getStatus() == 2)
      	 		{
      	 	 %>
      	 	 <td>形式审核未通过</td>
      	 	 <%
      	 	 	}
      	 		else if(sr.getStatus() == 3)
      	 		{
      	 	 %>
      	 	 <td>形式审核通过</td>
      	 	 <%
      	 	 	}
      	 	  	else if(sr.getStatus() == 4)
      	 		{
      	 	 %>
      	 	 <td>部门审核未通过</td>
      	 	 <%
      	 	 	}
      	 	 	else if(sr.getStatus() == 5)
      	 		{
      	 	 %>
      	 	 <td>审核通过</td>
      	 	 <%
      	 	 	}
      	 	  %>
      	 	<td><a href="searchDemand.action?id=<%=sr.getId() %>">查看</a></td>
      	 </tr>
      	 <%
      	 	}
      	  %>
	  </tbody>
	</table>
	<%
		}
	}
	 %>
    </div>
    </center>
  </body>
</html>

<%@ page import="java.util.*,com.dbEntity.DemandCollect" language="java" contentType="text/html; charset=UTF-8"
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
   <div style="width:80%;height:100%">
   <%
   		List<DemandCollect> list =(List)request.getAttribute("demandList");
   		if(list.isEmpty())
   		{
   	%>
   		<br><br><br><br>
   		<h5>暂无需求表需要审核</h5>
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
    <th>研究类型</th>
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
      			DemandCollect dc = list.get(i);
      			
      	 %>
      	 <tr>
      	 	<td><%=dc.getDemandId() %></td>
      	 	<td><%=dc.getDName() %></td>
      	 	<td><%=dc.getTechType() %></td>
      	 	<td><%=dc.getStartTime() %></td>
      	 	<td><%=dc.getEndTime() %></td>
      	 	<td><%=dc.getDStatus() %></td>
      	 	<td><a href="demandShow.action?id=<%=dc.getDemandId() %>">审核</a></td>
      	 </tr>
      	 <%
      	 	}
      	  %>
	  </tbody>
	</table>
	<%
		}
	 %>
    </div>
    </center>
  </body>
</html>

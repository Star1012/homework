<%@ page import="java.util.*,com.dbEntity.DemandCollect" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body bgColor="#E0F0F8">
<center>
    <div style="width:80%;height:100%">
     <div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><a href="setSubandNae.action"><span><img src="../images/t01.png" /></span>添加</a></li>
        <li class="click"><a href="updateManage.action?status=1"><span><img src="../images/t02.png" /></span>修改</a></li>
        <li><span><img src="../images/t03.png" /></span>删除</li>
        </ul>
        <ul class="toolbar1">
        <li><span><img src="../images/t05.png" /></span>设置</li>
        </ul>
    </div>
    <table class="tablelist">
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
      		List<DemandCollect> list =(List)request.getAttribute("demandList");
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
      	 	<td>查看</td>
      	 </tr>
      	 <%
      	 	}
      	  %>
	  </tbody>
	</table>
	</div>
</center>
</body>
</html>
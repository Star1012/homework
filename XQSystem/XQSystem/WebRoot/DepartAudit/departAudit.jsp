<%@ page language="java" import="java.util.*,com.dbEntity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
DemandCollect dc = (DemandCollect)request.getAttribute("demand");
OperatingOrganization opog = (OperatingOrganization)request.getAttribute("opog");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'formAudit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="FormAudit/saveAudit.action?status=depart&id=<%=dc.getDemandId() %>" method="post">
    <table width="80%" border="1" align="center">
  	  <tr>
    	<td width="16%">机构名称</td>
    	<td colspan="2"><%=opog.getOName() %></td>
    	<td width="12%">归口管理部门</td>
    	<td width="34%" colspan="2" name="tmd"><%=opog.getOTmd() %></td>
  	  </tr>
  	  <tr>
    	<td>通讯地址</td>
    	<td colspan="2"><%=opog.getOAddress() %></td>
    	<td>所在地域</td>
    	<td colspan="2"><%=opog.getOArea() %></td>
  	  </tr>
  	  <tr>
    	<td>单位网址</td>
    	<td colspan="2"><%=opog.getOUrl() %></td>
    	<td>电子邮箱</td>
    	<td colspan="2"><%=opog.getOEmail() %></td>
  	  </tr>
  	  <tr>
    	<td>法人代表</td>
    	<td colspan="2"><%=opog.getOLperson() %></td>
    	<td>邮政编码</td>
    	<td colspan="2"><%=opog.getOPostcode() %></td>
  	  </tr>
  	  <tr>
    	<td rowspan="2">联系人</td>
    	<td width="27%" rowspan="2"><%=opog.getOContacts() %></td>
    	<td width="11%">*电话</td>
    	<td colspan="3">1.手机<%=opog.getOMbPhone() %>2.固话<%=opog.getOPlPhone() %></td>
  	  </tr>
  	  <tr>
    	<td>传真</td>
    	<td colspan="3"><%=opog.getOFax() %></td>
  	  </tr>
  	  <tr>
    	<td>机构属性</td>
    	<td colspan="5"><%=opog.getOAttribute() %></td>
  	  </tr>
  	  <tr>
    	<td>机构简介</td>
    	<td colspan="5"><%=opog.getOOverView() %></td>
  	  </tr>
  	  <tr>
    	<td>技术需求名称</td>
    	<td colspan="2"><%=dc.getDName() %></td>
    	<td>*需求时限</td>
    	<td colspan="2">从<%=dc.getStartTime() %>至<%=dc.getEndTime() %></td>
  	  </tr>
  	  <tr>
    	<td>技术需求概述</td>
    	<td colspan="5">
    	1.主要问题:<%=dc.getTheme() %><br>
        2.技术关键:<%=dc.getTechKey() %><br>
        3.预期目标:<%=dc.getExpectTarget() %><br>
    	</td>
  	  </tr>
  	  <tr>
    	<td>关键字</td>
    	<td colspan="5"><%=dc.getDKey() %></td>
  	  </tr>
  	  <tr>
  	  	<td>拟投入资金</td>
  	  	<td colspan="5"><%=dc.getPlanInvestment() %></td>
  	  </tr>
  	  <tr>
  	  	<td rowspan="2">技术需求合作模式</td>
  	  	<td colspan="5"><%=dc.getDSolution() %></td>
  	  </tr>
  	  <tr>
  	  	<td colspan="5"><%=dc.getCooIntention() %></td>
  	  </tr>
  	  <tr>
    	<td>科技活动类型</td>
    	<td colspan="5"><%=dc.getTechType() %></td>
  	  </tr>
  	  <%
  	  	if(dc.getTechType().equals("基础研究"))
  	  	{
  	   %>
  	  <tr>
    	<td>学科分类（基础研究填写）</td>
    	<td colspan="5"><%=dc.getSubjects() %></td>
  	  </tr>
  	  <%
  	  	}
  	  	else
  	  	{
  	   %>
  	  <tr>
    	<td>需求技术所属领域（非基础研究填写）</td>
    	<td colspan="5"><%=dc.getDServics() %></td>
  	  </tr>
  	  <tr>
    	<td>需求技术应用行业</td>
    	<td colspan="5"><%=dc.getDAppIndustry() %></td>
  	  </tr>
  	  <%
  	  }
  	   %>
  	  <tr>
    	<td>部门审核</td>
    	<td colspan="5">
    		<label><input type="radio" name="depart" value="审核通过" onClick="Close('otherModel')">审核通过</label>
        	<label><input type="radio" name="depart" value="审核不通过" onClick="Close('otherModel')">审核不通过</label>
    	</td>
  	  </tr>
  	  <tr>
    	<td>审核不通过原因</td>
    	<td colspan="5"><textarea name="idea" cols="100%"></textarea></td>
  	  </tr>
      <tr>
    	<td colspan="6">
    	 <input type="submit" value="确认审核"> 
        </td>
  	  </tr>
	</table>
	</form>
  </body>
</html>

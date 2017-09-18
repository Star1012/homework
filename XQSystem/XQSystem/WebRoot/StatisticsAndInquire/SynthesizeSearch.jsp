<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SynthesizeSearch.jsp' starting page</title>
    
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
    <div class="search_condition">
    	<IFRAME scrolling="no" width="100%" height="60%" frameBorder=0 id=frmheade name=frmheade src="StatisticsAndInquire/SearchCondition.jsp" allowTransparency="true">
        </IFRAME>
    </div>
    <div class="search_result">
    	<IFRAME scrolling="yes" width="100%" height="100%" frameBorder=0 id=frmfoot name=frmfoot src="StatisticsAndInquire/SearchResult.jsp" allowTransparency="true">
        </IFRAME>
    </div>
  </body>
</html>

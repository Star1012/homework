<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
   <script type="text/javascript" src="js/jquery-1.4.js"></script>
  <script>
  
    var num=1;
	function addDiv()
	{
		var div=document.getElementById("div0");
		var node=document.getElementById("div1");
		var cnode=node.cloneNode(true);
		cnode.style.display="";
		if(num<=3)
		{
			div.appendChild(cnode);
			num++;
		}
	}
	
	function deleteDiv()
	{
		var div=document.getElementById("div0");
		var childs=div.childNodes;
		if(num>1)
		{ 
			div.removeChild(childs[childs.length-1]);
			num--; 
		}
	}
	
	var num1=1;
	function addDiv1()
	{
		var div=document.getElementById("div2");
		var node=document.getElementById("div3");
		var cnode=node.cloneNode(true);
		cnode.style.display="";
		if(num1<=12)
		{
			div.appendChild(cnode);
			num1++;
		}
	}
	
	function deleteDiv1()
	{
		var div=document.getElementById("div2");
		var childs=div.childNodes;
		if(num1>1)
		{ 
			div.removeChild(childs[childs.length-1]);
			num1--; 
		}
		
	}
	
	function submit()
	{
		document.form1.submit();
	}
	
	function SetWinHeight(obj) 
	{ 
		var win=window.parent.document.getElementById(obj); 
		if (document.getElementById) 
		{ 
			if (win && !window.opera) 
			{ 
				if (win.contentDocument && win.contentDocument.body.offsetHeight) 
					win.height = win.contentDocument.body.offsetHeight; 
				else if(win.Document && win.Document.body.scrollHeight) 
					win.height = win.Document.body.scrollHeight; 
			} 	
		} 
	} 
  </script>
  <body>
  	<form name="form1" action="StatisticsAndInquire/Comprehensiveindex.action" method="post" target="frmfoot" id="form1">
  		<div style="background-color:#D1EEEE">
  			<h4>输入检索条件:</h4>
				<div id="div0">
					<div>	
						<input type="button" value="+" id="btn1" onclick="addDiv();SetWinHeight('win')">
						<input type="button" value="-" id="btn2" onclick="deleteDiv();SetWinHeight('win')">
						<select style="width:120px" name="firstindex01">
						   <option value="OName">机构名称</option>
						   <option value="OArea">所在地域</option>
						   <option value="OAttribute">机构属性</option>
						   <option value="OTmd">归口部门</option>
					 	</select>
					 	<input type="text"  size="20" name="firstvalue01">
					 	<select name="firsttype01">
					 		<option value="and">并含</option>
					 		<option value="or">或含</option>
					 		<option value="not">不含</option>
					 	</select>
					 	<input type="text" size="20" name="firstvalue02">
					 	<select name="typefirst01">
						 	<option value="equal">精确</option>
						 	<option value="like">模糊</option>
					 	</select>
					 </div>
				</div>
				
				<div id="div2">
					<div Style="height:5px"></div>
						<div>
							<input type="button" value="+" id="btn1" onclick="addDiv1();SetWinHeight('win')">
							<input type="button" value="-" id="btn2" onclick="deleteDiv1();SetWinHeight('win')">
							<select style="width:120px" name="firstindex11">
							   <option value="manageDepart">部门审核</option>
							   <option value="DName">需求名称</option>
							   <option value="DKey">关键字</option>
							   <option value="theme">主要问题</option>
							   <option value="techKey">技术关键</option>
							   <option value="expecttarget">预期目标</option>
							   <option value="techType">科技活动类型</option>
							   <option value="DAppIndustry">需求应用行业</option>
						 	</select>
						 	<input type="text"  size="20" name="firstvalue11">
						 	<select name="firsttype11">
						 		<option value="and">并含</option>
					 		<option value="or">或含</option>
					 		<option value="not">不含</option>
						 	</select>
						 	<input type="text" size="20" name="firstvalue12">
						 	<select name="typefirst11">
							 	<option value="equal">精确</option>
							 	<option value="like">模糊</option>
						 	</select>
						 </div>
					</div>
				<div>
					<div style="height:5px"></div>
					<div>
						起始时间:从<input type="text" name="begintime1" size="20">年到<input type="text" name="begintime2" size="20">年
					</div>
				</div>
				<div>
					<div style="height:5px"></div>
					<div>
					              截止时间:从<input type="text" name="finaltime1" size="20">年到<input type="text" name="finaltime2" size="20">年
					</div>
				</div>
				<div>
					<div style="height:8px"></div>
					<input name="submit" type="submit" value="检索">
				</div>
				<br>
  		</div>
  	</form>
  	<div id="div1" style="display:none">
					 	<div Style="height:5px"></div>
					 	<select style="width:66px" name="selecttype01">
					 		<option value="and">并含</option>
					 		<option value="or">或含</option>
					 		<option value="not">不含</option>
					 	</select>
						<select style="width:120px" name="selectindex01">
						    <option value="OName">机构名</option>
						   <option value="OArea">所在地域</option>
						   <option value="OAttribute">机构属性</option>
						   <option value="OTmd">归口部门</option>
					 	</select>
					 	<input type="text"  size="20" name="selectvalue01">
					 	<select name="selecttype02">
					 		<option value="and">并含</option>
					 		<option value="or">或含</option>
					 		<option value="not">不含</option>
					 	</select>
					 	<input type="text" size="20" name="selectvalue02">
						 <select name="type01">
						 	<option value="equal">精确</option>
						 	<option value="like">模糊</option>
						 </select>
	</div>
	<div id="div3" style="display:none">
				<div Style="height:5px"></div>
					 	<select style="width:66px" name="selecttype11">
					 		<option value="and">并含</option>
					 		<option value="or">或含</option>
					 		<option value="not">不含</option>
					 	</select>
						<select style="width:120px" name="selectindex11">
						   <option value="xssh">形式审核</option>
						   <option value="bmsh">部门审核</option>
						   <option value="needname">需求名</option>
						   <option value="key">关键字</option>
						   <option value="mainproblem">主要问题</option>
						   <option value="maintechnology">技术关键</option>
						   <option value="plantarget">预期目标</option>
						   <option value="researchtype">科技活动类型</option>
						   <option value="needtype">需求应用行业</option>
						   <option value="needdepartment">需求所属领域</option>
						   <option value="subjecttype">学科分类</option>
					 	</select>
					 	<input type="text" size="20" name="selectvalue11">
					 	<select name="selecttype12">
					 		<option value="and">并含</option>
					 		<option value="or">或含</option>
					 		<option value="not">不含</option>
					 	</select>
					 	<input type="text" name="selectvalue12" size="20">
						 <select name="type11">
						 	<option value="equal">精确</option>
						 	<option value="like">模糊</option>
						 </select>
</div>
  </body>
</html>

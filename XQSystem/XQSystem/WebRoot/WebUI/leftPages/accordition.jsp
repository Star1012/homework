<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>左侧菜单</title>
<script type="text/javascript" src="../js/text/text-overflow.js">  没有影响
	
</script> 
<script type="text/javascript" src="../js/jquery-1.4.js">
	
</script>
<script type="text/javascript" src="../js/nav/ddaccordion.js">//控制li的显示
	
</script>
<script type="text/javascript" src="../js/framework.js">//控制ul与li的边框

</script>
<link href="../css/import_basic.css" rel="stylesheet" type="text/css" /> <!-- 字体 -->
<link rel="stylesheet" type="text/css" id="skin" />
</head>
<body>
<script type="text/javascript">
var html="<div id='scrollContent'><div class='arrowlistmenu'><div class='menuheader expandable'>系统菜单</div><ul class='categoryitems' id='category'>";
<%
	if(session.getAttribute("manage").equals(true))
	{		// 管理员权限
%>
			html +="<li><a><span class='text_slice'>权限管理</sapn></a><ul>";
			html +="<li><a href='../../SystemManage/roleManage.action' target='frmright'><span class='text_slice'>角色管理</span></a></li>";
			html +="<li><a href='../../SystemManage/SetNewRole.jsp' target='frmright'><span class='text_slice'>添加角色</span></a></li></ul></li>";
			html +="<li><a href='../../OrgInstitution/setSubandNae.action' target='frmright'><span class='text_slice'>需求填报</span></a></li>";
			html +="<li><a href='../../OrgInstitution/demandManage.action?status=0' target='frmright'><span class='text_slice'>需求管理</span></a></li>";
			html +="<li><a href='../../FormAudit/formAuditManage.action?status=2' target='frmright'><span class='text_slice'>形式审核</span></a></li>";
			html +="<li><a href='../../DepartAudit/departAuditManage.action?status=3' target='frmright'><span class='text_slice'>部门审核</span></a></li>";
			html +="<li><a><span class='text_slice'>统计查询</span></a><ul>";
			html +="<li><a href='../../StatisticsAndInquire/SynthesizeSearch.jsp' target='frmright'><span class='text_slice'>条件查询</span></a></li>";
			html +="<li><a href='../../StatisticsAndInquire/Test.html' target='frmright'><span class='text_slice'>高级检索</span></a></li>";
<%
	}
 	else{
 		if(session.getAttribute("addDemand").equals(true))
 		{ 		//填写征集表权限
%>
			html +="<li><a href='../../OrgInstitution/setSubandNae.action'><span class='text_slice'>需求填报</span></a></li>";
<%
		}
		if(session.getAttribute("demandManage").equals(true))
		{		 //需求表管理
%>
			html +="<li><a href='../../OrgInstitution/demandManage.action' target='frmright'><span class='text_slice'>需求管理</span></a></li>";
<%
		}
		if(session.getAttribute("formManage").equals(true))
		{		// 形式审核权限
%>
			html +="<li><a href='../../NetWorkAudit/DemandManage.jsp' target='frmright'><span class='text_slice'>形式审核</span></a></li>";
<%
		}
		if(session.getAttribute("departManage").equals(true))
		{	// 部门审核
%>
			html +="<li><a href='../../NetWorkAudit/DepartmentManage.jsp' target='frmright'><span class='text_slice'>部门审核</span></a></li>";
<%
		}
		if(session.getAttribute("SqMangae").equals(true))
		{ 
			// 统计查询权限
%>
			html +="<li><a><span class='text_slice'>统计查询</span></a><ul>";
			html +="<li><a href='../../NetWorkAudit/ClassfiySearch.jsp' target='frmright'><span class='text_slice'>分类索引</span></a></li>";
			html +="<li><a href='../../NetWorkAudit/SynthesizeSearch.jsp' target='frmright'><span class='text_slice'>综合查询</span></a></li>";
			html +="<li><a href='../../NetWorkAudit/Statistics.jsp' target='frmright'><span class='text_slice'>数据统计</span></a></li></ul></li>";
<%
		}
		if(session.getAttribute("PmManage").equals(true))
		{ 	//修改自己密码权限
		
%>
			html +="<li><a href='../../OrgInstitution/PersonalMessage.jsp' target='frmright'><span class='text_slice'>个人信息</span></a></li>";
<% 		
 		}
 	}
%>
		html +="</ul></div></div>";
		document.write(html);
</script>
</body>
</html>
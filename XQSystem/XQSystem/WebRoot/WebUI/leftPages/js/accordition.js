var power;
var xqzj_qx;
var xqgl_qx;
var yhxx_qx;
var xssh_qx;
var bmsh_qx;
var tjcx_qx;
//创建XMLHttpRequest对象
function createXMLHttpRequest(){
	if(window.XMLHttpRequest){//Mozilla浏览器
		XMLHttpReq = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		try{
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try{
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){}
		}
	}
}

//刷新列表框函数
function JudgePower(){
	var url ="accordition";
	createXMLHttpRequest();
	XMLHttpReq.open("GET",url,true);
	XMLHttpReq.send(null);//发送请求
	
	XMLHttpReq.onreadystatechange =processListResponse;//指定响应函数
	
}

//处理返回信息
function processListResponse(){
	if(XMLHttpReq.readyState == 4){//判断对象状态
		if(XMLHttpReq.status == 200){//信息已经成功返回，开始处理信息
			updateLeftPage();
		}else{
			window.alert("您所请求的页面有异常。");
		}
	}
}

function updateLeftPage()
{
	var html="";
	power = XMLHttpReq.responseXML.getElementsByTagName("manage")[0].firstChild.data;
	xqzj_qx = XMLHttpReq.responseXML.getElementsByTagName("addDemand")[0].firstChild.data;
	xqgl_qx = XMLHttpReq.responseXML.getElementsByTagName("demandManage")[0].firstChild.data;
	yhxx_qx = XMLHttpReq.responseXML.getElementsByTagName("PmManage")[0].firstChild.data;
	xssh_qx = XMLHttpReq.responseXML.getElementsByTagName("formManage")[0].firstChild.data;
	bmsh_qx = XMLHttpReq.responseXML.getElementsByTagName("departManage")[0].firstChild.data;
	tjcx_qx = XMLHttpReq.responseXML.getElementsByTagName("SqManage")[0].firstChild.data;
	
	var ul = document.getElementById("category");
	if (power == 1) {
		html += "<li><a href='../../OrgInstitution/DemandManage.jsp' target='frmright'><span class='text_slice'>用户管理</span></a></li>"
				+"<li><a><span class='text_slice'>权限管理</span></a><ul>"
				+ "<li><a href='../../SystemManage/RoleBroswer.jsp' target='frmright'><span class='text_slice'>角色管理</span></a></li>"
				+ "<li><a href='../../SystemManage/RolePermissionManage.jsp' target='frmright'><span class='text_slice'>新增角色</span></a></li></ul></li>"
				
				+ "<li><a href='../../OrgInstitution/DemandManage.jsp' target='frmright'><span class='text_slice'>需求管理</span></a></li>"
				+ "<li><a href='../../NetWorkAudit/DemandManage.jsp' target='frmright'><span class='text_slice'>形式审核</span></a></li>"
				+ "<li><a href='../../NetWorkAudit/DepartmentManage.jsp' target='frmright'><span class='text_slice'>部门审核</span></a></li>"
				+ "<li><a></a><span>查询统计</span></a><ul>"
				+ "<li><a href='../../NetWorkAudit/ClassfiySearch.jsp' target='frmright'><span class='text_slice'>分类索引</span></a></li>"
				+ "<li><a href='../../NetWorkAudit/SynthesizeSearch.jsp' target='frmright'><span class='text_slice'>综合查询</span></a></li></ul></li>"
				+ "<li><a href='../yonghu/mmxg.jsp' target='frmright'><span class='text_slice'>修改密码</span></a></li>"
				+ "<li><a href='../templete/xtjs.html' target='frmright'><span class='text_slice'>系统介绍</span></a></li>"
				+ "<li><a href='../templete/bzwj.html' target='frmright'><span class='text_slice'>帮助文件</span></a></li>"
				+ "<li><a href='../templete/cjwtjd.html' target='frmright'><span class='text_slice'>常见问题解答</span></a></li>";
				ul.innerHTML+=html;
	}
	
	else{
	if (xqzj_qx == 1) {
			html += "<li><a href='../../OrgInstitution/CreateDemand.jsp' target='frmright'><span class='text_slice'>需求征集</span></a></li>";
	}
	if (xqgl_qx == 1) {
			html += "<li><a href='../../OrgInstitution/DemandManage.jsp' target='frmright'><span class='text_slice'>需求管理</span></a></li>";
	}
	if (yhxx_qx == 1) {
			html += "<li><a href='../../OrgInstitution/PersonalMessage.jsp' target='frmright'><span class='text_slice'>用户信息</span></a></li>";
	}
	if (xssh_qx == 1) {
			html += "<li><a href='../../NetWorkAudit/DemandManage.jsp' target='frmright'><span class='text_slice'>形式审核</span></a></li>";
	}
	if (bmsh_qx == 1) {
			html += "<li><a href='../../NetWorkAudit/DepartmentManage.jsp' target='frmright'><span class='text_slice'>部门审核</span></a></li>";
	}
	if (tjcx_qx == 1) {
			
			html +="<li><a></a><span>查询统计</span></a><ul>"
				 + "<li><a href='../../NetWorkAudit/ClassfiySearch.jsp' target='frmright'><span class='text_slice'>分类索引</span></a></li>"
				 + "<li><a href='../../NetWorkAudit/SynthesizeSearch.jsp' target='frmright'><span class='text_slice'>综合查询</span></a></li>"
				 + "<li><a href='../../NetWorkAudit/Statistics.jsp' target='frmright'><span class='text_slice'>综合查询</span></a></li></ul></li>";
	}
	html += "<li><a href='../templete/xtjs.html' target='frmright'><span class='text_slice'>系统介绍</span></a></li>";
	html += "<li><a href='../templete/bzwj.html' target='frmright'><span class='text_slice'>帮助文件</span></a></li>";
	html += "<li><a href='../templete/cjwtjd.html' target='frmright'><span class='text_slice'>常见问题解答</span></a></li>";
	
	}
	ul.innerHTML+=html;	
}
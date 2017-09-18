<%@ page language="java" import="java.util.*,com.clubmanage.model.Users,com.clubmanage.model.Activity" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Users um = (Users) session.getAttribute("userKey");
Activity acd = (Activity) session.getAttribute("activitysTransKey");
%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title></title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/themify-icons.css" rel="stylesheet">

<script type="text/javascript">
	function checkRadio(){
		if(document.getElementById('zhuangtaiNo').checked){
			document.getElementById('Radio').style.display = 'block';
		}
		else
		{
			document.getElementById('Radio').style.display = 'none';
		}
	}
</script>

</head>
<body>

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                    社团活动管理系统
                </a>
            </div>

            <ul class="nav">
                <li>
                    <a href="index.jsp">
                        <i class="ti-panel"></i>
                        <p>主页</p>
                    </a>
                </li>
                <li>
                    <a href="users.jsp">
                        <i class="ti-user"></i>
                        <p>用户页面</p>
                    </a>
                </li>
                <%
                	if(um.getJuese().equals("社团")||um.getJuese().equals("管理"))
                	{
                %>
                <li>
                    <a href="activity.jsp">
                        <i class="ti-text"></i>
                        <p>申请活动</p>
                    </a>
                </li>
                <li>
                    <a href="activityRetrieveb.action">
                        <i class="ti-map"></i>
                        <p>未过活动</p>
                    </a>
                </li>
                <li>
                    <a href="activitylookton.jsp">
                        <i class="ti-bell"></i>
                        <p>结束活动</p>
                    </a>
                </li>
                <%} %>
                <li>
                    <a href="activityRetrievet.action">
                        <i class="ti-view-list-alt"></i>
                        <p>查看活动</p>
                    </a>
                </li>
                <%
                	if(um.getJuese().equals("团委")||um.getJuese().equals("管理"))
                	{
                %>
                <li  class="active">
                    <a href="activityRetrievew.action">
                        <i class="ti-pencil-alt2"></i>
                        <p>审核活动</p>
                    </a>
                </li>
                <%} %>
            </ul>
    	</div>
    </div>

    <div class="main-panel">
		<nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="#">活动审核</a>
                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-8 col-md-7">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">活动表单</h4>
                            </div>
                            <div class="content">
                                <form action="activityUpdate" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>活动单号</label>
                                                <input name="activityId" type="text" class="form-control border-input" readonly placeholder="活动单号" value="<%=acd.getActivityId() %>">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>活动主题</label>
                                                <input name="zhuti" type="text" class="form-control border-input" readonly placeholder="活动主题" value="<%=acd.getZhuti() %>">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>关键字</label>
                                                <input name="guanjianzi" type="text" class="form-control border-input" readonly placeholder="关键字" value="<%=acd.getGuanjianzi() %>">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>活动时间</label>
                                                <input name="shijian" type="text" class="form-control border-input" readonly placeholder="活动时间" value="<%=acd.getShijian() %>">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>活动地点</label>
                                                <input name="didian" type="text" class="form-control border-input" readonly placeholder="活动地点" value="<%=acd.getDidian() %>">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>举办方</label>
                                                <input name="jubanfang" type="text" class="form-control border-input" readonly placeholder="举办方" value="<%=acd.getJubanfang() %>">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>负责人</label>
                                                <input name="fuzeren" type="text" class="form-control border-input" readonly placeholder="负责人" value="<%=acd.getFuzeren() %>">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>联系方式</label>
                                                <input name="lianxifangshi" type="text" class="form-control border-input" readonly placeholder="联系方式" value="<%=acd.getLianxifangshi() %>">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>活动内容</label>
                                                <textarea name="neirong" class="form-control border-input" readonly placeholder="活动内容"><%=acd.getNeirong() %></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>赞助商</label>
                                                <input name="zanzhushang" type="text" class="form-control border-input" readonly placeholder="赞助商" value="<%=acd.getZanzhushang() %>">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>赞助资金</label>
                                                <input name="zanzhuzijin" type="text" class="form-control border-input" readonly placeholder="赞助资金" value="<%=acd.getZanzhuzijin() %>">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>审批资金</label>
                                                <input name="shenpizijin" type="text" class="form-control border-input" readonly placeholder="审批资金" value="<%=acd.getShenpizijin() %>">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row" style="display:none">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>图片地址</label>
                                                <input name="tupian" type="text" class="form-control border-input" readonly placeholder="图片" value="<%=acd.getTupian() %>">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                    	<div class="col-md-6">
                                    		<div class="form-group">
                                    			<label>&nbsp;</label>
                                    			<input name="zhuangtai"  type="radio" value="通过" onclick="checkRadio()"><label>通过</label>
                                    		</div>
                                    	</div>
                                    	<div class="col-md-6">
                                    		<div class="form-group">
                                    			<label>&nbsp;</label>
                                    			<input name="zhuangtai" id="zhuangtaiNo" type="radio" value="不通过" onclick="checkRadio()"><label>不通过</label>
                                    		</div>
                                    	</div>
                                    </div>
                                    
                                    <div class="row" id="Radio" style="display: none;">
                                    	<div class="col-md-6">
                                    		<div class="form-group">
                                    			<label>理由</label>
                                    			<input name="gaishu" type="text" class="form-control border-input" placeholder="不通过的理由">
                                    		</div>
                                    	</div>
                                    </div>

                                    <div class="text-center">
                                        <button type="submit"  class="btn btn-info btn-fill btn-wd">提交审核</button>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
</div>


</body>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="assets/js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <!---<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>--->

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="assets/js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="assets/js/demo.js"></script>

</html>

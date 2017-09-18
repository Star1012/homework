<%@ page language="java" import="java.util.*,com.clubmanage.model.Users,com.clubmanage.model.Activity" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Users um = (Users) session.getAttribute("userKey");
Activity acd = (Activity) session.getAttribute("activitysEndKey");
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
                <li class="active">
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
                <li>
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
                    <a class="navbar-brand" href="#">结束活动</a>
                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-8 col-md-7">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">活动总结</h4>
                            </div>
                            <div class="content">
                                <form method="post">
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
                                    	<div class="col-md-12">
                                    		<div class="form-group">
                                    			<label>活动总结</label>
                                    			<textarea name="gaishu" class="form-control border-input"></textarea>
                                    		</div>
                                    	</div>
                                    </div>

                                    <div class="text-center">
                                        <button type="button"  onClick="javascrtpt:window.location.href='index.jsp'">结束活动</button>
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

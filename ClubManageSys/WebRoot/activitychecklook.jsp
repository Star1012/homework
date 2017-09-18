<%@ page language="java" import="java.util.*,com.clubmanage.model.Users,com.clubmanage.model.Activity" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Users um = (Users) session.getAttribute("userKey");
List<Activity> acl = (List<Activity>) session.getAttribute("activitysWeiKey");
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
                <li class="active">
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
		
		<div>
			&nbsp;
		</div>
		
		<div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">未审核的活动列表</h4>
                            </div>
                            <div class="content table-responsive table-full-width">
								<table class="table table-hover">
									<thead>
										<th>活动编号</th>
										<th>活动主题</th>
										<th>活动时间</th>
										<th>活动地点</th>
										<th>关键字</th>
										<th>审核</th>
									</thread>
									<tbody>
										<%
											for(int i=0;i<acl.size();i++)
											{
												Activity a = acl.get(i);
										%>
										<tr>
											<td><%=a.getActivityId() %></td>
											<td><%=a.getZhuti() %></td>
											<td><%=a.getShijian() %></td>
											<td><%=a.getDidian() %></td>
											<td><%=a.getGuanjianzi() %></td>
											<td><a href="activityTrans.action?activityId=<%=a.getActivityId() %>">审核</a></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
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
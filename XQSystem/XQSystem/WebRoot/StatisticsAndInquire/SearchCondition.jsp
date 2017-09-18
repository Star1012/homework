<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SearchCondition.jsp' starting page</title>
    
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
    <div class="title">
    <form action="StatisticsAndInquire/searchDemand.action" target='frmfoot' method="post">
	<table class="table1" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="96">时效状态</td>
			<td class="td2" colspan="8">
				<select name="status">
					<option value="default">请选择</option>
					<option value="1">待审核</option>
					<option value="2">形式审核未通过</option>
					<option value="3">形式审核通过</option>
					<option value="4">部门审核通过</option>
					<option value="5">部门审核未通过</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="9" class="td1">单位机构——————————————————————————————————————————————————</td>
		</tr>
		<tr>
			<td>机构名称</td>
			<td width="168"><input type="text" name="oname"></td>
			<td width="84">
				<select name="onSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
			<td width="96">归口管理单位</td>
			<td width="168"><input type="text" name="tmd"></td>
			<td width="84">
				<select name="tmSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
			<td width="64">所在地域</td>
			<td width="168"><input type="text" name="area"></td>
			<td width="84">
				<select name="arSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>法人代表</td>
			<td><input type="text" name="lperson"></td>
			<td>
				<select name="lpSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
			<td>联系人</td>
			<td><input type="text" name="contacts"></td>
			<td>
				<select name="coSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
			<td>机构属性</td>
			<td><input type="text" name="attribute"></td>
			<td>
				<select name="atSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="9" class="td1">技术需求——————————————————————————————————————————————————</td>
		</tr>
		<tr>
			<td>需求名称</td>
			<td><input type="text" name="dname"></td>
			<td>
				<select name="dnSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
			<td>关键字</td>
			<td><input type="text" name="key"></td>
			<td>
				<select name="keSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
			<td>拟投资金</td>
			<td><input type="text" name="investment"></td>
			<td>
				<select name="invSearchType">
					<option value="default">请选择</option>
					<option value="slur">模糊查找</option>
					<option value="accuracy">准确查找</option>
				</select>
			</td>
		</tr>
		<tr>
		  <td>需求解决方式</td>
		  <td><input type="text" name="solution" /></td>
		  <td><select name="soSearchType">
		    <option value="default">请选择</option>
		    <option value="slur">模糊查找</option>
		    <option value="accuracy">准确查找</option>
	      </select></td>
		  <td>科技活动类型</td>
		  <td><input type="text" name="type" /></td>
		  <td><select name="tySearchType">
		    <option value="default">请选择</option>
		    <option value="slur">模糊查找</option>
		    <option value="accuracy">准确查找</option>
	      </select></td>
		  <td>学科分类</td>
		  <td><input type="text" name="subject" /></td>
		  <td><select name="suSearchType">
		    <option value="default">请选择</option>
		    <option value="slur">模糊查找</option>
		    <option value="accuracy">准确查找</option>
	      </select></td>
  		</tr>
		<tr>
		  <td>需求所属领域</td>
		  <td><input type="text" name="servics" /></td>
		  <td>
		  	<select name="seSearchType">
		    <option value="default">请选择</option>
		    <option value="slur">模糊查找</option>
		    <option value="accuracy">准确查找</option>
	      	</select>
	      </td>
		  <td>需求应用行业</td>
		  <td><input type="text" name="industry" /></td>
		  <td><select name="indSearchType">
		    <option value="default">请选择</option>
		    <option value="slur">模糊查找</option>
		    <option value="accuracy">准确查找</option>
	      </select></td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
  		</tr>
		<tr>
		  <td colspan="9">时间条件<span class="td1">——————————————————————————————————————————————————</span></td>
  		</tr>
		<tr>
		 <td>施行日期</td>
		  <td colspan="3">
		  从<input name="sstartTime" type="text" size="22" />
		  至 <input name="estartTime" type="text" size="22" /></td>
		  <td>截止日期</td>
		  <td colspan="3">
		  从<input name="sendTime" type="text" size="22" />
		  至<input name="eendTime" type="text" size="22" /></td>
		  <td><input name="提交" type="submit" value="查询"/></td>
		</tr>
	</table>
	</form>
	</div>
  </body>
</html>

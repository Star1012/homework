<%@ page language="java" import="java.util.*,com.dbEntity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
DemandCollect dc = (DemandCollect)request.getAttribute("demandCollect");
String key[] = dc.getDKey().split("、");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showDemand.jsp' starting page</title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/showUpdateDemand.js">
	</script>
  </head>
  
  <body onload="loading()">
  <form id="form1" action="OrgInstitution/updateDemand.action?id=<%=dc.getDemandId() %>" method="post">
    <table width="80%" border="1" align="center">
      <tr>
    	<td width="16%">*技术需求名称</td>
    	<td colspan="2"><input type="text" name="dname" value="<%=dc.getDName() %>"></td>
   	 	<td width="12%">*需求时限</td>
    	<td width="34%" colspan="2">
    		开始时间<input type="text" name="startTime" value="<%=dc.getStartTime() %>">
    		截止时间<input type="text" name="endTime" value="<%=dc.getEndTime() %>">
    	</td>
      </tr>
  	  <tr>
    	<td rowspan="3">*技术需求概述</td>
    	<td colspan="5">
    	1.主要问题:<input type="text" name="theme" value="<%=dc.getTheme() %>">
    	</td>
  	  </tr>
  	  <tr>
  	  	<td colspan="5">
    	2.技术关键:<input type="text" name="techkey" value="<%=dc.getTechKey() %>">
    	</td>
  	  </tr>
  	  <tr>
  	  	<td colspan="5">
    	3.预期目标:<input type="text" name="target" value="<%=dc.getExpectTarget() %>">
    	</td>
  	  </tr>
  	  <tr>
    	<td>*关键字</td>
    	<td colspan="5">
    		<input type="text" name="key1" value="<%=key[0] %>">
    		<input type="text" name="key2" value="<%=key[0] %>">
    		<input type="text" name="key3" value="<%=key[0] %>">
    		<input type="text" name="key4" value="<%=key[0] %>">
    		<input type="text" name="key5" value="<%=key[0] %>">
    	</td>
      </tr>
      <tr>
      	<td>*拟投入资金</td>
      	<td colspan="5">
      		<input type="text" name="investment" value="<%=dc.getPlanInvestment() %>">
      	</td>
      </tr>
      <tr>
      	<td rowspan="2">*技术需求合作模式</td>
      	<td colspan="5">
      		<label><input type="radio" name="model" value="基础研究" onClick="Close('otherModel')">独立开发</label>
        	<label><input type="radio" name="model" value="应用研究" onClick="Close('otherModel')">技术转让</label>
       		<label><input type="radio" name="model" value="试验发展" onClick="Close('otherModel')">技术入股</label>
        	<label><input type="radio" name="model" value="研究发展与应用成果" onClick="Close('otherModel')">合作开发</label>
        	<label><input type="radio" name="model" value="技术推广与科技服务" onClick="Show('otherModel')">其他</label>
        	<label><input class="input5" type="text" id="otherModel" name="model" style="display:none;"></label>
      	</td>
      </tr>
      <tr>
      	<td>技术合作单位</td>
      	<td colspan="4">
      		<input type="text" name="intention" value="<%=dc.getCooIntention() %>>">
      	</td>
      </tr>
  	  <tr>
    	<td>*科技活动类型</td>
    	<td colspan="5">
    		<label><input type="radio" name="type" id="S_Type" value="基础研究" onclick="getUI();">基础研究</label>
        	<label><input type="radio" name="type" id="S_Type" value="应用研究" onclick="getUI();">应用研究</label>
        	<label><input type="radio" name="type" id="S_Type" value="试验发展" onclick="getUI();">试验发展</label>
        	<br>
        	<label><input type="radio" name="type" id="S_Type" value="研究发展与应用成果" onclick="getUI();">研究发展与应用成果</label>
        	<label><input type="radio" name="type" id="S_Type" value="技术推广与科技服务" onclick="getUI();">技术推广与科技服务</label>
        	<label><input type="radio" name="type" id="S_Type" value="生产性活动" onclick="getUI();">生产性活动</label>
    		</td>
      </tr>
  	  <tr>
    	<td>学科分类（基础研究填写）</td>
    	<td colspan="5">
    		<select name="firstSubjects" id="firstSubjects" onchange="refreshList('firstSubjects','secondSubjects','1','subject');">
                <option value="default">请选择</option>
                <%
					ArrayList sublist = (ArrayList)request.getAttribute("firstSubject");
					for(int i=0;i<sublist.size();i++)
					{
						Subjects firstSub = (Subjects)sublist.get(i);
				%>
				<option value="<%=firstSub.getCode() %>"><%=firstSub.getName()%></option>
				<%
					}
				%>
            </select>
            <select name="secondSubjects" id="secondSubjects" onchange="refreshList('secondSubjects','thirdSubjects','2','subject');">
                <option value="default">请选择</option>
            </select>
            <select name="thirdSubjects" id="thirdSubjects">
                <option value="default">请选择</option>
            </select>
    	</td>
  	  </tr>
  	  <tr>
    	<td>需求技术所属领域（非基础研究填写）</td>
    	<td colspan="5">
    		<label><input type="checkbox" name="servics" value="电子信息技术" onClick="Close('otherServics')">电子信息技术</label>
        	<label><input type="checkbox" name="servics" value="光机电一体化" onClick="Close('otherServics')">光机电一体化 </label>
        	<label><input type="checkbox" name="servics" value="软件" onClick="Close('otherServics')">软件</label>
        	<label><input type="checkbox" name="servics" value="生物制药技术" onClick="Close('otherServics')">生物制药技术</label>
       	 	<label><input type="checkbox" name="servics" value="新材料及应用技术" onClick="Close('otherServics')">新材料及应用技术</label>
        	<br>
        	<label><input type="checkbox" name="servics" value="先进制造技术" onClick="Close('otherServics')">先进制造技术</label>
        	<label><input type="checkbox" name="servics" value="现代农业技术" onClick="Close('otherServics')">现代农业技术</label>
        	<label><input type="checkbox" name="servics" value="新能源与高效节能技术" onClick="Close('otherServics')">新能源与高效节能技术</label>
        	<br>
        	<label><input type="checkbox" name="servics" value="资源与环境保护新技术" onClick="Close('otherServics')">资源与环境保护新技术</label>
        	<label><input type="checkbox" name="servics" value="其他技术" onClick="Show('otherServics')">其他技术（注明）</label>
       	    <label><input class="input5" type="text" id="otherServics" name="otherServics" style="display:none;"></label>
    	</td>
  	  </tr>
  	  <tr>
    	<td>需求技术应用行业</td>
    	<td colspan="5">
    		<select name="firstIndustry" id="firstIndustry" onchange="refreshList('firstIndustry','secondIndustry','1','industry');">
                <option value="default">请选择</option>
                <%
					ArrayList naelist = (ArrayList)request.getAttribute("firstNationalEconomy");
					for(int i=0;i<naelist.size();i++)
					{
						NationalEconomy firstNae = (NationalEconomy)naelist.get(i);
				%>
				<option value="<%=firstNae.getCode() %>"><%=firstNae.getName() %></option>
				<%
					}
				 %>
            </select>
            <select name="secondIndustry" id="secondIndustry" onchange="refreshList('secondIndustry','thirdIndustry','2','industry');">
                <option value="default">请选择</option>
            </select>
            <select name="thirdIndustry" id="thirdIndustry">
                <option value="default">请选择</option>
            </select>
    	</td>
  	  </tr>
  	  <tr>
  	  	<td colspan="3"><input type="submit" value="保存" name="save"></td>
  	  	<td colspan="3"><input type="submit" value="提交" name="add"></td>
  	  </tr>
  	</table>
  </form>
  </body>
</html>

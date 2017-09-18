<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,com.dbEntity.Subjects,com.dbEntity.NationalEconomy"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新建需求征集表</title>
<link rel="stylesheet"  type="text/css" href="css/CreateDemand.css"/>
<script type="text/javascript" src="js/CreateDemand.js">
</script>
<style type="text/css">
html,body,div,li,form,input,th,td{margin:0;padding:0;font-family:宋体; font-size:100%; }
ul,ol,li{ list-style:none;}
a:link, a:visited{ color:#FF00FF; text-decoration:none;}
a:hover{ color:#12b7f5;}

table {
	border-collapse: collapse;
	border: none;
	margin: 60px auto;
	width: 800px;
}

th, td {
	border: Groove #000000 2px;
	height: 20px;
	margin-bottom: 12px; line-height: 25px;  opacity: 1.0;
}

div ,td{
	text-align: left;
}
</style>
</head>
<body style="background-color:#E0F0F8;">

<form action="createDemand" id="form1" method="post">
<center>
<table class="table" border="1" >
    <caption><h3>技术需求申请报</h3></caption>
    <tr>
        <td class="td1">*技术需求名称</td>
        <td class="td2"><input type="text" class="input1" id="dname" name="dname"></td>
        <td class="td3">*需求时限</td>
        <td class="td4"><input type="text" class="input2" id="startTime" name="startTime" onblur="isValidityYear()">年至<input type="text" class="input2" id="endTime" name="endTime" onblur="isValidityYear()">年</td>
        <td class="td6" id="timeText"></td>
    </tr>
    <tr class="tr1">
        <td colspan="5">*重大科技需求概述</td>
    </tr>
    <tr class="tr2">
    	<td class="td5" colspan="4">1、主要问题(需要解决的重大技术问题，限500字以内)</td>
    	<td class="td6" id="text1">0/500</td>
    </tr>
    <tr class="tr3">
        <td colspan="5">
        <textarea class="textarea" onkeyup="showTextLength('text1','theme')" id="theme" name="theme"></textarea>
        </td>
    </tr>
     <tr class="tr2">
    	<td class="td5" colspan="4">2、技术关键(所需的关键技术、主要指标，限500字以内)</td>
    	<td class="td6" id="text2">0/500</td>
    </tr>
    <tr class="tr3">
        <td colspan="5">
        <textarea class="textarea" onkeyup="showTextLength('text2','tech-key')" id="techkey" name="techkey"></textarea>
        </td>
    </tr>
     <tr class="tr2">
    	<td class="td5" colspan="4">3、预期目标(技术创新性、社会经济效益，限500字以内)</td>
    	<td class="td6" id="text3">0/500</td>
    </tr>
    <tr class="tr3">
        <td colspan="5">
        <textarea class="textarea" onkeyup="showTextLength('text3','target')" id="target" name="target"></textarea>
        </td>
    </tr>
    
    <tr>
        <td>*关键字:</td>
        <td colspan="4">
        <input class="input3" type="text" id="key1" name="key1">
        <input class="input3" readonly="readonly" type="text" id="key2" name="key2" onkeyup="ChangeState()">
        <input class="input3" readonly="readonly" type="text" id="key3" name="key3" onkeyup="ChangeState()">
        <input class="input3" readonly="readonly" type="text" id="key4" name="key4" onkeyup="ChangeState()">
        <input class="input3" readonly="readonly" type="text" id="key5" name="key5" onkeyup="ChangeState()">
        </td>
    </tr>
    
    <tr>
    	<td class="td1">拟投入资金总额</td>
    	<td colspan="4"><input type="text" id="investment" name="investment" class="input4">万元</td>
    </tr>
    <tr>
        <td class="td1">*技术需求合作模式</td>
        <td colspan="4">
        <label><input type="radio" name="model" value="基础研究" onClick="Close('otherModel')">独立开发</label>
        <label><input type="radio" name="model" value="应用研究" onClick="Close('otherModel')">技术转让</label>
        <label><input type="radio" name="model" value="试验发展" onClick="Close('otherModel')">技术入股</label>
        <label><input type="radio" name="model" value="研究发展与应用成果" onClick="Close('otherModel')">合作开发</label>
        <label><input type="radio" name="model" value="技术推广与科技服务" onClick="Show('otherModel')">其他</label>
        <label><input class="input5" type="text" id="otherModel" name="model" style="display:none;"></label>
        </td>
    </tr>
    <tr>
        <td class="td1"></td>
        <td colspan="4">
        	合作意向单位
            <input type="text" name="intention" id="intention" onkeyup="findName('intention','popup','complete_table','message_body');">
            <div style="position:absolute;" id="popup">
                <table id="complete_table" bgcolor="#fffafa" border="0" cellspacing="0" cellpadding="0">
                    <tbody id="message_body"></tbody>
                </table>
            </div>
        </td>
    </tr>
    <tr>
        <td class="td1">科技活动类型</td>
        <td colspan="4">
        <label><input type="radio" name="type" id="Type" value="基础研究" onclick="getUI();">基础研究</label>
        <label><input type="radio" name="type" id="S_Typ" value="应用研究" onclick="getUI();">应用研究</label>
        <label><input type="radio" name="type" id="S_Type" value="试验发展" onclick="getUI();">试验发展</label>
        <br>
        <label><input type="radio" name="type" id="S_Type" value="研究发展与应用成果" onclick="getUI();">研究发展与应用成果</label>
        <label><input type="radio" name="type" id="S_Type" value="技术推广与科技服务" onclick="getUI();">技术推广与科技服务</label>
        <label><input type="radio" name="type" id="S_Type" value="生产性活动" onclick="getUI();">生产性活动</label>
        </td>
    </tr>
    <tr id="sub" style="display:none;">
        <td class="td1">学科分类</td>
        <td colspan="4">
            <select name="firstSubjects" id="firstSubjects" onchange="refreshList('firstSubjects','secondSubjects','1','subject');">
                <option value="default">请选择</option>
				<%
					ArrayList sublist = (ArrayList)session.getAttribute("firstSubject");
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
    <tr id="servics" style="display:none;">
        <td class="td1">需求技术所属领域</td>
        <td colspan="4">
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
    <tr id="industry" style="display:none;">
        <td id="td1">需求技术应用行业</td>
        <td colspan="4">
        	<select name="firstIndustry" id="firstIndustry" onchange="refreshList('firstIndustry','secondIndustry','1','industry');">
                <option value="default">请选择</option>
				<%
					ArrayList naelist = (ArrayList)session.getAttribute("firstNationalEconomy");
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
    
    <tr style="display:none">
        <td colspan="5" align="center">
        <input type="submit" value="保存" name="save">
        <input type="submit" value="提交" name="add">
        </td>
    </tr>
</table>

	<button type="button" onclick="javascrtpt:window.location.href='../WebUI/index.html'">提交</button>

</center>
</form>
</body>
</html>
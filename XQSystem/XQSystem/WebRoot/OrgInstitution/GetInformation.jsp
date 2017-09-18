<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"scr="../JavaScript/MapandParamMap.js"></script>
<script type="text/javascript" src="js/GetInformation.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册新用户</title>
<style type="text/css">
table td{text-align:left;}
.td1{color:#C2C2C2;}
</style>
</head>
<body>
<form action="register.action" method="post" id="form" target="_top">
<center>
<table>
    <caption><h3>机构注册</h3></caption>
    <tr>
        <td>单位机构用户名:</td>
        <td><input type="text" name="code" id="code" onblur="Validity('O_Code');" ></td>
        <td class="td1" id="CodeMes">如：12345678-9</td>
    </tr>
    <tr>
        <td id="a" tabindex="0">密码:</td>
        <td><input type="password" name="password" id="password" onblur="Validity('Pwd');"></td>
        <td class="td1" id="pwdMes">6-18位以字母开头，不含特殊字符</td>
    </tr>
    <tr>
        <td tabindex="0">确认密码:</td>
        <td><input type="password" name="conPwd" id="conPwd" onblur="Validity('conPwd');"></td>
		<td class="td1" id="conPwdMes">重新输入密码</td>    
    </tr>
    <tr>
        <td tabindex="0">机构全称:</td>
        <td><input type="text" name="username" id="username" onblur="Validity('O_Name');"></td>
    	<td class="td1" id="nameMes">请输入完整的机构名称</td>
    </tr>
    <tr>
    	<td>单位机构地址</td>
    	<td><input type="text" name="area" id="area"></td>
    </tr>
    <tr>
    	<td>单位机构所在地域</td>
    	<td><input type="text" name="address" id="address"></td>
    </tr>
    <tr>
        <td>网址:</td>
        <td><input type="text" name="url" id="url" onblur="Validity('O_Url');"></td>
    	<td class="td1" id="urlMes"></td>
    </tr>
    <tr>
        <td tabindex="0">电子邮箱:</td>
        <td>
            <input type="text" name="email" id="email" onblur="Validity('O_Email');">
        </td>
    	<td class="td1" id="emailMes">请输入正确邮箱地址</td>
    </tr>
    <tr>
        <td tabindex="0">法人代表:</td>
        <td><input type="text" name="lperson" id="lperson" onblur="Validity('O_Lperson');"></td>
    	<td class="td1" id="lpersonMes">不能为空</td>
    </tr>
    <tr>
        <td tabindex="0">邮政编码:</td>
        <td><input type="text" name="postcode" id="postcode" onblur="Validity('O_Postcode');"></td>
    	<td class="td1" id="postcodeMes">请输入正确的邮政编码</td>
    </tr>
    <tr>
        <td tabindex="0">联系人:</td>
        <td><input type="text" name="contacts" id="contacts" onblur="Validity('O_Contacts');"></td>
    	<td class="td1" id="contactsMes">不能为空</td>
    </tr>
    <tr>
        <td tabindex="0">固定电话:</td>
        <td><input type="text" name="plphone" id="plphone" onblur="Validity('O_PlPhone');"></td>
    	<td class="td1" id="plPhoneMes">不能为空</td>
    </tr>
    <tr>
        <td tabindex="0">手机:</td>
        <td><input type="text" name="mbphone" id="mbphone" onblur="Validity('O_MbPhone');"></td>
    	<td class="td1" id="mbPhoneMes">请输入11位手机号码</td>
    </tr>
    <tr>
        <td tabindex="0">传真:</td>
        <td><input type="text" name="fax" id="fax" onblur="Validity('O_Fax');"></td>
    	<td class="td1" id="faxMes">请输入正确的传真号</td>
    </tr>
    <tr>
        <td>机构属性:</td>
        <td colspan="2">
           <input type="radio" name="attribute" id="attribute" value="企业">企业
           <input type="radio" name="attribute" id="attribute" value="企业">高等院校
           <input type="radio" name="attribute" id="attribute" value="企业">研究机构
           <input type="radio" name="attribute" id="attribute" value="企业">其他
        </td>
    </tr>
    <tr>
        <td>归口部门:</td>
        <td>
            <select name="tmd" id="tmd">
                <option value="0">企业</option>
                <option value="1">高等院校</option>
                <option value="2">研究机构</option>
                <option value="4">其他</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>机构简介:</td>
        <td class="td1" colspan="2" id="overViewMes">不能超过500字</td>
    </tr>
    <tr>
        <td colspan="3">
        <textarea rows="20" cols="40" name="overView" id="overView">
        </textarea>
        </td>
    </tr>
    <tr>
        <td colspan=2>
        <input  type="submit" value="注册"> <!--onclick="verify()"-->
        <input  type="reset" value="重置">
        </td>
    </tr>
</table>
</center>
</form>
</body>
</html>
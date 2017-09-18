var XMLHttpReq;
var upper;
var lower;
var completeDiv;
var inputField;
var completeTable;
var completeBody;

function loading()
{
	alert("aaa");
}

function isValidityYear()
{
	var start = document.getElementById("D_StartTime");
	var end = document.getElementById("D_EndTime");
	var text = document.getElementById("timeText");
	var myDate = new Date();
	if(start.value<myDate.getFullYear())
	{	
		text.innerText = "起始日期需大于等于当前日期!"
		start.foucus();
		end.readOnly = true;
	}
	else
	{
		text.innerText = "";
		end.readOnly = false;
	}
	if(end!="")
	{
		if(end.value<start.value)
		{
			text.innerText = "截止日期应大于起始日期！"
		}
		else
		{
			text.innerText = ""
		}
	}	
}

//显示文本长度
function showTextLength(location,text)
{
	
	var location = document.getElementById(location);
	var text = document.getElementById(text);
	location.innerText = text.value.length+"/500";
}


//按研究类型显示学科分类或技术应用行业
function getUI()
{
	var n = document.getElementById("form1").S_Type;
	var S_type;
	for(var i=0;i<n.length;i++)
	{
		if(n.item(i).checked)
		{
			S_type = n.item(i).value;
			break;
		}
		else
		{
			continue;
		}
	}
	
	var servics = document.getElementById("servics");
	var sub = document.getElementById("sub");
	var industry = document.getElementById("industry");
	if(S_type == ("基础研究"))
	{
		servics.style.display="none";
		industry.style.display="none";
		sub.style.display="table-row";
	}
	else
	{
		sub.style.display="none";
		servics.style.display="table-row";
		industry.style.display="table-row";
	}
}


//关键字依次录入
function ChangeState()
{
	var D_key1 = document.getElementById('D_Key1');
	var D_key2 = document.getElementById('D_Key2');
	var D_key3 = document.getElementById('D_Key3');
	var D_key4 = document.getElementById('D_Key4');
	var D_key5 = document.getElementById('D_Key5');
	
	if(D_key1.value!="")
	{
		D_key2.readOnly = false;
	}
	else
	{
		D_key2.value = false;
		D_key2.readOnly = true;
	}
	
	if(D_key2.value!="")
	{
		D_key3.readOnly = false;
	}
	else
	{
		D_key3.value="";
		D_key3.readOnly = true;
	}
	
	if(D_key3.value!="")
	{
		D_key4.readOnly = false;
	}
	else
	{
		D_key4.value="";
		D_key4.readOnly = true;
	}
	
	if(D_key4.value!="")
	{
		D_key5.readOnly = false;
	}
	else
	{
		D_key5.value="";
		D_key5.readOnly = true;
	}
}

//隐藏合作模式点击除其他外的输入框
function Close(id)
{
	var widget = document.getElementById(id);
	widget.style.display="none";
}
//展示合作模式点击其他后的输入框
function Show(id)
{
	var widget = document.getElementById(id);
	widget.style.display="table-row";
}

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
function refreshList(upperId,lowerId,tier,action){
	upper = document.getElementById(upperId).value;
	lower = document.getElementById(lowerId);
	if(upper ==""){
		clearList();
		return;
	}
	var url ="CreateDemand?upper="+upper+"&tier="+tier+"&action="+action;
	createXMLHttpRequest();
	XMLHttpReq.open("GET",url,true);
	XMLHttpReq.send(null);//发送请求
	
	XMLHttpReq.onreadystatechange =processListResponse;//指定响应函数
	
}

//处理返回信息
function processListResponse(){
	if(XMLHttpReq.readyState == 4){//判断对象状态
		if(XMLHttpReq.status == 200){//信息已经成功返回，开始处理信息
			updateList();
		}else{
			window.alert("您所请求的页面有异常。");
		}
	}
}

//更新列表框中列表项函数
function updateList(){
	clearList();
	//clearList("thirdSubjects");
	
	var results = XMLHttpReq.responseXML.getElementsByTagName("name");
	var values = XMLHttpReq.responseXML.getElementsByTagName("value");
	var option = null;
	
	option = document.createElement("option");
	option.appendChild(document.createTextNode('请选择'));
	option.value="default";
	lower.appendChild(option);
	for(var i=0;i<results.length;i++){
		option = document.createElement("option");
		option.appendChild(document.createTextNode(results[i].firstChild.nodeValue));
		option.value = values[i].firstChild.data;
		lower.appendChild(option);
	}
}
//请除列表框中原有选项的函数
function clearList(){
	while(lower.childNodes.length>0){
		lower.removeChild(lower.childNodes[0]);
	}
}

//发送匹配请求函数
function findName(inputArea,popupArea,messageTable,messageBody){
	inputField = document.getElementById(inputArea);
	completeTable = document.getElementById(popupArea);
	completeBody = document.getElementById(messageTable);
	completeDiv = document.getElementById(messageTable);
	
	if(inputField.value.length>0){
    	createXMLHttpRequest();
	    var url = "CreateDemand?action=match&names="+inputField.value;
	    //编码
	    url = encodeURI(url);
	    url = encodeURI(url);
	    XMLHttpReq.open("GET",url,true);
	    XMLHttpReq.send(null);//发送请求
	    XMLHttpReq.onreadystatechange = processMatchResponse;//响应指定函数
	    
	}
    else{
    	clearNames();
    }
}


//处理返回信息匹配函数
function processMatchResponse(){
	if(XMLHttpReq.readyState == 4){//判断对象状态
		if(XMLHttpReq.status == 200){//信息已经成功返回，开始处理信息
		
			setNames(XMLHttpReq.responseXML.getElementsByTagName("res"));
		}
    	else{
    		window.alert("您所请求的页面有异常。");
    	}
    }
}

//生成与输入内容匹配行
function setNames(names){
	clearNames();
	var size = names.length;
	setOffsets();
	
	var row,cell,txtNode;
	for(var i=0;i<5;i++)
	{
		var nextNode = names[i].firstChild.data;
		row = document.createElement("tr");
		cell = document.createElement("td");
		
		cell.onmouseout = function(){this.className='mouseOver';};
		cell.onmouseover = function(){this.className='mouseOut';};
		cell.setAttribute("bgcolor","#FFFAFA");
		cell.setAttribute("border","0");
		cell.onclick = function() {completeField(this);};
		
		txtNode = document.createTextNode(nextNode);
		cell.appendChild(txtNode);
		row.appendChild(cell);
		completeBody.appendChild(row);
		
	}
}

//设置显示位置
function setOffsets(){
	completeDiv.style.width = inputField.offsetWidth+"px";
	var left = calculateOffset(inputField,"offsetLeft");
	var top = calculateOffset(inputField,"offsetTop")+inputField.offsetHeight;
	completeDiv.style.border = "black 1px solid";
	completeDiv.style.left = left +"px";
	completeDiv.style.top = top+"px";
}

//计算显示位置
function calculateOffset(field,attr){
	var offset = 0;
	while(field){
		offset +=field[attr];
		field = field.offsetParent;
	}
	return offset;
}

//填写输入框
function completeField(cell){
	inputField.value = cell.firstChild.nodeValue;
	clearNames();
}

//清除自动完成行
function clearNames(){
	var ind = completeBody.childNodes.length;
	for(var i = ind - 1;i>=0;i--){
		completeBody.removeChild(completeBody.childNodes[i]);
	}
	completeDiv.style.border = "none";
}
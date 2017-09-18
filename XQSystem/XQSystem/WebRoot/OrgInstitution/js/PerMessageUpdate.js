/**
*MAP类
*实现了类似于java语言中的Map接口的常用方法
*/
function Map(){
	//key集
	this.keys = new Array();
	//value集
	this.values = new Array();
	//添加key-value进Map
	this.put = function(key,value){
		if(key == null || key ==undefined)
			return;
		var length = this.size();
		for(var i = 0;i < length;i++){
			//如果keys数组中有相同的记录，则不覆盖原记录的值
			if(this.keys[i] == key)
				this.values[i] == value;
		}
		this.keys.push(key);
		this.values.push(value);
	};
	
	//获取指定key的value
	this.get = function(key){
		var length = this.size();
		for(var i = 0;i < length;i++){
			if(this.keys[i] == key){
				return this.values[i];
			}else{
				continue;
			}
			return null;
		}
	};
	
	//移除指定key所对应的map
	this.remove = function(key){
		var length = this.size();
		for(var i = 0;i < length;i++){
			if(this.keys[i] == key){
				while(i<length-1){
					this.keys[i] = this.keys[i+1];
					this.values[i] = this.values[i+1];
					i++;
				}
				//处理最后一个元素
				this.keys.pop();
				this.value.pop();
				break;
			}
		}
	};
	
	//是否包含指定的key
	this.containsKey = function(key){
		var length = this.size();
		for(var i = 0;i < length;i++){
			if(this.keys[i] == key){
				return true;
			}
		}
		return false;
	};
	
	//包含记录总数
	this.size = function(){
		return this.keys.length;
	};
	
	//是否为空
	this.isEmpty = function(){
		return this.size() == 0 ? true:false;
	};
	
	//清空Map
	this.clear = function(){
		this.keys = new Array();
		this.values = new Array();
	};
	
	//将map转成字符串，格式：key1=value1,key2=value2
	this.toString = function(){
		var length = this.size();
		var str = "";
		for(var i = 0;i < length;i++){
			str = str + this.keys[i] + "=" +this.value[i];
			if(i!=length-1)
				str+=",";
		}
		return str;
	};
}


/**
*ParamMap类，用于存储HTTP请求中Get方法或者Post方法所传递的参数
*继承于Map类，但改写一些方法，以适合HTTP请求中的参数格式
*与Map不同之处有：ParamMap允许多个同名的"key"存在（即支持复选框等需要传递多个值得控件）
*toString方法返回的"key=value"对，以"&"号连接，二不是","等
*/
function ParamMap(){
	//继承Map类
	Map.call(this);
	//重写put方法,允许多个同名key存在
	this.put = function(key,value){
		if(key == null || key == undefined)
			return;
		this.keys.push(key);
		this.values.push(value);
	};
	
	//重写get方法，返回values数组
	this.get = function(key){
		var results = new Array();
		var length = this.size();
		for(var i = 0;i < length;i++){
			if(this.keys[i] == key)
				results.push(this.values[i]);
	    }
		return results;
	}
	
	//重写remove方法
	this.remove = function(key){
		var length = this.size();
		for(var i = 0;i < length;i++){
			if(this.keys[i] == key){
				while(i < length - 1){
					this.keys[i] = this.keys[i+1];
					this.values[i] = this.keys[i+1];
					i++;
				}
				//处理最后一个元素
				this.keys.pop();
				this.values.pop();
			}
		}
	};
	
	//重写toString方法，转成XMLHttpRequest.send(ajaxString)方法的参数格式形式
	//形如：key1=value1&key2=value2
	this.toString = function(){
		var length = this.size();
		var str = "";
		for(var i = 0;i < length;i++){
			str = str + this.keys[i] + "=" +this.values[i];
			if(i!=length - 1)
				str+="&";
		}
		return str;
	};
}

var XMLHttpReq;

//检验页面是否有值被修改

////创建XMLHttpReq对象
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

function sendRequest(){
	
	//封装参数
	var action="update";
	var O_code = document.getElementById("O_Code").innerText;
	var name = document.getElementById("O_Name").value;
	var address = document.getElementById("City").value;
	var url = document.getElementById("O_Url").value;
	var email = document.getElementById("O_Email").value;
	var lperson = document.getElementById("O_Lperson").value;
	var postcode = document.getElementById("O_Postcode").value;
	var contacts = document.getElementById("O_Contacts").value;
	var plphone = document.getElementById("O_PlPhone").value;
	var mbphone = document.getElementById("O_MbPhone").value;
	var fax = document.getElementById("O_Fax").value;
	var attribute = document.getElementById("O_Attribute").value;
	var tmd = document.getElementById("O_Tmd").value;
	var overview = document.getElementById("O_OverView").value;
	var map = new ParamMap();
	map.put("O_Code",O_code);
	map.put("O_Name",name);
	map.put("City",address);
	map.put("O_Url",url);
	map.put("O_Email",email);
	map.put("O_Lperson",lperson);
	map.put("O_Postcode",postcode);
	map.put("O_Contacts",contacts);
	map.put("O_PlPhone",plphone);
	map.put("O_MbPhone",mbphone);
	map.put("O_Fax",fax);
	map.put("O_Attribute",attribute);
	map.put("O_Tmd",tmd);
	map.put("O_OverView",overview);
	map.put("action",action);
	
	url="PerMessageUpdate?"+map.toString();
	url = encodeURI(url);
    url = encodeURI(url);
	createXMLHttpRequest();
	XMLHttpReq.open("GET",url,true);
	XMLHttpReq.send(null);
	
	XMLHttpReq.onreadystatechange = processResponse;
	
}

function processResponse(){
	if(XMLHttpReq.readyState == 4){
		if(XMLHttpReq.status == 200){
			var res = XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			if(res == "success")
			{
				window.location.href ="RegisterSuccess.html"
			}
			if(res == "fail")
			{
				window.location.href ="RegisterFail.html"
			}
		}
	}
}
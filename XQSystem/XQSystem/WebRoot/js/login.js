    var XMLHttpReq = false;
    var user;
    var pwd;
    var randcode;
    
    //创建XMLHttpReq对象
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
    
    //界面图片加载
   function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}
    
	function MM_preloadImages() { //v3.0
		
		
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
	
	function realodpage(){
		//alert(document.getElementById("shuaxin").src);
		$("#shuaxin").attr("src","login/randCode.jsp?"+Math.random());
	}
	
	function re(){
		setTimeout("realodpage()",10);
	}
	
	re();
	
    //用户输入合法性校验并向服务器发送请求，等待服务器响应用户名和密码验证
	function CheckCode(s) //有特殊字符为true  
	{
		var containSpecial = RegExp(/[(\ )(\~)(\!)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);
		return (containSpecial.test(s));
	}
	function isNum(str){
		for (ilen = 0; ilen < str.length; ilen++) {
                if (str.charAt(ilen) < '0' || str.charAt(ilen) > '9') {
                       return false;
                    }
                }
            return true;
	}
	
	function IMG1_onclick2() {
		user = document.getElementById("username");
		pwd = document.getElementById("password");
		randcode = document.getElementById("randcode");
		var url = "login.action?username=" + user.value + "&password="
				+ pwd.value + "&randcode=" + randcode.value;
		url = encodeURI(url);
	    url = encodeURI(url);
	   
		if (user.value == "") {
			alert("用户名不能为空");
			
		} else if (pwd.value == "") {
			alert("密码不能为空");
			
		} else if (randcode.value == "") {
			alert("验证码不能为空");
			
		} else if (user.value.length > 20 || user.value.length < 6) {
			alert("用户名小于6位或大于20位");

		} else if (pwd.value.length > 20 || pwd.value.length < 6) {
			alert("密码小于6位或大于20位");

		} else if (!isNum(randcode.value)) {
				alert("验证码只能由数字组成!");
				randcode.value = "";
		} else if (CheckCode(user.value) && CheckCode(pwd.value)
					&& CheckCode(randcode.value)) {
				alert("有特殊字符请重新填写！");
				user.value = "";
				pwd.value = "";
				randcode.value = "";
		}else {
			window.location=url;
		}	
	}
	
	//发送请求函数
    function sendRequest(url){
    	createXMLHttpRequest();
    	XMLHttpReq.open("GET",url,true);
    	XMLHttpReq.send(null);
    	XMLHttpReq.onreadystatechange = processResponse;
    }
    
    //响应回复
    function processResponse(){
    	if(XMLHttpReq.readyState == 4){
    		if(XMLHttpReq.status == 200){
    			var res = XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
    			if(res == "1")
    			{
    				window.location.href="main.html"
    			}
    			if(res == "-1")
    			{
    				window.alert("用户名不存在");
    				user.value="";
    				pwd.value="";
    				randcode.value="";
    			}
    			if(res == "0")
    			{
    				window.alert("密码错误");
    				pwd.value="";
    				randcode.value="";
    			}
    		}
    	}
    }
	
	//功能函数
    function test()
    {
    	alert("如果丢失密码，请与0311-85813216电话联系！");
    	}
	
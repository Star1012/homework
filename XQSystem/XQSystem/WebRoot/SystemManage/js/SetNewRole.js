function ccc() {
		doAjax("../Removeabc", null, function(rstText) {
			var result = eval('(' + rstText + ')');
			if (result.status == 'OK') {
				alert("查询成功");
				return true;
			} else {
				alert("查询失败");
				return false;
			}
		});
	}

	function getXMLHttpResquest() {
		var request;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				request = new ActiveXObject("Msxml2.XMLHTTP");
			}
		}
		return request;
	}

	function doAjax(url, params, callBack) {
		var req = getXMLHttpResquest();
		if (req != null) {
			req.onreadystatechange = function() {
				if (req.readyState == 4) {
					if (200 == req.status) {
						callBack(req.responseText);

					} else if (404 == req.status) {
						alert('404-HTTP请求路径错误！');
					} else if (500 == req.status) {
						alert('500-HTTP请求路径错误！');
					}

				}

			};
			req.open("POST", url, true);
			req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;;charset=utf-8");
			req.send(params);
		}
	}


//获取之前页面传的参数
function Request(argname)
    {
    var url = location.href;
    var arrStr = url.substring(url.indexOf("?")+1).split("&");
    for(var i =0;i<arrStr.length;i++)
    {
        var loc = arrStr[i].indexOf(argname+"=");
        if(loc!=-1)
        {
        	alert(arrStr[i].replace(argname+"=","").replace("?",""));
            return arrStr[i].replace(argname+"=","").replace("?","");
            break;
        }
        
    }
    return "";
}

function doAjax(){
	alert('aaa');
	$.ajax({
		url:"getPermission.action?role="+Request('role'),
		type:"post",
		success:function(data){
			alert(data);
			var obj=eval("("+data+")");  
	        alert(obj);  
	        for(var i in obj){  
	        	alert("ID:"+obj[i].manage+"\nName:"+obj[i].addManage);  
	        }
		},
		error:function(e){
			alert("错误！")
		}
	});
}
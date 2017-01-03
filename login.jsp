<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<%-- <script language="javascript"
	src="<%=request.getContextPath()%>/WebContent/script/jquery.min.js"></script> --%>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	
	
	var userId=get_userid();
	alert("进入ajax方法");
	$.ajax({
		type : "get",
		url : "<%=request.getContextPath()%>/c",
		contentType: "application/json; charset=UTF-8",  
		/* datatype : "json",
		  data : JSON.stringify({
			'name' : 'why',
			'password' :'123'
		}),  */
		// int pageIndex, int numberPerPage
		data: {'name':'why','password':'123','userId':userId,'pageIndex':1,'numberPerPage':100}, 
		success : succFunction ,
		error:	  erryFunction
		
	});
	function erryFunction() {  
        alert("error");  
    }  
    function succFunction(json) {  
    	alert("成功"+json['data'][2]['name']+json['tipMsg']);
    	//var json = eval(json1); //数组         
    	
   		var $message;
   		var data=json['data'];
  
 		$.each(data, function (index, item) {  
           alert("进入循环"+item.name);
           $message=$('<a href="#" class="list-group-item"><h4 class="list-group-item-heading" style="color:red">'
           		+item.name+'</h4><p class="list-group-item-text">'
           					+item.introduction+'</p></a>');
           $('#table').append($message)
        });
    };
});

function get_userid(){
	 var token = window.localStorage.getItem('token');
	 var cid;
	 if(token){
		 $.ajaxSetup({
			 headers:{
				 'x-access-token':token
			 } 
		 }
		);
		 $.ajax({
	         type: "GET",
	         url: url + "/currentUserId" ,
	         dataType: "json", //表示返回值类型，不必须
	         success: function (jsonResult) {
	             cid = jsonResult.value;
	         }
	     });
	 }
	 
//  return cid  ;//如果得到返回值则永此
	 return '10000';
}

function add(){
	
	window.location.href="<%=request.getContextPath()%>/student.do?method=add";
}

function del(id){
	$.ajax( {
	type : "POST",
	url : "<%=request.getContextPath()%>/student.do?method=del&id="
					+ id,
			dataType : "json",
			success : function(data) {
				if (data.del == "true") {
					alert("删除成功！");
					$("#" + id).remove();
				} else {
					alert("删除失败！");
				}
			},
			error : function() {
				alert("网络连接出错！");
			}
		});
	}


	
</script>

</head>
<body>
<div id="table" class="list-group">
		<a href="#" class="list-group-item active" style="background-color:red">
			<h4 class="list-group-item-heading">推荐用户</h4>
		</a>
		 <a href="#" class="list-group-item">
			<h4 class="list-group-item-heading">用户一</h4>
			<p class="list-group-item-text">演员、艺人</p>
		</a> 
		<a href="#" class="list-group-item">
			<h4 class="list-group-item-heading">用户二</h4>
			<p class="list-group-item-text">摇滚歌手</p>
		</a>
		
	</div>
</body>
</html>
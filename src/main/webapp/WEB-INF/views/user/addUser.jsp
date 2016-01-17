<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="/myframe/js/jquery/1.11.0/jquery-1.11.0.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			$('#saveBtn').click(function(){
				var obj = {};
				obj.name= $('#name').val();
				obj.phone=$('#phone').val();

				console.log(obj.name+"---"+obj.phone);
				console.log(JSON.stringify(obj));
				console.log($("#userForm").serialize());
				$.ajax({
					type: "POST",
					url: "/myframe/hello/addUser.htmls",
					dataType: "json",
					contentType: "application/json",
					data: JSON.stringify(obj),
					success: function (result) {
//						goBack();
					}
				});
			});
			$('#saveBtn3').click(function(){
				var content = $("#userForm").serialize();
				console.log(content);
				$.ajax({
					type: "POST",
					url: "/myframe/hello/addUser.htmls",
					data: content ,
					dataType: "json",
					success: function (msg) {

					}
				});


			});
		});
	</script>
</head>
<body>
	
<form id="userForm" action="/myframe/hello/addUser2.htmls" method="post">
	姓名：<input name="name" id="name"/><br>
	电话:<input name="phone" id="phone"/><br>
	<input  name="id">
	<input type="button" value="提交" id="saveBtn"/>
	<input type="submit" value="提交2" id="subBtn"/>
	<input type="button" value="提交3" id="saveBtn3"/>
</form>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0058)http://demo.cssmoban.com/cssthemes3/cpts_22_ek/index.html# -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<!-- Custom Theme files -->
<link href="static/Home_files/style.css" rel="stylesheet" type="text/css" media="all">
<!-- Custom Theme files -->

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 
<meta name="keywords" content="">
<!--Google Fonts-->
<link href="Home_files/css" rel="stylesheet" type="text/css">
<link href="Home_files/css(1)" rel="stylesheet" type="text/css">
<!--Google Fonts-->
</head>
<body>
<div class="adcenter">
<div align="center">
<ins class="adsbygoogle" style="display:block" data-ad-client="ca-pub-1542822386688301" data-ad-slot="5966457456" data-ad-format="auto"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>
</div>
</div>
<div class="login">
	<div class="login-top" style="text-align: center">
		<h1>登陆</h1>
		<% 
			String msg = (String)request.getAttribute("msg");
			if(msg != null){%>
			<span style="color: red;text-align: center"><%=msg%></span>
			<%}
		%>
		<form method="post" action="user">
			<input type="hidden" name="method" value="login">
			<input type="text" name="userName" placeholder="用户名" >
            <input type="password" name="password" placeholder="密码">
	   		<div class="forgot">
	    		<input type="submit" value="Login">
	    	</div>
	    </form>
	    
	</div>
	<div class="login-bottom">
		<h3>新用户 &nbsp;<a href="Register.jsp">注册</a>&nbsp;</h3>
	</div>
</div>	
</body></html>
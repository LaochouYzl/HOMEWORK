<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0058)http://demo.cssmoban.com/cssthemes3/cpts_22_ek/index.html# -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登陆页面</title>
    <!-- Custom Theme files -->
    <link href="./Home_files/style.css" rel="stylesheet" type="text/css" media="all">
    <!-- Custom Theme files -->

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design">
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
    <div class="login-top">
        <h1>注册</h1>
        <% 
			String msg = (String)request.getAttribute("msg");
			if(msg != null){%>
			<span style="color: red;text-align: center"><%=msg%></span>
			<%}
		%>
        <form method="post" action="user">
        	<input type="hidden" name="method" value="register" id="method">
            <input type="text" name="userName" placeholder="用户名" id="userName">
            <input type="password" name="password" placeholder="密码" id="password">
            <input type="password" name="password_again" placeholder="再次密码" id="password_again">
            <div><input type="email" name="email" placeholder="邮箱" id="email" id="email"><input type="button" onclick="return putEmail()" value="获取验证码"></div>
            <input type="text" name="code" placeholder="验证码" id="code">
            <div class="forgot">
                <input type="button" value="REGISTER" onclick="return register()">
            </div>
        </form>
    </div>
    <div class="login-bottom">
        <h3>用户 &nbsp;<a href="Login.jsp">登陆</a>&nbsp;</h3>
    </div>
</div>
<script type="text/javascript" src="jquery/jquery-3.3.1.js"></script>
<script type="text/javascript">
    function putEmail() {
        var mail = document.getElementById("email").value;
        if(mail == ""){
        	alert("邮箱不能为空");
        	return false;
        }
        $.ajax({
            url:"user",
            type:"post",
            dataType:"json",
            data: {method:"sendMail", email:mail},
            success: function (data) {
                if(data == "1"){
                	alert("发送成功");
                }else if(data == "0"){
                	alert("该邮箱已经被注册了");
                }else if(data == "-1"){
                	alert("发送失败");
                }
            },error:function () {
                alert("发送错误")
            }
        });
    }
    
    function register() {
		var method = $("#method").val();
		var userName = $("#userName").val();
		var password = $("#password").val();
		var password_again = $("#password_again").val();
		var email = $("#email").val();
		var code = $("#code").val();
		if(userName == ""){
			window.alert("用户名不能为空");
			return false;
		}
		if(password == ""){
			window.alert("密码不能为空");
			return false;
		}
		if(password_again == ""){
			window.alert("重复密码不能为空");
			return false;
		}
		if(email == ""){
			window.alert("邮箱不能为空");
			return false;
		}
		if(code == ""){
			window.alert("验证码不能为空");
			return false;
		}
		$.ajax({
            url:"user",
            type:"post",
            dataType:"json",
            data: {method:method,userName:userName, password:password, password_again:password_again, email:email, code:code},
            success: function (data) {
            	if(data == "1"){
            		window.location.href="Index.jsp";	
            	}else if(data == "2"){
                	alert("验证码错误");
                }else if(data == "3"){
                	alert("两次密码不相同");
                }else if(data == "4"){
                	alert("该邮箱已经注册了, 请更换邮箱");
                }
            },error:function () {
                alert("未知错误")
            }
        });
	}
    
    
</script>
</body>
</html>
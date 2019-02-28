<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0069)http://demo.cssmoban.com/cssthemes4/btts_49_bs-count-error/index.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Error Page</title>
    <!-- Bootstrap core CSS -->
    <link href="static/Error_files/bootstrap.css" rel="stylesheet">
    <!-- FONT AWESOME CSS -->
    <link href="static/Error_files/font-awesome.min.css" rel="stylesheet">
    <!--GOOGLE FONT -->
 <link href="static/Error_files/css" rel="stylesheet" type="text/css">
    <!-- custom CSS here -->
    <link href="static/Error_files/style.css" rel="stylesheet">
<link rel="preload" href="static/Error_files/f.txt" as="script"><script src="static/Error_files/osd.js.下载"></script><script src="./Error_files/ca-pub-1542822386688301.js.下载"></script><script type="text/javascript" src="./Error_files/f.txt"></script><link rel="preload" href="http://pagead2.googlesyndication.com/pagead/js/r20190220/r20190131/show_ads_impl.js" as="script"></head>
<body style="">
<div class="adcenter"><script src="static/Error_files/ggad2_728x90.js.下载"></script><link type="text/css" rel="stylesheet" href="./Error_files/astyle.css">
<div align="center">
<script async="" src="static/Error_files/f(1).txt"></script>
<ins class="adsbygoogle" style="display: block; height: 90px;" data-ad-client="ca-pub-1542822386688301" data-ad-slot="5966457456" data-ad-format="auto" data-adsbygoogle-status="done"><ins id="aswift_0_expand" style="display:inline-table;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:1200px;background-color:transparent;"><ins id="aswift_0_anchor" style="display:block;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:1200px;background-color:transparent;"><iframe width="1200" height="90" frameborder="0" marginwidth="0" marginheight="0" vspace="0" hspace="0" allowtransparency="true" scrolling="no" allowfullscreen="true" onload="var i=this.id,s=window.google_iframe_oncopy,H=s&amp;&amp;s.handlers,h=H&amp;&amp;H[i],w=this.contentWindow,d;try{d=w.document}catch(e){}if(h&amp;&amp;d&amp;&amp;(!d.body||!d.body.firstChild)){if(h.call){setTimeout(h,0)}else if(h.match){try{h=s.upd(h,i)}catch(e){}w.location.replace(h)}}" id="aswift_0" name="aswift_0" style="left:0;position:absolute;top:0;border:0px;width:1200px;height:90px;" src="static/Error_files/saved_resource.html"></iframe></ins></ins></ins>
<script>
</script>
</div>
</div>
    
   
    <div class="container">
      
          <div class="row pad-top text-center">
                 <div class="col-md-6 col-md-offset-3 text-center">
                  <h1>  What have you done? </h1>
                   <h5> Now Go Back Using Below LInk</h5>
             <% 
             	
				String code = (String)request.getAttribute("code");
				if(code != null){%>
				<span id="error-link"><%=code%></span>
				<%}
			%>
             <% 
             	request.setCharacterEncoding("utf-8");
				String msg = (String)request.getAttribute("msg");
             	String content = new String(msg.getBytes("iso8859-1"), "utf-8");
				if(msg != null){%>
				<h2><%=msg%></h2>
				<%}
			%>
                     
</div>
        </div>
    </div>
    <!-- /.container -->
  
  
    <!--Core JavaScript file  -->
    <script src="static/Error_files/jquery-1.10.2.js.下载"></script>
    <!--bootstrap JavaScript file  -->
    <script src="static/Error_files/bootstrap.js.下载"></script>
     <!--Count Number JavaScript file  -->
    <script src="static/Error_files/countUp.js.下载"></script>
       <!--Custom JavaScript file  -->
    <script src="static/Error_files/custom.js.下载"></script>
</body>
</html>
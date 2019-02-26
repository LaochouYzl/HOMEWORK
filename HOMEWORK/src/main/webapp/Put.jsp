<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>问答发布</title>


<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<![endif]-->

<link href="static/put/normalize.css" rel="stylesheet">
<link href="static/put/jquery-ui.css" rel="stylesheet">
<link href="static/put/jquery.idealforms.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="static/index/index.css" type="text/css" media="all">
<link rel="stylesheet" href="static/css/login.css">
    <link rel="stylesheet" href="static/layui/css/layui.css">
<style type="text/css">
body{ background: url(static/Home_files/banner.jpg)repeat;
			   padding:0px 0px 30px 0px;
			   font-family: 'Roboto', sans-serif;
			   font-size: 100%;}
#my-form{width:55%;margin:0 auto;padding:3em;border-radius:3px;box-shadow:0 0 2px rgba(0,0,0,.2);}
#comments{width:350px;height:100px;}
    #page.boxed{
                max-width: 1335px;
                margin: 20px auto;
            }

</style>

</head>
<body class="single single-post postid-8007 single-format-standard image-blur boxed-layout btn-flat content-fullwidth qfe-js-composer js-comp-ver-4.0.1 vc_responsive page is-webkit" data-pid="8007" data-pkey="c80f875bd429c81befafc42abb785d58" style="">

<div id="page" backgroundsize="true" class=" breackall boxed ">
	<!-- left, center, classical, classic-centered -->
	<!-- !Header -->
	<header id="header" class="logo-left headerPM menuPosition" role="banner"><!-- class="overlap"; class="logo-left", class="logo-center", class="logo-classic" -->
		<div class="wf-wrap">
			<div class="wf-table">



<div id="branding" class="wf-td bit-logo-bar" style="">
	<a class="bitem logo nomarl" style="display: table-cell;" href=""><span class="logospan"><img class="preload-me" src="static/images/1.jpg" width="228" height="141" alt="校园活动"></span></a>
		<div class="bitem text" style="display: table-cell;vertical-align: middle;"><a href="" style="text-decoration: none;"><div id="bit-logoText" data-margin-left="17" style="position:relative;font-size:16px;  margin-left:17px;"><div class="logotext_outner"><div class="logotext_inner"><div><span style="font-size:20px;"><span style="color: rgb(51, 51, 51);"><strong>江西农业大学</strong></span></span></div>

<div><span style="font-size:14px;"><span style="color: rgb(51, 51, 51);"><font face="arial, helvetica, sans-serif">
<b>问答论坛</b>
</font></span></span></div>
</div></div></div>	</a></div>
	<!-- <div id="site-title" class="assistive-text"></div>
	<div id="site-description" class="assistive-text"></div> -->
</div>
								<div class="wf-mobile-visible wf-td assistive-info    " role="complementary">
					<div class="top-bar-right right bit_widget_more" bitdatamarker="bitHeader-2" bitdataaction="site_fix_container" bitdatacolor="white">
					 					</div>
				</div>


		<!-- !- Navigation -->
		<nav style="0" id="navigation" class="wf-td" bitdataaction="site_menu_container" bitdatalocation="primary">
			<ul id="main-nav" data-st="0" data-sp="0" data-fh="" data-mw="" data-lh="" class="mainmenu fancy-rollovers wf-mobile-hidden bit-menu-default underline-hover position-text-right" data-bit-menu="underline-hover" data-bit-float-menu="underline-hover">
                <li class=" menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-5365 current_page_item bit-menu-post-id-5365 menu-item-5583 act first level-arrows-on"><a href="blog?method=toBlogPage"><span style="color: black">问答论坛</span></a></li>
</ul>



					</nav>
		<div style="display:none;" id="main-nav-slide">
									<div class="main-nav-slide-inner" data-class="">
				<div class="floatmenu-bar-right bit_widget_more" bitdatamarker="bitHeader-3" bitdataaction="site_fix_container" bitdatacolor="white">
					 				</div>
			</div>
		</div>


			</div><!-- #branding -->
		</div><!-- .wf-wrap -->
	</header>


<div class="row">

  <div class="eightcol last" style="text-align: center">

   <form id="my-form" class="ideal-form" style="visibility: visible;" action="putQuiz" method="post" enctype="multipart/form-data">
          <div class="ideal-wrap"><label class="ideal-label" style="width: 150px;">问答主题:</label><span class="ideal-field invalid">
              <input id="username" name="title" type="text" autocomplete="off" style="background: white;border: #aaaaaa 1px solid"><i class="ideal-icon ideal-icon-valid" style="display: none;"></i><i class="ideal-icon ideal-icon-invalid" style="display: block;"></i></span><span class="ideal-error" style="display: none;">此处是必填的.</span></div>
          <div class="ideal-wrap"><label class="ideal-label" style="width: 150px;">问答具体内容:</label><span class="ideal-field invalid">
              <input id="pass" name="content" type="text" autocomplete="off" style="background: white;border: #aaaaaa 1px solid"><i class="ideal-icon ideal-icon-valid" style="display: none;"></i><i class="ideal-icon ideal-icon-invalid" style="display: block;"></i></span><span class="ideal-error" style="display: none;">此处是必填的.</span></div>
        <div class="ideal-wrap"><label class="ideal-label" style="width: 150px;">学院类别:</label><span class="ideal-field invalid">
            <input id="email" name="category" data-ideal="required email" type="text" autocomplete="off" style="background: white;border: #aaaaaa 1px solid"><i class="ideal-icon ideal-icon-valid" style="display: none;"></i><i class="ideal-icon ideal-icon-invalid" style="display: block;"></i></span><span class="ideal-error" style="display: none;">此处是必填的.</span></div>
          <div class="ideal-wrap"><label class="ideal-label" style="width: 150px;">问答图片:</label><span class="ideal-field invalid">
              <input id="email" name="file" value="选择图片" type="file" autocomplete="off" style="background: white;"><i class="ideal-icon ideal-icon-valid" style="display: none;"></i><i class="ideal-icon ideal-icon-invalid" style="display: block;"></i></span><span class="ideal-error" style="display: none;">此处是必填的.</span></div>
      <div class="ideal-wrap ideal-full-width"></div><div class="ideal-separator"><hr></div>
      <div class="ideal-wrap">
        <button type="submit" class="ideal-button">提交</button>
      </div>
    </form>



    <!-- End Form -->

  </div>
  <footer id="footer" class="footer">
	<div class="wf-wrap">
		<div class="wf-container qfe_row footer1" bitdataaction="site_widget_container" bitdatamarker="sidebar_2">
			<section id="simplepage-2" style="margin-bottom:20px;" class="widget simplepage site_tooler"><style class="style_simplepage-2">#simplepage-2 .widget-title{padding:0 0 0 10px;height:28px;line-height:28px;background-color:transparent;margin:0px;font-family:;font-size:px;font-weight:normal;font-style:normal;text-decoration:none;color:#ffffff;border-top:1px solid transparent;border-left:1px solid transparent;border-right:1px solid transparent;border-bottom:0px solid transparent;background-image:none;-webkit-border-top-left-radius: 4px;-webkit-border-top-right-radius: 4px;-moz-border-radius-topleft: 4px;-moz-border-radius-topright: 4px;border-top-left-radius: 4px;border-top-right-radius: 4px;} #simplepage-2 .widget-title{border-top:0;border-left:0;border-right:0;} #simplepage-2 .bitWidgetFrame{border-bottom:0;border-top:0;border-left:0;border-right:0;padding:4px 10px 4px 10px;}#simplepage-2{-webkit-box-shadow:none;box-shadow:none;}#simplepage-2 .bitWidgetFrame{background-color:transparent;background-image:none;-webkit-border-bottom-left-radius: 4px;border-bottom-left-radius: 4px;-webkit-border-bottom-right-radius: 4px;border-bottom-right-radius: 4px;}#simplepage-2 .bitWidgetFrame{padding-left:0px;padding-right:0px;}body #simplepage-2 .bitWidgetFrame{padding-top:0px !important;padding-bottom:0px !important;}</style><div class="simplepage_container bitWidgetFrame" data-post_id="7867"><section data-fixheight="" class="qfy-row-3-5c5d1d8047cea851355 section     no  section-text-no-shadow section-inner-no-shadow section-normal section-orgi" id="bit_oaeo1" style="margin-bottom:0;border-radius:0px;color:#ffffff;">
    <style class="row_class qfy_style_class">
	@media only screen and (min-width: 992px){
		section.section.qfy-row-3-5c5d1d8047cea851355{padding-left:0;padding-right:0;padding-top:20px;padding-bottom:0;margin-top:0;}section.section.qfy-row-3-5c5d1d8047cea851355 > .container{max-width:1280px;margin:0 auto;}}

	   @media only screen and (max-width: 992px){
		.bit-html section.section.qfy-row-3-5c5d1d8047cea851355{padding-left:15px;padding-right:15px;padding-top:20px;padding-bottom:0;margin-top:0;min-height:0;}}
	</style>
	    	    <div class="section-background-overlay background-overlay grid-overlay-0 " style="background-color: #474747;"></div>

    <div class="container">
        <div class="row qfe_row">
            <div data-animaleinbegin="bottom-in-view" data-animalename="qfyfadeInUp" data-duration="" data-delay="" class=" qfy-column-3-5c5d1d8047eba656413 qfy-column-inner  vc_span12  text-default small-screen-undefined fullrow" data-dw="1/1" data-fixheight=""><div style=";position:relative;" class="column_inner "><div class=" background-overlay grid-overlay-" style="background-color:transparent;width:100%;"></div><div class="column_containter" style="z-index:3;position:relative;">
	<div m-padding="0px 0px 0px 0px" p-padding="0 0 0 0" css_animation_delay="0" qfyuuid="qfy_column_text_p6tef" data-anitime="0.7" class="qfy-element qfy-text qfy-text-49369 qfe_text_column qfe_content_element  " style="position: relative;;;line-height:1.5em;;background-position:left top;background-repeat: no-repeat;;margin-top:0;margin-bottom:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;border-radius:0px;">
		<div class="qfe_wrapper">
			<div style="text-align: center;"><font color="#ffffff" face="宋体"><span style="font-size: 20px;"> <span style="font-family:微软雅黑;">版权所有 江西农业大学 地址：江西省南昌经济技术开发区　 邮编：330045</span> </span></font></div>

		</div>
	</div>
	<div m-padding="13px 0px 0px 0px" p-padding="13px 0 0 0" css_animation_delay="0" qfyuuid="qfy_column_text_r35lm" data-anitime="0.7" class="qfy-element qfy-text qfy-text-45334 qfe_text_column qfe_content_element  " style="position: relative;;;line-height:1.5em;;background-position:left top;background-repeat: no-repeat;;margin-top:0;margin-bottom:0;padding-top:13px;padding-bottom:0;padding-right:0;padding-left:0;border-radius:0px;">
		<div class="qfe_wrapper">
			<div style="text-align: center;"><span style="font-size:13px;"></span></div>

<div style="text-align: center;">&nbsp;</div>

		</div>
	</div> </div></div></div><style class="column_class qfy_style_class">@media only screen and (min-width: 992px){.qfy-column-3-5c5d1d8047eba656413 > .column_inner {padding-left:0;padding-right:0;padding-top:0;padding-bottom:0;}.qfe_row .vc_span_class.qfy-column-3-5c5d1d8047eba656413 {};}@media only screen and (max-width: 992px){.qfy-column-3-5c5d1d8047eba656413 > .column_inner{margin:0 auto 0 !important;padding-left:0;padding-right:0;padding-top:;padding-bottom:;}.display_entire .qfe_row .vc_span_class.qfy-column-3-5c5d1d8047eba656413 {}.qfy-column-3-5c5d1d8047eba656413 > .column_inner> .background-overlay,.qfy-column-3-5c5d1d8047eba656413 > .column_inner> .background-media{width:100% !important;left:0 !important;right:auto !important;}}</style>        </div>
    </div>

</section></div></section>		</div>
							</div><!-- .wf-wrap -->
	<!--  ************begin************* -->
<style type="text/css" id="static-stylesheet-footer">
#footer.footer .footer1 .widget{width: 99%;}#footer.footer .footer1 .widget.simplepage{width: 100%;}#footer.footer .footer2 .widget{width: 99%;}#footer.footer .footer2 .widget.simplepage{width: 100%;}#footer.footer .footer3 .widget{width: 99%;}#footer.footer .footer3 .widget.simplepage{width: 100%;}.bit_main_content{margin-top:0px;margin-bottom:40px}
</style>
<!--  ************end************* -->
</footer>
</div>

</div>


<script type="text/javascript" src="/static/put/jquery-1.8.2.min.js.下载"></script>
<script type="text/javascript" src="/static/put/jquery-ui.min.js.下载"></script>
<!-- <script type="text/javascript" src="/static/put/jquery.idealforms.js"></script> -->
<script type="text/javascript">
    function addFile() {
    	alert("hello world");
        document.getElementsByClassName("ideal-file").click();
    }
var options = {

	onFail: function(){
		alert( $myform.getInvalid().length +' invalid fields.' )
	},

	inputs: {
		'password': {
			filters: 'required pass',
		},
		'username': {
			filters: 'required username',
			data: {
			//ajax: { url:'validate.php' }
			}
		},
		'file': {
			filters: 'extension',
			data: { extension: ['jpg'] }
		},
		'comments': {
			filters: 'min max',
			data: { min: 50, max: 200 }
		},
		'states': {
			filters: 'exclude',
			data: { exclude: ['default'] },
			errors : {
				exclude: '选择国籍.'
			}
		},
		'langs[]': {
			filters: 'min max',
			data: { min: 2, max: 3 },
			errors: {
				min: 'Check at least <strong>2</strong> options.',
				max: 'No more than <strong>3</strong> options allowed.'
			}
		}
	}
	
};

var $myform = $('#my-form').idealforms(options).data('idealforms');

$('#reset').click(function(){
	$myform.reset().fresh().focusFirst()
});

$myform.focusFirst();
</script>


</body></html>
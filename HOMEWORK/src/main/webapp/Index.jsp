<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>线代君--计算工具</title>
    <link rel="stylesheet" href="static/Index_files/base.css">
    <link rel="stylesheet" href="static/Index_files/calculate.css">
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
    <script src="static/jquery/jquery-3.3.1.js"></script>
    <script src="js/jquery.SuperSlide.2.1.3.js"></script>
    <script src="js/jquery.cookie.js"></script>
</head>
<body>
<!--头部 begin-->
<div class="header w">
    <a href="index.html" class="logo">
        <img src="static/images/1.jpg" alt="" style="width:200px;height:60px">
    </a>
    <div class="shortcut">
        <ul>
            <li><a href="javascript:;">计算工具</a></li>
            <li><a href="blog?method=toBlogPage">问答论坛</a></li>
        </ul>
        <ul>
            <li>
                <div class="user_ico">
                    <a href="javascript:;"><img src="./img/user_pic.png" alt=""></a>
                </div>
            </li>
        </ul>
    </div>
    <hr>
</div>
<!--头部 end-->

<div class="content w">
    <div class="set-content">
        <div class="set-title hd">
            <ul class="clearfix">
                <li class="on">线代计算</li>
            </ul>
        </div>

        <div class="set-middle bd">
            <!--行列式计算 begin-->
            <ul class="set-middle-item">
                <div class="content-area">
                    <div class="area-left">
                        <div class="top-area">
                            <div class="card">
                                <div class="card-header">注意事项</div>
                                <div class="card-body">
                                    一行元素用空格分隔，换行代表下一行，输入的列数需要和行数相同，否则无法计算！导入的文件注意：导入的文件
                                    指定为CSV文件或者txt文件，导入的文件只需要包含数据，CSV文件不能有行标题！
                                </div>
                            </div>
                        </div>
                        <div class="show-area">
                            <div class="card col-50 fl">
                                <div class="card-header">输入或导入数据</div>
                                <div class="card-body">

                                    <div class="area-box determinant">
                                        <div class="box-title">
                                            <div class="box-title-left fl">行列式</div>
                                            <div class="box-title-right fr">
                                               行：<span contenteditable="true" id="1row">0</span>
                                                列：<span contenteditable="true" id="1col">0</span>
                                            </div>
                                        </div>

                                        <div class="box-body"> <pre id="data-1" contenteditable="true"></pre></div>

                                    </div>
                                </div>
                            </div>
                            <div class="card col-50 fl">
                                <div class="card-header">结果：</div>
                                <div class="card-body">
                                    <textarea class="result-area" id="result-1"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="area-right">
                        <div class="function-area">
                            <ul>
                                <li class="button" id="determinant-leading-in" onclick="openFile(1)"><input type="file" name="file" id="fileC" onchange="fileUpload(1)" style="display:none" num="1" /><a href="javascript:;">导入行列式</a></li>
                                <li class="button" id="calculate-determinant" onclick="getDeterminantValue()"><a href="javascript:;">计算行列式</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </ul>
            <!--行列式计算 end-->
            <!--矩阵初等计算 begin-->
            <ul class="set-middle-item">
                <div class="content-area">
                    <div class="area-left">
                        <div class="top-area">
                            <div class="card">
                                <div class="card-header">注意事项</div>
                                <div class="card-body">
                                    一行元素用空格分隔，换行代表下一行，输入的列数需要和行数相同，否则无法计算！导入的文件注意：导入的文件
                                    指定为CSV文件或者txt文件</div>
                            </div>
                        </div>
                        <div class="show-area">
                            <div class="card col-50 fl">
                                <div class="card-header">输入或导入数据</div>
                                <div class="card-body">

                                    <div class="area-box matrix">
                                        <div class="box-title">
                                            <div class="box-title-left fl">矩阵A</div>
                                            <div class="box-title-right fr">
                                                行：<span contenteditable="true" id="2row">0</span>
                                                列：<span contenteditable="true" id="2col">0</span>
                                            </div>
                                        </div>
                                        <div class="box-body"> <pre id="data-2" contenteditable="true"></pre></div>
                                        <div class="box-title">
                                            <div class="box-title-left fl">矩阵B</div>
                                            <div class="box-title-right fr">
                                                行：<span contenteditable="true" id="3row">0</span>
                                                列：<span contenteditable="true" id="3col">0</span>
                                            </div>
                                        </div>
                                        <div class="box-body"> <pre id="data-3" contenteditable="true"></pre></div>
                                    </div>

                                </div>
                            </div>
                            <div class="card col-50 fl">
                                <div class="card-header">结果：</div>
                                <div class="card-body">
                                    <pre class="result-area" id="result-2" style="display: block;width: 600px;height: 400px"></pre>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="area-right">
                        <div class="function-area">
                            <ul>
                                <li class="button"  onclick="openFile(2)"><input type="file" name="file" id="fileA" onchange="fileUpload(2)" style="display:none" num="2" /><a href="javascript:;">导入矩阵A</a></li>
                                <li class="button"  onclick="openFile(3)"><input type="file" name="file" id="fileB" onchange="fileUpload(3)" style="display:none" num="3" /><a href="javascript:;">导入矩阵B</a></li>
                                <li class="button" onclick="transposition()"><a href="javascript:;">矩阵转置</a></li>
                                <li class="button" onclick="plus()"><a href="javascript:;">矩阵加法</a></li>
                                <li class="button" onclick="multiply()"><a href="javascript:;">矩阵乘法</a></li>
                                <li class="button" onclick="getReverseMartrix()"><a href="javascript:;">求逆矩阵</a></li>
                                <li class="button" onclick="getRank()"><a href="javascript:;">矩阵的秩</a></li>
                                <li class="button"><a href="javascript:;">判断矩阵相似</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </ul>
            <!--矩阵初等计算 end-->
        </div>
    </div>
</div>

<script>
    jQuery(".set-content").slide({
        autoPlay : false,
        trigger : "mouseover",
        delayTime : 500,
        pnLoop : false
    });
    function openFile(id) {
    	var inputObj;
    	if(id == 1){
    		inputObj = document.getElementById("fileC");
    	}else if(id == 2){
    		inputObj = document.getElementById("fileA");
    	}else if(id == 3){
    		inputObj = document.getElementById("fileB");
    	}
        inputObj.click();
    };
    function fileUpload(id){
    	var num;
    	// 创建formdata对象
   	    var formData = new FormData();
    	if(id == 1){
    		num = document.getElementById("fileC").getAttribute("num");
   		 	formData.append('file', $('#fileC')[0].files[0]);
    	}else if(id == 2){
    		num = document.getElementById("fileA").getAttribute("num");
    		 formData.append('file', $('#fileA')[0].files[0]);
    	}else if(id == 3){
    		num = document.getElementById("fileB").getAttribute("num");
    		 formData.append('file', $('#fileB')[0].files[0]);
    	}
   	    // 给formData对象添加<input>标签,注意与input标签的ID一致
   	    formData.append("number", num);
   	    $.ajax({
               url : '/HOMEWORK/file',//这里写你的url
               type : 'POST',
               data : formData,
               contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
               processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
               dataType: 'json',//这里是返回类型，一般是json,text等
               clearForm: true,//提交后是否清空表单数据
               success: function(data) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
            	   var content = data["content"];
                   var row = data["row"];
                   var col = data["col"];
                   document.getElementById(id+"row").innerText = row;
                   document.getElementById(id+"col").innerText = col;
                   var number = data["number"];
                   var result = arrayToString(content, row, col);
                   document.getElementById("data-"+number).innerText=result;
               },
               error: function(data, status, e) {  //提交失败自动执行的处理函数。
                   console.error(e);
               }
           });
    };
    
    function arrayToString(content, row, col){
    	var result = "";
        var index = 0;
        for(var i = 0; i < row; i++){
     	   for(var j = 0; j < col; j++){
     		   result += content[index];
     		   result += " "
     		   index++;
     	   }
     	   result += "\n";
        }
        return result;
    }
    
    // 矩阵转置, 默认矩阵A
    function transposition(){
    	var text = document.getElementById("data-2").innerText;
    	var row = document.getElementById("2row").innerText;
    	var col = document.getElementById("2col").innerText;
    	$.ajax({
    		url:"matrix",
    		type:"post",
            dataType:"json",
            data:{method:"transposition", frist:text, row:row, col:col},
    		success:function(data){
    			content = data["content"];
    			row = data["row"];
    			col = data["col"];
    			var result = arrayToString(content, row, col);
    			document.getElementById("result-2").innerText = result;
    		},
    		error:function(){
    			alert("error");
    		}
    	});
    }
    
    // 求矩阵的逆矩阵
    function getReverseMartrix() {
    	var text = document.getElementById("data-2").innerText;
    	var row = document.getElementById("2row").innerText;
    	var col = document.getElementById("2col").innerText;
    	$.ajax({
    		url:"matrix",
    		type:"post",
            dataType:"json",
            data:{method:"getReverseMartrix", frist:text, row:row, col:col},
    		success:function(data){
    			content = data["content"];
    			row = data["row"];
    			col = data["col"];
    			var result = arrayToString(content, row, col);
    			document.getElementById("result-2").innerText = result;
    		},
    		error:function(){
    			alert("error");
    		}
    	});
	}
 	// 矩阵加法, 默认矩阵A
    function plus(){
    	var textA = document.getElementById("data-2").innerText;
    	var textB = document.getElementById("data-3").innerText;
    	var row = document.getElementById("2row").innerText;
    	var col = document.getElementById("2col").innerText;
    	$.ajax({
    		url:"matrix",
    		type:"post",
            dataType:"json",
            data:{method:"plus", first:textA, second:textB, row:row, col:col},
    		success:function(data){
    			content = data["content"];
    			row = data["row"];
    			col = data["col"];
    			var result = arrayToString(content, row, col);
    			alert(result);
    			document.getElementById("result-2").innerText = result;
    		},
    		error:function(){
    			alert("error");
    		}
    		
    	});
    }
 	
 	// 矩阵乘法, 默认矩阵A
    function multiply(){
    	var textA = document.getElementById("data-2").innerText;
    	var textB = document.getElementById("data-3").innerText;
    	var row = document.getElementById("2row").innerText;
    	var col = document.getElementById("2col").innerText;
    	$.ajax({
    		url:"matrix",
    		type:"post",
            dataType:"json",
            data:{method:"multiply", first:textA, second:textB, row:row, col:col},
    		success:function(data){
    			content = data["content"];
    			row = data["row"];
    			col = data["col"];
    			var result = arrayToString(content, row, col);
    			document.getElementById("result-2").innerText = result;
    		},
    		error:function(){
    			alert("error");
    		}
    		
    	});
    }
 	
 	// 矩阵的秩
 	function getRank() {
 		var text = document.getElementById("data-2").innerText;
    	var row = document.getElementById("2row").innerText;
    	var col = document.getElementById("2col").innerText;
    	$.ajax({
    		url:"matrix",
    		type:"post",
            dataType:"json",
            data:{method:"getRank", frist:text, row:row, col:col},
    		success:function(data){
    			content = data["content"];
    			document.getElementById("result-2").innerText = content;
    		},
    		error:function(){
    			alert("error");
    		}
    	});
	}
 	
 	// 计算行列式的值
 	function getDeterminantValue() {
 		var text = document.getElementById("data-1").innerText;
    	var row = document.getElementById("1row").innerText;
    	var col = document.getElementById("1col").innerText;
    	$.ajax({
    		url:"determinant",
    		type:"post",
            dataType:"json",
            data:{method:"getDeterminantValue", frist:text, row:row, col:col},
    		success:function(data){
    			content = data["content"];
    			document.getElementById("result-1").innerText = content;
    		},
    		error:function(){
    			alert("error");
    		}
    	});
	}
    
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 说明, 我们现在在jsp页面中不提倡使用java代码了,因此我们需要使用jstl EL表达式 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/font-awesome.min.css">
	<link rel="stylesheet" href="static/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">线代网站 - 控制面板</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i>${user.userName } <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="index.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="javascript:;"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 线代网站  <span class="badge" style="float:right">2</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="admin?method=toQuizManager" style="color:red;"><i class="glyphicon glyphicon-user"></i> 问答管理 </a> 
							</li>
							<li style="height:30px;">
								<a href="admin?method=toUserManager"><i class="glyphicon glyphicon-certificate"></i> 人员管理</a> 
							</li>
						</ul>
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
			<form class="form-inline" role="form" style="float:left;" action="searchByCondition" method="post">
			  <div class="form-group has-feedback">
			    <div class="input-group">
			      <div class="input-group-addon">姓名</div>
			      <input type="hidden" value="name" name="type">
			      <input name="queryText" class="form-control has-success" id="queryText" type="text" placeholder="请输入查询条件">
			    </div>
			  </div>
			  <button type="submit" class="btn btn-warning" id="queryBtn"><i class="glyphicon glyphicon-search"></i> 查询</button>
			</form>
			<a type="button" class="btn btn-primary" style="float:right;" href="toAddPage"><i class="glyphicon glyphicon-plus"></i> 新增</a>
			<br>
 <hr style="clear:both;">
          <div class="table-responsive">
          <form id="usersForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="allSelBox"></th>
                  <th>问答作者</th>
                  <th>问答标题</th>
                  <th>问答内容</th>
                  <th>发布时间</th>
                  <th>学院</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody id="userData">
              </tbody>
             	<c:forEach items="${list }" var="item" varStatus="status" begin="0">
             		<tr>
   	                  <td>${status.index+1 }</td>
   					  <td><input type="checkbox" name="userId" value="'+user.id+'"></td>
   	                  <td>${item.author }</td>
   	                  <td>${item.title }</td>
   	                  <td>${item.content }</td>
   	                  <td>${item.pubTime }</td>
   	                  <td>${item.category }</td>
   	                  <td>
<!--    					      <button type="button" class="btn btn-success btn-xs" onclick="toAssignPage('+(user.id)+')"><i class=" glyphicon glyphicon-check"></i></button> -->
   					      <%-- <a  class="btn btn-primary btn-xs" href="toEditPage?id=${customer.id }"><i class=" glyphicon glyphicon-pencil"></i></a> --%>
   						  <a  class="btn btn-danger btn-xs" href="admin?id=${item.id }&method=deleteQuiz"><i class=" glyphicon glyphicon-remove"></i></a>
   					  </td>
   	                </tr>
             	</c:forEach>
			  <tfoot>
				  <tr >
				    <td colspan="12" align="center">
						<ul class="pagination">
						</ul>
					</td>
				 </tr>
			  </tfoot>
            </table>
             </form>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="static/jquery/jquery-2.1.1.min.js"></script>
    <script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script src="static/script/docs.min.js"></script>
	<script src="${APP_PATH }/layer/layer.js"></script>
        <script type="text/javascript">
        	var likeflg = false;
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    pageQuery(1);
			    
			    $("#queryBtn").click(function(){
			    	var queryText = $("#queryText").val();
			    	if(queryText == ""){
			    		likeflg = false;
			    	}else{
			    		likeflg = true;
			    	}
			    	pageQuery(1)
			    });
			    
			    $("#allSelBox").click(function(){
			    	// 得到改复选框的勾选转态
			    	var flag = this.checked;
			    	// 从我们渲染的地方中,得到所有的复选框,, each: 循环遍历
			    	$("#userData :checkbox").each(function(){
			    		this.checked = flag;
			    	});
			    });
            });
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            
            // 使用ajax发送请求
            function pageQuery(pageNo){
            	var loadingIndex = null;
            	var jsonData = {"pageNo" : pageNo,"pageSize" : 5}
            	if(likeflg == true){
            		jsonData.queryText = $("#queryText").val();
            	}
            	$.ajax({
            		type : 'POST',
            		url : '${APP_PATH}/user/pageQuery',
            		data : jsonData,
            		beforeSend : function(){
            			loadingIndex = layer.msg('处理中', {icon:16});
            		},
            		success : function(result){
            			layer.close(loadingIndex);
            			if(result.success){
            				
            				// 局部刷新页面数据
            				var tableContent = ""
            				var pageContent = ""
            				
            				var userPage = result.data;
            				var users = userPage.results;
            				
            				// 循环遍历, 其中的方法为回调方法, 其中i为索引, user为临时变量
            				$.each(users, function(i, user){
            					tableContent += '<tr>';
	          	                tableContent += '  <td>'+(i+1)+'</td>';
	          					tableContent += '  <td><input type="checkbox" name="userId" value="'+user.id+'"></td>';
	          	                tableContent += '  <td>'+user.userName+'</td>';
	          	                tableContent += '  <td>'+(user.password)+'</td>';
	          	                tableContent += '  <td>'+(user.email)+'</td>';
	          	                tableContent += '  <td>';
	          					tableContent += '      <button type="button" class="btn btn-success btn-xs" onclick="toAssignPage('+(user.id)+')"><i class=" glyphicon glyphicon-check"></i></button>';
	          					tableContent += '      <button type="button" class="btn btn-primary btn-xs" onclick="toUpdatePage('+(user.id)+')"><i class=" glyphicon glyphicon-pencil"></i></button>';
	          					tableContent += '	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteUser('+(user.id)+', \''+(user.userName)+'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
	          					tableContent += '  </td>';
	          	                tableContent += '</tr>';
            				})
            				
            				if(pageNo > 1){
            					pageContent += '<li class="abled"><a href="#" onclick="pageQuery('+(pageNo-1)+')">上一页</a></li>'
            				}
            				
            				for(var i = 1;i<=userPage.pageMax;i++){
            					if( i == pageNo){
            						pageContent += '<li class="active"><a href="#">'+(i)+'</a></li>'
            					}else{
            						pageContent += '<li><a href="#" onclick="pageQuery('+(i)+')">'+(i)+'</a></li>'
            					}
            					
            				}
            				
            				if(pageNo < userPage.pageMax){
            					pageContent += '<li><a href="#" onclick="pageQuery('+(pageNo+1)+')">下一页</a></li>'
            				}
            				
            				
            				$("#userData").html(tableContent);
            				$(".pagination").html(pageContent);
            				
            			}else{
            				layer.msg("用户信息分页查询失败", {time:3000, icon:5, shift:6}, function(){
                        		
                        	});
            			}
            		}
            	});
            	
            }
            
            function toUpdatePage(id){
            	window.location.href="${APP_PATH}/user/toUpdatePage?userId="+id;
            }
            
            function deleteUser(id, userName){
            	layer.confirm("删除用户信息["+userName+"], 是否继续", {icon:3, title:'提示'}, function(cindex){
            		// 删除用户信息
            		var loadingIndex = null;
            		$.ajax({
            			type : "POST",
            			url : "${APP_PATH}/user/delete",
            			data : {"id" : id},
            			beforeSend : function(){
            				loadingIndex = layer.msg("处理中", {icon:16})
            			},
            			success : function(result){
            				layer.close(loadingIndex);
            				if(result.success){
            					pageQuery(1)
            				}else{
            					layer.msg("用户信息分页查询失败", {time:3000, icon:5, shift:6}, function(){
                            		
                            	});
            				}
            			}
            		})
            		layer.close(cindex);
            	}, function(cindex){
            		layer.close(cindex);
            	})
            }
            
            // 批量删除用户
            function deleteUsers(){
            	// 获取复选框
            	var boxs = $("#userData :checkbox");
            	if(boxs.length == 0){
            		layer.msg("请选择需要删除的用户", {timeout:2000, icon:5, shift:6}, function(){});
            	}else{
            		layer.confirm("删除所选的用户信息, 是否继续", {icon:3, title:'提示'}, function(cindex){
                		// 删除选择用户信息
                		var loadingIndex = null;
                		$.ajax({
                			type : "POST",
                			url : "${APP_PATH}/user/deletes",
                			// 如果想操作批量删除,我们需要我们批量选择的复选框进行表单序列化才可以
                			data : $("#usersForm").serialize(),
                			beforeSend : function(){
                				loadingIndex = layer.msg("处理中", {icon:16})
                			},
                			success : function(result){
                				layer.close(loadingIndex);
                				if(result.success){
                					pageQuery(1)
                				}else{
                					layer.msg("用户信息分页查询失败", {time:3000, icon:5, shift:6}, function(){
                                		
                                	});
                				}
                			}
                		})
                		layer.close(cindex);
                	}, function(cindex){
                		layer.close(cindex);
                	})
            	}
            	
            }
            
            function toAssignPage(id){
            	
            	window.location.href="${APP_PATH}/user/toAssignPage?userId="+id;
            	
            }
            
        </script>
  </body>
</html>

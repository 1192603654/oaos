<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
<script>
	function add(){
		location.href="moncomputertoadd";
		}
</script>
</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="default.jsp">首页</a></li>
    <li><a href="#">电脑领用管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click" onclick="add()"><span><img src="images/t01.png" /></span>添加</li>
        </ul>
       
    </div>
    <table class="imgtable">
    
    <thead>
    <tr>
    <th width="100px;">学生姓名</th>
    <th>电脑序号</th>
    <th>领取人签名</th>
    <th>领取时间</th>
    <th>是否赠送</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="s">
	    <tr>
		    <td>${s.interid }</td>
		    <td>${s.comid }</td>
		     <td>${s.cpname }</td>
		    <td>${s.cpdata }</td>
		    <td>${s.isgave }</td>
		    <td>
		   	<a href="moncomputertoupd?com.cpid=${s.cpid }" class="tablelink">修改</a>
		    <a href="moncomputerdele?com.cpid=${s.cpid }" class="tablelink">删除</a></td>
	    </tr>
    </c:forEach>
    </tbody>
    </table>
    <div class="message">共<i class="blue">${totalpage }</i>条记录，当前显示第&nbsp;<i class="blue">${currpage }&nbsp;</i>页</div>
    <div class="pagin">
    	<ul class="paginList">
        <li class="paginItem"><a href="moncomputerlist?currpage=${currpage-1 }"><span class="pagepre"></span></a></li>
		<c:forEach begin="1" end="${totalrow}" var="s">
			<li ${s==currpage ? 'class="paginItem current"':'class="paginItem"' }><a href="moncomputerlist?currpage=${s }">${s}</a></li>
		</c:forEach>
        
        <li class="paginItem"><a href="moncomputerlist?currpage=${currpage+1 }"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
</body>

</html>

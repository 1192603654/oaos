<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="editor/kindeditor.js"></script>
<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345	  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="default.jsp">首页</a></li>
    <li><a href="#">新增收入</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">新增收入</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
    <form action="monmonadd" method="post" onsubmit="return y()">
    <ul class="forminfo">
    <li><label>收费项目</label>  
    <div class="vocation">
    <select name="mon.monpro" class="select1">
    <option value="0">学费收入</option>
    <option value="1">项目开发</option>
    <option value="2">其他</option>
    </select>
    </div>
    </li>
    <li><label>收费金额</label><input name="mon.moncount" id="num" type="number" class="dfinput" style="width:518px;"/></li>
    <li><label>备注</label>
    <textarea id="content7" name="mon.monremark"  style="width:700px;height:250px;visibility:hidden;"></textarea>
    </li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="添加"/></li>
    </ul>
     </form>
    </div>
    <script>
    	function y(){
    		var num=$("#num").val();
    		if(num==null||num==""||num<0){
    			alert("金额填写有误！");
    			return false;
    		}
    	}
    </script>
</body>

</html>

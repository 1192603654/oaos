<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'leave.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="kindeditor-4.1.10/themes/default/default.css" />
		<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" href="kindeditor-4.1.10/themes/default/default.css" />
	<script charset="utf-8" src="kindeditor-4.1.10/kindeditor-min.js"></script>
<script type="text/javascript">
		var editor;
		KindEditor.ready(function(k){
			 editor=k.create("#kind_editor", {
				items : [
							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
							'insertunorderedlist', '|', 'emoticons']
					});
		});
		
		function checkform(){
// 			var  txt =document.getElementById("kind_editor").value;
			var  txt= editor.text();
			alert(txt);
			
		}
</script>
		<script type="text/javascript">
			function gettime(){
				var t1=document.getElementById("d11").value;
				var t2=document.getElementById("d12").value;
				document.getElementById("ltime").value=DateDiff(t2,t1);
			}
			function DateDiff(sDate1, sDate2){ //sDate1和sDate2是2006-12-18格式 
				var aDate, oDate1, oDate2, iDays;
				aDate = sDate1.split("-");
				oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]); //转换为12-18-2006格式 
				aDate = sDate2.split("-");
				oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]); 
				iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 /24); //把相差的毫秒数转换为天数 
				return iDays;
			 }
		</script>
	</head>

	<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
		<div class="formtitle">
			<span>反馈内容</span>
		</div>
			<form action="view!viewhui.action" method="post">
			<ul class="forminfo">
				<li>
					<label>
						<input type="text" name="sid" value="${viewstu.sid }">
					</label>
				</li>
				<li>
					<label style="margin-left: 700px;">
						标题:
					</label>
					<label style="text-decoration: underline;">
						${viewstu.vcontent }
					</label>	
					<i></i>
				</li>
				<li>
					<label style="margin-left: 700px;">
						内容：
					</label>
					<label style="text-decoration: underline;">
					
					${viewstu.views }
					</label>
				</li>
				<li>
					<label style="margin-top: -80px; width: 500px;" >
						
						<c:forEach items="${cont}" var="query">
							${query.TEACH } ${query.TIMES }
							<br/>
							${query.TCONTENT }
							<br/>
						</c:forEach>
						
						<c:forEach items="${teach}" var="teach">
							${teach.TEACH }  ${teach.TIMES }
							<br/>
							${teach.TCONTENT }
							<br/>
						</c:forEach>
						
						共
					<i class="blue"><font color="blue">${totalRow }</font></i>条记录，当前显示第&nbsp;
					<i class="blue"><font color="blue">${currpage }</font>&nbsp;/<font color="blue">${totalpage }</font></i>页
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="vte!viewlist.action?str=${currpage-1 }&sid=${tea.sid}">上一页 </a>
					<c:forEach begin="1" end="${totalpage}" var="hour">
							<a href="vte!viewlist.action?str=${hour }&sid=${tea.sid}"><font color="blue">${hour}</font></a>
					</c:forEach>
						<a href="vte!viewlist.action?str=${currpage+1}&sid=${tea.sid}">下一页 </a>
					</label>
				</li>
				
				<li>
						<textarea rows="5"  id="kind_editor" name="v.viewsto"></textarea><!-- 这个控件 -->
				</li>
				<li>
					<label style="margin-left: 500px;">
						<input  type="submit" class="btn" value="发送"/>
					</label>
				</li>
    				
			</ul>
		</form>
	</body>
	
</html>

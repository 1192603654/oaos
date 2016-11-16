<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>注册</title>

		<link rel="stylesheet" href="csss/style.css" />

		<script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
		<script type="text/javascript" src="js/easyform.js"></script>

	</head>
	<body>
		<br />
		<div class="form-div">
		<br />
			<br />
			<br />
			<br/>
			<form id="reg-form" action="login!add.action" method="post">

				<table>
					<tr>
						<td>
							用户名
						</td>
						<td>
							<input name="lo.uname" type="text" id="uid"
								easyform="length:4-16;char-normal;real-time;"
								message="用户名必须为4—16位的英文字母或数字"
								easytip="disappear:lost-focus;theme:blue;"
								ajax-message="用户名已存   在!" />
						</td>
					</tr>
					<tr>
						<td>
							密码
						</td>
						<td>
							<input name="lo.password" type="password" id="psw1"
								easyform="length:6-16;" message="密码必须为6—16位"
								easytip="disappear:lost-focus;theme:blue;" />
						</td>
					</tr>
					<tr>
						<td>
							确认密码
						</td>
						<td>
							<input name="lo.pwd" type="password" id="psw2"
								easyform="length:6-16;equal:#psw1;" message="两次密码输入要一致"
								easytip="disappear:lost-focus;theme:blue;" />
						</td>
					</tr>
					<tr>
						<td>
							email
						</td>
						<td>
							<input name="lo.email" type="text" id="email"
								easyform="email;real-time;" message="Email格式要正确"
								easytip="disappear:lost-focus;theme:blue;"
								ajax-message="这个Email地址已经被注册过，请换一个吧!" />
						</td>
					</tr>
					<tr>
						<td>
							昵称
						</td>
						<td>
							<input name="lonickname" type="text" id="nickname"
								easyform="length:2-16" message="昵称必须为2—16位"
								easytip="disappear:lost-focus;theme:blue;" />
						</td>
					</tr>
				</table>

				<div class="buttons">
					<input value="注 册" type="submit"
						style="margin-left: 100px; margin-top: 20px;" />

					<input value="我有账号，我要登录" type="button"
						style="margin-right: 45px; margin-top: 20px;" />
				</div>

				<br class="clear" />
				</form>

		</div>

		<script type="text/javascript">
$(document).ready(function(){
	$('#reg-form').easyform();
});
</script>


	</body>
</html>

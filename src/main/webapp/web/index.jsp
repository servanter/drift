<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>漂流瓶</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="<%=path%>/js/drift.js"></script>
	</head>

	<body>
		<form action="">
			phone:<input id="user_phone" name="user_phone" type="text">
			<br>
			content:<textarea id="bottle_content" rows="7" cols="20"></textarea><br>
			result:<textarea id="bottle_result" rows="7" cols="20"></textarea><br>
			<input id="release_button" type="button" value="发送">
			<input id="service_path" type="hidden" value="<%=path%>" />
		</form>
		<div style="color:#FF0000">
			注意:直接指定某个人,请输入"@张大师:你是谁"这种格式，Thanks
		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OPENKM TEST</title>
</head>
<body>
	<center>
	<h4>OPENKM测试</h4>
		<table border="1">
			<form action="do.Upload" method="post" enctype="multipart/form-data">
			<tr>
				<td>上传：</td>
				<td><input type="file" name="upfile" size="50"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="上传"></td>
			</tr>
			<tr>
			<td>下载：</td>
				<td><a href="Get.Test">显示文件下载列表</a></td>
			</tr>
			</form>

		</table>
	</center>
</body>
</html>
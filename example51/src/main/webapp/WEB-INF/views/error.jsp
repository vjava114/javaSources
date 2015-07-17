<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오류</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
</head>
<body>
<div id="page">
	<h3>오류</h3>
	<table>
		<tr>
			<th width="60">원인</th>
			<td>${exception.message }</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="button" value="목록" onclick="location.href='index'" />
	</div>
</div>
</body>
</html>
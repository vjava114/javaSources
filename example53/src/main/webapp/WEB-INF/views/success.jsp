<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입 결과</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
</head>
<body>
<div id="page">
	<h3>회원 가입 결과</h3>
	<table>
		<tr>
			<th width="60">결과</th>
			<td>${id }님의 가입을 축하드립니다.<br /><br />
				아래 버튼을 클릭하여 목록으로 이동하신 다음, 로그인하셔서 사용하시면 됩니다.<br /><br />
				감사합니다.
			</td>
		</tr>
	</table><br />
	<div align="center">
		<input type="button" value="목록" onclick="location.href='index'" />
	</div>
</div>
</body>
</html>
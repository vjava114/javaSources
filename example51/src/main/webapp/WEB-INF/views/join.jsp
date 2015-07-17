<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) {
	var result = true;
	if(isNull(f.id, document.getElementById("error_id"), "계정을 입력하세요!!")) result = false;
	if(isNull(f.password, document.getElementById("error_password"), "암호를 입력하세요!!")) result = false;
	if(!isEqual(f.password, f.password2, document.getElementById("error_password2"), "암호가 일치하지 않습니다.")) result = false;
	if(isNull(f.email, document.getElementById("error_email"), "이메일을 입력하세요!!")) result = false;
	return result;
}
</script>
</head>
<body>
<div id="dialog">
	<h3 align="center">회원 가입</h3>
	<form action="join" method="post" onsubmit="return checkForm(this)">
		<table width="300" align="center">
			<tr>
				<th width="100">계정</th>
				<td><input type="text" name="id" size="20" /><br />
					<span class="error-text" id="error_id"></span>
				</td>
			</tr>
			<tr>
				<th>암호</th>
				<td><input type="password" name="password" size="20" /><br />
					<span class="error-text" id="error_password"></span>
				</td>
			</tr>
			<tr>
				<th>암호확인</th>
				<td><input type="password" name="password2" size="20" /><br />
					<span class="error-text" id="error_password2"></span>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" size="20" /><br />
					<span class="error-text" id="error_email"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인" />&nbsp;
					<input type="button" value="취소" onclick="history.back();" />
				</td>
			</tr>
		</table><br />
		<c:if test="${not empty error }">
			<div align="center" class="error-text">${error }</div>
		</c:if>
	</form>
</div>
</body>
</html>
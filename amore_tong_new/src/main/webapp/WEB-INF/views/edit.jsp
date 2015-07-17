<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글형 게시판 예제</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) {
	var result = true;
	if(isNull(f.title, document.getElementById("error_title"), "제목을 입력하세요!!")) result = false;
	if(isNull(f.content, document.getElementById("error_content"), "내용을 입력하세요!!")) result = false;
	return result;
}
</script>
</head>
<body>
<div id="page">
<h3>${title}</h3>
<form:form modelAttribute="board" method="post" action="save" onsubmit="return checkForm(this)">
<input type="hidden" name="action" value="${action}" />
<input type="hidden" name="no" value="${board.no}" />
<input type="hidden" name="page" value="${page}" />
<table>
	<tr>
		<th align="center" width="60">작성자</th>
		<td>${board.id}</td>
	</tr>
	<tr>
		<th align="center">제목</th>
		<td><form:input path="title" name="title" size="80" htmlEscape="false" />&nbsp;
			<span class="error-text" id="error_title"></span>
		</td>
	</tr>
	<tr>
		<th align="center">내용</th>
		<td><form:textarea path="content" name="content" cssStyle="margin:0px; width:600px; height:400px" htmlEscape="false"/><br />
			<span class="error-text" id="error_content"></span>
		</td>
	</tr>
</table><br />
<div align="center">
	<input type="submit" value="확인" />&nbsp;
	<c:if test="${action=='update' or action=='answer'}">
	<input type="button" value="취소" onclick="location.href='view?no=${board.no}&page=${page}&back=y'" />&nbsp;
	</c:if>
	<input type="button" value="목록" onclick="location.href='index?page=${page}'" />
</div>
</form:form>
</div>
</body>
</html>
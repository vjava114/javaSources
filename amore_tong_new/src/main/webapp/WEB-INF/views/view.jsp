<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글형 게시판 예제</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
</head>
<body>
<div id="page">
<h3>글 보기</h3>
<table>
	<tr>
		<th align="center" width="60">제목</th>
		<td>${board.title }</td>
	</tr>
	<tr>
		<th align="center">작성자</th>
		<td>${board.id }</td>
	</tr>
	<tr>
		<th align="center">작성일</th>
		<td><fmt:formatDate value="${board.wdate }" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/></td>
	</tr>
	<tr>
		<th align="center">내용</th>
		<td width="600" height="300" valign="top">${board.content }</td>
	</tr>
</table><br />
<div align="center">
	<input type="button" value="답글" onclick="location.href='edit?no=${board.no}&page=${page }&action=answer'" />&nbsp;
	<c:if test="${id == board.id }">	
		<input type="button" value="편집" onclick="location.href='edit?no=${board.no}&page=${page }&action=update'" />&nbsp;
		<input type="button" value="삭제" onclick="location.href='delete?no=${board.no}&page=${page }'" />&nbsp;
	</c:if>
	<input type="button" value="목록" onclick="location.href='index?page=${page}'" />
</div>
</div>
</body>
</html>
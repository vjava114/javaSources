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
<h3>글 목록</h3>
<div align="left">
	<c:if test="${not empty id}">
		${id}님. 안녕하세요.&nbsp;&nbsp;<a href="static/j_spring_security_logout">[로그아웃]</a>
	</c:if>
	<c:if test="${empty id}">
		로그인 하시면 글을 작성하거나 보실 수 있습니다.&nbsp;&nbsp;<a href="login">[로그인]</a>
	</c:if>
</div>
<table>
	<tr>
		<th width="60" align="center">글 번호</th>
		<th align="center">제목</th>
		<th width="60" align="center">조회수</th>
		<th width="60" align="center">작성자</th>
		<th width="60" align="center">작성일</th>
	</tr>
	<c:forEach items="${boards }" var="board">
	<tr>
		<td align="center">${board.no }</td>
		<td><a href="view?no=${board.no }&page=${page }">${board.title }</a></td>
		<td align="center">${board.read_count }</td>
		<td align="center">${board.id }</td>
		<td align="center"><fmt:formatDate value="${board.wdate }" type="both" pattern="yyyy/MM/dd"/></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5" align="center">
			${link}
		</td>
	</tr>
</table><br />
<div align="center">
	<input type="button" value="새 글 쓰기" onclick="location.href='edit?page=${page}&action=new'" />
</div>
</div>
</body>
</html>
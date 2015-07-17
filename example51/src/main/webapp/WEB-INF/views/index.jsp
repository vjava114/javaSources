<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>한 줄 메모장</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">
function checkForm(f) { 
	var result = true;
	if(isNull(f.content, document.getElementById("error_content"), "메모를 입력하세요!!")) result = false;
	return result;
}
</script>
</head>
<body>
<div id="page">
<h3>회원용 한 줄 메모장</h3>
<div>
	<c:if test="${not empty id }">
		${id }님 안녕하세요.&nbsp;&nbsp;
		<a href="static/j_spring_security_logout">[로그아웃]</a>
	</c:if>
	<c:if test="${empty id }">
		로그인하시면 메모를 남길 수 있습니다.&nbsp;&nbsp;
		<a href="login">[로그인]</a>
	</c:if>
</div><br />
<table>
	<tr>
		<th width="60" align="center">번호</th>
		<th align="center">메모</th>
		<th width="60" align="center">작성자</th>
		<th width="160" align="center">작성일</th>
	</tr>
	<c:forEach items="${boards }" var="board">
	<tr>
		<td align="center">${board.no }</td>
		<td>${board.content }
			<c:if test="${not empty id and id == board.id}">
			&nbsp;<span class="small"><a href="delete?no=${board.no}&page=${currPage}">삭제</a></span>
			</c:if>
		</td>
		<td align="center">${board.id }</td>
		<td align="center"><fmt:formatDate value="${board.wdate }" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="4" align="center">
			<c:if test="${firstPage > 1 }"><a href="index?page=${firstPage-1 }">[&lt;]</a></c:if>
			<c:forEach var="page" begin="${firstPage }" end="${lastPage }">
				<c:if test="${page == currPage }"><b>[${page }]</b></c:if>
				<c:if test="${page != currPage }"><a href="index?page=${page }">[${page }]</a></c:if>
			</c:forEach>
			<c:if test="${lastPage < totalPage }"><a href="index?page=${lastPage+1 }">[&gt;]</a></c:if>
		</td>
	</tr>
</table><br />
<c:if test="${not empty id }">
	<form action="write" method="post" onsubmit="return checkForm(this)">
		<table>
			<tr>
				<th width="60" align="center">작성자</th>
				<th align="center">메모</th>
			</tr>
			<tr>
				<td align="center">${id }</td>				
				<td><input type="text" name="content" size="100" /><br />
					<span class="error-text" id="error_content" ></span>
				</td>
			</tr>			
		</table><br />
		<div align="center">
			<input type="submit" value="등록" />
		</div>
	</form>
</c:if>
</div>
</body>
</html>
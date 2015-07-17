<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<%@ include file="../views/menu.jsp" %>
<%@ include file="../views/Search.jsp" %>

<html>
	<body>
	
		<h3> default Exception page</h3>
		<%	Exception exception = (Exception)request.getAttribute("exception");	%>
		<%=":: message ::" +  exception.getMessage() %>
		
		<hr/>
		
		<br/>
		<%=  request.getRequestURI() %>
		<br/>
		<%=  request.getRequestURL() %>
		
		<hr/>
		
		<br><br><br><br><br>		
		<center>
		<a  style="font-size: xx-large; ">지금 선택하신 조회 조건에 해당하는 자료가 없습니다.</a><br><br>
        <a style="font-size: xx-large;">조건을 달리하여 검색 해 보세요.</a>
        </center>
		
	
	</body>
</html>
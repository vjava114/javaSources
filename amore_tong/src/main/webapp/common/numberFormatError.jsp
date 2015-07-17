<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<html>
	<body>
	
		<h3> NumberFormatException page</h3>
		<%	Exception exception = (Exception)request.getAttribute("exception");	%>
			<%=":: message ::" +  exception.getMessage() %>
		
		<hr/>
		<br/>
		<%=  request.getRequestURI() %>
		<br/>
		<%=  request.getRequestURL() %>
	
	</body>
</html>
<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<script type="text/javascript">
function dojob(){
	alert("다시 확인해주세요");
}

window.onload = dojob;
</script>

<html>
	<body>
	
		<h3> NullPointerException page</h3>
		<%	Exception exception = (Exception)request.getAttribute("exception");	%>
		<%=":: message ::" +  exception.getMessage() %>
		
		<hr/>
		<br/>
		<%=  request.getRequestURI() %>
		<br/>
		<%=  request.getRequestURL() %>
		
		<h1><a href="index.do">뒤로</a></h1뒤로> 
	</body>
</html>
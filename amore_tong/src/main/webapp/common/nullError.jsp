<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<script type="text/javascript">
function dojob(){
	alert("�ٽ� Ȯ�����ּ���");
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
		
		<h1><a href="index.do">�ڷ�</a></h1�ڷ�> 
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<% String loginOk = request.getParameter("loginOk"); 
if ( loginOk == null || loginOk =="")
{
	loginOk = "";
}
%>


<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
var loginOk = "<%= loginOk%>";

$(document).ready(function(){
	if(loginOk == "fail"){
		alert("로그인 실패. 아이디와 패스워드를 확인하세요.");
		document.getElementsByName("id").focus();
	}
});
</script>
<script type="text/javascript">

function login()
{
	var f=document.login_frm;
	if(f.id.value=="")
	{
		alert("ID를 입력하세요");
		f.id.focus();
		return;
	}
	if(f.pw.value=="")
	{
		alert("Password를 입력하세요");
		f.pw.focus();
		return;
	}
	
	f.submit();
}
</script>
</head>
<body>
	<center>
	<div id=loginpage>
		<img src="image/loginmain.gif">
		<form method=post action="login.go" name="login_frm">
			<table border=0 width=180>
				<tr>
					<td align="right" width=20%><b>ID</b></td>
					<td align="left" width=80%>
						<input type="text" size=15 name="id" style="width: 150px" >
					</td>
				</tr>
				<tr>
					<td align="right" width=20%><b>Password</b></td>
					<td align="left" width=80%>
						<input type="password" size=15 name=pw style="width: 150px" >
					</td>
				</tr>
				<tr>
					<td align="right" colspan=2>
						<img src="image/login1.PNG" border="0" onclick="login()">					
					</td>	
				</tr>
			</table>
		</form>	
	</div>	
	</center>
</body>
</html>
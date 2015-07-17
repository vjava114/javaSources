<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
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
	if(f.pwd.value=="")
	{
		alert("Password를 입력하세요");
		f.pwd.focus();
		return;
	}
	
	f.submit();
}
</script>
</head>
<body>
  <center>
   <form method=post action="../member/login_ok.jsp" name="login_frm">
   <table border=0 width=180>
     <tr>
      <td align=right width=20%>ID</td>
      <td align=left width=80%>
       <input type=text size=15 name=id>
      </td>
     </tr>
     <tr>
      <td align=right width=20%>Password</td>
      <td align=left width=80%>
       <input type=password size=15 name=pwd>
      </td>
     </tr>
     <tr>
      <td align=right colspan=2>
       <input type=button value=로그인 onclick="login()">
       <input type=button value=회원가입 onclick="join()">
      </td>
     </tr>
   </table>
   </form>
  </center>
</body>
</html>










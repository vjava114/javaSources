<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.member.*"%>
<jsp:useBean id="dao" class="my.member.MemberDAO"/>
<%
    String id=request.getParameter("id");
    int check=dao.idcheck(id);
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function idcheck()
{
	var f=document.idcheck_frm;
	if(f.id.value=="")
	{
		alert("ID를 입력하세요");
		f.id.focus();
		return;
	}
	
	f.submit();
}
function ok()
{
	opener.join_frm.id.value=document.idcheck_frm.id.value;
	self.close();
}
</script>
</head>
<body>
  <center>
   <h3>ID 중복체크</h3>
   <form method=post action="idcheck.jsp" name="idcheck_frm">
   <table border=0 width=300>
    <tr>
     <td width=30% align=right>ID입력</td>
     <td width=70% align=left>
      <input type=text name=id size=12 value="<%=id%>">
      <input type=button value="전송" onclick="idcheck()">
     </td>
    </tr>
   </table>
   </form>
   <%
       if(check==1)
       {
   %>
   <table border=0 width=300>
    <tr>
      <td align=center>
         <font color=red><b><%=id %></b>는(은) 이미 사용중입니다</font>
      </td>
    </tr>
   </table>
   <%
       }
       else
       {
   %>
   <table border=0 width=300>
    <tr>
      <td align=center>
        <font color=blue><b><%=id %></b>는(은) 사용 가능합니다</font>
      </td>
    </tr>
    <tr>
      <td align=center>
       <input type="button" value="확인" onclick="ok()">
      </td>
    </tr>
   </table>
   <%
       }
   %>
  </center>
</body>
</html>













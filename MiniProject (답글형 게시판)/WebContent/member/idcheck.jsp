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
		alert("ID�� �Է��ϼ���");
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
   <h3>ID �ߺ�üũ</h3>
   <form method=post action="idcheck.jsp" name="idcheck_frm">
   <table border=0 width=300>
    <tr>
     <td width=30% align=right>ID�Է�</td>
     <td width=70% align=left>
      <input type=text name=id size=12 value="<%=id%>">
      <input type=button value="����" onclick="idcheck()">
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
         <font color=red><b><%=id %></b>��(��) �̹� ������Դϴ�</font>
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
        <font color=blue><b><%=id %></b>��(��) ��� �����մϴ�</font>
      </td>
    </tr>
    <tr>
      <td align=center>
       <input type="button" value="Ȯ��" onclick="ok()">
      </td>
    </tr>
   </table>
   <%
       }
   %>
  </center>
</body>
</html>













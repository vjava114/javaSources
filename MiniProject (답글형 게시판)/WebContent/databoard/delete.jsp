<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
     String strNo=request.getParameter("no");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function del()
{
	// if($(pwd).value=="") JQuery
	var f=document.del_frm;
	if(f.pwd.value=="")
	{
		alert("��й�ȣ�� �Է��ϼ���");
		f.pwd.focus();
		return;
	}
	
	f.submit();
}
</script>
</head>
<body>
   <center>
     <h3>�����ϱ�</h3>
     <form method=post action="../databoard/delete_ok.jsp" name="del_frm">
     <table border=1 bordercolor=black width=300 cellpadding="0"
      cellspacing="0">
      <tr height=35>
        <td width=30% align=right>��й�ȣ</td>
        <td width=70% align=left>
         &nbsp;<input type=password name=pwd size=15>
         <input type=hidden name=no value=<%=strNo %>>
        </td>
      </tr>
      <tr height=35>
        <td colspan=2 align=center>
         <input type=button value=���� onclick="del()">
         <input type=button value=��� 
            onclick="javascript:history.back()">
        </td>
      </tr>
     </table>
     </form>
   </center>
</body>
</html>

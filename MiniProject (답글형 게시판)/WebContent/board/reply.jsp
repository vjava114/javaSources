<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
      main(�۾���) = reply.jsp   ==   reply_ok.jsp <==> DAO <==> Oracle 
                     �̸�     NOT NULL     ==> main.jsp(list.jsp���) sendRedirect
                     email
                     subject  NOT NULL
                     content  NOT NULL
                     pwd      NOT NULL ==> javascript
--%>
<%
     //�Խù� ��ȣ�� �޴´�
     String strNo=request.getParameter("no");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function send()
{
	var f=document.frm;
	if(f.name.value=="")
	{
		alert("�̸��� �Է��ϼ���");
		f.name.focus();
		return;
	}
	if(f.subject.value=="")
	{
		alert("������ �Է��ϼ���");
		f.subject.focus();
		return;
	}
	if(f.content.value=="")
	{
		alert("������ �Է��ϼ���");
		f.content.focus();
		return;
	}
	if(f.pwd.value=="")
	{
		alert("��й�ȣ�� �Է��ϼ���");
		f.pwd.focus();
		return;
	}
	
	f.submit();//form ==> action�� �ִ� �������� ������ ����
}
</script>
</head>
<body>
   <center>
      <img src="../image/title.gif">
      <br><br>
      <form method=post action="../board/reply_ok.jsp" name="frm">
      <table border=1 bordercolor=#ccccff width=500 
       cellspacing="0" cellpadding="0">
        <tr>
         <td>
          <table border=0 width=500 cellspacing="0"
           cellpadding="0">
           <tr height=30>
            <td width=15% align=right>�̸�</td>
            <td width=85% align=left>
             <input type=text size=15 name=name>
             <input type=hidden name=no value=<%=strNo %>>
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right>Email</td>
            <td width=85% align=left>
             <input type=text size=45 name=email>
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right>����</td>
            <td width=85% align=left>
             <input type=text size=45 name=subject>
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right valign=top>����</td>
            <td width=85% align=left>
             <textarea rows="15" cols="50" name="content"></textarea>
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right>���</td>
            <td width=85% align=left>
             <input type=password size=10 name=pwd>
            </td>
           </tr> 
           <tr height=30>
            <td colspan=2 align=center>
             <input type=button value="�亯�ϱ�" onclick="send()">
             <input type=button value="���" 
               onclick="javascript:history.back()">
            </td>
           </tr> 
          </table>
         </td>
        </tr>
      </table>
      </form>
   </center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.change.ChangeJSP"%>
<%
    String mode=request.getParameter("mode");
    if(mode==null)
    	mode="0";
    
    int index=Integer.parseInt(mode);
    String jsp=ChangeJSP.change(index);
    
    String mid=(String)session.getAttribute("id");
    String log_jsp="";
    // session == null (����� �ȵ�)
    if(mid==null)
    	log_jsp="../member/login.jsp";
    else
    	log_jsp="../member/logout.jsp";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
td,th{font-family: �޸ո���ü;}
a{
    text-decoration: none;
    color:black;
}
a:hover{
   text-decoration: underline;
   color:green;
}
</style>
<script type="text/javascript">
function join()
{
	location.href="main.jsp?mode=8";
}
</script>
</head>
<body>
  <center>
    <table border=1 bordercolor=black width=1024 height=768
     cellspacing="0" cellpadding="0">
      <tr>
        <td colspan=2 width=100% height=120 background="../image/back.jpg" valign=middle align=center>
          <font color=white size=7>JSP MiniProject</font>
        </td>
      </tr> 
      <tr>
        <td width=200 height=568 align=center valign=top>
         <jsp:include page="<%= log_jsp %>"></jsp:include>
         <p><br><br></p>
         <%@ include file="menu.jsp" %>
        </td>
        <td width=824 height=568 align=center valign=top>
         <br>
         <jsp:include page="<%=jsp %>"></jsp:include>
        </td>
      </tr>
      <tr>
        <td colspan=2 width=100% height=80 align=center>
          ���κ�ȣ��ħ &nbsp; ����� ����� &nbsp; ����Ʈ����
          <br><br>
          <address>����� ���빮�� âõ�� 18-21 �ǵ����� ���� 13,14��
          &nbsp;&nbsp; TEL) 02-335-1234 </address>
        </td>
      </tr>
    </table>
  </center>
</body>
</html>






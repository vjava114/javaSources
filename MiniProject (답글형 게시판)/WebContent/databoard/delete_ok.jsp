<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.databoard.*"%>
<jsp:useBean id="dao" class="my.databoard.DataBoardDAO"/>
<%
   String no=request.getParameter("no");
   String pwd=request.getParameter("pwd");
   
   //�����ͺ��̽� ����
   boolean bCheck=dao.delete(Integer.parseInt(no), pwd);
   //�̵�
   if(bCheck==true)
   {
      response.sendRedirect("../main/main.jsp?mode=9");
   }
   else
   {
%>
      <script>
      alert("��й�ȣ�� Ʋ���ϴ�");
      history.back();
      </script>
<%
   }

%>

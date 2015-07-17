<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.databoard.*"%>
<jsp:useBean id="dao" class="my.databoard.DataBoardDAO"/>
<%
   String no=request.getParameter("no");
   String pwd=request.getParameter("pwd");
   
   //데이터베이스 연동
   boolean bCheck=dao.delete(Integer.parseInt(no), pwd);
   //이동
   if(bCheck==true)
   {
      response.sendRedirect("../main/main.jsp?mode=9");
   }
   else
   {
%>
      <script>
      alert("비밀번호가 틀립니다");
      history.back();
      </script>
<%
   }

%>

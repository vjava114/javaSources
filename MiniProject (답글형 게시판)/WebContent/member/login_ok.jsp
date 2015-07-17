<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.member.*"%>
<jsp:useBean id="dao" class="my.member.MemberDAO"/>
<%
     String id=request.getParameter("id");
     String pwd=request.getParameter("pwd");
     
     String result=dao.isLogin(id, pwd);
     
     if(result.equals("NOID"))
     {
%>
         <script>
         alert("ID가 존재하지 않습니다");
         history.back();
         </script>
<% 
     }
     else if(result.equals("NOPWD"))
     {
%>
         <script>
         alert("비밀번호가 틀립니다");
         history.back();
         </script>    
<%  	 
     }
     else
     {
    	 session.setAttribute("id",id );
    	 session.setAttribute("name", result);
    	 
    	 response.sendRedirect("../main/main.jsp");
     }
%>







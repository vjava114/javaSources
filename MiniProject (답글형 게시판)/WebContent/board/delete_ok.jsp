<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.board.*"%>
<jsp:useBean id="dao" class="my.board.FreeBoardDAO"/>
<%
    String pwd=request.getParameter("pwd");
    String no=request.getParameter("no");
    
    //DAO 연결
    boolean bCheck=dao.delete(Integer.parseInt(no), pwd);
    //이동 (pwd(X) back() , pwd(O) list)
    if(bCheck==true)
    {
    	response.sendRedirect("../main/main.jsp?mode=1");
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









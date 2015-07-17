<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.board.*"%>
<jsp:useBean id="dao" class="my.board.FreeBoardDAO"/>
<%
    request.setCharacterEncoding("EUC-KR");
%>
<jsp:useBean id="vo" class="my.board.FreeBoardVO">
  <jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
    //데이터 연동
    dao.reply(vo.getNo(), vo);
    response.sendRedirect("../main/main.jsp?mode=1");
%>

















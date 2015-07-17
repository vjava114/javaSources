<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.member.*"%>
<jsp:useBean id="dao" class="my.member.MemberDAO"/>
<%
    request.setCharacterEncoding("EUC-KR");
%>
<jsp:useBean id="vo" class="my.member.MemberVO">
 <jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
    dao.join(vo);
    response.sendRedirect("../main/main.jsp");
%>

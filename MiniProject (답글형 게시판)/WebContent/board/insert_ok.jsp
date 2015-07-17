<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.board.*"%>
<%--
     DAO객체 생성
     
 --%>    
<jsp:useBean id="dao" class="my.board.FreeBoardDAO"/>
<%
    request.setCharacterEncoding("EUC-KR");
%>
<jsp:useBean id="vo" class="my.board.FreeBoardVO">
  <jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%--

      1. insert.jsp에서 넘어 오는 값을 받는다.
      2. 오라클 연결(FreeBoardDAO를 생성)
         <jsp:useBean>
      3. FreeBoardVO로 데이터를 묶어준다 (DAO->insert(FreeBoardVO vo))
         <jsp:setProperty>
      4. 이동 sendRedirect(main.jsp?mode=1)
      String name=request.getParameter("name");
      --
      --
      --
      --
      FreeBoardVO vo=new FreeBoardVO();
      vo.setName(name);
      --
      --
      --
      --
      dao.insert(vo);
      
--%>
<%
   //데이터 연결 메소드 호출
   dao.insert(vo);
   response.sendRedirect("../main/main.jsp?mode=1");
%>






















<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.board.*"%>
<%--
     DAO��ü ����
     
 --%>    
<jsp:useBean id="dao" class="my.board.FreeBoardDAO"/>
<%
    request.setCharacterEncoding("EUC-KR");
%>
<jsp:useBean id="vo" class="my.board.FreeBoardVO">
  <jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%--

      1. insert.jsp���� �Ѿ� ���� ���� �޴´�.
      2. ����Ŭ ����(FreeBoardDAO�� ����)
         <jsp:useBean>
      3. FreeBoardVO�� �����͸� �����ش� (DAO->insert(FreeBoardVO vo))
         <jsp:setProperty>
      4. �̵� sendRedirect(main.jsp?mode=1)
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
   //������ ���� �޼ҵ� ȣ��
   dao.insert(vo);
   response.sendRedirect("../main/main.jsp?mode=1");
%>






















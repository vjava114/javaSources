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
     boolean bCheck=dao.update(vo);
     if(bCheck==false)
     {
    	 //update.jsp�̵�
%>
        <script>
        alert("��й�ȣ�� Ʋ���ϴ�");
        history.back();
        </script>
<%
     }
     else
     {
    	 //list.jsp�̵�
    	 response.sendRedirect("../main/main.jsp?mode=1");
     }
%>







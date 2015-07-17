<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.board.*"%>
<jsp:useBean id="dao" class="my.board.FreeBoardDAO"/>

<%
    String strNo=request.getParameter("no");
    FreeBoardVO vo=dao.getContentData(Integer.parseInt(strNo));
    
    /*
          UPDATE FREEBOARD SET step=step+1 
                      WHERE ref=1 AND step>0
                      
                  no  ref  step  jump root depth
          AAAAA   1    1    0     0    0    2
           ->DDDD 4    1    1     1    1    0
           ->BBBB 2    1    2     1    1    1
           
            ->CCC 3    1    3     2    2    0
           
            
           
    */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
  <center>
    <img src="../image/title.gif">
    <br><br>
    <table border=1 bordercolor=#ccccff width=600 cellpadding="0"
     cellspacing="0">
      <tr height=35>
       <td width=20% bgcolor=#ccccff>이름</td>
       <td width=30%><%=vo.getName() %></td>
       <td width=20% bgcolor=#ccccff>조회수</td>
       <td width=30%><%=vo.getReadnum() %></td>
      </tr> 
      <tr height=35>
       <td width=20% bgcolor=#ccccff>이메일</td>
       <td width=30%><%=vo.getEmail() %></td>
       <td width=20% bgcolor=#ccccff>작성일</td>
       <td width=30%><%=vo.getRegdate().toString() %></td>
      </tr> 
      <tr height=35>
       <td width=20% bgcolor=#ccccff>제목</td>
       <td colspan=3 align=left><%=vo.getSubject() %></td>
      </tr> 
      <tr>
        <td colspan=4 align=left valign=top height=200>
        <pre>
         <%=vo.getContent() %>
        </pre>
        </td>
      </tr>
    </table>
    <br>
    <table border=0 width=600 cellpadding="0"
     cellspacing="0">
     <tr>
      <td align=right>
       <a href="main.jsp?mode=4&no=<%=strNo%>">
       <img src="../image/reply.gif" border=0>
       </a>
       <a href="main.jsp?mode=5&no=<%=strNo%>">
       <img src="../image/modify.gif" border=0>
       </a>
       <a href="main.jsp?mode=6&no=<%=strNo%>">
       <img src="../image/delete.gif" border=0>
       </a>
       <a href="main.jsp?mode=1"><img src="../image/list.gif" border=0></a>
      </td>
     </tr>
    </table>
  </center>
</body>
</html>








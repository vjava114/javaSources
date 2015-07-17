<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
   <center>
     <form method=post action="../member/logout_ok.jsp">
     <table border=0 width=200>
      <tr>
       <td align=center>
       <b><%= session.getAttribute("name") %></b>님 접속중!!
       </td>
      </tr>
      <tr>
        <td align=right>
         <input type=submit value=logout>
         <input type=button value=회원수정>
        </td>
      </tr>
     </table>
     </form>
   </center>
</body>
</html>








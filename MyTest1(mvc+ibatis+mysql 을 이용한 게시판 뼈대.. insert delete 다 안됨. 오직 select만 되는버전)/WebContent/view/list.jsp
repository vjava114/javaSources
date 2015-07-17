<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"    import="java.util.*,my.dao.*"%>      
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
    <center>
      <h3>게시판</h3>
      <table border=0 width=650>
        <tr>
         <td align=left>
         <a href="insert.do">새글</a>
         </td>
        </tr>
      </table>
      <table border=0 width=650>
        <tr bgcolor="#ccccff">
         <th width=15%>번호</th>
         <th width=35%>제목</th>
         <th width=35%>이름</th>       
         <th width=15%>조회수</th>
        </tr>
        <c:forEach var="vo" items="${list }">
         <tr>
           <td width=15%>${vo.no }</td>
           <td width=35% align=left><a href="content.do?no=${vo.no }">${vo.subject}</a></td>
           <td width=35%>${vo.name }</td>
           <td width=15%>${vo.readnum }</td>           
         </tr>
        </c:forEach>
        </table>
      <hr width=650>   
        <table border=0 width=650>
        <tr>
          <td align=center>
          <a href="list.do?curpage=${ curpage>1?curpage-1:curpage}">이전</a>
          &nbsp;
          <c:forEach var="i" begin="1" end="${totalpage }" >
      		 <a href="list.do?curpage=${i}">[${i }]</a> 
          </c:forEach>
          <a href="list.do?curpage=${ curpage<totalpage?curpage+1:curpage}">다음</a>
          &nbsp;&nbsp;
          ${curpage } page / ${totalpage } pages
          </td>
        </tr>
      </table>
      </center>
</body>
</html>
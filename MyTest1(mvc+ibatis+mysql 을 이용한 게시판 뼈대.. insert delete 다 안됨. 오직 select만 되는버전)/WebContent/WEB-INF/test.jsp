<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.dao.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--  jstl.jar 와 standard.jar가 추가 되어있어야 가능! -->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

        <c:forEach var="vo" items="${list }">
         <tr>
           <td width=15%>${vo.no }</td>
           <td width=35% align=left><a href="content.do?no=${vo.no }">${vo.subject}</a></td>
           <td width=35%>${vo.name }</td>
           <td width=15%>${vo.readnum }</td>           
         </tr>
        </c:forEach>
	

</body>
</html>
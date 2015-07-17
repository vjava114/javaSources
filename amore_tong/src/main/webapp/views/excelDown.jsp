<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>
<c:set var="date" value="<%=new Date()%>"/>


<%
String brand = request.getParameter("brand");
// if(brand.equals("ap")){
//     response.setHeader("Content-Disposition","inline; filename=ap.xls");
// }else if(brand.equals("at")){
//     response.setHeader("Content-Disposition","inline; filename=at.xls");
// }else if(brand.equals("hera")){
//     response.setHeader("Content-Disposition","inline; filename=hera.xls");
// }else if(brand.equals("lk")){
//     response.setHeader("Content-Disposition","inline; filename=lk.xls");
// }else if(brand.equals("pm")){
//     response.setHeader("Content-Disposition","inline; filename=pm.xls");
// }else if(brand.equals("sw")){
//     response.setHeader("Content-Disposition","inline; filename=sw.xls");
// }else{
//     response.setHeader("Content-Disposition","inline; filename=default.xls");
// }

response.setHeader("Content-Disposition","inline; filename="+brand+".xls");
response.setHeader("Content-Type", "application/vnd.ms-xls");


%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
        <tr >
            <td colspan = '10'>
            출력 시간 : <fmt:formatDate value="${date}" pattern="yy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        <c:if test="${param.sword ne null || param.sword ne ''}" >
        <tr>
          <td bgcolor="#c2c2c2">${param.s_name}</td><td>${param.sword}</td>
        </tr>
        </c:if>
        <c:if test="${param.evt_code ne null || param.evt_code ne ''}">
        <tr> 
          <td bgcolor="#c2c2c2">evt_code</td><td>${param.evt_code}</td>
        </tr>
        </c:if>
        <c:if test="${param.edate ne null ||  param.edate ne ''}">
        <tr>
          <td bgcolor="#c2c2c2">검색 기간</td><td>${param.sdate}~${param.edate}</td>
        </tr>
        </c:if>
        <tr>
        	<td bgcolor="#c2c2c2">총 접속자 수</td><td>${count }</td>
        </tr>
</table>
<hr>
<table border="1">
       <tr >
            <th width="7%">EventCode</th>
            <th width="5%">brand</th>
            <th width="10%">code</th>       
            <th width="10%">Tel</th>
            <th width="5%">접속횟수</th>
            <th width="7%">방문일자</th>
            <th width="7%">hera_no</th>
            <th width="5%">이름</th>
            <th width="18%">샵</th>
            <th width="10%">샵Tel</th>
            <th width="5%">엔젤</th>
            <th width="5%">쿠폰</th>        
        </tr>
        <c:forEach var="vo" items="${list }">
        
        <tr>
            <td>${ vo.eventVO.evt_code }</td>
            <td>${ vo.eventVO.brand }</td>
            <td>${ vo.eventVO.userno }</td>
            <td style=mso-number-format:'\@'>${ vo.uphone }</td>
            <td>${ vo.eventVO.vcnt }</td>
            <td><fmt:formatDate value="${ vo.eventVO.rdate }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
            
            
            <td>${ vo.user_no }</td>
            <td>${ vo.name }</td>
            <td>${ vo.shop_name }</td>
            <td>${ vo.shop_phone }</td>
            <td>${ vo.angel_name }</td>
            <td>${ vo.coupon_cnt }</td>
        </tr>
        </c:forEach>
    
</table>
</body>
</html>
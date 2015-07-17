<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>${ brand }</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/common.css" />
</head>
<body>
<%@ include file="menu.jsp" %>
<%@ include file="Search.jsp" %>


<center>
<div class="abody">
    <table class="acticleTable">
        <tr bgcolor="#ccccff" height="5">
        <th width="5%">EventCode</th>
        <th width="3%">brand</th>
        <th width="8%">code</th>       
        <th width="10">Tel</th>
        <th width="5%">접속횟수</th>
        <th width="12%">방문일자</th>
        <th width="12%">최종 방문 일자</th>
        <th width="5%">hera_no</th>
        <th width="5%">이름</th>
        <th width="15%">샵</th>
        <th width="10%">샵Tel</th>
        <th width="3%">엔젤</th>
        <th width="3%">쿠폰</th>
        <th width="5%">비고</th>
        </tr>
        <c:forEach var="vo" items="${list }">
        <tr height="20" class="contentsTr">
            <td >${ vo.eventVO.evt_code }</td>
            <td>${ vo.eventVO.brand }</td>
            <td>${ vo.eventVO.userno }</td>
            <td>${ vo.uphone }</td>
            <td>${ vo.eventVO.vcnt }</td>
            <td><fmt:formatDate value="${ vo.eventVO.rdate }" pattern="yy/MM/dd HH:mm:ss"/></td>
            <td>-</td>
            
            <td>${ vo.user_no }</td>
            <td>${ vo.name }</td>
            <td>${ vo.shop_name }</td>
            <td>${ vo.shop_phone }</td>
            <td>${ vo.angel_name }</td>
            <td>${ vo.coupon_cnt }</td>
            <td>-</td>
        </tr>
        </c:forEach>
    </table>
</div>    
   <%@ include file="paging.jsp" %>
</center>



</body>
</html>
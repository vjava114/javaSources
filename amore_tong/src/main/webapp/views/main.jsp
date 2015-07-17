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
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<SCRIPT type="text/javascript">



</SCRIPT>

</head>
<body>

<%@ include file="menu.jsp" %>
<%@ include file="Search.jsp" %>
<center>

<table class="acticleTable">
        <tr bgcolor="#ccccff" height="5">
        <th width="5%">EventCode</th>
        <th width="3%">brand</th>
        <th width="8%">code</th>       
        <th width="10" align="center">Tel</th>
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
</table>

<br>
<br>
<a id="brandChoisePlz" style="font-size: xx-large;">먼저 <b>브랜드</b>를 선택후, 조회 하세요</a>
<br>
<br>
   

</center>



</body>
</html>
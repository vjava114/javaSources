<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
asdf
<c:forEach var="list" items="${User}" varStatus="status">
<p>${list.user_no}</p>
<p>${list.ap2_no}</p>
<p>${list.phone}</p>
<p>${list.name}</p>
<p>${list.shop_phone}</p>
<p>${list.shop_name}</p>
<p>${list.sms}</p>
<p>ANT TEST</p>
</c:forEach>
</body>
</html>
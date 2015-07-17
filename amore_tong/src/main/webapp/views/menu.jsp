<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>



<%
String id = (String) session.getAttribute("id");
if(id==null || id=="")
	id = "";
%>

<c:set var="date" value="<%=new Date()%>"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/common.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>


<script type="text/javascript">
window.onload = function(){
	// 글자색 변경
	var brand = '${brand}';
	document.getElementById(brand).style.color="red";
	
	// TD 배경색 변경
	var Td = '${brand}'+"Td";
	document.getElementById(Td).bgColor = "#c2c2c2";
	
};
</script>



</head>

<body>

<center>
<table class="acticleTable">

	<tr class="totalTong">
		<td>${brand } 총 접속자 수(${totalcount })</td><td class="user" align="left"><a href="../logout.go?id=<%= id %>"><%= id %>&nbsp;&nbsp;Log Out&nbsp;</a></td>
	</tr>
	<tr class="finalSearchTime">
		<td colspan="2">최종 검색 시간 : <fmt:formatDate value="${date}" type="date" pattern="yy-MM-dd hh:mm:ss"/></td>
	</tr>
</table>

<table class="acticleTable">
<tr class="brandSearch">
	<td id="apTd"><a id="ap" href="main.do?brand=ap">아모레(ap)</a></td>
	<td id="atTd"><a id="at" href="main.do?brand=at">아리따움(at)</a></td>
	<td id="heraTd"><a id="hera" href="main.do?brand=hera">헤라(hera)</a></td>
	<td id="hrhTd"><a id="hrh" href="main.do?brand=hrh">헤라(hrh)</a></td>
	<td id="swTd"><a id="sw" href="main.do?brand=sw">설화수(sw)</a></td>
	<td id="pmTd"><a id="pm" href="main.do?brand=pm">프리메라(pm)</a></td>
	<td id="lkTd"><a id="lk" href="main.do?brand=lk">리리코스(lk)</a></td>
	
</tr>
</table>
</center>
</body>

</html>
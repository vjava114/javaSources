<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.net.URLEncoder" %>
<%@page import="java.net.URLDecoder" %>

<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/common.css" />


</head>



<!-- 
	필요한 파라미터
	curpage         : 현재페이지
	totalPage       : 토탈페이지
	page            : 하단에 뿌려질 [1] [2] .. [10] 번호 List
 -->

<body>
<div id="paging">
        <br>     
        <table border=0 width=90%>
         <tr height="30">
                <!-- 첫페이지로 -->
                <td align="right" width=30%>
	                <c:if test="${curpage>0 }"><c:if test="${curpage!=1 }">
	                    <a href="${brand }_list.do?curpage=1&sword=${ param.sword }&s_name=${param.s_name}&evt_code=${param.evt_code}&brand=${param.brand}&sdate=${param.sdate}&edate=${param.edate}">
	                        처음 ..
	                    </a>&nbsp;&nbsp;&nbsp;
	                </c:if></c:if>                
                </td>
	             <!-- 이전페이지 -->
	             <td align="right" width=5%>
	                <c:if test="${curpage>10 }">
	                                
	                    <a href="${brand }_list.do?curpage=${ curpage>10?curpage-10:curpage}&sword=${ param.sword }&s_name=${param.s_name}&evt_code=${param.evt_code}&brand=${param.brand}&sdate=${param.sdate}&edate=${param.edate}">
	                        이전
	                    </a>&nbsp;
	                </c:if>
	             </td>
				             
				             <!-- 페이징 [1] [2].. [10] -->
				             <td align="center" width=30% class="numbering">
				                <c:forEach var="i" items="${ page }">
				                    <c:choose>
				                        <c:when test="${i==curpage }">
				                            <a  href="${brand }_list.do?curpage=${i }&sword=${ param.sword }&s_name=${param.s_name}&evt_code=${param.evt_code}&brand=${param.brand}&sdate=${param.sdate}&edate=${param.edate}">
				                                <b>[${i}]</b>                   <!-- 현재페이지 강조 -->
				                            </a>&nbsp;&nbsp;&nbsp;
				                        </c:when>
				                        <c:otherwise> 
				                            <a href="${brand }_list.do?curpage=${i }&sword=${ param.sword }&s_name=${param.s_name}&evt_code=${param.evt_code}&brand=${param.brand}&sdate=${param.sdate}&edate=${param.edate}">
				                                ${i}
				                            </a>&nbsp;&nbsp;&nbsp;
				                        </c:otherwise>
				                    </c:choose>
				                </c:forEach>
				                
				             </td>
				             
				 <!-- 다음 -->            
	             <td align="left" width=5%>
	                <c:if test="${totalPage-10 > curpage }">
	                    <a href="${brand }_list.do?curpage=${ curpage<totalPage?curpage+10:curpage}&sword=${ param.sword }&s_name=${param.s_name}&evt_code=${param.evt_code}&brand=${param.brand}&sdate=${param.sdate}&edate=${param.edate}">
	                    다음
	                    </a>&nbsp;&nbsp;
	                </c:if>
	             </td>


	             <!-- 마지막 페이지 -->
	             <td align="left" width=30%>
                    <c:if test="${totalPage>1 }"><c:if test="${curpage!=totalPage }">
                      <a href="${brand }_list.do?curpage=${totalPage }&sword=${ param.sword }&s_name=${param.s_name}&evt_code=${param.evt_code}&brand=${param.brand}&sdate=${param.sdate}&edate=${param.edate}">
                              .. 맨끝
                      </a>&nbsp;&nbsp;&nbsp;
                     </c:if></c:if>				             
	             </td>
         </tr>
      </table>
</div>
</body>
</html>
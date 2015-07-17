<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   

<% String mid=(String)session.getAttribute("id");
if (mid==null || mid=="")
{
	mid="";
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- <link rel="stylesheet" href="../css/common.css" /> -->			<!-- 부모 페이지인 list.jsp 에 css가 정의 되어 있어서 여기서 또 해줄필요 읎다. -->
<link rel="stylesheet"  href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />


<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="../js/jquery-ui.js"></script>
<script>
    $(function() {
        $("#datepicker").datepicker();
        $("#datepicker1").datepicker();
    });
</script>
<script>
$("#datepicker").ui-datepicker{ font-size: 12px; width: 160px; }
$("#datepicker").ui-datepicker select.ui-datepicker-month{ width:30%; font-size: 11px; }
$("#datepicker").ui-datepicker select.ui-datepicker-year{ width:40%; font-size: 11px; }
</script>

<script type="text/javascript">
function goMain(){
	var brand = '${brand}';
	if (brand == "")
	{
		return false;
	}
	else
	{
		top.location.href ="main.do?brand=${brand}";        // 검색초기화 버튼 누르면 main으로 가면서 파람값 초기화됨.		
	};
	
    
}
</script>

<SCRIPT type="text/javascript">
function mappingAction(val){
	var brand= '${brand}';
	
	if(brand != "")
	{
    	var form = document.memberInfo;
    
	    if(val=="search")
	    {
	        form.action = "${brand }_list.do";        // 검색을 할것인지.
	    } 
	    else if(val=="excel")
	    {
	        form.action = "excel.do";                 // 엑셀출력을 할 것인지.
	    } 
	    else
	    {
	        return false;
	    }
    form.submit();
	}
	else
	{
		document.getElementById("brandChoisePlz").style.color = "red";		// 브랜드 선택 안하고 누르면
		return false;
	};
		
}
</SCRIPT>

</head>

<body>
<center>
    <!-- 조회조건 시작 -->
    <form name="memberInfo" method="post">
    <table class="acticleTable" id="brandTable" >
        <tr class="Search" height="50" align="center" valign="middle">
            
            <td bgcolor="#e6e9eb" width=15%>이벤트 날짜</td>
            <td width=7%>
                <select name="evt_code">
                    <c:forEach var="data" items="${ seldata }">
                       <c:if test="${data.evt_code eq param.evt_code}" >
                           <option value="${data.evt_code }" selected>${ data.evt_code }</option>
                       </c:if>   
                       <c:if test="${data.evt_code ne param.evt_code}" >
                           <option value="${data.evt_code }">${ data.evt_code }</option>
                       </c:if>
                    </c:forEach>
                </select>
                
            </td>
            <td bgcolor="#e6e9eb" width=15%>검색일자</td>
            <td width=10%>
                <input type="text" autocomplete="off" name="sdate" style="width:60px;" value="${param.sdate }" id="datepicker"> 
                ~   
                <input type="text" autocomplete="off" name="edate" style="width:60px;" value="${param.edate }" id="datepicker1">
            </td>
            <td bgcolor="#e6e9eb" width=15%>검색</td>
            <td width=25%>
                <select name="s_name">
                    <option value="name">이름</option>
                    <option value="phone">전화번호</option>
                    <option value="shop_code">샵코드</option>
                    <option value="shop_name">샵명</option>
                    <option value="userno" >Code</option>
                </select>
                <input type="text" name="sword" style="width:170px;" value="${param.sword }">
                <input type="button" onclick="mappingAction('search')" value="검색"  style="width:100px; height: 30px;"/>
            </td>
            <td bgcolor="#e6e9eb" width=30%>
                
                <input type="button"   onclick="goMain()"  value="검색초기화"   style="width:100px; height: 30px;"/>
                <input type="button"  onclick="mappingAction('excel')" value="엑셀저장" style="width:100px; height: 30px;"/>
            </td>
        </tr>
    </table>
    
            		<!-- 검색하기 위해 존재하는 항목들. -->
            <input type="hidden" value="${ brand }" name="brand"/>
            <input type="hidden" value="${ Other }" name="Other"/>	
            
            <input type="hidden" value="${ userTb }" name="userTb"/>	
            <input type="hidden" value="${ userCol }" name="userCol"/>	
            <input type="hidden" value="${ couponTb }" name="couponTb"/>	
            		<!-- 검색 항목 End -->	
    
    </form>
</center>
</body>
</html>
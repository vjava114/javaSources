<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
     1. JSP
        지시자 
          1) page : 페이지에 대한 정보
             = contentType : 변환 코드(text/html,text/xml= charset=euc-kr)
               *** 안드로이드로 값전송,Flex로 전송:UTF-8
                   무선 == http-->wap (XML,JSON:자바스크립트 데이터 표현법)
             = import : jsp에서 자바의 라이브러리를 사용할때
               *** 다른 속성은 한번만 사용이 가능하다
               예) <%@ page import="java.util.*"%>
                   <%@ page import="java.io.*"%>
                   <%@ page import="java.util.*,java.io.*"%> 
             = errorPage : 에러 발생시 처리하는 페이지 지정
          2) include : 특정영역에 다른 파일 첨부 할때 사용
                       <%@ include file="파일명"%> : 정적(변수가 공유)
                       <jsp:include page="파일명"%>: 동적(변수 공유가 안된다)
                       HTML) <iframe> : 파일안에 다른 파일 첨부
          3) taglib : XML형식 , 변환이 되면 자바언어로 변경
                      <c:if> : if , <c:forEach> : for
                      struts <s:if> ,spring <spring:html>,jstl,사용자정의
         내장객체 
            request : 요청값 정보,클라이언트 정보
                      1) getParameter() , getParameterValues()
                      2) setCharacterEncoding() : 한글변환
                      3) setAttribute():속성 추가
                      4) getAttribute(): 추가된 속성을 읽어 올때
            response : 응답 
                      1) sendRedirect() : 서버에서 다른 JSP로 이동
            session : 서버측에 클라이언트 정보 저장
                      1) setAttribute() : 저장
                      2) getAttribute() : 저장값 가지고 오기
                      3) invalidate() : 해제
            application : 서버 설정 파일 제어(web.xml)
                          getInitParameter()
                          log() : 서버측에 정보 출력
          JSP에서 자바 사용
            1) 표현식 : 브라우저에 출력 
               out.println() : 서블릿
               <%= %> : JSP의 일반 형태
               ${} : 현재 외부에서 가장 많아 사용되는 표현식
               <jsp:getProperty> : 거의 사용빈도가 없다
            2) 자바 일반 코딩 : 스크립트릿
               <% %> ==> taglib변경
               
            3) 메소드 선언,전역변수 : 선언문 
               <%! %> : 보안문제 
               
           JSP에서 사용되는 태그(액션)
               <jsp:useBean> 클래스 객체 생성
               <jsp:useBean id="a" class="A"/>
                   A a=new A();
               <jsp:setProperty> : 요청한 데이터를 받아서 
                                   해당객체에 값을 부여
                                   ==>객체의 setXxx()메소드 전체 호출
               <jsp:include> : 특정영역에 다른 JSP페이지를 
                               동적으로 추가 (JPanel)
               <jsp:forward> : 다른 JSP파일 번역내용을 
                               읽어와서 출력 (CardLayout)
                 ==> MVC의 주로 사용되는 태그
                 
           JSP에서 파일이동 방법(데이터 전송)
              POST : 내부 네트워크를 통해서 전송
                  HTML : <form>
              GET : URL뒤에 데이터 같이 전송
                  HTML : <a>
                  javascript : location.href
                  jsp : sendRedirect()
                        forward()
           JSP에서 자바언어 사용(별도로..)
              자바빈 
                = 액션빈 : 실제 기능을 처리하는 클래스
                           ~DAO,보안...
                = 데이터빈 (일반 데이터형으로 사용) : 읽기/쓰기
                            ==> setXxx(),getXxx(),isXxx()
                                ~VO , ~DTO
           JSP에서 데이터베이스 사용(12장 참조)
                Class.forName("") 오라클 드라이버 등록
                Connection : getConnection(url,username,pwd)
                Statement : executeQuery() : SELECT 
                            executeUpdate() : INSERT,UPDATE...
                ResultSet : 결과값 (레코드단위로 저장)
                   *** 커서의 위치 
                       출력값 첫번째 위치 : next()
                       마지막 위치 : previous()
                       
                   *** 값을 자바 데이터로 가지고 올때 
                   CHAR,VARCHAR2,CLOB : String (getString())
                   NUMBER : int (getInt())
                   BLOB,BFILE : InputStream(getInputStream())
                   TIMESTAMP,DATE : Date (getDate())
                       yy/mm/dd
                       => getDate()를 문자열 : yyyy-MM-dd
                       
                ==> 트랜잭션,프로시저, MVC (멘붕)
                   
           흐름 : content.jsp ==> no 
                       delete.jsp (no을 받는다)
                       
                  ==> delete_ok.jsp전송 (no,pwd)
                  ==> no,pwd ==> FreeBoardDAO로 전송하면
                                 데이터베이스 처리
                  ==> main.jsp?mode=1 (list.jsp)      
 --%>
 <%
     String strNo=request.getParameter("no");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function del()
{
	// if($(pwd).value=="") JQuery
	var f=document.del_frm;
	if(f.pwd.value=="")
	{
		alert("비밀번호를 입력하세요");
		f.pwd.focus();
		return;
	}
	
	f.submit();
}
</script>
</head>
<body>
   <center>
     <h3>삭제하기</h3>
     <form method=post action="../board/delete_ok.jsp" name="del_frm">
     <table border=1 bordercolor=black width=300 cellpadding="0"
      cellspacing="0">
      <tr height=35>
        <td width=30% align=right>비밀번호</td>
        <td width=70% align=left>
         &nbsp;<input type=password name=pwd size=15>
         <input type=hidden name=no value=<%=strNo %>>
        </td>
      </tr>
      <tr height=35>
        <td colspan=2 align=center>
         <input type=button value=삭제 onclick="del()">
         <input type=button value=취소 
            onclick="javascript:history.back()">
        </td>
      </tr>
     </table>
     </form>
   </center>
</body>
</html>











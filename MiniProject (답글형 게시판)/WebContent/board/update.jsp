<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.board.*"%>
<%--              no                no
      content.jsp====>(update.jsp <=====> FreeBoardDAO) 
                             text에 값을 채운다
                      ==>수정 요청 
                      (update_ok.jsp <=====> FreeBoardDAO)
                      ==>list.jsp(main.jsp?mode=1)
--%>
<jsp:useBean id="dao" class="my.board.FreeBoardDAO"/>
<%
    //content.jsp==> no전송
    String strNo=request.getParameter("no");
    FreeBoardVO vo=dao.getUpdateData(Integer.parseInt(strNo));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function send()
{
	var f=document.frm;
	if(f.name.value=="")
	{
		alert("이름을 입력하세요");
		f.name.focus();
		return;
	}
	if(f.subject.value=="")
	{
		alert("제목을 입력하세요");
		f.subject.focus();
		return;
	}
	if(f.content.value=="")
	{
		alert("내용을 입력하세요");
		f.content.focus();
		return;
	}
	if(f.pwd.value=="")
	{
		alert("비밀번호을 입력하세요");
		f.pwd.focus();
		return;
	}

	f.submit();//form ==> action에 있는 페이지로 데이터 전송
}
</script>
</head>
<body>
   <center>
      <img src="../image/title.gif">
      <br><br>
      <form method=post action="../board/update_ok.jsp" name="frm">
      <table border=1 bordercolor=#ccccff width=500 
       cellspacing="0" cellpadding="0">
        <tr>
         <td>
          <table border=0 width=500 cellspacing="0"
           cellpadding="0">
           <tr height=30>
            <td width=15% align=right>이름</td>
            <td width=85% align=left>
             <input type=text size=15 name=name value="<%=vo.getName()%>">
             <input type=hidden name=no value=<%= strNo%>>
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right>Email</td>
            <td width=85% align=left>
             <input type=text size=45 name=email value="<%=vo.getEmail()%>">
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right>제목</td>
            <td width=85% align=left>
             <input type=text size=45 name=subject value="<%=vo.getSubject()%>">
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right valign=top>내용</td>
            <td width=85% align=left>
             <textarea rows="15" cols="50" name="content"><%=vo.getContent() %></textarea>
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right>비번</td>
            <td width=85% align=left>
             <input type=password size=10 name=pwd>
            </td>
           </tr> 
           <tr height=30>
            <td colspan=2 align=center>
             <input type=button value="수정하기" onclick="send()">
             <input type=button value="취소" 
               onclick="javascript:history.back()">
            </td>
           </tr> 
          </table>
         </td>
        </tr>
      </table>
      </form>
   </center>
</body>
</html>
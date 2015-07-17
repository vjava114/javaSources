<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
      main(글쓰기) = insert.jsp   ==   insert_ok.jsp <==> DAO <==> Oracle 
                     이름     NOT NULL     ==> main.jsp(list.jsp출력) sendRedirect
                     email
                     subject  NOT NULL
                     content  NOT NULL
                     pwd      NOT NULL ==> javascript
--%>
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
	// 85=>1 87=>2 90=>3   1=90
}
</script>
</head>
<%--
     BLOB,BFILE : InputStream ==> 오라클에 사진,동영상 바이트 단위 저장
     CLOB ==> String(9i:InputStream,10g:String)
     JOIN,SUBQUERY,VIEW
     
     => Procedure
     => 데이터베이스 모델링
--%>
<body>
   <center>
      <img src="../image/title.gif">
      <br><br>
      <form method=post action="../databoard/insert_ok.jsp" 
      name="frm" enctype="multipart/form-data">
      <table border=1 bordercolor=#ccccff width=500 
       cellspacing="0" cellpadding="0">
        <tr>
         <td>
          <table border=0 width=500 cellspacing="0"
           cellpadding="0">
           <tr height=30>
            <td width=15% align=right>이름</td>
            <td width=85% align=left>
             <input type=text size=15 name=name>
            </td>
           </tr> 
           
           <tr height=30>
            <td width=15% align=right>제목</td>
            <td width=85% align=left>
             <input type=text size=45 name=subject>
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right valign=top>내용</td>
            <td width=85% align=left>
             <textarea rows="15" cols="50" name="content"></textarea>
            </td>
           </tr> 
           <tr height=30>
            <td width=15% align=right>파일</td>
            <td width=85% align=left>
             <input type=file size=35 name=upload>
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
             <input type=button value="글쓰기" onclick="send()">
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

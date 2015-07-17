<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.member.*,java.util.*"%>
<jsp:useBean id="dao" class="my.member.MemberDAO"/>
<%
    request.setCharacterEncoding("EUC-KR");
    String dong=request.getParameter("dong");
    ArrayList<ZipcodeVO> list=null;
    if(dong!=null)
    {
    list=dao.getPostFind(dong);
    }
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
td,th{font-family: 휴먼매직체;font-size:9pt}
a{
    text-decoration: none;
    color:black;
}
a:hover{
   text-decoration: underline;
   color:green;
}
</style>
<script type="text/javascript">
function find()
{
	var f=document.frm;
	if(f.dong.value=="")
	{
		alert("동/읍/면을 입력하세요");
		f.dong.focus();
		return;
	}
	
	f.submit();
}
function post(zip,addr)
{
	opener.join_frm.post1.value=zip.substring(0,3);
	opener.join_frm.post2.value=zip.substring(4,7);
	opener.join_frm.addr1.value=addr;
	self.close();
	
}
</script>
</head>
<body>
  <center>
    <h3>우편번호 찾기</h3>
    <form method=post action="postfind.jsp" name=frm>
    <table border=0 width=350>
     <tr>
      <td align=right width=20%>우편번호</td>
      <td align=left width=80%>
        <input type=text name=dong size=12>
        <input type=button value="검색" onclick="find()">
      </td>
     </tr>
     <tr>
       <td colspan=2 align=right>
         <font color=red size=2>
                ※동/읍/면을 입력하세요
         </font>
       </td>
     </tr>
    </table>
    </form>
    <br>
    <table border=0 width=350>
     <tr bgcolor=#ccccff>
      <th width=25%>우편번호</th>
      <th width=75%>주소</th>
     </tr>
     <%
         if(list!=null)
         {
        	 for(int i=0;i<list.size();i++)
        	 {
        		 ZipcodeVO vo=list.get(i);
        		 String zipcode=vo.getZipcode();
        		 String temp=vo.getSido()+" "
        				     +vo.getGugun()+" "
        				     +vo.getDong()+" "
        				     +vo.getBunji();
     %>
               <tr>
                <td width=25%><%=zipcode %></td>
                <td width=75% align=left>
                <a href="javascript:post('<%=zipcode %>','<%=temp %>')"><%=temp %></a>
                </td>
               </tr>
     <%
        	 }
         }
     %>
    </table>
    <hr width=350>
  </center>
</body>
</html>















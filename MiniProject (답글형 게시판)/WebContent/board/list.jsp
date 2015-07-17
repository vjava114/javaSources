<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,my.board.*"%>
<jsp:useBean id="dao" class="my.board.FreeBoardDAO"/>
<%--
    FreeBoardDAO dao=new FreeBoardDAO();
 --%>
 <%!
      int totalpage=0; //총페이지
      int curpage=1; //현재페이지
      int back=0;  //이전페이지
      int forward=0;//다음페이지
 %>
 <%
     String strPage=request.getParameter("page");
     if(strPage==null)
    	 strPage="1";
     curpage=Integer.parseInt(strPage);
     
     ArrayList<FreeBoardVO> list=dao.getBoardAllData(curpage);
     totalpage=dao.getTotalPage();
     
     int count=dao.getCount();
     count=count-((curpage*10)-10);
     /*
         count=16-10
         1p  2p
         16  6
         15  5
         14  4
         13  3
         12  2
         --  1
         11
         10
         9
         8
         7
         
     */
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
  <center>
    <img src="../image/title.gif">
    <br>
    <table width=700 border=0 cellpadding="0" cellspacing="0">
     <tr>
      <td align=left>
       <a href="main.jsp?mode=3">
       <img src="../image/write.gif" border=0>
       </a>
      </td>
     </tr>
    </table>
    <br>
    <table width=700 border=0 cellpadding="0" cellspacing="0">
      <tr bgcolor=#ccccff height=25>
       <th width=10%>번호</th>
       <th width=45%>제목</th>
       <th width=15%>이름</th>
       <th width=20%>작성일</th>
       <th width=10%>조회수</th>
      </tr>
      <%
          String color="";
          for(int i=0;i<list.size();i++)
          {
        	  if(i%2==1)
        		  color="rgb(255,255,220)";
        	  else
        		  color="white";
        	  
        	  FreeBoardVO vo=list.get(i);
      %>
              <tr height=25 bgcolor=<%=color %>>
                <td width=10%><%=count-- %></td>
		        <td width=45% align=left>
		         <%
		             if(vo.getJump()==0)
		             {
		         %>
		                <a href="main.jsp?mode=2&no=<%=vo.getNo()%>"><%=vo.getSubject() %></a>
		         <%
		             }
		             else
		             {
		            	 for(int j=0;j<vo.getJump();j++)
		            	 {
		         %>
		                    &nbsp;&nbsp;
		         <%
		                  }
		         %>
		                 <img src="../image/re_icon.gif">
		                 <a href="main.jsp?mode=2&no=<%=vo.getNo()%>"><%=vo.getSubject() %></a>
		         <%
		             }
		         
		             Date date=new Date();
		             SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		             if(vo.getRegdate().toString().equals(sdf.format(date)))
		             {
		         %>
		                 <sup><img src="../image/new.gif"></sup>
		         <%
		             }
		         %>
		        </td>
		        <td width=15%>
		         <a href="mailto:<%=vo.getEmail()%>"><%=vo.getName() %></a>
		        </td>
		        <td width=20%><%=vo.getRegdate().toString() %></td>
		        <td width=10%><%=vo.getReadnum() %></td>
              </tr>
      <%
          }
      %>
    </table>
    <hr width=700>
    <table width=700 border=0 cellpadding="0" cellspacing="0">
      <tr>
        <td align=left>
         Search&nbsp;
         <select name="fs">
          <option value="name">이름</option>
          <option value="subject">제목</option>
          <option value="content">내용</option>
         </select>
         <input type=text name=ss size=15>
         <input type=button value="찾기">
        </td>
        <td align=right>
         <%
            if(curpage>1)
            	back=curpage-1;
         %>
         <a href="main.jsp?mode=1&page=<%=back%>">
          <img src="../image/back.gif" border=0></a>
         
           <%
               for(int i=1;i<=totalpage;i++)
               {
           %>
                 [
           
                  <a href="main.jsp?mode=1&page=<%=i %>"><%=i %></a>
                 ]
           <%
               }
           %>
         <%
            if(curpage<totalpage)
            	forward=curpage+1;
         %>
         <a href="main.jsp?mode=1&page=<%=forward%>"><img src="../image/next.gif" border=0></a>
         &nbsp;&nbsp;&nbsp;
         <%=curpage %> page / <%=totalpage %> pages
        </td>
      </tr>
    </table>
  </center>
</body>
</html>









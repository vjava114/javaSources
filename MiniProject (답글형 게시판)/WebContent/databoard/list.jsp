<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.databoard.*,java.util.*"%>
<jsp:useBean id="dao" class="my.databoard.DataBoardDAO"/>
<%
    ArrayList<DataBoardVO> list=
                    dao.getDataBoardAllData();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
var i=0;
function content(id)
{
	var p=document.getElementById(id);
	//�±׸� ������ �´� 
	if(i==0)
	{
		p.style.display='';
		i=1;
	}
	else
	{
		p.style.display='none';
		i=0;
	}
}
</script>
</head>
<body>
   <center>
     <h3>�ڷ��</h3><br>
     <table border=0 width=600 cellspacing="0"
       cellpadding="0">
       <tr>
        <td align=left>
        <a href="main.jsp?mode=10">����</a>
        </td>
       </tr>
     </table>
     <table border=0 width=600 cellspacing="0"
       cellpadding="0">
       <tr bgcolor=#ccccff>
         <th width=10%>��ȣ</th>
         <th width=45%>����</th>
         <th width=15%>�̸�</th>
         <th width=20%>�ۼ���</th>
         <th width=10%>��ȸ��</th>
       </tr>
       <%
           if(list!=null)
           {
        	   for(DataBoardVO vo:list)
        	   {
        		   //for(int i=0;i<list.size();i++)
       %>
                   <tr>
                     <td width=10%><%=vo.getNo() %></td>
                     <td width=45% align=left>
                     <a href="javascript:content('m<%=vo.getNo()%>')"><%=vo.getSubject() %></a>
                     </td>
                     <td width=15%><%=vo.getName() %></td>
                     <td width=20%><%=vo.getRegdate().toString() %></td>
                     <td width=10%><%=vo.getReadnum() %></td>
                   </tr>
                   <tr id="m<%=vo.getNo() %>" style="display:none">
                     <td colspan=5 align=left valign=top>
                     <hr>
                       �ۼ���:<font color=blue><b><%=vo.getName() %></b></font>
                       <br>
                       <pre><%=vo.getContent() %></pre>
                       <br>
                       <%
                           if(vo.getFilesize()!=0)
                           {
                       %>
                              ÷������:<a href="../databoard/download.jsp?filename=<%=vo.getFilename()%>"><%=vo.getFilename() %></a>(<%=vo.getFilesize() %>)Bytes
                       <%
                           }
                       %>
                       &nbsp;<a href="">����</a>&nbsp;&nbsp;
                       <a href="main.jsp?mode=11&no=<%=vo.getNo()%>">����</a>
                     <hr>
                     </td>
                   </tr>
       <%
        	   }
           }
       %>
     </table>
     <hr width=600>
   </center>
</body>
</html>












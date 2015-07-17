<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
     1. JSP
        ������ 
          1) page : �������� ���� ����
             = contentType : ��ȯ �ڵ�(text/html,text/xml= charset=euc-kr)
               *** �ȵ���̵�� ������,Flex�� ����:UTF-8
                   ���� == http-->wap (XML,JSON:�ڹٽ�ũ��Ʈ ������ ǥ����)
             = import : jsp���� �ڹ��� ���̺귯���� ����Ҷ�
               *** �ٸ� �Ӽ��� �ѹ��� ����� �����ϴ�
               ��) <%@ page import="java.util.*"%>
                   <%@ page import="java.io.*"%>
                   <%@ page import="java.util.*,java.io.*"%> 
             = errorPage : ���� �߻��� ó���ϴ� ������ ����
          2) include : Ư�������� �ٸ� ���� ÷�� �Ҷ� ���
                       <%@ include file="���ϸ�"%> : ����(������ ����)
                       <jsp:include page="���ϸ�"%>: ����(���� ������ �ȵȴ�)
                       HTML) <iframe> : ���Ͼȿ� �ٸ� ���� ÷��
          3) taglib : XML���� , ��ȯ�� �Ǹ� �ڹپ��� ����
                      <c:if> : if , <c:forEach> : for
                      struts <s:if> ,spring <spring:html>,jstl,���������
         ���尴ü 
            request : ��û�� ����,Ŭ���̾�Ʈ ����
                      1) getParameter() , getParameterValues()
                      2) setCharacterEncoding() : �ѱۺ�ȯ
                      3) setAttribute():�Ӽ� �߰�
                      4) getAttribute(): �߰��� �Ӽ��� �о� �ö�
            response : ���� 
                      1) sendRedirect() : �������� �ٸ� JSP�� �̵�
            session : �������� Ŭ���̾�Ʈ ���� ����
                      1) setAttribute() : ����
                      2) getAttribute() : ���尪 ������ ����
                      3) invalidate() : ����
            application : ���� ���� ���� ����(web.xml)
                          getInitParameter()
                          log() : �������� ���� ���
          JSP���� �ڹ� ���
            1) ǥ���� : �������� ��� 
               out.println() : ����
               <%= %> : JSP�� �Ϲ� ����
               ${} : ���� �ܺο��� ���� ���� ���Ǵ� ǥ����
               <jsp:getProperty> : ���� ���󵵰� ����
            2) �ڹ� �Ϲ� �ڵ� : ��ũ��Ʈ��
               <% %> ==> taglib����
               
            3) �޼ҵ� ����,�������� : ���� 
               <%! %> : ���ȹ��� 
               
           JSP���� ���Ǵ� �±�(�׼�)
               <jsp:useBean> Ŭ���� ��ü ����
               <jsp:useBean id="a" class="A"/>
                   A a=new A();
               <jsp:setProperty> : ��û�� �����͸� �޾Ƽ� 
                                   �ش簴ü�� ���� �ο�
                                   ==>��ü�� setXxx()�޼ҵ� ��ü ȣ��
               <jsp:include> : Ư�������� �ٸ� JSP�������� 
                               �������� �߰� (JPanel)
               <jsp:forward> : �ٸ� JSP���� ���������� 
                               �о�ͼ� ��� (CardLayout)
                 ==> MVC�� �ַ� ���Ǵ� �±�
                 
           JSP���� �����̵� ���(������ ����)
              POST : ���� ��Ʈ��ũ�� ���ؼ� ����
                  HTML : <form>
              GET : URL�ڿ� ������ ���� ����
                  HTML : <a>
                  javascript : location.href
                  jsp : sendRedirect()
                        forward()
           JSP���� �ڹپ�� ���(������..)
              �ڹٺ� 
                = �׼Ǻ� : ���� ����� ó���ϴ� Ŭ����
                           ~DAO,����...
                = �����ͺ� (�Ϲ� ������������ ���) : �б�/����
                            ==> setXxx(),getXxx(),isXxx()
                                ~VO , ~DTO
           JSP���� �����ͺ��̽� ���(12�� ����)
                Class.forName("") ����Ŭ ����̹� ���
                Connection : getConnection(url,username,pwd)
                Statement : executeQuery() : SELECT 
                            executeUpdate() : INSERT,UPDATE...
                ResultSet : ����� (���ڵ������ ����)
                   *** Ŀ���� ��ġ 
                       ��°� ù��° ��ġ : next()
                       ������ ��ġ : previous()
                       
                   *** ���� �ڹ� �����ͷ� ������ �ö� 
                   CHAR,VARCHAR2,CLOB : String (getString())
                   NUMBER : int (getInt())
                   BLOB,BFILE : InputStream(getInputStream())
                   TIMESTAMP,DATE : Date (getDate())
                       yy/mm/dd
                       => getDate()�� ���ڿ� : yyyy-MM-dd
                       
                ==> Ʈ�����,���ν���, MVC (���)
                   
           �帧 : content.jsp ==> no 
                       delete.jsp (no�� �޴´�)
                       
                  ==> delete_ok.jsp���� (no,pwd)
                  ==> no,pwd ==> FreeBoardDAO�� �����ϸ�
                                 �����ͺ��̽� ó��
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
		alert("��й�ȣ�� �Է��ϼ���");
		f.pwd.focus();
		return;
	}
	
	f.submit();
}
</script>
</head>
<body>
   <center>
     <h3>�����ϱ�</h3>
     <form method=post action="../board/delete_ok.jsp" name="del_frm">
     <table border=1 bordercolor=black width=300 cellpadding="0"
      cellspacing="0">
      <tr height=35>
        <td width=30% align=right>��й�ȣ</td>
        <td width=70% align=left>
         &nbsp;<input type=password name=pwd size=15>
         <input type=hidden name=no value=<%=strNo %>>
        </td>
      </tr>
      <tr height=35>
        <td colspan=2 align=center>
         <input type=button value=���� onclick="del()">
         <input type=button value=��� 
            onclick="javascript:history.back()">
        </td>
      </tr>
     </table>
     </form>
   </center>
</body>
</html>











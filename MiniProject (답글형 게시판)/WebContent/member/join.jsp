<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function postfind()
{
	window.open("../member/postfind.jsp","postfind","width=390, height=400, menubar=no, statusbar=no, titlebar=no, toolbar=no, scrollbars=yes");
}
function idcheck()
{
	var f=document.join_frm;
	if(f.id.value=="")
    {
		alert("ID�� �Է��ϼ���");
		f.id.focus();
		return;
    }
	window.open("../member/idcheck.jsp?id="+f.id.value,"idcheck","width=350, height=250, menubar=no, statusbar=no, titlebar=no, toolbar=no, scrollbars=yes");
}
function join()
{
	 //������ ó��
	 document.join_frm.submit();
}
</script>
</head>
<body>
   <center>
     <h3>ȸ�� ����</h3>
     <form method=post name=join_frm action="../member/join_ok.jsp">
     <table border=1 bordercolor=black width=500 cellpadding="0"
     cellspacing="0">
      <tr>
        <td>
          <table border=0 width=500>
           <tr>
             <td width=15% align=right>ID</td>
             <td width=85% align=left>
              <input type=text name=id size=12>
              <input type=button value=�ߺ�üũ onclick="idcheck()">
             </td>
           </tr>
           <tr>
             <td width=15% align=right>��й�ȣ</td>
             <td width=85% align=left>
              <input type=password name=pwd size=12>
              &nbsp;&nbsp;
              ���Է�:<input type=password name=pwd2 size=12>
             </td>
           </tr>
           <tr>
             <td width=15% align=right>�̸�</td>
             <td width=85% align=left>
              <input type=text name=name size=12>
             </td>
           </tr>
           <tr>
             <td width=15% align=right>�̸���</td>
             <td width=85% align=left>
              <input type=text name=email size=45>
             </td>
           </tr>
           <tr>
             <td width=15% align=right>����</td>
             <td width=85% align=left>
              <input type=radio name=sex value=���� checked>����   
              <input type=radio name=sex value=����>����
             </td>
           </tr>
           <tr>
             <td width=15% align=right>��ȭ��ȣ</td>
             <td width=85% align=left>
              <select name=tel1>
               <option>010</option>
               <option>011</option>
               <option>017</option>
               <option>018</option>
               <option>019</option>
              </select>
              <input type=text name=tel2 size=7>-
              <input type=text name=tel3 size=12>
             </td>
           </tr>
           <tr>
             <td width=15% align=right>�����ȣ</td>
             <td width=85% align=left>
              <input type=text name=post1 size=5 readonly>-
              <input type=text name=post2 size=5 readonly>
              <input type=button value="�����ȣ �˻�" 
               onclick="postfind()">
             </td>
           </tr>
           <tr>
             <td width=15% align=right>�ּ�</td>
             <td width=85% align=left>
              <input type=text name=addr1 size=50 readonly>
             </td>
           </tr>
           <tr>
             <td width=15% align=right>���ּ�</td>
             <td width=85% align=left>
              <input type=text name=addr2 size=50>
             </td>
           </tr>
           
           <tr>
             <td width=15% align=right>�Ұ�</td>
             <td width=85% align=left>
              <textarea rows="12" cols="55" name=content></textarea>
             </td>
           </tr>
           <tr>
             <td colspan=2 align=center>
              <input type=button value=���� onclick="join()">
              <input type=button value=��� 
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













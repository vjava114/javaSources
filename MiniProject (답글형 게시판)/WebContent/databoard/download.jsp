<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.io.*,java.net.*"%>
<%
    String filename=request.getParameter("filename");
    System.out.println(filename);
    String path="c:\\download\\";
    try
    {
    	//��û�� ����ڿ��� ���� ��Ʈ���� �غ��Ѵ�
    	//�̹� �غ�Ǿ� �ִ� ��Ʈ���� �ֱ� ������
    	//�ݵ�� ���� �ٽ� ����
    	out.clear();
    	out=pageContext.pushBody();
    	
    	File f=new File(path+filename);
    	int size=(int)f.length();
    	response.setHeader("Content-Disposition",
    			"attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
    	response.setContentLength(size);
    	
    	BufferedInputStream bis=
    			 new BufferedInputStream(new FileInputStream(f));
    	BufferedOutputStream bos=
    			new BufferedOutputStream(response.getOutputStream());
    	
    	byte[] buffer=new byte[1024];
    	int i=0;
    	while((i=bis.read(buffer, 0, 1024))!=-1)
    	{
    		bos.write(buffer, 0, i);
    	}
    	bis.close();
    	bos.close();
    	
    }catch(Exception ex)
    {
    	
    }
%>







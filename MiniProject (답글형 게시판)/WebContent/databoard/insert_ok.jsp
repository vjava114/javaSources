<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="my.databoard.*,com.oreilly.servlet.*,com.oreilly.servlet.multipart.*,com.oreilly.*,java.io.*"%>
<%
       String path="c:\\download";
       String enctype="EUC-KR";
       int size=500*1024*1024;
       
       MultipartRequest mr=new MultipartRequest(request,path,size,enctype,new DefaultFileRenamePolicy());
       //값을 받는다
       String name=mr.getParameter("name");
       String subject=mr.getParameter("subject");
       String content=mr.getParameter("content");
       String pwd=mr.getParameter("pwd");
       String fn=mr.getFilesystemName("upload");
       
       // DB : filename,filesize
       DataBoardVO vo=new DataBoardVO();
       vo.setName(name);
       vo.setSubject(subject);
       vo.setContent(content);
       vo.setPwd(pwd);
       
       if(fn==null)//upload가 안된상태
       {
    	   vo.setFilename("");
    	   vo.setFilesize(0);
       }
       else//upload가 된상태
       {
    	   File f=new File(path+"\\"+fn);
    	   vo.setFilename(fn);
    	   vo.setFilesize((int)f.length());
       }
       
       //DB연동
       DataBoardDAO dao=new DataBoardDAO();
       dao.insert(vo);
       //이동
       response.sendRedirect("../main/main.jsp?mode=9");
       
%>














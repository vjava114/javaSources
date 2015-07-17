<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>	

 <%

 
	 String regid = request.getParameter("regid");
 	String deviceInfo = request.getParameter("deviceInfo");
 
 	
 	
 	
 	regid = "APA91bHDmnomrgJOGQyOTv3LEMdiuD6Lyg9MCaPkFtvYBC8klASGsAlRzPxlM2xr318v7731--wO8JnpqyUr7rHQK-dcHjyKhjq_OKzjGe0fiFYZnz2QSP1xYIGjrYdWzgE1I2Lm_GYf";
 	// regid 를 임의로 등록하였습니다.
 	
	 try {
		 String driverName = "com.mysql.jdbc.Driver";	
		 String url = "jdbc:mysql://localhost/kang_test";	
		 ResultSet rs = null;

	  

		 Class.forName(driverName);

		 Connection con = DriverManager.getConnection(url,"root","wnsdud12");

	  

		 Statement stmt = con.createStatement();			

		 String sql = "insert into gcm (regid) values ('" + regid + "')";
		 System.out.println("sql 로그 : " + sql);

		 //rs = stmt.executeQuery(sql);				<=== 처음에 이렇게 되어있었는데 insert 날릴땐 아래처럼 써야한다.
		 stmt.execute(sql);

		 out.print("success");

		 con.close();

	 }catch (Exception e) {
		 System.out.println("에러메시지 :" + e.getMessage());

		 out.print("fail");		

	 }

%>

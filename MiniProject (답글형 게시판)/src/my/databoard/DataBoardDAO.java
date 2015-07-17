package my.databoard;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import javax.naming.*;

/*
 *    servlet-api.jar(tomcat->lib) : jdk1.7.0.29=>jre=>lib=>ext
 *    commons-dbcp.jar,commons-pool.jar : tomcat=>lib
 *    ojdbc6.jar (oracle) : jdk1.7.0.29=>jre=>lib=>ext
 *    
 *    ibatis2.756.jar ==> 프로젝트 폴더 WEB-INF==>lib
 *    jstl.jar,cos.jar,spring관련 lib==> WEB-INF==>lib
 */
public class DataBoardDAO {
    private Connection conn;
    private PreparedStatement ps;
    
    public void getConnection()
    {
    	try
    	{
    		Context init=new InitialContext();
    		Context  c=(Context)init.lookup("java://comp/env");
    		DataSource ds=(DataSource)c.lookup("jdbc/oracle");
    		conn=ds.getConnection();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    
    public void disConnection()
    {
    	try
    	{
    		if(ps!=null) ps.close();
    		if(conn!=null) conn.close();
    	}catch(Exception ex){}
    }
    
    //기능
    //1. 목록(다운로드) ==> IO (file copy)
    public ArrayList<DataBoardVO> getDataBoardAllData()
    {
    	ArrayList<DataBoardVO> list=
    			new ArrayList<DataBoardVO>();
    	try
    	{
    		//연결
    		getConnection();
    		//쿼리문장 전송 : commons-fileupload(apache)(MVC)
    		//cos.jar ==> 오레일리(jsp)
    		String sql="SELECT no,name,subject,content,readnum,regdate,"
    				+"filename,filesize "
    				+"FROM databoard "
    				+"ORDER BY no DESC";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			DataBoardVO vo=new DataBoardVO();
    			vo.setNo(rs.getInt(1));
    			vo.setName(rs.getString(2));
    			vo.setSubject(rs.getString(3));
    			vo.setContent(rs.getString(4));
    			vo.setReadnum(rs.getInt(5));
    			vo.setRegdate(rs.getDate(6));
    			vo.setFilename(rs.getString(7));
    			vo.setFilesize(rs.getInt(8));
    			
    			list.add(vo);
    			// int a=null
    		}
    		rs.close();
    		
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    	return list;
    }
    //2. 추가(업로드)
    public void insert(DataBoardVO vo)
    {
    	try
    	{
    		getConnection();
    		String sql="SELECT MAX(no) FROM databoard";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		int max=rs.getInt(1);
    		rs.close();
    		ps.close();
    		
    		sql="INSERT INTO databoard VALUES(?,?,?,?,?,0,SYSDATE,?,?)";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, max+1);
    		ps.setString(2, vo.getName());
    		ps.setString(3, vo.getSubject());
    		ps.setString(4, vo.getContent());
    		ps.setString(5, vo.getPwd());
    		ps.setString(6, vo.getFilename());
    		ps.setInt(7, vo.getFilesize());
    		
    		ps.executeUpdate();
    		
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    }
    //3. 수정
    //4. 삭제
    public boolean delete(int no,String pwd)
    {
    	boolean bCheck=false;
    	try
    	{
    		getConnection();
    		String sql="SELECT pwd FROM databoard "
    				+"WHERE no=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, no);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		String db_pwd=rs.getString(1);
    		rs.close();
    		ps.close();
    		
    		if(db_pwd.equals(pwd))
    		{
    			sql="DELETE FROM databoard "
    			   +"WHERE no=?";
    			ps=conn.prepareStatement(sql);
    			ps.setInt(1,no);
    			ps.executeUpdate();
    			bCheck=true;
    		}
    		else
    		{
    			bCheck=false;
    		}
    		
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    	return bCheck;
    }
}





















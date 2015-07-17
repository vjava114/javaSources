package my.member;
/*
 *   기능 
 *    1) 우편번호 === 9/18
 *    2) 아이디 중복체크
 *    3) 회원가입
 *    4) 회원수정
 *    5) 회원탈퇴
 *    6) 로그인
 *    7) 쪽지확인
 *    8) 쪽지보내기
 *    9) 쪽지삭제
 */
import java.sql.*;
import java.util.*;
public class MemberDAO {
    private Connection conn;//오라클 연결 (Socket)
    // 오라클의 IP,PORT
    private PreparedStatement ps;//쿼리문장 (InputStream,OutputStream)
    // 오라클연결 ==> TCP
    private final String URL="jdbc:oracle:thin:@localhost:1522:ORA11";
    private final String DRIVER="oracle.jdbc.driver.OracleDriver";
    
    //드라이버 등록
    public MemberDAO()
    {
    	try
    	{
    		Class.forName(DRIVER);
    		//드라이버 등록 ==> DriverManager클래스가 리플렉션
    		//리플렉션 : 클래스의 이름만으로 메모리 할당
    		//이름으로 메모리 할당 : 패키지명부터 출력
    	}catch(ClassNotFoundException cf)
    	{
    		System.out.println(cf.getMessage());
    	}
    }
    //오라클 연결
    public void getConnection()
    {
    	try
    	{
    		conn=DriverManager.getConnection(URL,"scott","tiger");
    	}catch(Exception ex){}
    }
    //오라클 해제
    public void disConnection()
    {
    	try
    	{
    		if(ps!=null)ps.close();
    		if(conn!=null)conn.close();
    	}catch(Exception ex){}
    }
    
    //기능 처리
    //1.우편번호 검색
    public ArrayList<ZipcodeVO> getPostFind(String dong)
    {
    	ArrayList<ZipcodeVO> list=
    			new ArrayList<ZipcodeVO>();
    	try
    	{
    		getConnection();
    		String sql="SELECT zipcode,sido,gugun,dong,"
    				+"NVL(bunji,' ') "
    				+"FROM ZIPCODE "
    				+"WHERE dong LIKE '%'||?||'%'";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1,dong);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			ZipcodeVO vo=new ZipcodeVO();
    			vo.setZipcode(rs.getString(1));
    			vo.setSido(rs.getString(2));
    			vo.setGugun(rs.getString(3));
    			vo.setDong(rs.getString(4));
    			vo.setBunji(rs.getString(5));
    			
    			//list에 첨부
    			list.add(vo);
    			
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
    // SELECT id FROM MEMBER
    //아이디 중복처리
    public int idcheck(String id)
    {
    	int count=0;
    	try
    	{
    		getConnection();
    		String sql="SELECT COUNT(*) FROM MEMBER "
    			     +"WHERE id=?";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1,id);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		count=rs.getInt(1);
    		rs.close();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    	return count;
    }    
    
    
    //회원가입
    public void join(MemberVO vo)
    {
    	try
    	{
    		getConnection();
    		String sql="INSERT INTO member VALUES(?,?,?,?,?,"
    				+"?,?,?,?,?,SYSDATE,'n')";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, vo.getId());
    		ps.setString(2, vo.getPwd());
    		ps.setString(3, vo.getName());
    		ps.setString(4, vo.getEmail());
    		ps.setString(5, vo.getSex());
    		
    		ps.setString(6, vo.getTel());
    		ps.setString(7, vo.getPost());
    		ps.setString(8, vo.getAddr1());
    		ps.setString(9, vo.getAddr2());
    		ps.setString(10, vo.getContent());
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
    
    
    
    
    
    //로그인 
    public String isLogin(String id,String pwd)
    {
    	/*
    	 *   ID가 있는지?
    	 *     ID(o)
    	 *       => 비밀번호 확인 
    	 *             pwd(o)  ==> 인증
    	 *                          ==> 세션사용으로 저장 
    	 *                          logout으로 변경
    	 *             pwd(x)  ==> 메세지 전송
    	 *     ID(x) ==> 메세지 전송
    	 *     
    	 *     웹 사이트 
    	 *       화면 변경 (웹 흐름)
    	 */
    	String result="";
    	try
    	{
    		getConnection();
    		String sql="SELECT COUNT(*) FROM member "
    		          +"WHERE id=?";//ID의 존재 여부
    		ps=conn.prepareStatement(sql);
    		ps.setString(1,id);
    		
    		//실행 결과값을 가지고 온다 
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		
    		int count=rs.getInt(1);
    		rs.close();
    		ps.close();
    		//////////////////////////////ID존재 여부 검색
    		if(count==0)
    		{
    			result="NOID";
    		}
    		else
    		{
    			sql="SELECT pwd,name FROM member "
    				+"WHERE id=?";
    			ps=conn.prepareStatement(sql);
    			ps.setString(1, id);
    			
    			rs=ps.executeQuery();
    			rs.next();
    			
    			String db_pwd=rs.getString(1);
    			String name=rs.getString(2);
    			
    			rs.close();
    			//비밀번호 검색
    			if(db_pwd.equals(pwd))//인증
    			{
    				result=name;
    			}
    			else 
    			{
    				result="NOPWD";
    			}
    		}
    		
    		
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    	return result;
    }
    
}














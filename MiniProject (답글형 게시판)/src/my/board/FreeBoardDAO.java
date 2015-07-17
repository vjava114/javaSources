package my.board;
import java.util.*;
import java.sql.*;
/*
 *    JDBC : 수행시마다 오라클 연결 : Connection 
 *           = 수행하는 일이 많아지면 오라클에 부하가 심하다
 *           = 연결하는 시간이 소비된다
 *    Connection을 관리
 *           = 먼저 연결시켜 놓고 기다리고 있다
 *    DBCP ===> 현재 실무 일반적으로 사용(pool)
 *    
 *    코딩의 문제 
 *      라이브러리(SQL Mapper)
 *      IBATIS
 *      HIBERNATE(객체지향) : Spring과 호환
 *      
 *    
 */
/*
 *     DAO 
 *      1) 드라이버 등록 
 *         Class.forName()
 *      2) 연결
 *         Connection.getConnection()
 *      3) 퀴리문장 생성
 *         PreparedStatement.prepareStatement()
 *      4) 퀴리 실행
 *         executeUpdate()
 *         executeQuery()
 *      5) 결과값 출력
 *         ResultSet ==> next()
 *            NUMBER       getInt(),getDouble()
 *            VARCHAR2,CHAR     getString()
 *            CLOB         getString()
 *            DATE         getDate()
 *            BLOB,BFILE   InputStream() : 바이너리 저장
 *            
 */
public class FreeBoardDAO {
	
     private Connection conn;//데이터베이스 연결(Socket)
     private PreparedStatement ps;//입출력(스트림) InputStream,OutputStream
     private final String URL="jdbc:oracle:thin:@localhost:1522:ORA11";
     private final String DRIVER="oracle.jdbc.driver.OracleDriver";
     private final String USERNAME="scott";
     private final String PWD="tiger";
     private static FreeBoardDAO dao;
     
     //드라이버 등록
     public FreeBoardDAO()
     {
    	 try
    	 {
    		 Class.forName(DRIVER);
    		 //리플렉션 (클래스의 이름으로 메모리 할당)
    	 }catch(ClassNotFoundException ex)
    	 {
    		 System.out.println(ex.getMessage());
    	 }
     }
     //오라클 연결
     public void getConnection()
     {
    	 try
    	 {
    		 conn=DriverManager.getConnection(URL,USERNAME,PWD);
    	 }catch(Exception ex){}
     }
     //오라클 연결 해제
     public void disConnection()
     {
    	 try
    	 {
    		 if(ps!=null)ps.close();
    		 if(conn!=null)conn.close();
    	 }catch(Exception ex){}
     }
     //싱글턴 : 객체 메모리를 한번만 생성(면접)
     public static FreeBoardDAO newInstance()
     {
    	 if(dao==null)
    		 dao=new FreeBoardDAO();
    	 return dao;
     }
     //기능
     //1. 목록 ==> 내용보기(답변,수정,삭제) ==> 추가 ==> 찾기
     public ArrayList<FreeBoardVO> getBoardAllData(int curpage)
     {
    	 ArrayList<FreeBoardVO> list=new ArrayList<FreeBoardVO>();
    	 try
    	 {
    		 /*                      ref    step   jump
    		  *      AAAAA            2       1     0
    		  *        ->BBBBB        2       2     1
    		  *          ->CCCCC      2       3     2
    		  *      DDDDD            1       1     0
    		  *      
    		  *             ORDER BY ref DESC,step ASC
    		  *             
    		  *             ORDER BY deptno DESC,ename ASC
    		  */
    		 //연결
    		 getConnection();
    		 //쿼리문장 생성
    		 String sql="SELECT no,subject,name,NVL(email,' '),regdate,readnum,jump "
    				 +"FROM FREEBOARD ORDER BY ref DESC,step ASC";
    		 ps=conn.prepareStatement(sql);//쿼리문장 생성
    		 ResultSet rs=ps.executeQuery();
    		 /*
    		  *   페이지 나누기 
    		  */
    		 int i=0; // 10개씩 i<10
    		 int j=0; // while문이 돌아가는 횟수 j>=pagecnt
    		 int pagecnt=(curpage*10)-10;//각 페이지 시작위치
    		 /*
    		  *  while()
    		  *  {
    		  *    j++;
    		  *  }
    		  */
    		 /*
    		  *   1p  2p  3p
    		  *    0  10  20
    		  *    
    		  *    9  19  29
    		  */
    		 while(rs.next())
    		 {
    			 if(i<10 && j>=pagecnt)
    			 {
    				 FreeBoardVO vo=new FreeBoardVO();
    				 vo.setNo(rs.getInt(1));
    				 vo.setSubject(rs.getString(2));
    				 vo.setName(rs.getString(3));
    				 vo.setEmail(rs.getString(4));
    				 vo.setRegdate(rs.getDate(5));
    				 vo.setReadnum(rs.getInt(6));
    				 vo.setJump(rs.getInt(7));
    				 
    				 list.add(vo);
    				 i++;
    			 }
    			 j++;
    		 }
    		 
    		 rs.close();
    		 
    	 }catch(Exception ex)
    	 {
    		 System.out.println(ex.getMessage());
    	 }
    	 finally
    	 {
    		 disConnection();
    		 //연결 해제
    	 }
    	 return list;
     }
     //총페이지
     public int getTotalPage()
     {
    	 int total=0;
    	 try
    	 {
    		 getConnection();
    		 String sql="SELECT COUNT(*) FROM FREEBOARD";
    		 ps=conn.prepareStatement(sql);
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 int count=rs.getInt(1);
    		 rs.close();
    		 
    		 total=count/10;// 12 (10,2)
    		 if(count%10>0)
    		      total++;
    	 }catch(Exception ex)
    	 {
    		 System.out.println(ex.getMessage());
    	 }
    	 finally
    	 {
    		 disConnection();
    	 }
    	 return total;
     }
     
     //내용보기
     public FreeBoardVO getContentData(int no)
     {
    	 FreeBoardVO vo=new FreeBoardVO();
    	 try
    	 {
    		 //연결
    		 getConnection();
    		 //쿼리생성
    		 //조회수 증가
    		 String sql="UPDATE FREEBOARD SET "
    		           +"readnum=readnum+1 "
    		           +"WHERE no=?";
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, no);
    		 ps.executeUpdate();
    		 ps.close();
    		 //요청 데이터를 가지고 온다
    		 sql="SELECT name,readnum,NVL(email,' '),"
    				 +"regdate,subject,content "
    				 +"FROM FREEBOARD "
    				 +"WHERE no=?";
    		 //전송
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, no);
    		 //실행
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 //값
    		 vo.setName(rs.getString(1));
    		 vo.setReadnum(rs.getInt(2));
    		 vo.setEmail(rs.getString(3));
    		 vo.setRegdate(rs.getDate(4));
    		 vo.setSubject(rs.getString(5));
    		 vo.setContent(rs.getString(6));
    		 
    		 rs.close();
    	 }catch(Exception ex)
    	 {
    		 System.out.println(ex.getMessage());
    	 }
    	 finally
    	 {
    		 //해제
    		 disConnection();
    	 }
    	 return vo;
     }
     
     //데이터 추가
     public void insert(FreeBoardVO vo)
     {
    	 /*
    	  *    no : board_no_seq.nextval
    	  *    -------
    	  *    name
    	  *    email
    	  *    subject
    	  *    content
    	  *    pwd
    	  *    -------
    	  *    regdate : sysdate
    	  *    readnum : 0
    	  *    ref ==> 새글 (새롭게 생성)
    	  *    step    : 0
    	  *    jump    : 0
    	  *    root    : 0
    	  *    depth   : 0
    	  */
    	 try
    	 {
    		 getConnection();
    		 String sql="SELECT MAX(ref) FROM FREEBOARD";
    		 ps=conn.prepareStatement(sql);
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 int max=rs.getInt(1);
    		 rs.close();
    		 ps.close();
    		 
    		 //insert
    		 sql="INSERT INTO FREEBOARD VALUES(freeboard_no_seq.nextVal,?,?,?,?,?,"
    				 +"SYSDATE,0,?,0,0,0,0)";
    		 ps=conn.prepareStatement(sql);
    		 ps.setString(1, vo.getName());
    		 ps.setString(2, vo.getEmail());
    		 ps.setString(3,vo.getSubject());
    		 ps.setString(4, vo.getContent());
    		 ps.setString(5, vo.getPwd());
    		 ps.setInt(6, max+1);
    		 
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
     
     //번호 정렬
     public int getCount()
     {
    	 int count=0;
    	 try
    	 {
    		 getConnection();
    		 String sql="SELECT COUNT(*) FROM FREEBOARD";
    		 ps=conn.prepareStatement(sql);
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
     
     //답변하기
     public void reply(int pno,FreeBoardVO vo)
     {
    	 /*
    	  *    no : board_no_seq.nextval
    	  *    -------
    	  *    name
    	  *    email
    	  *    subject
    	  *    content
    	  *    pwd
    	  *    -------
    	  *    regdate : sysdate
    	  *    readnum : 0
    	  *    ref ==> 클릭번호 (pno==>ref)
    	  *    step    : pno==>step+1
    	  *    jump    : pno==>jump+1
    	  *    root    : pno
    	  *    depth   : 0
    	  *    
    	  *    ==> 1. SEELCT ref,step,jump FROM FREEBOARD
    	  *           WHERE no=pno;
    	  *        2. UPDATE FREEBOARD SET 
    	  *                  step=step+1 
    	  *                  WHERE ref=ref AND step>step
    	  *        3. INSERT INTO
    	  *        4. 클릭번호의 depth증가
    	  *    
    	  */
    	 try
    	 {
    		 getConnection();
    		 String sql="SELECT ref,step,jump FROM FREEBOARD "
    				 +"WHERE no=?";//pno
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1,pno);
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 int ref=rs.getInt(1);
    		 int step=rs.getInt(2);
    		 int jump=rs.getInt(3);
    		 rs.close();
    		 ps.close();
    		 /*                   no  ref  step  jump root depth  
    		  *    AAAAA           1   1    0      0   0     2
    		  *      ->FFFFFF      5   1    1      1   1     0
    		  *      ->BBBBB       2   1    2      1   1     1
    		  *      
    		  *       ->CCCCC      3   1    3      2   2     1
    		  *        ->DDDDDD    4   1    4      3   3     0
    		  *      
    		  *      
    		  */
    		 //순서를 바꿔주는 쿼리(답변 퀴리문장의 핵심)
    		 sql="UPDATE FREEBOARD SET step=step+1 "
    				 +"WHERE ref=? AND step>?";
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, ref);
    		 ps.setInt(2,step);
    		 
    		 ps.executeUpdate();
    		 ps.close();
    		 
    		 
    		 sql="INSERT INTO FREEBOARD VALUES("
    				 +"freeboard_no_seq.nextVal,?,?,"
    				 +"?,?,?,SYSDATE,0,?,?,?,?,0)";
    		 ps=conn.prepareStatement(sql);
    		 ps.setString(1,vo.getName());
    		 ps.setString(2, vo.getEmail());
    		 ps.setString(3,vo.getSubject());
    		 ps.setString(4,vo.getContent());
    		 ps.setString(5,vo.getPwd());
    		 
    		 ps.setInt(6, ref);
    		 ps.setInt(7, step+1);
    		 ps.setInt(8,jump+1);
    		 ps.setInt(9, pno);
    		 
    		 ps.executeUpdate();
    		 ps.close();
    		 
    		 //루트와 관련
    		 sql="UPDATE FREEBOARD SET depth=depth+1 "
    				 +"WHERE no=?";//Procedure,Function,Package
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1,pno);
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
     
     //수정 데이터 가지고 오기
     public FreeBoardVO getUpdateData(int no)
     {
    	 FreeBoardVO vo=new FreeBoardVO();
    	 try
    	 {
    		 getConnection();
    		 String sql="SELECT name,NVL(email,' '),subject,content "
    				 +"FROM FREEBOARD "
    				 +"WHERE no=?";
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, no);
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 
    		 vo.setName(rs.getString(1));
    		 vo.setEmail(rs.getString(2));
    		 vo.setSubject(rs.getString(3));
    		 vo.setContent(rs.getString(4));
    		 
    		 rs.close();
    	 }catch(Exception ex)
    	 {
    		 System.out.println(ex.getMessage());
    	 }
    	 finally
    	 {
    		 disConnection();
    	 }
    	 return vo;
     }
     
     //실제 수정하기 
     public boolean update(FreeBoardVO vo)
     {
    	 boolean result=false;
    	 /*
    	  *   비밀번호 검색
    	  *     NO  : ==> 비밀번호 재입력(update.jsp)
    	  *     YES : ==> 수정 ==> list.jsp이동
    	  */
    	 
    	 try
    	 {
    		 getConnection();
    		 String sql="SELECT pwd FROM FREEBOARD "
    				 +"WHERE no=?";
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, vo.getNo());
    		 
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 
    		 String db_pwd=rs.getString(1);
    		 rs.close();
    		 ps.close();
    		 
    		 //비교
    		 if(db_pwd.equals(vo.getPwd()))
    		 {
    			 result=true;
    			 sql="UPDATE FREEBOARD SET "
    				+"name=?,email=?,subject=?,"
    			    +"content=?,regdate=SYSDATE "
    				+"WHERE no=?";
    			 ps=conn.prepareStatement(sql);
    			 ps.setString(1,vo.getName());
    			 ps.setString(2,vo.getEmail());
    			 ps.setString(3,vo.getSubject());
    			 ps.setString(4,vo.getContent());
    			 ps.setInt(5,vo.getNo());
    			 ps.executeUpdate();
    		 }
    		 else
    		 {
    			 result=false;
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
     
     //삭제하기 
     public boolean delete(int no,String pwd)
     {
    	 /*
    	  *   1. 비밀번호 확인
    	  *      비밀번호가 같은 경우
    	  *        1) depth (0)
    	  *            ==> 삭제 
    	  *                상위(root)의 depth 감소
    	  *           depth (X)
    	  *            ==> 관리자에 의해 삭제
    	  *      다른 경우 : bCheck=false 
    	  *                  no  ref step jump root depth
    	  *      AAAAA        1   1    0   0    0     1
    	  *        ->BBBBB    2   1    1   1    1     1
    	  *          ->CCCCC  3   1    2   2    2     0
    	  *          
    	  *      no bno
    	  *       1  2
    	  *           
    	  *      
    	  */
    	 boolean bCheck=false;
    	 try
    	 {
    		 getConnection();
    		 String sql="SELECT pwd,root,depth FROM FREEBOARD "
    				 +"WHERE no=?";
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1,no);
    		 
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 
    		 String db_pwd=rs.getString(1);
    		 int root=rs.getInt(2);
    		 int depth=rs.getInt(3);
    		 
    		 rs.close();
    		 ps.close();
    		 
    		 if(db_pwd.equals(pwd))
    		 {
    			 if(depth==0)
    			 {
    				sql="DELETE FROM FREEBOARD "
    					+"WHERE no=?";//no
    				ps=conn.prepareStatement(sql);
    				ps.setInt(1, no);
    				ps.executeUpdate();
    				ps.close();
    				
    				sql="UPDATE FREEBOARD SET "
    					+"depth=depth-1 "
    					+"WHERE no=?";//root
    				ps=conn.prepareStatement(sql);
    				ps.setInt(1, root);
    				ps.executeUpdate();
    			 }
    			 else
    			 {
    				String msg="관리자에 의해 삭제되었습니다";
    				sql="UPDATE FREEBOARD SET "
    					+"subject=?,content=? "
    				    +"WHERE no=?";
    				ps=conn.prepareStatement(sql);
    				ps.setString(1, msg);
    				ps.setString(2, msg);
    				ps.setInt(3, no);
    				
    				ps.executeUpdate();
    			 }
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

































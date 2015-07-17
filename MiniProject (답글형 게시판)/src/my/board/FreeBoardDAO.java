package my.board;
import java.util.*;
import java.sql.*;
/*
 *    JDBC : ����ø��� ����Ŭ ���� : Connection 
 *           = �����ϴ� ���� �������� ����Ŭ�� ���ϰ� ���ϴ�
 *           = �����ϴ� �ð��� �Һ�ȴ�
 *    Connection�� ����
 *           = ���� ������� ���� ��ٸ��� �ִ�
 *    DBCP ===> ���� �ǹ� �Ϲ������� ���(pool)
 *    
 *    �ڵ��� ���� 
 *      ���̺귯��(SQL Mapper)
 *      IBATIS
 *      HIBERNATE(��ü����) : Spring�� ȣȯ
 *      
 *    
 */
/*
 *     DAO 
 *      1) ����̹� ��� 
 *         Class.forName()
 *      2) ����
 *         Connection.getConnection()
 *      3) �������� ����
 *         PreparedStatement.prepareStatement()
 *      4) ���� ����
 *         executeUpdate()
 *         executeQuery()
 *      5) ����� ���
 *         ResultSet ==> next()
 *            NUMBER       getInt(),getDouble()
 *            VARCHAR2,CHAR     getString()
 *            CLOB         getString()
 *            DATE         getDate()
 *            BLOB,BFILE   InputStream() : ���̳ʸ� ����
 *            
 */
public class FreeBoardDAO {
	
     private Connection conn;//�����ͺ��̽� ����(Socket)
     private PreparedStatement ps;//�����(��Ʈ��) InputStream,OutputStream
     private final String URL="jdbc:oracle:thin:@localhost:1522:ORA11";
     private final String DRIVER="oracle.jdbc.driver.OracleDriver";
     private final String USERNAME="scott";
     private final String PWD="tiger";
     private static FreeBoardDAO dao;
     
     //����̹� ���
     public FreeBoardDAO()
     {
    	 try
    	 {
    		 Class.forName(DRIVER);
    		 //���÷��� (Ŭ������ �̸����� �޸� �Ҵ�)
    	 }catch(ClassNotFoundException ex)
    	 {
    		 System.out.println(ex.getMessage());
    	 }
     }
     //����Ŭ ����
     public void getConnection()
     {
    	 try
    	 {
    		 conn=DriverManager.getConnection(URL,USERNAME,PWD);
    	 }catch(Exception ex){}
     }
     //����Ŭ ���� ����
     public void disConnection()
     {
    	 try
    	 {
    		 if(ps!=null)ps.close();
    		 if(conn!=null)conn.close();
    	 }catch(Exception ex){}
     }
     //�̱��� : ��ü �޸𸮸� �ѹ��� ����(����)
     public static FreeBoardDAO newInstance()
     {
    	 if(dao==null)
    		 dao=new FreeBoardDAO();
    	 return dao;
     }
     //���
     //1. ��� ==> ���뺸��(�亯,����,����) ==> �߰� ==> ã��
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
    		 //����
    		 getConnection();
    		 //�������� ����
    		 String sql="SELECT no,subject,name,NVL(email,' '),regdate,readnum,jump "
    				 +"FROM FREEBOARD ORDER BY ref DESC,step ASC";
    		 ps=conn.prepareStatement(sql);//�������� ����
    		 ResultSet rs=ps.executeQuery();
    		 /*
    		  *   ������ ������ 
    		  */
    		 int i=0; // 10���� i<10
    		 int j=0; // while���� ���ư��� Ƚ�� j>=pagecnt
    		 int pagecnt=(curpage*10)-10;//�� ������ ������ġ
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
    		 //���� ����
    	 }
    	 return list;
     }
     //��������
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
     
     //���뺸��
     public FreeBoardVO getContentData(int no)
     {
    	 FreeBoardVO vo=new FreeBoardVO();
    	 try
    	 {
    		 //����
    		 getConnection();
    		 //��������
    		 //��ȸ�� ����
    		 String sql="UPDATE FREEBOARD SET "
    		           +"readnum=readnum+1 "
    		           +"WHERE no=?";
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, no);
    		 ps.executeUpdate();
    		 ps.close();
    		 //��û �����͸� ������ �´�
    		 sql="SELECT name,readnum,NVL(email,' '),"
    				 +"regdate,subject,content "
    				 +"FROM FREEBOARD "
    				 +"WHERE no=?";
    		 //����
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, no);
    		 //����
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 //��
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
    		 //����
    		 disConnection();
    	 }
    	 return vo;
     }
     
     //������ �߰�
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
    	  *    ref ==> ���� (���Ӱ� ����)
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
     
     //��ȣ ����
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
     
     //�亯�ϱ�
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
    	  *    ref ==> Ŭ����ȣ (pno==>ref)
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
    	  *        4. Ŭ����ȣ�� depth����
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
    		 //������ �ٲ��ִ� ����(�亯 ���������� �ٽ�)
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
    		 
    		 //��Ʈ�� ����
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
     
     //���� ������ ������ ����
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
     
     //���� �����ϱ� 
     public boolean update(FreeBoardVO vo)
     {
    	 boolean result=false;
    	 /*
    	  *   ��й�ȣ �˻�
    	  *     NO  : ==> ��й�ȣ ���Է�(update.jsp)
    	  *     YES : ==> ���� ==> list.jsp�̵�
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
    		 
    		 //��
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
     
     //�����ϱ� 
     public boolean delete(int no,String pwd)
     {
    	 /*
    	  *   1. ��й�ȣ Ȯ��
    	  *      ��й�ȣ�� ���� ���
    	  *        1) depth (0)
    	  *            ==> ���� 
    	  *                ����(root)�� depth ����
    	  *           depth (X)
    	  *            ==> �����ڿ� ���� ����
    	  *      �ٸ� ��� : bCheck=false 
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
    				String msg="�����ڿ� ���� �����Ǿ����ϴ�";
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

































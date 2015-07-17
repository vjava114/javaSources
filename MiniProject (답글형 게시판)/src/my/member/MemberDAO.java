package my.member;
/*
 *   ��� 
 *    1) �����ȣ === 9/18
 *    2) ���̵� �ߺ�üũ
 *    3) ȸ������
 *    4) ȸ������
 *    5) ȸ��Ż��
 *    6) �α���
 *    7) ����Ȯ��
 *    8) ����������
 *    9) ��������
 */
import java.sql.*;
import java.util.*;
public class MemberDAO {
    private Connection conn;//����Ŭ ���� (Socket)
    // ����Ŭ�� IP,PORT
    private PreparedStatement ps;//�������� (InputStream,OutputStream)
    // ����Ŭ���� ==> TCP
    private final String URL="jdbc:oracle:thin:@localhost:1522:ORA11";
    private final String DRIVER="oracle.jdbc.driver.OracleDriver";
    
    //����̹� ���
    public MemberDAO()
    {
    	try
    	{
    		Class.forName(DRIVER);
    		//����̹� ��� ==> DriverManagerŬ������ ���÷���
    		//���÷��� : Ŭ������ �̸������� �޸� �Ҵ�
    		//�̸����� �޸� �Ҵ� : ��Ű������� ���
    	}catch(ClassNotFoundException cf)
    	{
    		System.out.println(cf.getMessage());
    	}
    }
    //����Ŭ ����
    public void getConnection()
    {
    	try
    	{
    		conn=DriverManager.getConnection(URL,"scott","tiger");
    	}catch(Exception ex){}
    }
    //����Ŭ ����
    public void disConnection()
    {
    	try
    	{
    		if(ps!=null)ps.close();
    		if(conn!=null)conn.close();
    	}catch(Exception ex){}
    }
    
    //��� ó��
    //1.�����ȣ �˻�
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
    			
    			//list�� ÷��
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
    //���̵� �ߺ�ó��
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
    
    
    //ȸ������
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
    
    
    
    
    
    //�α��� 
    public String isLogin(String id,String pwd)
    {
    	/*
    	 *   ID�� �ִ���?
    	 *     ID(o)
    	 *       => ��й�ȣ Ȯ�� 
    	 *             pwd(o)  ==> ����
    	 *                          ==> ���ǻ������ ���� 
    	 *                          logout���� ����
    	 *             pwd(x)  ==> �޼��� ����
    	 *     ID(x) ==> �޼��� ����
    	 *     
    	 *     �� ����Ʈ 
    	 *       ȭ�� ���� (�� �帧)
    	 */
    	String result="";
    	try
    	{
    		getConnection();
    		String sql="SELECT COUNT(*) FROM member "
    		          +"WHERE id=?";//ID�� ���� ����
    		ps=conn.prepareStatement(sql);
    		ps.setString(1,id);
    		
    		//���� ������� ������ �´� 
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		
    		int count=rs.getInt(1);
    		rs.close();
    		ps.close();
    		//////////////////////////////ID���� ���� �˻�
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
    			//��й�ȣ �˻�
    			if(db_pwd.equals(pwd))//����
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














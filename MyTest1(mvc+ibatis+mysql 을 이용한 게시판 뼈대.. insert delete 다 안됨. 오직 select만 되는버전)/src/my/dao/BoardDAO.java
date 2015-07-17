package my.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.util.*;

import com.ibatis.common.resources.Resources;


public class BoardDAO {
	
	private static SqlMapClient sqlmap;
	
	static
	{
		try
		{
			Reader reader = Resources.getResourceAsReader("my/dao/SqlMapConfig.xml");
			System.out.println("sqlmapconfig.xml ���� �б� �Ϸ�");
					// connection ������ ����ִ� SqlMapConfig.xml �����´�
					// �ݺ��Ǵ� �������� ��� �ϴ� board-sqlmap ������ ����ִ�.
																					
					// -- �ڵ��� ���� ��ɾ� �ϳ������� ��� �ݺ����� ������� ���ɡ� --
			
			
			sqlmap = SqlMapClientBuilder.buildSqlMapClient(reader); 
			System.out.println("sqlmap ���� ���� �Ϸ�");
			// sqlmap���� ��ɾ� �ϳ� �����ָ�~     ��Ŀ�ؼ�->��������->��Ŀ�ؼǱ���!!
		}catch(Exception ex)
		{
			System.out.println("���̹�Ƽ�� ����" );
			System.out.println(ex.getMessage());
			
		}		
	}
	
	// select * from freeboard
	public static List getboardAllData()throws Exception
	{
		return sqlmap.queryForList("getboardAllData");
	}
	
//	// insert
//	public static void boardInsert(BoardVO vo) throws Exception {
//		sqlmap.insert("boardInsert", vo);
//	}
//	
//	// delete
//	public static void boardDelete(int no) throws Exception {
//		sqlmap.delete("boarddelete", no);
//	}
//	
	
}

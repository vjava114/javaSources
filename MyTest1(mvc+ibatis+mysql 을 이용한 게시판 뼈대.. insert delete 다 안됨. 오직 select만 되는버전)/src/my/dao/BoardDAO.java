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
			System.out.println("sqlmapconfig.xml 파일 읽기 완료");
					// connection 정보가 담겨있는 SqlMapConfig.xml 가져온다
					// 반복되는 쿼리문을 제어를 하는 board-sqlmap 정보도 들어있다.
																					
					// -- ★따라서 이제 명령어 하나만으로 길고도 반복적인 쿼리제어가 가능★ --
			
			
			sqlmap = SqlMapClientBuilder.buildSqlMapClient(reader); 
			System.out.println("sqlmap 빌더 생성 완료");
			// sqlmap에다 명령어 하나 떤져주면~     겟커넥션->쿼리실행->디스커넥션까지!!
		}catch(Exception ex)
		{
			System.out.println("아이바티스 에러" );
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

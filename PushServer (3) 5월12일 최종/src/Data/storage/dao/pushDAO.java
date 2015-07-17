package Data.storage.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Data.storage.sqlmap.SqlMap;

public class pushDAO extends SqlMap {

	/**
	 * SqlMapClient instances are thread safe, so you only need one. In this
	 * case, we'll use a static singleton. So sue me. ;-)
	 */

//	public static void insertAccount(HashMap<String, String> account)
//			throws SQLException {
//		sqlMapper.insert("test.insert", account);
//	}

	public static ArrayList<HashMap<String, String>> select()
			throws SQLException {
		return (ArrayList<HashMap<String, String>>) sqlMapper.queryForList("push.select");
	}
 
	public static ArrayList<HashMap<String, String>> select (HashMap<String, String> map)
			throws SQLException {
		return (ArrayList<HashMap<String, String>>) sqlMapper.queryForList("push.select",map);
	}
	
	
	//////////////////////////// 업데이트 치기 ////////////////////////
	
	// 푸시 성공시
	public void updatePushList(HashMap<String, String> map)throws Exception
	{
		sqlMapper.update("push.pushListUpdate", map);
	}
	
	// 푸시 실패시
	public void updatePushList_fail(HashMap<String, String> map)throws Exception
	{
		sqlMapper.update("push.updatePushList_fail", map);
	}
	
	// lms 전송후 업데이트 해주는거
	public void updatePushList_LMS(int seq)throws Exception
	{
		sqlMapper.update("push.updatePushList_LMS", seq);
	}
	
	// lms 하지 않았을때  해주는거
	public void no_updatePushList_LMS(int seq)throws Exception
	{
		sqlMapper.update("push.no_updatePushList_LMS", seq);
	}
	
	
	
	
	// 팀장님 쿼리.. for루프 돌기 전, statas를 'PROCESS로 변경'
	public void statusUpdate(int msg_seq)throws Exception
	{
		sqlMapper.update("push.statusUpdate", msg_seq);
	}
	
	public void statusUpdate2(int msg_seq)throws Exception
	{
		sqlMapper.update("push.statusUpdate2", msg_seq);
	}
}
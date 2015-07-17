/*
 * 
 */
package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwCmAdminInfoMapper;
import com.wallet.membership.model.MwCmAdminInfo;
import com.wallet.membership.model.MwCmAdminInfoExample;

public class MwCmAdminInfoDao extends MybatisCilent implements MwCmAdminInfoMapper {
	String MapperPath = "com.wallet.membership.mapper.MwCmAdminInfoMapper.";
	public void rollback(){
		sqlMapper.rollback();
	}
	
	public void commit() {
		sqlMapper.commit();
	}

	
	public int countByExample(MwCmAdminInfoExample example) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapper.selectOne(MapperPath + "countByExample",example);
	}
	
	public String selectByAdminPass(MwCmAdminInfo recode) {
		// TODO Auto-generated method stub
		return (String)sqlMapper.selectOne(MapperPath + "selectByAdminPass",recode);
	}

	
	public int deleteByExample(MwCmAdminInfoExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.delete(MapperPath + "deleteByExample",example);
	}

	
	public int insert(MwCmAdminInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(MwCmAdminInfo record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert(MapperPath + "insertSelective",record);
	}

	
	public List<MwCmAdminInfo> selectByExample(MwCmAdminInfoExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(MapperPath + "selectByExample",example);
	}

	
	public int updateByExampleSelective(MwCmAdminInfo record,
			MwCmAdminInfoExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmAdminInfoMapper.updateByExampleSelective", param);
	}

	
	public int updateByExample(MwCmAdminInfo record,
			MwCmAdminInfoExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKeySelective(MwCmAdminInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKey(MwCmAdminInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int deleteByPrimaryKey(String adminId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public MwCmAdminInfo selectByPrimaryKey(String adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<MwCmAdminInfo> selectByAdmin(MwCmAdminInfoExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(MapperPath + "selectByAdmin",example);
	}
	
	public int selectByAdminCount(MwCmAdminInfoExample example) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapper.selectOne(MapperPath + "selectByAdminCount",example);
	}
}

/*
 * 
 */
package com.wallet.membership.dao;

import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwCsCpnShareratioMapper;
import com.wallet.membership.model.MwCsCpnShareratio;
import com.wallet.membership.model.MwCsCpnShareratioExample;
import com.wallet.membership.model.MwCsCpnShareratioKey;

public class MwCsCpnShareratioDao extends MybatisCilent implements MwCsCpnShareratioMapper {
	String MapperPath = "com.wallet.membership.mapper.MwCsCpnShareratioMapper.";
	public void rollback(){
		sqlMapper.rollback();
	}
	
	public void commit() {
		sqlMapper.commit();
	}

	
	public int countByExample(MwCsCpnShareratioExample example) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapper.selectOne(MapperPath + "countByExample",example);
	}

	
	public int deleteByExample(MwCsCpnShareratioExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.delete(MapperPath + "deleteByExample",example);
	}

	
	public int deleteByPrimaryKey(MwCsCpnShareratioKey key) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insert(MwCsCpnShareratio record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(MwCsCpnShareratio record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert(MapperPath + "insertSelective",record);
	}

	
	public List<MwCsCpnShareratio> selectByExample(
			MwCsCpnShareratioExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(MapperPath + "selectByExample",example);
	}

	
	public MwCsCpnShareratio selectByPrimaryKey(MwCsCpnShareratioKey key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateByExampleSelective(MwCsCpnShareratio record,
			MwCsCpnShareratioExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByExample(MwCsCpnShareratio record,
			MwCsCpnShareratioExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKeySelective(MwCsCpnShareratio record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKey(MwCsCpnShareratio record) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwCsBulkCpnMapper;
import com.wallet.membership.model.MwCsBulkCpn;
import com.wallet.membership.model.MwCsBulkCpnExample;
import com.wallet.membership.model.MwCsBulkCpnKey;

public class MwCsBulkCpnDao  extends MybatisCilent implements MwCsBulkCpnMapper{
	private final String preMapperName = "com.wallet.membership.mapper.MwCsBulkCpnMapper.";
	public void commit(){
		sqlMapper.commit();
	}
	
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	public int countByExample(MwCsBulkCpnExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne(preMapperName + "countByExample", example);
	}

	
	public int deleteByExample(MwCsBulkCpnExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.delete(preMapperName + "deleteByExample", example);
	}

	
	public int deleteByPrimaryKey(MwCsBulkCpnKey key) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insert(MwCsBulkCpn record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert(preMapperName + "insert", record);
	}

	
	public int insertSelective(MwCsBulkCpn record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert(preMapperName + "insertSelective", record);
	}

	
	public List<MwCsBulkCpn> selectByExample(MwCsBulkCpnExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectByExample", example);
	}

	
	public MwCsBulkCpn selectByPrimaryKey(MwCsBulkCpnKey key) {
		// TODO Auto-generated method stub
		return (MwCsBulkCpn) sqlMapper.selectOne(preMapperName + "selectByExample", key);
	}

	
	public int updateByExampleSelective(MwCsBulkCpn record,
			MwCsBulkCpnExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update(preMapperName+"updateByExampleSelective", param);
	}

	
	public int updateByExample(MwCsBulkCpn record, MwCsBulkCpnExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update(preMapperName+"updateByExample", param);
	}

	
	public int updateByPrimaryKeySelective(MwCsBulkCpn record) {
		// TODO Auto-generated method stub
		return sqlMapper.update(preMapperName+"updateByPrimaryKeySelective", record);
	}

	
	public int updateByPrimaryKey(MwCsBulkCpn record) {
		// TODO Auto-generated method stub
		return sqlMapper.update(preMapperName+"updateByPrimaryKey", record);
	}

}

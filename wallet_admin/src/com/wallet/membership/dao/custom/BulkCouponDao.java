/**
 * @author 이경훈
 * 쿼리를 쉽게 사용할수 있도록 Custom한다.
 * */
package com.wallet.membership.dao.custom;

import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.BulkCouponMapper;
import com.wallet.membership.model.custom.BulkCoupon;

/**
 * 
 *
 */
public class BulkCouponDao extends MybatisCilent implements BulkCouponMapper {
		
	public void commit() {
		sqlMapper.commit();
	}
	
	public void rollback(){
		sqlMapper.rollback();
	}

	public int countByExample(BulkCoupon BulkCoupon) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.custom.BulkCouponMapper.countByExample", BulkCoupon);
	}

	public List<BulkCoupon> selectByExample(BulkCoupon BulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.custom.BulkCouponMapper.selectByExample", BulkCoupon);
	}

	public int updateByMwCsCpnlistSelective(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.custom.BulkCouponMapper.updateByMwCsCpnlistSelective", bulkCoupon);
	}

	public int updateByMwCsBulkCpnSelective(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.custom.BulkCouponMapper.updateByMwCsBulkCpnSelective", bulkCoupon);
	}

	public int updateByCpnlistSelective(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.custom.BulkCouponMapper.updateByCpnlistSelective", bulkCoupon);
	}

	public int deleteByMwCsCpnlistSelective(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.custom.BulkCouponMapper.deleteByMwCsCpnlistSelective", bulkCoupon);
	}

	public int deleteByMwCsBulkCpnSelective(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.custom.BulkCouponMapper.deleteByMwCsBulkCpnSelective", bulkCoupon);
	}

	public int deleteByCpnlistSelective(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.custom.BulkCouponMapper.deleteByCpnlistSelective", bulkCoupon);
	}

	public List<BulkCoupon> popupByExample(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.custom.BulkCouponMapper.popupByExample", bulkCoupon);
	}

	public int PopupcountByExample(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return  (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.custom.BulkCouponMapper.PopupcountByExample", bulkCoupon);
	}

	public List<BulkCoupon> selectByMemberExample(BulkCoupon bulkCoupon) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.custom.BulkCouponMapper.selectByMemberExample", bulkCoupon);
	}	
}

/**
 * @author 이경훈
 * 쿼리를 쉽게 사용할수 있도록 Custom한다.
 * */
package com.wallet.membership.dao.custom;

import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponShareMapper;
import com.wallet.membership.model.custom.CouponShare;

/**
 * 
 *
 */
public class CouponShareDao extends MybatisCilent implements CouponShareMapper {
		
	public void commit() {
		sqlMapper.commit();
	}
	
	public void rollback(){
		sqlMapper.rollback();
	}

	public int countByExample(CouponShare couponShare) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.custom.CouponShareMapper.countByExample", couponShare);
	}

	
	public List<CouponShare> selectByExample(CouponShare couponShare) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.custom.CouponShareMapper.selectByExample", couponShare);
	}

//	public int updateBycpnlistSelective(CouponShare couponShare) {
//		// TODO Auto-generated method stub
//		return sqlMapper.update("com.wallet.membership.mapper.custom.CouponShareMapper.updateBycpnlistSelective", couponShare);
//	}

//	public int updateBymembcardlistSelective(CouponShare couponShare) {
//		// TODO Auto-generated method stub
//		return sqlMapper.update("com.wallet.membership.mapper.custom.CouponShareMapper.updateBymembcardlistSelective", couponShare);
//	}

	public int updateByMwCsCpnlistSelective(CouponShare couponShare) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.custom.CouponShareMapper.updateByMwCsCpnlistSelective", couponShare);
	}

	public int insertSelective(CouponShare couponShare) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.custom.CouponShareMapper.insertSelective", couponShare);
	}


//	public int updateByMWCSCPNSHARERATIOSelective(CouponShare couponShare) {
//		// TODO Auto-generated method stub
//		return sqlMapper.update("com.wallet.membership.mapper.custom.CouponShareMapper.updateByMWCSCPNSHARERATIOSelective", couponShare);
//	}


}
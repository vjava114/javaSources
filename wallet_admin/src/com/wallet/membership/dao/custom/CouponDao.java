package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponMapper;
import com.wallet.membership.model.CpnListAll;
import com.wallet.membership.model.MwCsBulkCpn;
import com.wallet.membership.model.MwCsCpnregHist;
import com.wallet.membership.model.UserInfo;
import com.wallet.membership.model.custom.Coupon;
import com.wallet.membership.model.custom.CouponIssue;

public class CouponDao extends MybatisCilent implements CouponMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.CouponMapper.";

	
	/**
	 * 
	 */
	public void commit() {
		sqlMapper.commit();
	}


	public void rollback() {
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : CouponDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 최경진
	 * @since 2012.09.10
	 */
	public CouponDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectCouponList
	 * @Description : 쿠폰 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Coupon>
	 * @author 최경진
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public List<Coupon> selectCouponList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponList", params);
	}
	

	/**
	 * @Method Name : selectCouponListCnt
	 * @Description : 쿠폰 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 최경진
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer selectCouponListCnt(HashMap<String,Object> params){
		return (Integer) sqlMapper.selectOne(preMapperName + "selectCouponListCnt", params);
	}


	/**
	 * @Description : 쿠폰발행정보
	 * @param : HashMap<String ,Object>
	 * @return : List<Coupon>
	 * @author 최경진
	 * @since 2012.09.12
	 */
	@SuppressWarnings("unchecked")
	public List<CouponIssue> selectCouponIssueList(HashMap<String, Object> params) {
		return sqlMapper.selectList(preMapperName + "selectCouponIssueList", params);
	}


	/**
	 * @Description : 쿠폰발행정보 건수 조회 
	 * @param : HashMap<String ,Object>
	 * @return : List<Coupon>
	 * @author 최경진
	 * @since 2012.09.12
	 */
	@SuppressWarnings("unchecked")
	public Integer selectCouponIssueListCnt(HashMap<String,Object> params){
		return (Integer) sqlMapper.selectOne(preMapperName + "selectCouponIssueListCnt", params);
	}


	/**
	 * 쿠폰등록(현행- CPN_LIST)
	 */
	public Integer insertCouponList(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "insertCouponList", params));
	}

	/**
	 * 쿠폰등록(new - MW_CS_CPN_LIST)
	 */
	public Integer insertMwCsCouponList(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "insertMwCsCouponList", params));
	}


	public String selectLastCpnId() {
		return (String) sqlMapper.selectOne(preMapperName + "selectLastCpnId");
	}


	public CpnListAll selectCpnListAll(HashMap<String, Object> params) {
		return (CpnListAll) sqlMapper.selectOne(preMapperName + "selectCpnListAll", params);
	}


	public int updateCouponList(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateCouponList", params));
	}


	public int updateMwCsCouponList(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMwCsCouponList", params));
	}


	public int deleteCouponList(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "deleteCouponList", params));
	}


	public int deleteMwCsCouponList(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "deleteMwCsCouponList", params));
	}

	@SuppressWarnings("unchecked")
	public List<Coupon> selectCouponApprList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponApprList", params);
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectCouponApprCnt(HashMap<String,Object> params){
		return (Integer) sqlMapper.selectOne(preMapperName + "selectCouponApprCnt", params);
	}


	public int insertCouponRegHist(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "insertCouponRegHist", params));
	}


	public MwCsCpnregHist selectCpnregHist(HashMap<String, Object> params) {
		return (MwCsCpnregHist) sqlMapper.selectOne(preMapperName + "selectCpnregHist", params);
	}


	public List<CouponIssue> selectCouponHandIssuePopList(HashMap<String, Object> params) {
		return sqlMapper.selectList(preMapperName + "selectCouponHandIssuePopList", params);
	}


	public Integer selectCouponHandIssuePopListCnt(HashMap<String, Object> params) {
		return (Integer) sqlMapper.selectOne(preMapperName + "selectCouponHandIssuePopListCnt", params);
	}


	public int insertMyCpn(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "insertMyCpn", params));
	}


	public int insertCpnGenHis(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "insertCpnGenHis", params));
	}


	public UserInfo getUserInfo(HashMap<String, Object> params) {
		return (UserInfo) sqlMapper.selectOne(preMapperName + "getUserInfo", params);
	}


	public String getDeviceWidth(HashMap<String, Object> params) {
		return (String) sqlMapper.selectOne(preMapperName + "getDeviceWidth", params);
	}



	public String getMyCpn(HashMap<String, Object> params) {
		return (String) sqlMapper.selectOne(preMapperName + "getMyCpn", params);
	}


	public MwCsBulkCpn getMwCsBulkCpnLast(HashMap<String, Object> params) {
		return (MwCsBulkCpn) sqlMapper.selectOne(preMapperName + "getMwCsBulkCpnLast", params);
	}


	public int updateMwCsBulkCpnIssueYn(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMwCsBulkCpnIssueYn", params));
	}


	public int insertMwCsIssueCpn(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "insertMwCsIssueCpn", params));
	}
	
	/**
	 * @Method Name : cpnIdDupCheck
	 * @Description : 쿠폰 ID의 등록 전, 중복여부를 확인한다.
	 * @param : String
	 * @return : Integer 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.12.14
	 */
	public Integer cpnIdDupCheck(String manualCpnId){
		Integer result = 0;
		
		result = (Integer) sqlMapper.selectOne(preMapperName + "cpnIdDupCheck", manualCpnId);
		
		return result;
	}
	
}

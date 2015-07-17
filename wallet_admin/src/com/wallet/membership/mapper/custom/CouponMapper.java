package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.CpnListAll;
import com.wallet.membership.model.MwCsBulkCpn;
import com.wallet.membership.model.MwCsCpnregHist;
import com.wallet.membership.model.UserInfo;
import com.wallet.membership.model.custom.Coupon;
import com.wallet.membership.model.custom.CouponIssue;

public interface CouponMapper {
	List<Coupon> selectCouponList(HashMap<String,Object> params);
	Integer selectCouponListCnt(HashMap<String,Object> params);
	public List<CouponIssue> selectCouponIssueList(HashMap<String, Object> params);
	public Integer selectCouponIssueListCnt(HashMap<String,Object> params);
	Integer insertCouponList(HashMap<String,Object> params);
	Integer insertMwCsCouponList(HashMap<String,Object> params);
	String selectLastCpnId();
	CpnListAll selectCpnListAll(HashMap<String, Object> params);
	int updateCouponList(HashMap<String, Object> params);
	int updateMwCsCouponList(HashMap<String, Object> params);
	int deleteCouponList(HashMap<String, Object> params);
	int deleteMwCsCouponList(HashMap<String, Object> params);
	List<Coupon> selectCouponApprList(HashMap<String,Object> params);
	Integer selectCouponApprCnt(HashMap<String,Object> params);
	int insertCouponRegHist(HashMap<String, Object> params);
	MwCsCpnregHist selectCpnregHist(HashMap<String, Object> params);
	List<CouponIssue> selectCouponHandIssuePopList(HashMap<String, Object> params);
	Integer selectCouponHandIssuePopListCnt(HashMap<String, Object> params);
	int insertMyCpn(HashMap<String, Object> params);
	int insertCpnGenHis(HashMap<String, Object> params);
	UserInfo getUserInfo(HashMap<String, Object> params);
	String getDeviceWidth(HashMap<String, Object> params);
	String getMyCpn(HashMap<String, Object> params);
	MwCsBulkCpn getMwCsBulkCpnLast(HashMap<String, Object> params);
	int updateMwCsBulkCpnIssueYn(HashMap<String, Object> params);
	int insertMwCsIssueCpn(HashMap<String, Object> params);
	/**
	 * @Method Name : cpnIdDupCheck
	 * @Description : 쿠폰 ID의 등록 전, 중복여부를 확인한다.
	 * @param : String
	 * @return : Integer 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.12.14
	 */
	Integer cpnIdDupCheck(String manualCpnId);
}

package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.CouponDao;
import com.wallet.membership.model.CpnListAll;
import com.wallet.membership.model.MwCsBulkCpn;
import com.wallet.membership.model.MwCsCpnregHist;
import com.wallet.membership.model.UserInfo;
import com.wallet.membership.model.custom.Coupon;
import com.wallet.membership.model.custom.CouponIssue;

public class CouponService {
	private final CouponDao sDao;

	/**
	 * @Method Name : CouponService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author �ְ���
	 * @since 2012.09.11
	 */
	public CouponService() {
		sDao = new CouponDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author �ְ���
	 * @since 2012.09.11
	 */
	public void commit(){
		sDao.commit();
	}


	public void rollback() {
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectCouponList
	 * @Description : ���� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Coupon>
	 * @author �ְ���
	 * @since 2012.09.11
	 */
	public List<Coupon> selectCouponList(HashMap<String ,Object> params) {
		return sDao.selectCouponList(params);
	}
	
	/**
	 * @Method Name : selectCouponListCnt
	 * @Description : ���� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author �ְ���
	 * @since 2012.09.11
	 */
	public int selectCouponListCnt(HashMap<String, Object> params) {
		return Integer.parseInt(sDao.selectCouponListCnt(params).toString());
	}
	
	/**
	 * @Description : ������������ ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Coupon>
	 * @author �ְ���
	 * @since 2012.09.12
	 */
	public List<CouponIssue> selectCouponIssueList(HashMap<String ,Object> params) {
		return sDao.selectCouponIssueList(params);
	}
	
	/**
	 * @Description : ������������ �Ǽ� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author �ְ���
	 * @since 2012.09.12
	 */
	public int selectCouponIssueListCnt(HashMap<String, Object> params) {
		return Integer.parseInt(sDao.selectCouponIssueListCnt(params).toString());
	}

	
	public int insertCouponList(HashMap<String, Object> params){
		return sDao.insertCouponList(params);
	}
	
	
	public int insertMwCsCouponList(HashMap<String, Object> params){
		return sDao.insertMwCsCouponList(params);
	}

	public String selectLastCpnId() {
		return sDao.selectLastCpnId();
	}

	public CpnListAll selectCpnListAll(HashMap<String, Object> params) {
		return sDao.selectCpnListAll(params);
	}

	public int updateCouponList(HashMap<String, Object> params) {
		return sDao.updateCouponList(params);
	}

	public int updateMwCsCouponList(HashMap<String, Object> params) {
		return sDao.updateMwCsCouponList(params);
	}

	public int deleteCouponList(HashMap<String, Object> params) {
		return sDao.deleteCouponList(params);
	}

	public int deleteMwCsCouponList(HashMap<String, Object> params) {
		return sDao.deleteMwCsCouponList(params);
	}

	public List<Coupon> selectCouponApprList(HashMap<String,Object> params) {
		return sDao.selectCouponApprList(params);
	}
	
	public int selectCouponApprCnt(HashMap<String, Object> params) {
		return Integer.parseInt(sDao.selectCouponApprCnt(params).toString());
	}

	public int insertCouponRegHist(HashMap<String, Object> params) {
		return sDao.insertCouponRegHist(params);
	}

	public MwCsCpnregHist selectCpnregHist(HashMap<String, Object> params) {
		return sDao.selectCpnregHist(params);
	}

	public List<CouponIssue> selectCouponHandIssuePopList(HashMap<String, Object> params) {
		return sDao.selectCouponHandIssuePopList(params);
	}

	public int selectCouponHandIssuePopListCnt(HashMap<String, Object> params) {
		return Integer.parseInt(sDao.selectCouponHandIssuePopListCnt(params).toString());
	}

	public int insertMyCpn(HashMap<String, Object> params) {
		return sDao.insertMyCpn(params);
	}

	public int insertCpnGenHis(HashMap<String, Object> params) {
		return sDao.insertCpnGenHis(params);
	}

	public UserInfo getUserInfo(HashMap<String, Object> params) {
		return sDao.getUserInfo(params);
	}
	
	public String getDeviceWidth(HashMap<String, Object> params){
		return sDao.getDeviceWidth(params);
	}

	public String getMyCpn(HashMap<String, Object> params) {
		return sDao.getMyCpn(params);
	}

	public MwCsBulkCpn getMwCsBulkCpnLast(HashMap<String, Object> params) {
		return sDao.getMwCsBulkCpnLast(params);
	}

	public int updateMwCsBulkCpnIssueYn(HashMap<String, Object> params) {
		return sDao.updateMwCsBulkCpnIssueYn(params);		
	}

	public int insertMwCsIssueCpn(HashMap<String, Object> params) {
		return sDao.insertMwCsIssueCpn(params);
	}
	
	/**
	 * @Method Name : cpnIdDupCheck
	 * @Description : ���� ��� ��, ���� id�� �������� �ִ� ���, ���� id �ߺ����θ� üũ�Ѵ�.
	 * @param : String
	 * @return : int 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
	 * @author trkim
	 * @since 2012.12.14
	 */
	public int cpnIdDupCheck(String manualCpnId){
		int result = 0;
		
		result = Integer.parseInt(sDao.cpnIdDupCheck(manualCpnId).toString());
		
		return result;
	}
	
}

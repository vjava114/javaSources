package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class TransMst extends Common {
	private Integer seq;   					// ��ȣ
	private String acposTid;             	//���հ����ŷ���ȣ	  
	private String trFg;                 		//�ŷ�����	          
	private String cstatus;              //��һ���	          
	private String pstatus;              //ó������	          
	private String trStatus;             //�ŷ�����	          
	private String offerId;              //���۸�ID	          
	private String custId;               //����ȣ	          
	private String phoneNo;              //��ȭ��ȣ	          
	private String tcode;                //��Ż��ڵ�	        
	private String serviceId;            //������ID	          
	private String kShopId;              //KT���޻�ID	        
	private String mocapayTid;           //��ī���̰ŷ���ȣ	       
	private String mocapayTdate;         //��ī���̰ŷ�����	       
	private String mocapayTtime;         //��ī���̰ŷ��ð�	       
	private Integer ttlPrice;            //�Ѱ����ݾ�	        
	private Integer expTtlPrice;         //��������ݾ�	           
	private Integer realTtlPrice;        //���������ݾ�	           
	private Integer expCpnDcPrice;       //�������ο����ݾ�	      
	private Integer expMembDcPrice;      //��������ο���ݾ�	    
	private Integer expMembUsePoint;     //�������뿹��ݾ�	    
	private Integer expMembSavePoint;    //�������������ݾ�	    
	private Integer expStampSaveCnt;     //�������������󰹼�	    
	private Integer realCpnDcPrice;      //�����������αݾ�	      
	private Integer realMembDcPrice;     //������������αݾ�	    
	private Integer realMembUsePoint;    //������������ݾ�	    
	private Integer realMembSavePoint;   //��������������ݾ�	    
	private Integer realStampSaveCnt;    //������������������	    
	private String orgAcposTid;          //���հ������ŷ���ȣ	    
	private String orgAcposTdate;        //���հ������ŷ�����	    
	private String orgMocapayTid;        //��ī���̿��ŷ���ȣ	    
	private String orgMocapayTdate;      //��ī���̿��ŷ�����	    
	private String trDate;               //�ŷ�����	          
	private String mpBranch;             //���۸��� ACPOS_TID	
	private String mpSeq;                //��ī���� SEQ	      
	private String regDttm;              //����Ͻ�	          
	private String lastDttm;             //���������Ͻ�	      
	private String ncancelYn;            //
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getAcposTid() {
		return acposTid;
	}
	public void setAcposTid(String acposTid) {
		this.acposTid = acposTid;
	}
	public String getTrFg() {
		return trFg;
	}
	public void setTrFg(String trFg) {
		this.trFg = trFg;
	}
	public String getCstatus() {
		return cstatus;
	}
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getTrStatus() {
		return trStatus;
	}
	public void setTrStatus(String trStatus) {
		this.trStatus = trStatus;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getkShopId() {
		return kShopId;
	}
	public void setkShopId(String kShopId) {
		this.kShopId = kShopId;
	}
	public String getMocapayTid() {
		return mocapayTid;
	}
	public void setMocapayTid(String mocapayTid) {
		this.mocapayTid = mocapayTid;
	}
	public String getMocapayTdate() {
		return mocapayTdate;
	}
	public void setMocapayTdate(String mocapayTdate) {
		this.mocapayTdate = mocapayTdate;
	}
	public String getMocapayTtime() {
		return mocapayTtime;
	}
	public void setMocapayTtime(String mocapayTtime) {
		this.mocapayTtime = mocapayTtime;
	}
	public Integer getTtlPrice() {
		return ttlPrice;
	}
	public void setTtlPrice(Integer ttlPrice) {
		this.ttlPrice = ttlPrice;
	}
	public Integer getExpTtlPrice() {
		return expTtlPrice;
	}
	public void setExpTtlPrice(Integer expTtlPrice) {
		this.expTtlPrice = expTtlPrice;
	}
	public Integer getRealTtlPrice() {
		return realTtlPrice;
	}
	public void setRealTtlPrice(Integer realTtlPrice) {
		this.realTtlPrice = realTtlPrice;
	}
	public Integer getExpCpnDcPrice() {
		return expCpnDcPrice;
	}
	public void setExpCpnDcPrice(Integer expCpnDcPrice) {
		this.expCpnDcPrice = expCpnDcPrice;
	}
	public Integer getExpMembDcPrice() {
		return expMembDcPrice;
	}
	public void setExpMembDcPrice(Integer expMembDcPrice) {
		this.expMembDcPrice = expMembDcPrice;
	}
	public Integer getExpMembUsePoint() {
		return expMembUsePoint;
	}
	public void setExpMembUsePoint(Integer expMembUsePoint) {
		this.expMembUsePoint = expMembUsePoint;
	}
	public Integer getExpMembSavePoint() {
		return expMembSavePoint;
	}
	public void setExpMembSavePoint(Integer expMembSavePoint) {
		this.expMembSavePoint = expMembSavePoint;
	}
	public Integer getExpStampSaveCnt() {
		return expStampSaveCnt;
	}
	public void setExpStampSaveCnt(Integer expStampSaveCnt) {
		this.expStampSaveCnt = expStampSaveCnt;
	}
	public Integer getRealCpnDcPrice() {
		return realCpnDcPrice;
	}
	public void setRealCpnDcPrice(Integer realCpnDcPrice) {
		this.realCpnDcPrice = realCpnDcPrice;
	}
	public Integer getRealMembDcPrice() {
		return realMembDcPrice;
	}
	public void setRealMembDcPrice(Integer realMembDcPrice) {
		this.realMembDcPrice = realMembDcPrice;
	}
	public Integer getRealMembUsePoint() {
		return realMembUsePoint;
	}
	public void setRealMembUsePoint(Integer realMembUsePoint) {
		this.realMembUsePoint = realMembUsePoint;
	}
	public Integer getRealMembSavePoint() {
		return realMembSavePoint;
	}
	public void setRealMembSavePoint(Integer realMembSavePoint) {
		this.realMembSavePoint = realMembSavePoint;
	}
	public Integer getRealStampSaveCnt() {
		return realStampSaveCnt;
	}
	public void setRealStampSaveCnt(Integer realStampSaveCnt) {
		this.realStampSaveCnt = realStampSaveCnt;
	}
	public String getOrgAcposTid() {
		return orgAcposTid;
	}
	public void setOrgAcposTid(String orgAcposTid) {
		this.orgAcposTid = orgAcposTid;
	}
	public String getOrgAcposTdate() {
		return orgAcposTdate;
	}
	public void setOrgAcposTdate(String orgAcposTdate) {
		this.orgAcposTdate = orgAcposTdate;
	}
	public String getOrgMocapayTid() {
		return orgMocapayTid;
	}
	public void setOrgMocapayTid(String orgMocapayTid) {
		this.orgMocapayTid = orgMocapayTid;
	}
	public String getOrgMocapayTdate() {
		return orgMocapayTdate;
	}
	public void setOrgMocapayTdate(String orgMocapayTdate) {
		this.orgMocapayTdate = orgMocapayTdate;
	}
	public String getTrDate() {
		return trDate;
	}
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public String getMpBranch() {
		return mpBranch;
	}
	public void setMpBranch(String mpBranch) {
		this.mpBranch = mpBranch;
	}
	public String getMpSeq() {
		return mpSeq;
	}
	public void setMpSeq(String mpSeq) {
		this.mpSeq = mpSeq;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getLastDttm() {
		return lastDttm;
	}
	public void setLastDttm(String lastDttm) {
		this.lastDttm = lastDttm;
	}
	public String getNcancelYn() {
		return ncancelYn;
	}
	public void setNcancelYn(String ncancelYn) {
		this.ncancelYn = ncancelYn;
	}   
	
}

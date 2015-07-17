package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class SettleTrns extends Common {
	private Integer seq;   // ��ȣ
    private String acposTid;
    private String trFg;
    private String cstatus;
    private String pstatus;
    private String trStatus;
    private String offerId;
    private String custId;
    private String phoneNo;
    private String tcode;
    private String serviceId;
    private String kShopId;
    private String mocapayTid;
    private String mocapayTdate;
    private String mocapayTtime;
    private Integer ttlPrice;
    private Integer expTtlPrice;
    private Integer realTtlPrice;
    private Integer expCpnDcPrice;
    private Integer expMembDcPrice;
    private Integer expMembUsePoint;
    private Integer expMembSavePoint;
    private Integer expStampSaveCnt;
    private Integer realCpnDcPrice;
    private Integer realMembDcPrice;
    private Integer realMembUsePoint;
    private Integer realMembSavePoint;
    private Integer realStampSaveCnt;
    private String orgAcposTid;
    private String orgAcposTdate;
    private String orgMocapayTid;
    private String orgMocapayTdate;
    private String regDttm;
    private String lastDttm;
    
    private String tr_date; 			// ��ȸ����(�Ϻ���Ȳ����)
    private String tr_date_txt; 		// ��ȸ����(�Ϻ���Ȳ����)_����� �ؽ�Ʈ ����
    private Integer cnt; 				// ���հ��� �Ǽ� 
    private Integer coup_cnt; 		//�����Ǽ�
    private Integer mem_dc_cnt; 	//����� ���� �Ǽ�
    private Integer mem_pot_use_cnt; 	//����� ����Ʈ ��� �Ǽ�
    private Integer mem_pot_dep_cnt; 	//����� ���� �Ǽ�
    private Integer coup_amt; 		// ���� �ݾ�
    
    private String compId;		// ����ID
    private String brandId; 	// �귣��ID
    private String cpnId;  		// ����ID
    private String apprDate; 	// ���� ��������
    private String apprTime; 	// ���� ���νð�
    private String membId; 	// ����� ID
    private Integer realDcPrice; // �������αݾ�
    private Integer realUsePoint; // ����Ʈ���ݾ�
    private Integer realSavePoint; // ����Ʈ ���� �ݾ�
    private Integer realSaveCnt; // ������ ���� ���� 
    
    private String userName;  // ����
    private String regDay;	// �����ٿ�ε�����
    private String compName;
    private String comSort;
    private String brandCode;
    private String pointDupUsable;
    private String cpnDupUsableYn;
    private String cpnSort;
    private Integer minPayPrice;
    private Integer maxDicPrice;
    private Integer memDcRate;
    private String memExp;
    private String memUseUnit;
    private Integer memMinPayPrice;
    private Integer memMaxDcPrice;
    
    private String saveSort;
    private Integer saveRate;
    private String saveNotice;
    private String saveExp;
    private String saveAmtSort;
    private Integer saveMinPayPrice;
    private Integer saveMaxPayPrice;
    private Integer saveMinUsePoint;
    private String regDt; // ����� ��������
    
    private Integer apprCnt; // ���ΰǼ�
    private Integer cancelCnt; // ��ҰǼ�
    private Integer apprAmt; // ����Ǽ�
    private Integer cancelAmt; // ����ݾ�
    
    private String upCompName; // ���޻��
    private String brandName; // �귣���
    private String kShopName; // ��������
    
    private String cpnNo; // ���ڵ�������ȣ
    private String memApprDate; // ����� ���� ���� ����
    private String memApprTime; // ����� ���� ���� �ð�
    private String msCardNo; // ����� ī���ȣ
    private String rgType; // ����
    private String shopId; // ������ID
    private String shopName; // ��������
    private String payComopId; // �����̿� ������ID
    private String payCompName; // �����̿� �������

	private String dcRate; // �����������
	private String dcExp; // ��������αٻ����
	private String dcUnit; // ��������δ���
	private Integer dcMinAmt; // ������������������ݾ�
	private Integer dcMaxAmt; //������ִ����αݾ�
	private String saveRateFiexedYn; //��������������
	private Integer saveCashRate; // ����������
	private Integer saveCardRate; // ī��������
	private Integer saveCompRate; // �ڻ�������
	private Integer saveDebitRate; // ���������� 
	private Integer saveMaxPoint; //   ���������ִ�����Ʈ
	private String usePointType; // ����Ʈ��뱸��
	private String usePointUnit; // ����Ʈ������
	private String useExp; // ���ٻ����
	private Integer useFixedRate; // ��������Ʈ
	private Integer useMinPayPrice; // ����Ʈ��밡�����������ݾ�
	private Integer useMinPoint; // ��밡����������Ʈ
	private Integer useMaxPoint; // ��밡���ִ�����Ʈ 
	
	private Integer payTot; // �����ݾ���(���رݾ�)
	private Integer dcAmt;  // �������αݾ�
	private String apprNo; // ���ι�ȣ;
	private String payCompId; // ������Id
	
    
    public String getApprNo() {
		return apprNo;
	}
	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}
	public Integer getDcAmt() {
		return dcAmt;
	}
	public void setDcAmt(Integer dcAmt) {
		this.dcAmt = dcAmt;
	}
	public Integer getPayTot() {
		return payTot;
	}
	public void setPayTot(Integer payTot) {
		this.payTot = payTot;
	}
	public String getDcRate() {
		return dcRate;
	}
	public void setDcRate(String dcRate) {
		this.dcRate = dcRate;
	}
	public String getDcExp() {
		return dcExp;
	}
	public void setDcExp(String dcExp) {
		this.dcExp = dcExp;
	}
	public String getDcUnit() {
		return dcUnit;
	}
	public void setDcUnit(String dcUnit) {
		this.dcUnit = dcUnit;
	}
	public Integer getDcMinAmt() {
		return dcMinAmt;
	}
	public void setDcMinAmt(Integer dcMinAmt) {
		this.dcMinAmt = dcMinAmt;
	}
	public Integer getDcMaxAmt() {
		return dcMaxAmt;
	}
	public void setDcMaxAmt(Integer dcMaxAmt) {
		this.dcMaxAmt = dcMaxAmt;
	}
	public String getSaveRateFiexedYn() {
		return saveRateFiexedYn;
	}
	public void setSaveRateFiexedYn(String saveRateFiexedYn) {
		this.saveRateFiexedYn = saveRateFiexedYn;
	}
	public Integer getSaveCashRate() {
		return saveCashRate;
	}
	public void setSaveCashRate(Integer saveCashRate) {
		this.saveCashRate = saveCashRate;
	}
	public Integer getSaveCardRate() {
		return saveCardRate;
	}
	public void setSaveCardRate(Integer saveCardRate) {
		this.saveCardRate = saveCardRate;
	}
	public Integer getSaveCompRate() {
		return saveCompRate;
	}
	public void setSaveCompRate(Integer saveCompRate) {
		this.saveCompRate = saveCompRate;
	}
	public Integer getSaveDebitRate() {
		return saveDebitRate;
	}
	public void setSaveDebitRate(Integer saveDebitRate) {
		this.saveDebitRate = saveDebitRate;
	}
	public Integer getSaveMaxPoint() {
		return saveMaxPoint;
	}
	public void setSaveMaxPoint(Integer saveMaxPoint) {
		this.saveMaxPoint = saveMaxPoint;
	}
	public String getUsePointUnit() {
		return usePointUnit;
	}
	public void setUsePointUnit(String usePointUnit) {
		this.usePointUnit = usePointUnit;
	}
	public String getUseExp() {
		return useExp;
	}
	public void setUseExp(String useExp) {
		this.useExp = useExp;
	}
	public Integer getUseFixedRate() {
		return useFixedRate;
	}
	public void setUseFixedRate(Integer useFixedRate) {
		this.useFixedRate = useFixedRate;
	}
	public Integer getUseMinPayPrice() {
		return useMinPayPrice;
	}
	public void setUseMinPayPrice(Integer useMinPayPrice) {
		this.useMinPayPrice = useMinPayPrice;
	}
	public Integer getUseMinPoint() {
		return useMinPoint;
	}
	public void setUseMinPoint(Integer useMinPoint) {
		this.useMinPoint = useMinPoint;
	}
	public Integer getUseMaxPoint() {
		return useMaxPoint;
	}
	public void setUseMaxPoint(Integer useMaxPoint) {
		this.useMaxPoint = useMaxPoint;
	}
	public String getPayComopId() {
		return payComopId;
	}
	public void setPayComopId(String payComopId) {
		this.payComopId = payComopId;
	}
	public String getPayCompName() {
		return payCompName;
	}
	public void setPayCompName(String payCompName) {
		this.payCompName = payCompName;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCpnNo() {
		return cpnNo;
	}
	public void setCpnNo(String cpnNo) {
		this.cpnNo = cpnNo;
	}
	public String getMemApprDate() {
		return memApprDate;
	}
	public void setMemApprDate(String memApprDate) {
		this.memApprDate = memApprDate;
	}
	public String getMemApprTime() {
		return memApprTime;
	}
	public void setMemApprTime(String memApprTime) {
		this.memApprTime = memApprTime;
	}
	public String getMsCardNo() {
		return msCardNo;
	}
	public void setMsCardNo(String msCardNo) {
		this.msCardNo = msCardNo;
	}
	public String getRgType() {
		return rgType;
	}
	public void setRgType(String rgType) {
		this.rgType = rgType;
	}
    public String getUpCompName() {
		return upCompName;
	}
	public void setUpCompName(String upCompName) {
		this.upCompName = upCompName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getkShopName() {
		return kShopName;
	}
	public void setkShopName(String kShopName) {
		this.kShopName = kShopName;
	}
	public Integer getApprCnt() {
		return apprCnt;
	}
	public void setApprCnt(Integer apprCnt) {
		this.apprCnt = apprCnt;
	}
	public Integer getCancelCnt() {
		return cancelCnt;
	}
	public void setCancelCnt(Integer cancelCnt) {
		this.cancelCnt = cancelCnt;
	}

	public Integer getApprAmt() {
		return apprAmt;
	}
	public void setApprAmt(Integer apprAmt) {
		this.apprAmt = apprAmt;
	}
	public Integer getCancelAmt() {
		return cancelAmt;
	}
	public void setCancelAmt(Integer cancelAmt) {
		this.cancelAmt = cancelAmt;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRegDay() {
		return regDay;
	}
	public void setRegDay(String regDay) {
		this.regDay = regDay;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getComSort() {
		return comSort;
	}
	public void setComSort(String comSort) {
		this.comSort = comSort;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getPointDupUsable() {
		return pointDupUsable;
	}
	public void setPointDupUsable(String pointDupUsable) {
		this.pointDupUsable = pointDupUsable;
	}
	public String getCpnDupUsableYn() {
		return cpnDupUsableYn;
	}
	public void setCpnDupUsableYn(String cpnDupUsableYn) {
		this.cpnDupUsableYn = cpnDupUsableYn;
	}
	public String getCpnSort() {
		return cpnSort;
	}
	public void setCpnSort(String cpnSort) {
		this.cpnSort = cpnSort;
	}
	public Integer getMinPayPrice() {
		return minPayPrice;
	}
	public void setMinPayPrice(Integer minPayPrice) {
		this.minPayPrice = minPayPrice;
	}
	public Integer getMaxDicPrice() {
		return maxDicPrice;
	}
	public void setMaxDicPrice(Integer maxDicPrice) {
		this.maxDicPrice = maxDicPrice;
	}
	public Integer getMemDcRate() {
		return memDcRate;
	}
	public void setMemDcRate(Integer memDcRate) {
		this.memDcRate = memDcRate;
	}
	public String getMemExp() {
		return memExp;
	}
	public void setMemExp(String memExp) {
		this.memExp = memExp;
	}
	public String getMemUseUnit() {
		return memUseUnit;
	}
	public void setMemUseUnit(String memUseUnit) {
		this.memUseUnit = memUseUnit;
	}
	public Integer getMemMinPayPrice() {
		return memMinPayPrice;
	}
	public void setMemMinPayPrice(Integer memMinPayPrice) {
		this.memMinPayPrice = memMinPayPrice;
	}
	public Integer getMemMaxDcPrice() {
		return memMaxDcPrice;
	}
	public void setMemMaxDcPrice(Integer memMaxDcPrice) {
		this.memMaxDcPrice = memMaxDcPrice;
	}
	public String getSaveSort() {
		return saveSort;
	}
	public void setSaveSort(String saveSort) {
		this.saveSort = saveSort;
	}
	public Integer getSaveRate() {
		return saveRate;
	}
	public void setSaveRate(Integer saveRate) {
		this.saveRate = saveRate;
	}
	public String getSaveNotice() {
		return saveNotice;
	}
	public void setSaveNotice(String saveNotice) {
		this.saveNotice = saveNotice;
	}
	public String getSaveExp() {
		return saveExp;
	}
	public void setSaveExp(String saveExp) {
		this.saveExp = saveExp;
	}
	public String getSaveAmtSort() {
		return saveAmtSort;
	}
	public void setSaveAmtSort(String saveAmtSort) {
		this.saveAmtSort = saveAmtSort;
	}
	public Integer getSaveMinPayPrice() {
		return saveMinPayPrice;
	}
	public void setSaveMinPayPrice(Integer saveMinPayPrice) {
		this.saveMinPayPrice = saveMinPayPrice;
	}
	public Integer getSaveMaxPayPrice() {
		return saveMaxPayPrice;
	}
	public void setSaveMaxPayPrice(Integer saveMaxPayPrice) {
		this.saveMaxPayPrice = saveMaxPayPrice;
	}
	public Integer getSaveMinUsePoint() {
		return saveMinUsePoint;
	}
	public void setSaveMinUsePoint(Integer saveMinUsePoint) {
		this.saveMinUsePoint = saveMinUsePoint;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getRealDcPrice() {
		return realDcPrice;
	}
	public void setRealDcPrice(Integer realDcPrice) {
		this.realDcPrice = realDcPrice;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getCpnId() {
		return cpnId;
	}
	public void setCpnId(String cpnId) {
		this.cpnId = cpnId;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprTime() {
		return apprTime;
	}
	public void setApprTime(String apprTime) {
		this.apprTime = apprTime;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getAcposTid() {
        return acposTid;
    }
    public String getTr_date() {
		return tr_date;
	}
	public void setTr_date(String tr_date) {
		this.tr_date = tr_date;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public Integer getCoup_cnt() {
		return coup_cnt;
	}
	public void setCoup_cnt(Integer coup_cnt) {
		this.coup_cnt = coup_cnt;
	}
	public Integer getMem_dc_cnt() {
		return mem_dc_cnt;
	}
	public void setMem_dc_cnt(Integer mem_dc_cnt) {
		this.mem_dc_cnt = mem_dc_cnt;
	}
	public Integer getMem_pot_use_cnt() {
		return mem_pot_use_cnt;
	}
	public void setMem_pot_use_cnt(Integer mem_pot_use_cnt) {
		this.mem_pot_use_cnt = mem_pot_use_cnt;
	}
	public Integer getMem_pot_dep_cnt() {
		return mem_pot_dep_cnt;
	}
	public void setMem_pot_dep_cnt(Integer mem_pot_dep_cnt) {
		this.mem_pot_dep_cnt = mem_pot_dep_cnt;
	}
	public Integer getCoup_amt() {
		return coup_amt;
	}
	public void setCoup_amt(Integer coup_amt) {
		this.coup_amt = coup_amt;
	}
	public void setAcposTid(String acposTid) {
        this.acposTid = acposTid == null ? null : acposTid.trim();
    }
    public String getTrFg() {
        return trFg;
    }
    public void setTrFg(String trFg) {
        this.trFg = trFg == null ? null : trFg.trim();
    }
    public String getCstatus() {
        return cstatus;
    }
    public void setCstatus(String cstatus) {
        this.cstatus = cstatus == null ? null : cstatus.trim();
    }
    public String getPstatus() {
        return pstatus;
    }
    public void setPstatus(String pstatus) {
        this.pstatus = pstatus == null ? null : pstatus.trim();
    }
    public String getTrStatus() {
        return trStatus;
    }
    public void setTrStatus(String trStatus) {
        this.trStatus = trStatus == null ? null : trStatus.trim();
    }
    public String getOfferId() {
        return offerId;
    }
    public void setOfferId(String offerId) {
        this.offerId = offerId == null ? null : offerId.trim();
    }
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim().replace("-", "");
    }
    public String getTcode() {
        return tcode;
    }
    public Integer getRealUsePoint() {
		return realUsePoint;
	}
	public void setRealUsePoint(Integer realUsePoint) {
		this.realUsePoint = realUsePoint;
	}
	public Integer getRealSavePoint() {
		return realSavePoint;
	}
	public void setRealSavePoint(Integer realSavePoint) {
		this.realSavePoint = realSavePoint;
	}
	public Integer getRealSaveCnt() {
		return realSaveCnt;
	}
	public void setRealSaveCnt(Integer realSaveCnt) {
		this.realSaveCnt = realSaveCnt;
	}
	public void setTcode(String tcode) {
        this.tcode = tcode == null ? null : tcode.trim();
    }
    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }
    public String getkShopId() {
        return kShopId;
    }
    public void setkShopId(String kShopId) {
        this.kShopId = kShopId == null ? null : kShopId.trim();
    }
    public String getMocapayTid() {
        return mocapayTid;
    }
    public void setMocapayTid(String mocapayTid) {
        this.mocapayTid = mocapayTid == null ? null : mocapayTid.trim();
    }
    public String getMocapayTdate() {
        return mocapayTdate;
    }
    public void setMocapayTdate(String mocapayTdate) {
        this.mocapayTdate = mocapayTdate == null ? null : mocapayTdate.trim();
    }
    public String getMocapayTtime() {
        return mocapayTtime;
    }
    public void setMocapayTtime(String mocapayTtime) {
        this.mocapayTtime = mocapayTtime == null ? null : mocapayTtime.trim();
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
        this.orgAcposTid = orgAcposTid == null ? null : orgAcposTid.trim();
    }
    public String getOrgAcposTdate() {
        return orgAcposTdate;
    }
    public void setOrgAcposTdate(String orgAcposTdate) {
        this.orgAcposTdate = orgAcposTdate == null ? null : orgAcposTdate.trim();
    }
    public String getOrgMocapayTid() {
        return orgMocapayTid;
    }
    public void setOrgMocapayTid(String orgMocapayTid) {
        this.orgMocapayTid = orgMocapayTid == null ? null : orgMocapayTid.trim();
    }
    public String getOrgMocapayTdate() {
        return orgMocapayTdate;
    }
    public void setOrgMocapayTdate(String orgMocapayTdate) {
        this.orgMocapayTdate = orgMocapayTdate == null ? null : orgMocapayTdate.trim();
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
	public String getPayCompId() {
		return payCompId;
	}
	public void setPayCompId(String payCompId) {
		this.payCompId = payCompId;
	}
	public String getTr_date_txt() {
		return tr_date_txt;
	}
	public void setTr_date_txt(String tr_date_txt) {
		this.tr_date_txt = tr_date_txt;
	}
	public String getUsePointType() {
		return usePointType;
	}
	public void setUsePointType(String usePointType) {
		this.usePointType = usePointType;
	}
    
}
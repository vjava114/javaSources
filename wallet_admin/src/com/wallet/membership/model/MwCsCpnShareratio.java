package com.wallet.membership.model;

import java.util.Date;

import com.wallet.membership.service.CpnBankInfoService;
import com.wallet.membership.service.MembCardlistService;
import com.wallet.membership.service.MwCmCompanyService;
import com.wallet.membership.service.MwCmPayCompanyService;

public class MwCsCpnShareratio extends MwCsCpnShareratioKey {
  
    private String compType;
    private String compId;
    private Integer compShareRatio;
    private String useYn;
    private String regUser;
    private Date regDtm;
    private String compName;
    
    public String getCompName() {
			return compName;
		}

		public void setCompName() {
			 if(compType.equals("02")){//±ÝÀ¶»ç
				CpnBankInfoService cpnBankInfoService = new CpnBankInfoService();
				CpnBankInfoExample cpnBankInfoExample = new CpnBankInfoExample();
				cpnBankInfoExample.or().andBankIdEqualTo(compId);
				CpnBankInfo cpnBankInfo = cpnBankInfoService.getByExampleOnly(cpnBankInfoExample);
				if(cpnBankInfo != null){
					compName = cpnBankInfo.getName();
					System.out.println(compName);
				}
			}else if(compType.equals("03")){//°áÁ¦»ç 
				MwCmPayCompanyService mwCmPayCompanyService = new MwCmPayCompanyService();
				MwCmPayCompanyExample mwCmPayCompanyExample = new MwCmPayCompanyExample();
				mwCmPayCompanyExample.or().andPaycompIdEqualTo(compId);
				MwCmPayCompany mwCmPayCompany = mwCmPayCompanyService.getByExampleOnly(mwCmPayCompanyExample);
				if(mwCmPayCompany != null){
					compName = mwCmPayCompany.getPaycompName();
				}
			}else if(compType.equals("04")){//¸É¹ö½±
				MembCardlistService membCardlistService = new MembCardlistService();
				MembCardlistExample membCardlistExample = new MembCardlistExample();
				membCardlistExample.or().andMembIdEqualTo(compId);
				MembCardlist membCardlist = membCardlistService.getByExampleOnly(membCardlistExample);
				if(membCardlist != null){
					compName = membCardlist.getName();
				}
			}else{//°¡¸ÍÁ¡, Á¦ÈÞ»ç
				MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
				MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
				mwCmCompanyExample.or().andCompIdEqualTo(compId);
				MwCmCompany mwCmCompany = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
				if(mwCmCompany!=null){
					compName = mwCmCompany.getCompName();
				}
			}
			
			this.compName = compName;
		}

		public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType == null ? null : compType.trim();
    }
  
    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId == null ? null : compId.trim();
    }

    public Integer getCompShareRatio() {
        return compShareRatio;
    }

    public void setCompShareRatio(Integer compShareRatio) {
        this.compShareRatio = compShareRatio;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn == null ? null : useYn.trim();
    }

    public String getRegUser() {
        return regUser;
    }

    public void setRegUser(String regUser) {
        this.regUser = regUser == null ? null : regUser.trim();
    }

    public Date getRegDtm() {
        return regDtm;
    }

    public void setRegDtm(Date regDtm) {
        this.regDtm = regDtm;
    }
}
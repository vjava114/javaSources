package com.wallet.membership.model;

import com.rocomo.common.model.Common;

public class CpnList extends Common {
    
    private String cpnId;
    private String compName;
    private String membId;
    private String partV;
    private String dispType;
    private String upRecommPlus;
    private String upMainPlus;
    private String upRecommMu;
    private String upMainMu;
    private String membName;
    private String part;
    

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.name
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.use_name
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String useName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.use_code
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String useCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.menufac_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String menufacId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.mode_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String modeV;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.period_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String periodV;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.sort_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String sortV;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.cate_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String cateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.chg_day
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String chgDay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.val_sday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String valSday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.val_eday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String valEday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.sday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String sday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.eday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String eday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.reg_user
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String regUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.reg_day
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String regDay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.chg_user
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String chgUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.memo
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.info
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.usim_mode
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String usimMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.prim_mode
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String primMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.aff_type
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String affType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.main_display
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String mainDisplay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.disp_order
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String dispOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.PAY_FREE_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String payFreeYn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.RECOMM_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String recommYn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.RECOMM_SEQ
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private Integer recommSeq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.POPU_SEQ
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private Integer popuSeq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.chg_tm
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String chgTm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.COMP_PAY_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String compPayYn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cpn_list.USIM_ID
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    private String usimId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.cpn_id
     *
     * @return the value of cpn_list.cpn_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getCpnId() {
        return cpnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.cpn_id
     *
     * @param cpnId the value for cpn_list.cpn_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    
    
    public void setCpnId(String cpnId) {
        this.cpnId = cpnId == null ? null : cpnId.trim();
    }    

    public String getPart() {
			return part;
		}

		public void setPart(String part) {
			this.part = part;
		}

		public String getMembName() {
			return membName;
		}

		public void setMembName(String membName) {
			this.membName = membName;
		}

		public String getUpRecommPlus() {
			return upRecommPlus;
		}

		public void setUpRecommPlus(String upRecommPlus) {
			this.upRecommPlus = upRecommPlus;
		}

		public String getUpMainPlus() {
			return upMainPlus;
		}

		public void setUpMainPlus(String upMainPlus) {
			this.upMainPlus = upMainPlus;
		}

		public String getUpRecommMu() {
			return upRecommMu;
		}

		public void setUpRecommMu(String upRecommMu) {
			this.upRecommMu = upRecommMu;
		}

		public String getUpMainMu() {
			return upMainMu;
		}

		public void setUpMainMu(String upMainMu) {
			this.upMainMu = upMainMu;
		}

		public String getCompName() {
			return compName;
		}

		public void setCompName(String compName) {
			this.compName = compName;
		}

		/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.memb_id
     *
     * @return the value of cpn_list.memb_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getMembId() {
        return membId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.memb_id
     *
     * @param membId the value for cpn_list.memb_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setMembId(String membId) {
        this.membId = membId == null ? null : membId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.part_v
     *
     * @return the value of cpn_list.part_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getPartV() {
        return partV;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.part_v
     *
     * @param partV the value for cpn_list.part_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setPartV(String partV) {
        this.partV = partV == null ? null : partV.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.disp_type
     *
     * @return the value of cpn_list.disp_type
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getDispType() {
        return dispType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.disp_type
     *
     * @param dispType the value for cpn_list.disp_type
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setDispType(String dispType) {
        this.dispType = dispType == null ? null : dispType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.name
     *
     * @return the value of cpn_list.name
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.name
     *
     * @param name the value for cpn_list.name
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.use_name
     *
     * @return the value of cpn_list.use_name
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getUseName() {
        return useName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.use_name
     *
     * @param useName the value for cpn_list.use_name
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setUseName(String useName) {
        this.useName = useName == null ? null : useName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.use_code
     *
     * @return the value of cpn_list.use_code
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getUseCode() {
        return useCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.use_code
     *
     * @param useCode the value for cpn_list.use_code
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setUseCode(String useCode) {
        this.useCode = useCode == null ? null : useCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.menufac_id
     *
     * @return the value of cpn_list.menufac_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getMenufacId() {
        return menufacId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.menufac_id
     *
     * @param menufacId the value for cpn_list.menufac_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setMenufacId(String menufacId) {
        this.menufacId = menufacId == null ? null : menufacId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.mode_v
     *
     * @return the value of cpn_list.mode_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getModeV() {
        return modeV;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.mode_v
     *
     * @param modeV the value for cpn_list.mode_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setModeV(String modeV) {
        this.modeV = modeV == null ? null : modeV.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.period_v
     *
     * @return the value of cpn_list.period_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getPeriodV() {
        return periodV;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.period_v
     *
     * @param periodV the value for cpn_list.period_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setPeriodV(String periodV) {
        this.periodV = periodV == null ? null : periodV.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.sort_v
     *
     * @return the value of cpn_list.sort_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getSortV() {
        return sortV;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.sort_v
     *
     * @param sortV the value for cpn_list.sort_v
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setSortV(String sortV) {
        this.sortV = sortV == null ? null : sortV.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.cate_id
     *
     * @return the value of cpn_list.cate_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getCateId() {
        return cateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.cate_id
     *
     * @param cateId the value for cpn_list.cate_id
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setCateId(String cateId) {
        this.cateId = cateId == null ? null : cateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.chg_day
     *
     * @return the value of cpn_list.chg_day
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getChgDay() {
        return chgDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.chg_day
     *
     * @param chgDay the value for cpn_list.chg_day
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setChgDay(String chgDay) {
        this.chgDay = chgDay == null ? null : chgDay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.val_sday
     *
     * @return the value of cpn_list.val_sday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getValSday() {
        return valSday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.val_sday
     *
     * @param valSday the value for cpn_list.val_sday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setValSday(String valSday) {
        this.valSday = valSday == null ? null : valSday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.val_eday
     *
     * @return the value of cpn_list.val_eday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getValEday() {
        return valEday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.val_eday
     *
     * @param valEday the value for cpn_list.val_eday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setValEday(String valEday) {
        this.valEday = valEday == null ? null : valEday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.sday
     *
     * @return the value of cpn_list.sday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getSday() {
        return sday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.sday
     *
     * @param sday the value for cpn_list.sday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setSday(String sday) {
        this.sday = sday == null ? null : sday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.eday
     *
     * @return the value of cpn_list.eday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getEday() {
        return eday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.eday
     *
     * @param eday the value for cpn_list.eday
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setEday(String eday) {
        this.eday = eday == null ? null : eday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.reg_user
     *
     * @return the value of cpn_list.reg_user
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getRegUser() {
        return regUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.reg_user
     *
     * @param regUser the value for cpn_list.reg_user
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setRegUser(String regUser) {
        this.regUser = regUser == null ? null : regUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.reg_day
     *
     * @return the value of cpn_list.reg_day
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getRegDay() {
        return regDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.reg_day
     *
     * @param regDay the value for cpn_list.reg_day
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setRegDay(String regDay) {
        this.regDay = regDay == null ? null : regDay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.chg_user
     *
     * @return the value of cpn_list.chg_user
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getChgUser() {
        return chgUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.chg_user
     *
     * @param chgUser the value for cpn_list.chg_user
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setChgUser(String chgUser) {
        this.chgUser = chgUser == null ? null : chgUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.memo
     *
     * @return the value of cpn_list.memo
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.memo
     *
     * @param memo the value for cpn_list.memo
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.info
     *
     * @return the value of cpn_list.info
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.info
     *
     * @param info the value for cpn_list.info
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.usim_mode
     *
     * @return the value of cpn_list.usim_mode
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getUsimMode() {
        return usimMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.usim_mode
     *
     * @param usimMode the value for cpn_list.usim_mode
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setUsimMode(String usimMode) {
        this.usimMode = usimMode == null ? null : usimMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.prim_mode
     *
     * @return the value of cpn_list.prim_mode
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getPrimMode() {
        return primMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.prim_mode
     *
     * @param primMode the value for cpn_list.prim_mode
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setPrimMode(String primMode) {
        this.primMode = primMode == null ? null : primMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.aff_type
     *
     * @return the value of cpn_list.aff_type
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getAffType() {
        return affType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.aff_type
     *
     * @param affType the value for cpn_list.aff_type
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setAffType(String affType) {
        this.affType = affType == null ? null : affType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.main_display
     *
     * @return the value of cpn_list.main_display
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getMainDisplay() {
        return mainDisplay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.main_display
     *
     * @param mainDisplay the value for cpn_list.main_display
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setMainDisplay(String mainDisplay) {
        this.mainDisplay = mainDisplay == null ? null : mainDisplay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.disp_order
     *
     * @return the value of cpn_list.disp_order
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getDispOrder() {
        return dispOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.disp_order
     *
     * @param dispOrder the value for cpn_list.disp_order
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setDispOrder(String dispOrder) {
        this.dispOrder = dispOrder == null ? null : dispOrder.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.PAY_FREE_YN
     *
     * @return the value of cpn_list.PAY_FREE_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getPayFreeYn() {
        return payFreeYn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.PAY_FREE_YN
     *
     * @param payFreeYn the value for cpn_list.PAY_FREE_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setPayFreeYn(String payFreeYn) {
        this.payFreeYn = payFreeYn == null ? null : payFreeYn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.RECOMM_YN
     *
     * @return the value of cpn_list.RECOMM_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getRecommYn() {
        return recommYn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.RECOMM_YN
     *
     * @param recommYn the value for cpn_list.RECOMM_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setRecommYn(String recommYn) {
        this.recommYn = recommYn == null ? null : recommYn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.RECOMM_SEQ
     *
     * @return the value of cpn_list.RECOMM_SEQ
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public Integer getRecommSeq() {
        return recommSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.RECOMM_SEQ
     *
     * @param recommSeq the value for cpn_list.RECOMM_SEQ
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setRecommSeq(Integer recommSeq) {
        this.recommSeq = recommSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.POPU_SEQ
     *
     * @return the value of cpn_list.POPU_SEQ
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public Integer getPopuSeq() {
        return popuSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.POPU_SEQ
     *
     * @param popuSeq the value for cpn_list.POPU_SEQ
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setPopuSeq(Integer popuSeq) {
        this.popuSeq = popuSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.chg_tm
     *
     * @return the value of cpn_list.chg_tm
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getChgTm() {
        return chgTm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.chg_tm
     *
     * @param chgTm the value for cpn_list.chg_tm
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setChgTm(String chgTm) {
        this.chgTm = chgTm == null ? null : chgTm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.COMP_PAY_YN
     *
     * @return the value of cpn_list.COMP_PAY_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getCompPayYn() {
        return compPayYn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.COMP_PAY_YN
     *
     * @param compPayYn the value for cpn_list.COMP_PAY_YN
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setCompPayYn(String compPayYn) {
        this.compPayYn = compPayYn == null ? null : compPayYn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cpn_list.USIM_ID
     *
     * @return the value of cpn_list.USIM_ID
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public String getUsimId() {
        return usimId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cpn_list.USIM_ID
     *
     * @param usimId the value for cpn_list.USIM_ID
     *
     * @mbggenerated Thu Sep 20 17:55:39 KST 2012
     */
    public void setUsimId(String usimId) {
        this.usimId = usimId == null ? null : usimId.trim();
    }
}
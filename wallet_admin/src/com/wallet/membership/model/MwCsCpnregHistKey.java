package com.wallet.membership.model;

import com.rocomo.common.model.Common;

public class MwCsCpnregHistKey extends Common {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CS_CPNREG_HIST.CPN_ID
     *
     * @mbggenerated Wed Sep 19 17:03:49 KST 2012
     */
    private String cpnId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CS_CPNREG_HIST.SEQ_NO
     *
     * @mbggenerated Wed Sep 19 17:03:49 KST 2012
     */
    private Integer seqNo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CS_CPNREG_HIST.CPN_ID
     *
     * @return the value of MW_CS_CPNREG_HIST.CPN_ID
     *
     * @mbggenerated Wed Sep 19 17:03:49 KST 2012
     */
    public String getCpnId() {
        return cpnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CS_CPNREG_HIST.CPN_ID
     *
     * @param cpnId the value for MW_CS_CPNREG_HIST.CPN_ID
     *
     * @mbggenerated Wed Sep 19 17:03:49 KST 2012
     */
    public void setCpnId(String cpnId) {
        this.cpnId = cpnId == null ? null : cpnId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CS_CPNREG_HIST.SEQ_NO
     *
     * @return the value of MW_CS_CPNREG_HIST.SEQ_NO
     *
     * @mbggenerated Wed Sep 19 17:03:49 KST 2012
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CS_CPNREG_HIST.SEQ_NO
     *
     * @param seqNo the value for MW_CS_CPNREG_HIST.SEQ_NO
     *
     * @mbggenerated Wed Sep 19 17:03:49 KST 2012
     */
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
}
package com.wallet.membership.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MwCsBulkCpnExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    protected List<Criteria> oredCriteria;

    private Integer rowsPerPage;

    private Integer startIndex;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public MwCsBulkCpnExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setRowsPerPage(Integer rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public Integer getRowsPerPage() {
        return rowsPerPage;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCpnIdIsNull() {
            addCriterion("CPN_ID is null");
            return (Criteria) this;
        }

        public Criteria andCpnIdIsNotNull() {
            addCriterion("CPN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCpnIdEqualTo(String value) {
            addCriterion("CPN_ID =", value, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdNotEqualTo(String value) {
            addCriterion("CPN_ID <>", value, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdGreaterThan(String value) {
            addCriterion("CPN_ID >", value, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdGreaterThanOrEqualTo(String value) {
            addCriterion("CPN_ID >=", value, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdLessThan(String value) {
            addCriterion("CPN_ID <", value, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdLessThanOrEqualTo(String value) {
            addCriterion("CPN_ID <=", value, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdLike(String value) {
            addCriterion("CPN_ID like", value, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdNotLike(String value) {
            addCriterion("CPN_ID not like", value, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdIn(List<String> values) {
            addCriterion("CPN_ID in", values, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdNotIn(List<String> values) {
            addCriterion("CPN_ID not in", values, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdBetween(String value1, String value2) {
            addCriterion("CPN_ID between", value1, value2, "cpnId");
            return (Criteria) this;
        }

        public Criteria andCpnIdNotBetween(String value1, String value2) {
            addCriterion("CPN_ID not between", value1, value2, "cpnId");
            return (Criteria) this;
        }

        public Criteria andIssueSeqIsNull() {
            addCriterion("ISSUE_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andIssueSeqIsNotNull() {
            addCriterion("ISSUE_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andIssueSeqEqualTo(Integer value) {
            addCriterion("ISSUE_SEQ =", value, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqNotEqualTo(Integer value) {
            addCriterion("ISSUE_SEQ <>", value, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqGreaterThan(Integer value) {
            addCriterion("ISSUE_SEQ >", value, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("ISSUE_SEQ >=", value, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqLessThan(Integer value) {
            addCriterion("ISSUE_SEQ <", value, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqLessThanOrEqualTo(Integer value) {
            addCriterion("ISSUE_SEQ <=", value, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqIn(List<Integer> values) {
            addCriterion("ISSUE_SEQ in", values, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqNotIn(List<Integer> values) {
            addCriterion("ISSUE_SEQ not in", values, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqBetween(Integer value1, Integer value2) {
            addCriterion("ISSUE_SEQ between", value1, value2, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andIssueSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("ISSUE_SEQ not between", value1, value2, "issueSeq");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNull() {
            addCriterion("BARCODE is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("BARCODE is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("BARCODE =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("BARCODE <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("BARCODE >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("BARCODE >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("BARCODE <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("BARCODE <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("BARCODE like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("BARCODE not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("BARCODE in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("BARCODE not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("BARCODE between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("BARCODE not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andIssueYnIsNull() {
            addCriterion("ISSUE_YN is null");
            return (Criteria) this;
        }

        public Criteria andIssueYnIsNotNull() {
            addCriterion("ISSUE_YN is not null");
            return (Criteria) this;
        }

        public Criteria andIssueYnEqualTo(String value) {
            addCriterion("ISSUE_YN =", value, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnNotEqualTo(String value) {
            addCriterion("ISSUE_YN <>", value, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnGreaterThan(String value) {
            addCriterion("ISSUE_YN >", value, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnGreaterThanOrEqualTo(String value) {
            addCriterion("ISSUE_YN >=", value, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnLessThan(String value) {
            addCriterion("ISSUE_YN <", value, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnLessThanOrEqualTo(String value) {
            addCriterion("ISSUE_YN <=", value, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnLike(String value) {
            addCriterion("ISSUE_YN like", value, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnNotLike(String value) {
            addCriterion("ISSUE_YN not like", value, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnIn(List<String> values) {
            addCriterion("ISSUE_YN in", values, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnNotIn(List<String> values) {
            addCriterion("ISSUE_YN not in", values, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnBetween(String value1, String value2) {
            addCriterion("ISSUE_YN between", value1, value2, "issueYn");
            return (Criteria) this;
        }

        public Criteria andIssueYnNotBetween(String value1, String value2) {
            addCriterion("ISSUE_YN not between", value1, value2, "issueYn");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeIsNull() {
            addCriterion("COMP_BARCODE is null");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeIsNotNull() {
            addCriterion("COMP_BARCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeEqualTo(String value) {
            addCriterion("COMP_BARCODE =", value, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeNotEqualTo(String value) {
            addCriterion("COMP_BARCODE <>", value, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeGreaterThan(String value) {
            addCriterion("COMP_BARCODE >", value, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("COMP_BARCODE >=", value, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeLessThan(String value) {
            addCriterion("COMP_BARCODE <", value, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeLessThanOrEqualTo(String value) {
            addCriterion("COMP_BARCODE <=", value, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeLike(String value) {
            addCriterion("COMP_BARCODE like", value, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeNotLike(String value) {
            addCriterion("COMP_BARCODE not like", value, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeIn(List<String> values) {
            addCriterion("COMP_BARCODE in", values, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeNotIn(List<String> values) {
            addCriterion("COMP_BARCODE not in", values, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeBetween(String value1, String value2) {
            addCriterion("COMP_BARCODE between", value1, value2, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andCompBarcodeNotBetween(String value1, String value2) {
            addCriterion("COMP_BARCODE not between", value1, value2, "compBarcode");
            return (Criteria) this;
        }

        public Criteria andRegUserIsNull() {
            addCriterion("REG_USER is null");
            return (Criteria) this;
        }

        public Criteria andRegUserIsNotNull() {
            addCriterion("REG_USER is not null");
            return (Criteria) this;
        }

        public Criteria andRegUserEqualTo(String value) {
            addCriterion("REG_USER =", value, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserNotEqualTo(String value) {
            addCriterion("REG_USER <>", value, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserGreaterThan(String value) {
            addCriterion("REG_USER >", value, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserGreaterThanOrEqualTo(String value) {
            addCriterion("REG_USER >=", value, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserLessThan(String value) {
            addCriterion("REG_USER <", value, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserLessThanOrEqualTo(String value) {
            addCriterion("REG_USER <=", value, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserLike(String value) {
            addCriterion("REG_USER like", value, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserNotLike(String value) {
            addCriterion("REG_USER not like", value, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserIn(List<String> values) {
            addCriterion("REG_USER in", values, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserNotIn(List<String> values) {
            addCriterion("REG_USER not in", values, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserBetween(String value1, String value2) {
            addCriterion("REG_USER between", value1, value2, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegUserNotBetween(String value1, String value2) {
            addCriterion("REG_USER not between", value1, value2, "regUser");
            return (Criteria) this;
        }

        public Criteria andRegDtmIsNull() {
            addCriterion("REG_DTM is null");
            return (Criteria) this;
        }

        public Criteria andRegDtmIsNotNull() {
            addCriterion("REG_DTM is not null");
            return (Criteria) this;
        }

        public Criteria andRegDtmEqualTo(Date value) {
            addCriterion("REG_DTM =", value, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmNotEqualTo(Date value) {
            addCriterion("REG_DTM <>", value, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmGreaterThan(Date value) {
            addCriterion("REG_DTM >", value, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmGreaterThanOrEqualTo(Date value) {
            addCriterion("REG_DTM >=", value, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmLessThan(Date value) {
            addCriterion("REG_DTM <", value, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmLessThanOrEqualTo(Date value) {
            addCriterion("REG_DTM <=", value, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmIn(List<Date> values) {
            addCriterion("REG_DTM in", values, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmNotIn(List<Date> values) {
            addCriterion("REG_DTM not in", values, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmBetween(Date value1, Date value2) {
            addCriterion("REG_DTM between", value1, value2, "regDtm");
            return (Criteria) this;
        }

        public Criteria andRegDtmNotBetween(Date value1, Date value2) {
            addCriterion("REG_DTM not between", value1, value2, "regDtm");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated do_not_delete_during_merge Mon Sep 17 17:56:16 KST 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MW_CS_BULK_CPN
     *
     * @mbggenerated Mon Sep 17 17:56:16 KST 2012
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
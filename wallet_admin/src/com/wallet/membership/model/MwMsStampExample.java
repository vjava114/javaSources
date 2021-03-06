package com.wallet.membership.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MwMsStampExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    protected List<Criteria> oredCriteria;

    private Integer rowsPerPage;

    private Integer startIndex;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    public MwMsStampExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
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
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
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
     * This class corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
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

        public Criteria andMembIdIsNull() {
            addCriterion("MEMB_ID is null");
            return (Criteria) this;
        }

        public Criteria andMembIdIsNotNull() {
            addCriterion("MEMB_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMembIdEqualTo(String value) {
            addCriterion("MEMB_ID =", value, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdNotEqualTo(String value) {
            addCriterion("MEMB_ID <>", value, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdGreaterThan(String value) {
            addCriterion("MEMB_ID >", value, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEMB_ID >=", value, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdLessThan(String value) {
            addCriterion("MEMB_ID <", value, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdLessThanOrEqualTo(String value) {
            addCriterion("MEMB_ID <=", value, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdLike(String value) {
            addCriterion("MEMB_ID like", value, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdNotLike(String value) {
            addCriterion("MEMB_ID not like", value, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdIn(List<String> values) {
            addCriterion("MEMB_ID in", values, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdNotIn(List<String> values) {
            addCriterion("MEMB_ID not in", values, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdBetween(String value1, String value2) {
            addCriterion("MEMB_ID between", value1, value2, "membId");
            return (Criteria) this;
        }

        public Criteria andMembIdNotBetween(String value1, String value2) {
            addCriterion("MEMB_ID not between", value1, value2, "membId");
            return (Criteria) this;
        }

        public Criteria andStampSeqIsNull() {
            addCriterion("STAMP_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andStampSeqIsNotNull() {
            addCriterion("STAMP_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andStampSeqEqualTo(Integer value) {
            addCriterion("STAMP_SEQ =", value, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqNotEqualTo(Integer value) {
            addCriterion("STAMP_SEQ <>", value, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqGreaterThan(Integer value) {
            addCriterion("STAMP_SEQ >", value, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("STAMP_SEQ >=", value, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqLessThan(Integer value) {
            addCriterion("STAMP_SEQ <", value, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqLessThanOrEqualTo(Integer value) {
            addCriterion("STAMP_SEQ <=", value, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqIn(List<Integer> values) {
            addCriterion("STAMP_SEQ in", values, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqNotIn(List<Integer> values) {
            addCriterion("STAMP_SEQ not in", values, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqBetween(Integer value1, Integer value2) {
            addCriterion("STAMP_SEQ between", value1, value2, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("STAMP_SEQ not between", value1, value2, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeIsNull() {
            addCriterion("STAMP_SVC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeIsNotNull() {
            addCriterion("STAMP_SVC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeEqualTo(String value) {
            addCriterion("STAMP_SVC_TYPE =", value, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeNotEqualTo(String value) {
            addCriterion("STAMP_SVC_TYPE <>", value, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeGreaterThan(String value) {
            addCriterion("STAMP_SVC_TYPE >", value, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeGreaterThanOrEqualTo(String value) {
            addCriterion("STAMP_SVC_TYPE >=", value, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeLessThan(String value) {
            addCriterion("STAMP_SVC_TYPE <", value, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeLessThanOrEqualTo(String value) {
            addCriterion("STAMP_SVC_TYPE <=", value, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeLike(String value) {
            addCriterion("STAMP_SVC_TYPE like", value, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeNotLike(String value) {
            addCriterion("STAMP_SVC_TYPE not like", value, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeIn(List<String> values) {
            addCriterion("STAMP_SVC_TYPE in", values, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeNotIn(List<String> values) {
            addCriterion("STAMP_SVC_TYPE not in", values, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeBetween(String value1, String value2) {
            addCriterion("STAMP_SVC_TYPE between", value1, value2, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampSvcTypeNotBetween(String value1, String value2) {
            addCriterion("STAMP_SVC_TYPE not between", value1, value2, "stampSvcType");
            return (Criteria) this;
        }

        public Criteria andStampUnitIsNull() {
            addCriterion("STAMP_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andStampUnitIsNotNull() {
            addCriterion("STAMP_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andStampUnitEqualTo(Integer value) {
            addCriterion("STAMP_UNIT =", value, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitNotEqualTo(Integer value) {
            addCriterion("STAMP_UNIT <>", value, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitGreaterThan(Integer value) {
            addCriterion("STAMP_UNIT >", value, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("STAMP_UNIT >=", value, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitLessThan(Integer value) {
            addCriterion("STAMP_UNIT <", value, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitLessThanOrEqualTo(Integer value) {
            addCriterion("STAMP_UNIT <=", value, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitIn(List<Integer> values) {
            addCriterion("STAMP_UNIT in", values, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitNotIn(List<Integer> values) {
            addCriterion("STAMP_UNIT not in", values, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitBetween(Integer value1, Integer value2) {
            addCriterion("STAMP_UNIT between", value1, value2, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("STAMP_UNIT not between", value1, value2, "stampUnit");
            return (Criteria) this;
        }

        public Criteria andStampImageIsNull() {
            addCriterion("STAMP_IMAGE is null");
            return (Criteria) this;
        }

        public Criteria andStampImageIsNotNull() {
            addCriterion("STAMP_IMAGE is not null");
            return (Criteria) this;
        }

        public Criteria andStampImageEqualTo(String value) {
            addCriterion("STAMP_IMAGE =", value, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageNotEqualTo(String value) {
            addCriterion("STAMP_IMAGE <>", value, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageGreaterThan(String value) {
            addCriterion("STAMP_IMAGE >", value, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageGreaterThanOrEqualTo(String value) {
            addCriterion("STAMP_IMAGE >=", value, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageLessThan(String value) {
            addCriterion("STAMP_IMAGE <", value, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageLessThanOrEqualTo(String value) {
            addCriterion("STAMP_IMAGE <=", value, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageLike(String value) {
            addCriterion("STAMP_IMAGE like", value, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageNotLike(String value) {
            addCriterion("STAMP_IMAGE not like", value, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageIn(List<String> values) {
            addCriterion("STAMP_IMAGE in", values, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageNotIn(List<String> values) {
            addCriterion("STAMP_IMAGE not in", values, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageBetween(String value1, String value2) {
            addCriterion("STAMP_IMAGE between", value1, value2, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampImageNotBetween(String value1, String value2) {
            addCriterion("STAMP_IMAGE not between", value1, value2, "stampImage");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoIsNull() {
            addCriterion("STAMP_MAX_NO is null");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoIsNotNull() {
            addCriterion("STAMP_MAX_NO is not null");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoEqualTo(Integer value) {
            addCriterion("STAMP_MAX_NO =", value, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoNotEqualTo(Integer value) {
            addCriterion("STAMP_MAX_NO <>", value, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoGreaterThan(Integer value) {
            addCriterion("STAMP_MAX_NO >", value, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("STAMP_MAX_NO >=", value, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoLessThan(Integer value) {
            addCriterion("STAMP_MAX_NO <", value, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoLessThanOrEqualTo(Integer value) {
            addCriterion("STAMP_MAX_NO <=", value, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoIn(List<Integer> values) {
            addCriterion("STAMP_MAX_NO in", values, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoNotIn(List<Integer> values) {
            addCriterion("STAMP_MAX_NO not in", values, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoBetween(Integer value1, Integer value2) {
            addCriterion("STAMP_MAX_NO between", value1, value2, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andStampMaxNoNotBetween(Integer value1, Integer value2) {
            addCriterion("STAMP_MAX_NO not between", value1, value2, "stampMaxNo");
            return (Criteria) this;
        }

        public Criteria andExpireSdayIsNull() {
            addCriterion("EXPIRE_SDAY is null");
            return (Criteria) this;
        }

        public Criteria andExpireSdayIsNotNull() {
            addCriterion("EXPIRE_SDAY is not null");
            return (Criteria) this;
        }

        public Criteria andExpireSdayEqualTo(String value) {
            addCriterion("EXPIRE_SDAY =", value, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayNotEqualTo(String value) {
            addCriterion("EXPIRE_SDAY <>", value, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayGreaterThan(String value) {
            addCriterion("EXPIRE_SDAY >", value, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayGreaterThanOrEqualTo(String value) {
            addCriterion("EXPIRE_SDAY >=", value, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayLessThan(String value) {
            addCriterion("EXPIRE_SDAY <", value, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayLessThanOrEqualTo(String value) {
            addCriterion("EXPIRE_SDAY <=", value, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayLike(String value) {
            addCriterion("EXPIRE_SDAY like", value, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayNotLike(String value) {
            addCriterion("EXPIRE_SDAY not like", value, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayIn(List<String> values) {
            addCriterion("EXPIRE_SDAY in", values, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayNotIn(List<String> values) {
            addCriterion("EXPIRE_SDAY not in", values, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayBetween(String value1, String value2) {
            addCriterion("EXPIRE_SDAY between", value1, value2, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireSdayNotBetween(String value1, String value2) {
            addCriterion("EXPIRE_SDAY not between", value1, value2, "expireSday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayIsNull() {
            addCriterion("EXPIRE_EDAY is null");
            return (Criteria) this;
        }

        public Criteria andExpireEdayIsNotNull() {
            addCriterion("EXPIRE_EDAY is not null");
            return (Criteria) this;
        }

        public Criteria andExpireEdayEqualTo(String value) {
            addCriterion("EXPIRE_EDAY =", value, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayNotEqualTo(String value) {
            addCriterion("EXPIRE_EDAY <>", value, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayGreaterThan(String value) {
            addCriterion("EXPIRE_EDAY >", value, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayGreaterThanOrEqualTo(String value) {
            addCriterion("EXPIRE_EDAY >=", value, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayLessThan(String value) {
            addCriterion("EXPIRE_EDAY <", value, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayLessThanOrEqualTo(String value) {
            addCriterion("EXPIRE_EDAY <=", value, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayLike(String value) {
            addCriterion("EXPIRE_EDAY like", value, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayNotLike(String value) {
            addCriterion("EXPIRE_EDAY not like", value, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayIn(List<String> values) {
            addCriterion("EXPIRE_EDAY in", values, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayNotIn(List<String> values) {
            addCriterion("EXPIRE_EDAY not in", values, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayBetween(String value1, String value2) {
            addCriterion("EXPIRE_EDAY between", value1, value2, "expireEday");
            return (Criteria) this;
        }

        public Criteria andExpireEdayNotBetween(String value1, String value2) {
            addCriterion("EXPIRE_EDAY not between", value1, value2, "expireEday");
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

        public Criteria andChgUserIsNull() {
            addCriterion("CHG_USER is null");
            return (Criteria) this;
        }

        public Criteria andChgUserIsNotNull() {
            addCriterion("CHG_USER is not null");
            return (Criteria) this;
        }

        public Criteria andChgUserEqualTo(String value) {
            addCriterion("CHG_USER =", value, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserNotEqualTo(String value) {
            addCriterion("CHG_USER <>", value, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserGreaterThan(String value) {
            addCriterion("CHG_USER >", value, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserGreaterThanOrEqualTo(String value) {
            addCriterion("CHG_USER >=", value, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserLessThan(String value) {
            addCriterion("CHG_USER <", value, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserLessThanOrEqualTo(String value) {
            addCriterion("CHG_USER <=", value, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserLike(String value) {
            addCriterion("CHG_USER like", value, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserNotLike(String value) {
            addCriterion("CHG_USER not like", value, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserIn(List<String> values) {
            addCriterion("CHG_USER in", values, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserNotIn(List<String> values) {
            addCriterion("CHG_USER not in", values, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserBetween(String value1, String value2) {
            addCriterion("CHG_USER between", value1, value2, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgUserNotBetween(String value1, String value2) {
            addCriterion("CHG_USER not between", value1, value2, "chgUser");
            return (Criteria) this;
        }

        public Criteria andChgDtmIsNull() {
            addCriterion("CHG_DTM is null");
            return (Criteria) this;
        }

        public Criteria andChgDtmIsNotNull() {
            addCriterion("CHG_DTM is not null");
            return (Criteria) this;
        }

        public Criteria andChgDtmEqualTo(Date value) {
            addCriterion("CHG_DTM =", value, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmNotEqualTo(Date value) {
            addCriterion("CHG_DTM <>", value, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmGreaterThan(Date value) {
            addCriterion("CHG_DTM >", value, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmGreaterThanOrEqualTo(Date value) {
            addCriterion("CHG_DTM >=", value, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmLessThan(Date value) {
            addCriterion("CHG_DTM <", value, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmLessThanOrEqualTo(Date value) {
            addCriterion("CHG_DTM <=", value, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmIn(List<Date> values) {
            addCriterion("CHG_DTM in", values, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmNotIn(List<Date> values) {
            addCriterion("CHG_DTM not in", values, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmBetween(Date value1, Date value2) {
            addCriterion("CHG_DTM between", value1, value2, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andChgDtmNotBetween(Date value1, Date value2) {
            addCriterion("CHG_DTM not between", value1, value2, "chgDtm");
            return (Criteria) this;
        }
        
        public Criteria andAChgDtmBetween(Date value1, Date value2) {
            addCriterion("A.CHG_DTM between", value1, value2, "chgDtm");
            return (Criteria) this;
        }
        
        public Criteria andARegDtmBetween(Date value1, Date value2) {
            addCriterion("A.REG_DTM between", value1, value2, "chgDtm");
            return (Criteria) this;
        }

        public Criteria andBMembNameLike(String value) {
            addCriterion("B.name like", value, "membName");
            return (Criteria) this;
        }

        public Criteria andCCompNameLike(String value) {
            addCriterion("C.COMP_NAME like", value, "compName");
            return (Criteria) this;
        }

        public Criteria andAMembIdEqualTo(String value) {
            addCriterion("A.MEMB_ID =", value, "membId");
            return (Criteria) this;
        }
        
        public Criteria andCCompIdEqualTo(String value) {
            addCriterion("C.COMP_ID =", value, "compId");
            return (Criteria) this;
        }

        public Criteria andAStampSeqEqualTo(Integer value) {
            addCriterion("A.STAMP_SEQ =", value, "stampSeq");
            return (Criteria) this;
        }

        public Criteria andAChgDtmIsNotNull() {
            addCriterion("A.CHG_DTM is not null");
            return (Criteria) this;
        }


        public Criteria andARegDtmIsNotNull() {
            addCriterion("A.REG_DTM is not null");
            return (Criteria) this;
        }

        public Criteria andCCompIdLike(String value) {
            addCriterion("C.COMP_ID like", value, "compId");
            return (Criteria) this;
        }


      
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated do_not_delete_during_merge Tue Sep 18 15:05:33 KST 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
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
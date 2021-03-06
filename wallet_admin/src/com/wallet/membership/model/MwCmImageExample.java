package com.wallet.membership.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MwCmImageExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    protected List<Criteria> oredCriteria;

    private Integer rowsPerPage;

    private Integer startIndex;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    public MwCmImageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
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
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
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
     * This class corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOsTypeIsNull() {
            addCriterion("OS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOsTypeIsNotNull() {
            addCriterion("OS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOsTypeEqualTo(String value) {
            addCriterion("OS_TYPE =", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeNotEqualTo(String value) {
            addCriterion("OS_TYPE <>", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeGreaterThan(String value) {
            addCriterion("OS_TYPE >", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OS_TYPE >=", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeLessThan(String value) {
            addCriterion("OS_TYPE <", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeLessThanOrEqualTo(String value) {
            addCriterion("OS_TYPE <=", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeLike(String value) {
            addCriterion("OS_TYPE like", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeNotLike(String value) {
            addCriterion("OS_TYPE not like", value, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeIn(List<String> values) {
            addCriterion("OS_TYPE in", values, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeNotIn(List<String> values) {
            addCriterion("OS_TYPE not in", values, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeBetween(String value1, String value2) {
            addCriterion("OS_TYPE between", value1, value2, "osType");
            return (Criteria) this;
        }

        public Criteria andOsTypeNotBetween(String value1, String value2) {
            addCriterion("OS_TYPE not between", value1, value2, "osType");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNull() {
            addCriterion("USE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNotNull() {
            addCriterion("USE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andUseTypeEqualTo(String value) {
            addCriterion("USE_TYPE =", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotEqualTo(String value) {
            addCriterion("USE_TYPE <>", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThan(String value) {
            addCriterion("USE_TYPE >", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("USE_TYPE >=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThan(String value) {
            addCriterion("USE_TYPE <", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThanOrEqualTo(String value) {
            addCriterion("USE_TYPE <=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLike(String value) {
            addCriterion("USE_TYPE like", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotLike(String value) {
            addCriterion("USE_TYPE not like", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeIn(List<String> values) {
            addCriterion("USE_TYPE in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotIn(List<String> values) {
            addCriterion("USE_TYPE not in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeBetween(String value1, String value2) {
            addCriterion("USE_TYPE between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotBetween(String value1, String value2) {
            addCriterion("USE_TYPE not between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("LEVEL =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("LEVEL <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("LEVEL >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("LEVEL >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("LEVEL <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("LEVEL <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("LEVEL like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("LEVEL not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("LEVEL in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("LEVEL not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("LEVEL between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("LEVEL not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andImageHostIsNull() {
            addCriterion("IMAGE_HOST is null");
            return (Criteria) this;
        }

        public Criteria andImageHostIsNotNull() {
            addCriterion("IMAGE_HOST is not null");
            return (Criteria) this;
        }

        public Criteria andImageHostEqualTo(String value) {
            addCriterion("IMAGE_HOST =", value, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostNotEqualTo(String value) {
            addCriterion("IMAGE_HOST <>", value, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostGreaterThan(String value) {
            addCriterion("IMAGE_HOST >", value, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_HOST >=", value, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostLessThan(String value) {
            addCriterion("IMAGE_HOST <", value, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_HOST <=", value, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostLike(String value) {
            addCriterion("IMAGE_HOST like", value, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostNotLike(String value) {
            addCriterion("IMAGE_HOST not like", value, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostIn(List<String> values) {
            addCriterion("IMAGE_HOST in", values, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostNotIn(List<String> values) {
            addCriterion("IMAGE_HOST not in", values, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostBetween(String value1, String value2) {
            addCriterion("IMAGE_HOST between", value1, value2, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageHostNotBetween(String value1, String value2) {
            addCriterion("IMAGE_HOST not between", value1, value2, "imageHost");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("IMAGE_URL is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("IMAGE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("IMAGE_URL =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("IMAGE_URL <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("IMAGE_URL >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_URL >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("IMAGE_URL <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_URL <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("IMAGE_URL like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("IMAGE_URL not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("IMAGE_URL in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("IMAGE_URL not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("IMAGE_URL between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("IMAGE_URL not between", value1, value2, "imageUrl");
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

        public Criteria andUseYnIsNull() {
            addCriterion("use_yn is null");
            return (Criteria) this;
        }

        public Criteria andUseYnIsNotNull() {
            addCriterion("use_yn is not null");
            return (Criteria) this;
        }

        public Criteria andUseYnEqualTo(String value) {
            addCriterion("use_yn =", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotEqualTo(String value) {
            addCriterion("use_yn <>", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnGreaterThan(String value) {
            addCriterion("use_yn >", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnGreaterThanOrEqualTo(String value) {
            addCriterion("use_yn >=", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLessThan(String value) {
            addCriterion("use_yn <", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLessThanOrEqualTo(String value) {
            addCriterion("use_yn <=", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLike(String value) {
            addCriterion("use_yn like", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotLike(String value) {
            addCriterion("use_yn not like", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnIn(List<String> values) {
            addCriterion("use_yn in", values, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotIn(List<String> values) {
            addCriterion("use_yn not in", values, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnBetween(String value1, String value2) {
            addCriterion("use_yn between", value1, value2, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotBetween(String value1, String value2) {
            addCriterion("use_yn not between", value1, value2, "useYn");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated do_not_delete_during_merge Mon Sep 17 22:40:49 KST 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Mon Sep 17 22:40:49 KST 2012
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
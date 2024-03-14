package com.gugpay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransOrderExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public TransOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void getOffset(Integer offset) {
        this.offset = offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andTransOrderIdIsNull() {
            addCriterion("transOrderId is null");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdIsNotNull() {
            addCriterion("transOrderId is not null");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdEqualTo(String value) {
            addCriterion("transOrderId =", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdNotEqualTo(String value) {
            addCriterion("transOrderId <>", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdGreaterThan(String value) {
            addCriterion("transOrderId >", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("transOrderId >=", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdLessThan(String value) {
            addCriterion("transOrderId <", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdLessThanOrEqualTo(String value) {
            addCriterion("transOrderId <=", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdLike(String value) {
            addCriterion("transOrderId like", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdNotLike(String value) {
            addCriterion("transOrderId not like", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdIn(List<String> values) {
            addCriterion("transOrderId in", values, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdNotIn(List<String> values) {
            addCriterion("transOrderId not in", values, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdBetween(String value1, String value2) {
            addCriterion("transOrderId between", value1, value2, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdNotBetween(String value1, String value2) {
            addCriterion("transOrderId not between", value1, value2, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNull() {
            addCriterion("mchId is null");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNotNull() {
            addCriterion("mchId is not null");
            return (Criteria) this;
        }

        public Criteria andMchIdEqualTo(String value) {
            addCriterion("mchId =", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotEqualTo(String value) {
            addCriterion("mchId <>", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThan(String value) {
            addCriterion("mchId >", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("mchId >=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThan(String value) {
            addCriterion("mchId <", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThanOrEqualTo(String value) {
            addCriterion("mchId <=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLike(String value) {
            addCriterion("mchId like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotLike(String value) {
            addCriterion("mchId not like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdIn(List<String> values) {
            addCriterion("mchId in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotIn(List<String> values) {
            addCriterion("mchId not in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdBetween(String value1, String value2) {
            addCriterion("mchId between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotBetween(String value1, String value2) {
            addCriterion("mchId not between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchTransNoIsNull() {
            addCriterion("mchTransNo is null");
            return (Criteria) this;
        }

        public Criteria andMchTransNoIsNotNull() {
            addCriterion("mchTransNo is not null");
            return (Criteria) this;
        }

        public Criteria andMchTransNoEqualTo(String value) {
            addCriterion("mchTransNo =", value, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoNotEqualTo(String value) {
            addCriterion("mchTransNo <>", value, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoGreaterThan(String value) {
            addCriterion("mchTransNo >", value, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoGreaterThanOrEqualTo(String value) {
            addCriterion("mchTransNo >=", value, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoLessThan(String value) {
            addCriterion("mchTransNo <", value, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoLessThanOrEqualTo(String value) {
            addCriterion("mchTransNo <=", value, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoLike(String value) {
            addCriterion("mchTransNo like", value, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoNotLike(String value) {
            addCriterion("mchTransNo not like", value, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoIn(List<String> values) {
            addCriterion("mchTransNo in", values, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoNotIn(List<String> values) {
            addCriterion("mchTransNo not in", values, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoBetween(String value1, String value2) {
            addCriterion("mchTransNo between", value1, value2, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andMchTransNoNotBetween(String value1, String value2) {
            addCriterion("mchTransNo not between", value1, value2, "mchTransNo");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("channelId is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channelId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("channelId =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("channelId <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("channelId >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("channelId >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("channelId <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("channelId <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("channelId like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("channelId not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("channelId in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("channelId not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("channelId between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("channelId not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(Byte value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(Byte value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(Byte value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(Byte value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(Byte value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(Byte value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<Byte> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<Byte> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(Byte value1, Byte value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(Byte value1, Byte value2) {
            addCriterion("result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andClientIpIsNull() {
            addCriterion("clientIp is null");
            return (Criteria) this;
        }

        public Criteria andClientIpIsNotNull() {
            addCriterion("clientIp is not null");
            return (Criteria) this;
        }

        public Criteria andClientIpEqualTo(String value) {
            addCriterion("clientIp =", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotEqualTo(String value) {
            addCriterion("clientIp <>", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpGreaterThan(String value) {
            addCriterion("clientIp >", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpGreaterThanOrEqualTo(String value) {
            addCriterion("clientIp >=", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLessThan(String value) {
            addCriterion("clientIp <", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLessThanOrEqualTo(String value) {
            addCriterion("clientIp <=", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLike(String value) {
            addCriterion("clientIp like", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotLike(String value) {
            addCriterion("clientIp not like", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpIn(List<String> values) {
            addCriterion("clientIp in", values, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotIn(List<String> values) {
            addCriterion("clientIp not in", values, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpBetween(String value1, String value2) {
            addCriterion("clientIp between", value1, value2, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotBetween(String value1, String value2) {
            addCriterion("clientIp not between", value1, value2, "clientIp");
            return (Criteria) this;
        }

        public Criteria andDeviceIsNull() {
            addCriterion("device is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIsNotNull() {
            addCriterion("device is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceEqualTo(String value) {
            addCriterion("device =", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotEqualTo(String value) {
            addCriterion("device <>", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceGreaterThan(String value) {
            addCriterion("device >", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceGreaterThanOrEqualTo(String value) {
            addCriterion("device >=", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLessThan(String value) {
            addCriterion("device <", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLessThanOrEqualTo(String value) {
            addCriterion("device <=", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLike(String value) {
            addCriterion("device like", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotLike(String value) {
            addCriterion("device not like", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceIn(List<String> values) {
            addCriterion("device in", values, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotIn(List<String> values) {
            addCriterion("device not in", values, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceBetween(String value1, String value2) {
            addCriterion("device between", value1, value2, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotBetween(String value1, String value2) {
            addCriterion("device not between", value1, value2, "device");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoIsNull() {
            addCriterion("remarkInfo is null");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoIsNotNull() {
            addCriterion("remarkInfo is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoEqualTo(String value) {
            addCriterion("remarkInfo =", value, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoNotEqualTo(String value) {
            addCriterion("remarkInfo <>", value, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoGreaterThan(String value) {
            addCriterion("remarkInfo >", value, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoGreaterThanOrEqualTo(String value) {
            addCriterion("remarkInfo >=", value, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoLessThan(String value) {
            addCriterion("remarkInfo <", value, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoLessThanOrEqualTo(String value) {
            addCriterion("remarkInfo <=", value, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoLike(String value) {
            addCriterion("remarkInfo like", value, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoNotLike(String value) {
            addCriterion("remarkInfo not like", value, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoIn(List<String> values) {
            addCriterion("remarkInfo in", values, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoNotIn(List<String> values) {
            addCriterion("remarkInfo not in", values, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoBetween(String value1, String value2) {
            addCriterion("remarkInfo between", value1, value2, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andRemarkInfoNotBetween(String value1, String value2) {
            addCriterion("remarkInfo not between", value1, value2, "remarkInfo");
            return (Criteria) this;
        }

        public Criteria andChannelUserIsNull() {
            addCriterion("channelUser is null");
            return (Criteria) this;
        }

        public Criteria andChannelUserIsNotNull() {
            addCriterion("channelUser is not null");
            return (Criteria) this;
        }

        public Criteria andChannelUserEqualTo(String value) {
            addCriterion("channelUser =", value, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserNotEqualTo(String value) {
            addCriterion("channelUser <>", value, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserGreaterThan(String value) {
            addCriterion("channelUser >", value, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserGreaterThanOrEqualTo(String value) {
            addCriterion("channelUser >=", value, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserLessThan(String value) {
            addCriterion("channelUser <", value, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserLessThanOrEqualTo(String value) {
            addCriterion("channelUser <=", value, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserLike(String value) {
            addCriterion("channelUser like", value, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserNotLike(String value) {
            addCriterion("channelUser not like", value, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserIn(List<String> values) {
            addCriterion("channelUser in", values, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserNotIn(List<String> values) {
            addCriterion("channelUser not in", values, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserBetween(String value1, String value2) {
            addCriterion("channelUser between", value1, value2, "channelUser");
            return (Criteria) this;
        }

        public Criteria andChannelUserNotBetween(String value1, String value2) {
            addCriterion("channelUser not between", value1, value2, "channelUser");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("userName =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("userName <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("userName >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("userName <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("userName like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("userName not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("userName in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("userName not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdIsNull() {
            addCriterion("channelMchId is null");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdIsNotNull() {
            addCriterion("channelMchId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdEqualTo(String value) {
            addCriterion("channelMchId =", value, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdNotEqualTo(String value) {
            addCriterion("channelMchId <>", value, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdGreaterThan(String value) {
            addCriterion("channelMchId >", value, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("channelMchId >=", value, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdLessThan(String value) {
            addCriterion("channelMchId <", value, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdLessThanOrEqualTo(String value) {
            addCriterion("channelMchId <=", value, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdLike(String value) {
            addCriterion("channelMchId like", value, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdNotLike(String value) {
            addCriterion("channelMchId not like", value, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdIn(List<String> values) {
            addCriterion("channelMchId in", values, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdNotIn(List<String> values) {
            addCriterion("channelMchId not in", values, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdBetween(String value1, String value2) {
            addCriterion("channelMchId between", value1, value2, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelMchIdNotBetween(String value1, String value2) {
            addCriterion("channelMchId not between", value1, value2, "channelMchId");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoIsNull() {
            addCriterion("channelOrderNo is null");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoIsNotNull() {
            addCriterion("channelOrderNo is not null");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoEqualTo(String value) {
            addCriterion("channelOrderNo =", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoNotEqualTo(String value) {
            addCriterion("channelOrderNo <>", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoGreaterThan(String value) {
            addCriterion("channelOrderNo >", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("channelOrderNo >=", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoLessThan(String value) {
            addCriterion("channelOrderNo <", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoLessThanOrEqualTo(String value) {
            addCriterion("channelOrderNo <=", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoLike(String value) {
            addCriterion("channelOrderNo like", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoNotLike(String value) {
            addCriterion("channelOrderNo not like", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoIn(List<String> values) {
            addCriterion("channelOrderNo in", values, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoNotIn(List<String> values) {
            addCriterion("channelOrderNo not in", values, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoBetween(String value1, String value2) {
            addCriterion("channelOrderNo between", value1, value2, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoNotBetween(String value1, String value2) {
            addCriterion("channelOrderNo not between", value1, value2, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeIsNull() {
            addCriterion("channelErrCode is null");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeIsNotNull() {
            addCriterion("channelErrCode is not null");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeEqualTo(String value) {
            addCriterion("channelErrCode =", value, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeNotEqualTo(String value) {
            addCriterion("channelErrCode <>", value, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeGreaterThan(String value) {
            addCriterion("channelErrCode >", value, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("channelErrCode >=", value, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeLessThan(String value) {
            addCriterion("channelErrCode <", value, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeLessThanOrEqualTo(String value) {
            addCriterion("channelErrCode <=", value, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeLike(String value) {
            addCriterion("channelErrCode like", value, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeNotLike(String value) {
            addCriterion("channelErrCode not like", value, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeIn(List<String> values) {
            addCriterion("channelErrCode in", values, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeNotIn(List<String> values) {
            addCriterion("channelErrCode not in", values, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeBetween(String value1, String value2) {
            addCriterion("channelErrCode between", value1, value2, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrCodeNotBetween(String value1, String value2) {
            addCriterion("channelErrCode not between", value1, value2, "channelErrCode");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgIsNull() {
            addCriterion("channelErrMsg is null");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgIsNotNull() {
            addCriterion("channelErrMsg is not null");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgEqualTo(String value) {
            addCriterion("channelErrMsg =", value, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgNotEqualTo(String value) {
            addCriterion("channelErrMsg <>", value, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgGreaterThan(String value) {
            addCriterion("channelErrMsg >", value, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgGreaterThanOrEqualTo(String value) {
            addCriterion("channelErrMsg >=", value, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgLessThan(String value) {
            addCriterion("channelErrMsg <", value, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgLessThanOrEqualTo(String value) {
            addCriterion("channelErrMsg <=", value, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgLike(String value) {
            addCriterion("channelErrMsg like", value, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgNotLike(String value) {
            addCriterion("channelErrMsg not like", value, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgIn(List<String> values) {
            addCriterion("channelErrMsg in", values, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgNotIn(List<String> values) {
            addCriterion("channelErrMsg not in", values, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgBetween(String value1, String value2) {
            addCriterion("channelErrMsg between", value1, value2, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andChannelErrMsgNotBetween(String value1, String value2) {
            addCriterion("channelErrMsg not between", value1, value2, "channelErrMsg");
            return (Criteria) this;
        }

        public Criteria andExtraIsNull() {
            addCriterion("extra is null");
            return (Criteria) this;
        }

        public Criteria andExtraIsNotNull() {
            addCriterion("extra is not null");
            return (Criteria) this;
        }

        public Criteria andExtraEqualTo(String value) {
            addCriterion("extra =", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotEqualTo(String value) {
            addCriterion("extra <>", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThan(String value) {
            addCriterion("extra >", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThanOrEqualTo(String value) {
            addCriterion("extra >=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThan(String value) {
            addCriterion("extra <", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThanOrEqualTo(String value) {
            addCriterion("extra <=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLike(String value) {
            addCriterion("extra like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotLike(String value) {
            addCriterion("extra not like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraIn(List<String> values) {
            addCriterion("extra in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotIn(List<String> values) {
            addCriterion("extra not in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraBetween(String value1, String value2) {
            addCriterion("extra between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotBetween(String value1, String value2) {
            addCriterion("extra not between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIsNull() {
            addCriterion("notifyUrl is null");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIsNotNull() {
            addCriterion("notifyUrl is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlEqualTo(String value) {
            addCriterion("notifyUrl =", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotEqualTo(String value) {
            addCriterion("notifyUrl <>", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlGreaterThan(String value) {
            addCriterion("notifyUrl >", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("notifyUrl >=", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLessThan(String value) {
            addCriterion("notifyUrl <", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("notifyUrl <=", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLike(String value) {
            addCriterion("notifyUrl like", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotLike(String value) {
            addCriterion("notifyUrl not like", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIn(List<String> values) {
            addCriterion("notifyUrl in", values, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotIn(List<String> values) {
            addCriterion("notifyUrl not in", values, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlBetween(String value1, String value2) {
            addCriterion("notifyUrl between", value1, value2, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("notifyUrl not between", value1, value2, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andParam1IsNull() {
            addCriterion("param1 is null");
            return (Criteria) this;
        }

        public Criteria andParam1IsNotNull() {
            addCriterion("param1 is not null");
            return (Criteria) this;
        }

        public Criteria andParam1EqualTo(String value) {
            addCriterion("param1 =", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotEqualTo(String value) {
            addCriterion("param1 <>", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1GreaterThan(String value) {
            addCriterion("param1 >", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1GreaterThanOrEqualTo(String value) {
            addCriterion("param1 >=", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1LessThan(String value) {
            addCriterion("param1 <", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1LessThanOrEqualTo(String value) {
            addCriterion("param1 <=", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1Like(String value) {
            addCriterion("param1 like", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotLike(String value) {
            addCriterion("param1 not like", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1In(List<String> values) {
            addCriterion("param1 in", values, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotIn(List<String> values) {
            addCriterion("param1 not in", values, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1Between(String value1, String value2) {
            addCriterion("param1 between", value1, value2, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotBetween(String value1, String value2) {
            addCriterion("param1 not between", value1, value2, "param1");
            return (Criteria) this;
        }

        public Criteria andParam2IsNull() {
            addCriterion("param2 is null");
            return (Criteria) this;
        }

        public Criteria andParam2IsNotNull() {
            addCriterion("param2 is not null");
            return (Criteria) this;
        }

        public Criteria andParam2EqualTo(String value) {
            addCriterion("param2 =", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2NotEqualTo(String value) {
            addCriterion("param2 <>", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2GreaterThan(String value) {
            addCriterion("param2 >", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2GreaterThanOrEqualTo(String value) {
            addCriterion("param2 >=", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2LessThan(String value) {
            addCriterion("param2 <", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2LessThanOrEqualTo(String value) {
            addCriterion("param2 <=", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2Like(String value) {
            addCriterion("param2 like", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2NotLike(String value) {
            addCriterion("param2 not like", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2In(List<String> values) {
            addCriterion("param2 in", values, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2NotIn(List<String> values) {
            addCriterion("param2 not in", values, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2Between(String value1, String value2) {
            addCriterion("param2 between", value1, value2, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2NotBetween(String value1, String value2) {
            addCriterion("param2 not between", value1, value2, "param2");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("expireTime is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("expireTime is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("expireTime =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("expireTime <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("expireTime >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expireTime >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("expireTime <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("expireTime <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("expireTime in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("expireTime not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("expireTime between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("expireTime not between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeIsNull() {
            addCriterion("transSuccTime is null");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeIsNotNull() {
            addCriterion("transSuccTime is not null");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeEqualTo(Date value) {
            addCriterion("transSuccTime =", value, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeNotEqualTo(Date value) {
            addCriterion("transSuccTime <>", value, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeGreaterThan(Date value) {
            addCriterion("transSuccTime >", value, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("transSuccTime >=", value, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeLessThan(Date value) {
            addCriterion("transSuccTime <", value, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeLessThanOrEqualTo(Date value) {
            addCriterion("transSuccTime <=", value, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeIn(List<Date> values) {
            addCriterion("transSuccTime in", values, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeNotIn(List<Date> values) {
            addCriterion("transSuccTime not in", values, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeBetween(Date value1, Date value2) {
            addCriterion("transSuccTime between", value1, value2, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andTransSuccTimeNotBetween(Date value1, Date value2) {
            addCriterion("transSuccTime not between", value1, value2, "transSuccTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

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
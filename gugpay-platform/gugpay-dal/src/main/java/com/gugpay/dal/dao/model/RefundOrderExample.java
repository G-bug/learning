package com.gugpay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RefundOrderExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public RefundOrderExample() {
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

        public Criteria andRefundorderidIsNull() {
            addCriterion("refundOrderId is null");
            return (Criteria) this;
        }

        public Criteria andRefundorderidIsNotNull() {
            addCriterion("refundOrderId is not null");
            return (Criteria) this;
        }

        public Criteria andRefundorderidEqualTo(String value) {
            addCriterion("refundOrderId =", value, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidNotEqualTo(String value) {
            addCriterion("refundOrderId <>", value, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidGreaterThan(String value) {
            addCriterion("refundOrderId >", value, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidGreaterThanOrEqualTo(String value) {
            addCriterion("refundOrderId >=", value, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidLessThan(String value) {
            addCriterion("refundOrderId <", value, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidLessThanOrEqualTo(String value) {
            addCriterion("refundOrderId <=", value, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidLike(String value) {
            addCriterion("refundOrderId like", value, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidNotLike(String value) {
            addCriterion("refundOrderId not like", value, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidIn(List<String> values) {
            addCriterion("refundOrderId in", values, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidNotIn(List<String> values) {
            addCriterion("refundOrderId not in", values, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidBetween(String value1, String value2) {
            addCriterion("refundOrderId between", value1, value2, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andRefundorderidNotBetween(String value1, String value2) {
            addCriterion("refundOrderId not between", value1, value2, "refundorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidIsNull() {
            addCriterion("payOrderId is null");
            return (Criteria) this;
        }

        public Criteria andPayorderidIsNotNull() {
            addCriterion("payOrderId is not null");
            return (Criteria) this;
        }

        public Criteria andPayorderidEqualTo(String value) {
            addCriterion("payOrderId =", value, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidNotEqualTo(String value) {
            addCriterion("payOrderId <>", value, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidGreaterThan(String value) {
            addCriterion("payOrderId >", value, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidGreaterThanOrEqualTo(String value) {
            addCriterion("payOrderId >=", value, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidLessThan(String value) {
            addCriterion("payOrderId <", value, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidLessThanOrEqualTo(String value) {
            addCriterion("payOrderId <=", value, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidLike(String value) {
            addCriterion("payOrderId like", value, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidNotLike(String value) {
            addCriterion("payOrderId not like", value, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidIn(List<String> values) {
            addCriterion("payOrderId in", values, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidNotIn(List<String> values) {
            addCriterion("payOrderId not in", values, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidBetween(String value1, String value2) {
            addCriterion("payOrderId between", value1, value2, "payorderid");
            return (Criteria) this;
        }

        public Criteria andPayorderidNotBetween(String value1, String value2) {
            addCriterion("payOrderId not between", value1, value2, "payorderid");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoIsNull() {
            addCriterion("channelPayOrderNo is null");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoIsNotNull() {
            addCriterion("channelPayOrderNo is not null");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoEqualTo(String value) {
            addCriterion("channelPayOrderNo =", value, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoNotEqualTo(String value) {
            addCriterion("channelPayOrderNo <>", value, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoGreaterThan(String value) {
            addCriterion("channelPayOrderNo >", value, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoGreaterThanOrEqualTo(String value) {
            addCriterion("channelPayOrderNo >=", value, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoLessThan(String value) {
            addCriterion("channelPayOrderNo <", value, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoLessThanOrEqualTo(String value) {
            addCriterion("channelPayOrderNo <=", value, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoLike(String value) {
            addCriterion("channelPayOrderNo like", value, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoNotLike(String value) {
            addCriterion("channelPayOrderNo not like", value, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoIn(List<String> values) {
            addCriterion("channelPayOrderNo in", values, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoNotIn(List<String> values) {
            addCriterion("channelPayOrderNo not in", values, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoBetween(String value1, String value2) {
            addCriterion("channelPayOrderNo between", value1, value2, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andChannelpayordernoNotBetween(String value1, String value2) {
            addCriterion("channelPayOrderNo not between", value1, value2, "channelpayorderno");
            return (Criteria) this;
        }

        public Criteria andMchidIsNull() {
            addCriterion("mchId is null");
            return (Criteria) this;
        }

        public Criteria andMchidIsNotNull() {
            addCriterion("mchId is not null");
            return (Criteria) this;
        }

        public Criteria andMchidEqualTo(String value) {
            addCriterion("mchId =", value, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidNotEqualTo(String value) {
            addCriterion("mchId <>", value, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidGreaterThan(String value) {
            addCriterion("mchId >", value, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidGreaterThanOrEqualTo(String value) {
            addCriterion("mchId >=", value, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidLessThan(String value) {
            addCriterion("mchId <", value, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidLessThanOrEqualTo(String value) {
            addCriterion("mchId <=", value, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidLike(String value) {
            addCriterion("mchId like", value, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidNotLike(String value) {
            addCriterion("mchId not like", value, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidIn(List<String> values) {
            addCriterion("mchId in", values, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidNotIn(List<String> values) {
            addCriterion("mchId not in", values, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidBetween(String value1, String value2) {
            addCriterion("mchId between", value1, value2, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchidNotBetween(String value1, String value2) {
            addCriterion("mchId not between", value1, value2, "mchid");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoIsNull() {
            addCriterion("mchRefundNo is null");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoIsNotNull() {
            addCriterion("mchRefundNo is not null");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoEqualTo(String value) {
            addCriterion("mchRefundNo =", value, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoNotEqualTo(String value) {
            addCriterion("mchRefundNo <>", value, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoGreaterThan(String value) {
            addCriterion("mchRefundNo >", value, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoGreaterThanOrEqualTo(String value) {
            addCriterion("mchRefundNo >=", value, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoLessThan(String value) {
            addCriterion("mchRefundNo <", value, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoLessThanOrEqualTo(String value) {
            addCriterion("mchRefundNo <=", value, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoLike(String value) {
            addCriterion("mchRefundNo like", value, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoNotLike(String value) {
            addCriterion("mchRefundNo not like", value, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoIn(List<String> values) {
            addCriterion("mchRefundNo in", values, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoNotIn(List<String> values) {
            addCriterion("mchRefundNo not in", values, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoBetween(String value1, String value2) {
            addCriterion("mchRefundNo between", value1, value2, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andMchrefundnoNotBetween(String value1, String value2) {
            addCriterion("mchRefundNo not between", value1, value2, "mchrefundno");
            return (Criteria) this;
        }

        public Criteria andChannelidIsNull() {
            addCriterion("channelId is null");
            return (Criteria) this;
        }

        public Criteria andChannelidIsNotNull() {
            addCriterion("channelId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelidEqualTo(String value) {
            addCriterion("channelId =", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotEqualTo(String value) {
            addCriterion("channelId <>", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidGreaterThan(String value) {
            addCriterion("channelId >", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidGreaterThanOrEqualTo(String value) {
            addCriterion("channelId >=", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLessThan(String value) {
            addCriterion("channelId <", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLessThanOrEqualTo(String value) {
            addCriterion("channelId <=", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLike(String value) {
            addCriterion("channelId like", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotLike(String value) {
            addCriterion("channelId not like", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidIn(List<String> values) {
            addCriterion("channelId in", values, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotIn(List<String> values) {
            addCriterion("channelId not in", values, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidBetween(String value1, String value2) {
            addCriterion("channelId between", value1, value2, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotBetween(String value1, String value2) {
            addCriterion("channelId not between", value1, value2, "channelid");
            return (Criteria) this;
        }

        public Criteria andPayamountIsNull() {
            addCriterion("payAmount is null");
            return (Criteria) this;
        }

        public Criteria andPayamountIsNotNull() {
            addCriterion("payAmount is not null");
            return (Criteria) this;
        }

        public Criteria andPayamountEqualTo(Long value) {
            addCriterion("payAmount =", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotEqualTo(Long value) {
            addCriterion("payAmount <>", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountGreaterThan(Long value) {
            addCriterion("payAmount >", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountGreaterThanOrEqualTo(Long value) {
            addCriterion("payAmount >=", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountLessThan(Long value) {
            addCriterion("payAmount <", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountLessThanOrEqualTo(Long value) {
            addCriterion("payAmount <=", value, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountIn(List<Long> values) {
            addCriterion("payAmount in", values, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotIn(List<Long> values) {
            addCriterion("payAmount not in", values, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountBetween(Long value1, Long value2) {
            addCriterion("payAmount between", value1, value2, "payamount");
            return (Criteria) this;
        }

        public Criteria andPayamountNotBetween(Long value1, Long value2) {
            addCriterion("payAmount not between", value1, value2, "payamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountIsNull() {
            addCriterion("refundAmount is null");
            return (Criteria) this;
        }

        public Criteria andRefundamountIsNotNull() {
            addCriterion("refundAmount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundamountEqualTo(Long value) {
            addCriterion("refundAmount =", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountNotEqualTo(Long value) {
            addCriterion("refundAmount <>", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountGreaterThan(Long value) {
            addCriterion("refundAmount >", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountGreaterThanOrEqualTo(Long value) {
            addCriterion("refundAmount >=", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountLessThan(Long value) {
            addCriterion("refundAmount <", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountLessThanOrEqualTo(Long value) {
            addCriterion("refundAmount <=", value, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountIn(List<Long> values) {
            addCriterion("refundAmount in", values, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountNotIn(List<Long> values) {
            addCriterion("refundAmount not in", values, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountBetween(Long value1, Long value2) {
            addCriterion("refundAmount between", value1, value2, "refundamount");
            return (Criteria) this;
        }

        public Criteria andRefundamountNotBetween(Long value1, Long value2) {
            addCriterion("refundAmount not between", value1, value2, "refundamount");
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

        public Criteria andClientipIsNull() {
            addCriterion("clientIp is null");
            return (Criteria) this;
        }

        public Criteria andClientipIsNotNull() {
            addCriterion("clientIp is not null");
            return (Criteria) this;
        }

        public Criteria andClientipEqualTo(String value) {
            addCriterion("clientIp =", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipNotEqualTo(String value) {
            addCriterion("clientIp <>", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipGreaterThan(String value) {
            addCriterion("clientIp >", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipGreaterThanOrEqualTo(String value) {
            addCriterion("clientIp >=", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipLessThan(String value) {
            addCriterion("clientIp <", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipLessThanOrEqualTo(String value) {
            addCriterion("clientIp <=", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipLike(String value) {
            addCriterion("clientIp like", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipNotLike(String value) {
            addCriterion("clientIp not like", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipIn(List<String> values) {
            addCriterion("clientIp in", values, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipNotIn(List<String> values) {
            addCriterion("clientIp not in", values, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipBetween(String value1, String value2) {
            addCriterion("clientIp between", value1, value2, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipNotBetween(String value1, String value2) {
            addCriterion("clientIp not between", value1, value2, "clientip");
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

        public Criteria andRemarkinfoIsNull() {
            addCriterion("remarkInfo is null");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoIsNotNull() {
            addCriterion("remarkInfo is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoEqualTo(String value) {
            addCriterion("remarkInfo =", value, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoNotEqualTo(String value) {
            addCriterion("remarkInfo <>", value, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoGreaterThan(String value) {
            addCriterion("remarkInfo >", value, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoGreaterThanOrEqualTo(String value) {
            addCriterion("remarkInfo >=", value, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoLessThan(String value) {
            addCriterion("remarkInfo <", value, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoLessThanOrEqualTo(String value) {
            addCriterion("remarkInfo <=", value, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoLike(String value) {
            addCriterion("remarkInfo like", value, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoNotLike(String value) {
            addCriterion("remarkInfo not like", value, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoIn(List<String> values) {
            addCriterion("remarkInfo in", values, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoNotIn(List<String> values) {
            addCriterion("remarkInfo not in", values, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoBetween(String value1, String value2) {
            addCriterion("remarkInfo between", value1, value2, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andRemarkinfoNotBetween(String value1, String value2) {
            addCriterion("remarkInfo not between", value1, value2, "remarkinfo");
            return (Criteria) this;
        }

        public Criteria andChanneluserIsNull() {
            addCriterion("channelUser is null");
            return (Criteria) this;
        }

        public Criteria andChanneluserIsNotNull() {
            addCriterion("channelUser is not null");
            return (Criteria) this;
        }

        public Criteria andChanneluserEqualTo(String value) {
            addCriterion("channelUser =", value, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserNotEqualTo(String value) {
            addCriterion("channelUser <>", value, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserGreaterThan(String value) {
            addCriterion("channelUser >", value, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserGreaterThanOrEqualTo(String value) {
            addCriterion("channelUser >=", value, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserLessThan(String value) {
            addCriterion("channelUser <", value, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserLessThanOrEqualTo(String value) {
            addCriterion("channelUser <=", value, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserLike(String value) {
            addCriterion("channelUser like", value, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserNotLike(String value) {
            addCriterion("channelUser not like", value, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserIn(List<String> values) {
            addCriterion("channelUser in", values, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserNotIn(List<String> values) {
            addCriterion("channelUser not in", values, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserBetween(String value1, String value2) {
            addCriterion("channelUser between", value1, value2, "channeluser");
            return (Criteria) this;
        }

        public Criteria andChanneluserNotBetween(String value1, String value2) {
            addCriterion("channelUser not between", value1, value2, "channeluser");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("userName =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("userName <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("userName >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("userName <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("userName like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("userName not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("userName in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("userName not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andChannelmchidIsNull() {
            addCriterion("channelMchId is null");
            return (Criteria) this;
        }

        public Criteria andChannelmchidIsNotNull() {
            addCriterion("channelMchId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelmchidEqualTo(String value) {
            addCriterion("channelMchId =", value, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidNotEqualTo(String value) {
            addCriterion("channelMchId <>", value, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidGreaterThan(String value) {
            addCriterion("channelMchId >", value, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidGreaterThanOrEqualTo(String value) {
            addCriterion("channelMchId >=", value, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidLessThan(String value) {
            addCriterion("channelMchId <", value, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidLessThanOrEqualTo(String value) {
            addCriterion("channelMchId <=", value, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidLike(String value) {
            addCriterion("channelMchId like", value, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidNotLike(String value) {
            addCriterion("channelMchId not like", value, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidIn(List<String> values) {
            addCriterion("channelMchId in", values, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidNotIn(List<String> values) {
            addCriterion("channelMchId not in", values, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidBetween(String value1, String value2) {
            addCriterion("channelMchId between", value1, value2, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelmchidNotBetween(String value1, String value2) {
            addCriterion("channelMchId not between", value1, value2, "channelmchid");
            return (Criteria) this;
        }

        public Criteria andChannelordernoIsNull() {
            addCriterion("channelOrderNo is null");
            return (Criteria) this;
        }

        public Criteria andChannelordernoIsNotNull() {
            addCriterion("channelOrderNo is not null");
            return (Criteria) this;
        }

        public Criteria andChannelordernoEqualTo(String value) {
            addCriterion("channelOrderNo =", value, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoNotEqualTo(String value) {
            addCriterion("channelOrderNo <>", value, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoGreaterThan(String value) {
            addCriterion("channelOrderNo >", value, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoGreaterThanOrEqualTo(String value) {
            addCriterion("channelOrderNo >=", value, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoLessThan(String value) {
            addCriterion("channelOrderNo <", value, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoLessThanOrEqualTo(String value) {
            addCriterion("channelOrderNo <=", value, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoLike(String value) {
            addCriterion("channelOrderNo like", value, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoNotLike(String value) {
            addCriterion("channelOrderNo not like", value, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoIn(List<String> values) {
            addCriterion("channelOrderNo in", values, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoNotIn(List<String> values) {
            addCriterion("channelOrderNo not in", values, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoBetween(String value1, String value2) {
            addCriterion("channelOrderNo between", value1, value2, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelordernoNotBetween(String value1, String value2) {
            addCriterion("channelOrderNo not between", value1, value2, "channelorderno");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeIsNull() {
            addCriterion("channelErrCode is null");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeIsNotNull() {
            addCriterion("channelErrCode is not null");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeEqualTo(String value) {
            addCriterion("channelErrCode =", value, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeNotEqualTo(String value) {
            addCriterion("channelErrCode <>", value, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeGreaterThan(String value) {
            addCriterion("channelErrCode >", value, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("channelErrCode >=", value, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeLessThan(String value) {
            addCriterion("channelErrCode <", value, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeLessThanOrEqualTo(String value) {
            addCriterion("channelErrCode <=", value, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeLike(String value) {
            addCriterion("channelErrCode like", value, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeNotLike(String value) {
            addCriterion("channelErrCode not like", value, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeIn(List<String> values) {
            addCriterion("channelErrCode in", values, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeNotIn(List<String> values) {
            addCriterion("channelErrCode not in", values, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeBetween(String value1, String value2) {
            addCriterion("channelErrCode between", value1, value2, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrcodeNotBetween(String value1, String value2) {
            addCriterion("channelErrCode not between", value1, value2, "channelerrcode");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgIsNull() {
            addCriterion("channelErrMsg is null");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgIsNotNull() {
            addCriterion("channelErrMsg is not null");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgEqualTo(String value) {
            addCriterion("channelErrMsg =", value, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgNotEqualTo(String value) {
            addCriterion("channelErrMsg <>", value, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgGreaterThan(String value) {
            addCriterion("channelErrMsg >", value, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgGreaterThanOrEqualTo(String value) {
            addCriterion("channelErrMsg >=", value, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgLessThan(String value) {
            addCriterion("channelErrMsg <", value, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgLessThanOrEqualTo(String value) {
            addCriterion("channelErrMsg <=", value, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgLike(String value) {
            addCriterion("channelErrMsg like", value, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgNotLike(String value) {
            addCriterion("channelErrMsg not like", value, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgIn(List<String> values) {
            addCriterion("channelErrMsg in", values, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgNotIn(List<String> values) {
            addCriterion("channelErrMsg not in", values, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgBetween(String value1, String value2) {
            addCriterion("channelErrMsg between", value1, value2, "channelerrmsg");
            return (Criteria) this;
        }

        public Criteria andChannelerrmsgNotBetween(String value1, String value2) {
            addCriterion("channelErrMsg not between", value1, value2, "channelerrmsg");
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

        public Criteria andNotifyurlIsNull() {
            addCriterion("notifyUrl is null");
            return (Criteria) this;
        }

        public Criteria andNotifyurlIsNotNull() {
            addCriterion("notifyUrl is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyurlEqualTo(String value) {
            addCriterion("notifyUrl =", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlNotEqualTo(String value) {
            addCriterion("notifyUrl <>", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlGreaterThan(String value) {
            addCriterion("notifyUrl >", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlGreaterThanOrEqualTo(String value) {
            addCriterion("notifyUrl >=", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlLessThan(String value) {
            addCriterion("notifyUrl <", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlLessThanOrEqualTo(String value) {
            addCriterion("notifyUrl <=", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlLike(String value) {
            addCriterion("notifyUrl like", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlNotLike(String value) {
            addCriterion("notifyUrl not like", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlIn(List<String> values) {
            addCriterion("notifyUrl in", values, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlNotIn(List<String> values) {
            addCriterion("notifyUrl not in", values, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlBetween(String value1, String value2) {
            addCriterion("notifyUrl between", value1, value2, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlNotBetween(String value1, String value2) {
            addCriterion("notifyUrl not between", value1, value2, "notifyurl");
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

        public Criteria andExpiretimeIsNull() {
            addCriterion("expireTime is null");
            return (Criteria) this;
        }

        public Criteria andExpiretimeIsNotNull() {
            addCriterion("expireTime is not null");
            return (Criteria) this;
        }

        public Criteria andExpiretimeEqualTo(Date value) {
            addCriterion("expireTime =", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotEqualTo(Date value) {
            addCriterion("expireTime <>", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeGreaterThan(Date value) {
            addCriterion("expireTime >", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expireTime >=", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeLessThan(Date value) {
            addCriterion("expireTime <", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeLessThanOrEqualTo(Date value) {
            addCriterion("expireTime <=", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeIn(List<Date> values) {
            addCriterion("expireTime in", values, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotIn(List<Date> values) {
            addCriterion("expireTime not in", values, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeBetween(Date value1, Date value2) {
            addCriterion("expireTime between", value1, value2, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotBetween(Date value1, Date value2) {
            addCriterion("expireTime not between", value1, value2, "expiretime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeIsNull() {
            addCriterion("refundSuccTime is null");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeIsNotNull() {
            addCriterion("refundSuccTime is not null");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeEqualTo(Date value) {
            addCriterion("refundSuccTime =", value, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeNotEqualTo(Date value) {
            addCriterion("refundSuccTime <>", value, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeGreaterThan(Date value) {
            addCriterion("refundSuccTime >", value, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refundSuccTime >=", value, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeLessThan(Date value) {
            addCriterion("refundSuccTime <", value, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeLessThanOrEqualTo(Date value) {
            addCriterion("refundSuccTime <=", value, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeIn(List<Date> values) {
            addCriterion("refundSuccTime in", values, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeNotIn(List<Date> values) {
            addCriterion("refundSuccTime not in", values, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeBetween(Date value1, Date value2) {
            addCriterion("refundSuccTime between", value1, value2, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andRefundsucctimeNotBetween(Date value1, Date value2) {
            addCriterion("refundSuccTime not between", value1, value2, "refundsucctime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
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
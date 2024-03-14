package com.gugpay.dal.dao.model;

import java.io.Serializable;
import java.util.Date;

public class RefundOrder implements Serializable {
    /**
     * 退款订单号
     *
     * @mbggenerated
     */
    private String refundorderid;

    /**
     * 支付订单号
     *
     * @mbggenerated
     */
    private String payorderid;

    /**
     * 渠道支付单号
     *
     * @mbggenerated
     */
    private String channelpayorderno;

    /**
     * 商户ID
     *
     * @mbggenerated
     */
    private String mchid;

    /**
     * 商户退款单号
     *
     * @mbggenerated
     */
    private String mchrefundno;

    /**
     * 渠道ID
     *
     * @mbggenerated
     */
    private String channelid;

    /**
     * 支付金额,单位分
     *
     * @mbggenerated
     */
    private Long payamount;

    /**
     * 退款金额,单位分
     *
     * @mbggenerated
     */
    private Long refundamount;

    /**
     * 三位货币代码,人民币:cny
     *
     * @mbggenerated
     */
    private String currency;

    /**
     * 退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败,4-业务处理完成
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * 退款结果:0-不确认结果,1-等待手动处理,2-确认成功,3-确认失败
     *
     * @mbggenerated
     */
    private Byte result;

    /**
     * 客户端IP
     *
     * @mbggenerated
     */
    private String clientip;

    /**
     * 设备
     *
     * @mbggenerated
     */
    private String device;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remarkinfo;

    /**
     * 渠道用户标识,如微信openId,支付宝账号
     *
     * @mbggenerated
     */
    private String channeluser;

    /**
     * 用户姓名
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 渠道商户ID
     *
     * @mbggenerated
     */
    private String channelmchid;

    /**
     * 渠道订单号
     *
     * @mbggenerated
     */
    private String channelorderno;

    /**
     * 渠道错误码
     *
     * @mbggenerated
     */
    private String channelerrcode;

    /**
     * 渠道错误描述
     *
     * @mbggenerated
     */
    private String channelerrmsg;

    /**
     * 特定渠道发起时额外参数
     *
     * @mbggenerated
     */
    private String extra;

    /**
     * 通知地址
     *
     * @mbggenerated
     */
    private String notifyurl;

    /**
     * 扩展参数1
     *
     * @mbggenerated
     */
    private String param1;

    /**
     * 扩展参数2
     *
     * @mbggenerated
     */
    private String param2;

    /**
     * 订单失效时间
     *
     * @mbggenerated
     */
    private Date expiretime;

    /**
     * 订单退款成功时间
     *
     * @mbggenerated
     */
    private Date refundsucctime;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getRefundorderid() {
        return refundorderid;
    }

    public void setRefundorderid(String refundorderid) {
        this.refundorderid = refundorderid;
    }

    public String getPayorderid() {
        return payorderid;
    }

    public void setPayorderid(String payorderid) {
        this.payorderid = payorderid;
    }

    public String getChannelpayorderno() {
        return channelpayorderno;
    }

    public void setChannelpayorderno(String channelpayorderno) {
        this.channelpayorderno = channelpayorderno;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getMchrefundno() {
        return mchrefundno;
    }

    public void setMchrefundno(String mchrefundno) {
        this.mchrefundno = mchrefundno;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public Long getPayamount() {
        return payamount;
    }

    public void setPayamount(Long payamount) {
        this.payamount = payamount;
    }

    public Long getRefundamount() {
        return refundamount;
    }

    public void setRefundamount(Long refundamount) {
        this.refundamount = refundamount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getRemarkinfo() {
        return remarkinfo;
    }

    public void setRemarkinfo(String remarkinfo) {
        this.remarkinfo = remarkinfo;
    }

    public String getChanneluser() {
        return channeluser;
    }

    public void setChanneluser(String channeluser) {
        this.channeluser = channeluser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChannelmchid() {
        return channelmchid;
    }

    public void setChannelmchid(String channelmchid) {
        this.channelmchid = channelmchid;
    }

    public String getChannelorderno() {
        return channelorderno;
    }

    public void setChannelorderno(String channelorderno) {
        this.channelorderno = channelorderno;
    }

    public String getChannelerrcode() {
        return channelerrcode;
    }

    public void setChannelerrcode(String channelerrcode) {
        this.channelerrcode = channelerrcode;
    }

    public String getChannelerrmsg() {
        return channelerrmsg;
    }

    public void setChannelerrmsg(String channelerrmsg) {
        this.channelerrmsg = channelerrmsg;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getNotifyurl() {
        return notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public Date getRefundsucctime() {
        return refundsucctime;
    }

    public void setRefundsucctime(Date refundsucctime) {
        this.refundsucctime = refundsucctime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", refundorderid=").append(refundorderid);
        sb.append(", payorderid=").append(payorderid);
        sb.append(", channelpayorderno=").append(channelpayorderno);
        sb.append(", mchid=").append(mchid);
        sb.append(", mchrefundno=").append(mchrefundno);
        sb.append(", channelid=").append(channelid);
        sb.append(", payamount=").append(payamount);
        sb.append(", refundamount=").append(refundamount);
        sb.append(", currency=").append(currency);
        sb.append(", status=").append(status);
        sb.append(", result=").append(result);
        sb.append(", clientip=").append(clientip);
        sb.append(", device=").append(device);
        sb.append(", remarkinfo=").append(remarkinfo);
        sb.append(", channeluser=").append(channeluser);
        sb.append(", username=").append(username);
        sb.append(", channelmchid=").append(channelmchid);
        sb.append(", channelorderno=").append(channelorderno);
        sb.append(", channelerrcode=").append(channelerrcode);
        sb.append(", channelerrmsg=").append(channelerrmsg);
        sb.append(", extra=").append(extra);
        sb.append(", notifyurl=").append(notifyurl);
        sb.append(", param1=").append(param1);
        sb.append(", param2=").append(param2);
        sb.append(", expiretime=").append(expiretime);
        sb.append(", refundsucctime=").append(refundsucctime);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RefundOrder other = (RefundOrder) that;
        return (this.getRefundorderid() == null ? other.getRefundorderid() == null : this.getRefundorderid().equals(other.getRefundorderid()))
            && (this.getPayorderid() == null ? other.getPayorderid() == null : this.getPayorderid().equals(other.getPayorderid()))
            && (this.getChannelpayorderno() == null ? other.getChannelpayorderno() == null : this.getChannelpayorderno().equals(other.getChannelpayorderno()))
            && (this.getMchid() == null ? other.getMchid() == null : this.getMchid().equals(other.getMchid()))
            && (this.getMchrefundno() == null ? other.getMchrefundno() == null : this.getMchrefundno().equals(other.getMchrefundno()))
            && (this.getChannelid() == null ? other.getChannelid() == null : this.getChannelid().equals(other.getChannelid()))
            && (this.getPayamount() == null ? other.getPayamount() == null : this.getPayamount().equals(other.getPayamount()))
            && (this.getRefundamount() == null ? other.getRefundamount() == null : this.getRefundamount().equals(other.getRefundamount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getClientip() == null ? other.getClientip() == null : this.getClientip().equals(other.getClientip()))
            && (this.getDevice() == null ? other.getDevice() == null : this.getDevice().equals(other.getDevice()))
            && (this.getRemarkinfo() == null ? other.getRemarkinfo() == null : this.getRemarkinfo().equals(other.getRemarkinfo()))
            && (this.getChanneluser() == null ? other.getChanneluser() == null : this.getChanneluser().equals(other.getChanneluser()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getChannelmchid() == null ? other.getChannelmchid() == null : this.getChannelmchid().equals(other.getChannelmchid()))
            && (this.getChannelorderno() == null ? other.getChannelorderno() == null : this.getChannelorderno().equals(other.getChannelorderno()))
            && (this.getChannelerrcode() == null ? other.getChannelerrcode() == null : this.getChannelerrcode().equals(other.getChannelerrcode()))
            && (this.getChannelerrmsg() == null ? other.getChannelerrmsg() == null : this.getChannelerrmsg().equals(other.getChannelerrmsg()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getNotifyurl() == null ? other.getNotifyurl() == null : this.getNotifyurl().equals(other.getNotifyurl()))
            && (this.getParam1() == null ? other.getParam1() == null : this.getParam1().equals(other.getParam1()))
            && (this.getParam2() == null ? other.getParam2() == null : this.getParam2().equals(other.getParam2()))
            && (this.getExpiretime() == null ? other.getExpiretime() == null : this.getExpiretime().equals(other.getExpiretime()))
            && (this.getRefundsucctime() == null ? other.getRefundsucctime() == null : this.getRefundsucctime().equals(other.getRefundsucctime()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRefundorderid() == null) ? 0 : getRefundorderid().hashCode());
        result = prime * result + ((getPayorderid() == null) ? 0 : getPayorderid().hashCode());
        result = prime * result + ((getChannelpayorderno() == null) ? 0 : getChannelpayorderno().hashCode());
        result = prime * result + ((getMchid() == null) ? 0 : getMchid().hashCode());
        result = prime * result + ((getMchrefundno() == null) ? 0 : getMchrefundno().hashCode());
        result = prime * result + ((getChannelid() == null) ? 0 : getChannelid().hashCode());
        result = prime * result + ((getPayamount() == null) ? 0 : getPayamount().hashCode());
        result = prime * result + ((getRefundamount() == null) ? 0 : getRefundamount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getClientip() == null) ? 0 : getClientip().hashCode());
        result = prime * result + ((getDevice() == null) ? 0 : getDevice().hashCode());
        result = prime * result + ((getRemarkinfo() == null) ? 0 : getRemarkinfo().hashCode());
        result = prime * result + ((getChanneluser() == null) ? 0 : getChanneluser().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getChannelmchid() == null) ? 0 : getChannelmchid().hashCode());
        result = prime * result + ((getChannelorderno() == null) ? 0 : getChannelorderno().hashCode());
        result = prime * result + ((getChannelerrcode() == null) ? 0 : getChannelerrcode().hashCode());
        result = prime * result + ((getChannelerrmsg() == null) ? 0 : getChannelerrmsg().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getNotifyurl() == null) ? 0 : getNotifyurl().hashCode());
        result = prime * result + ((getParam1() == null) ? 0 : getParam1().hashCode());
        result = prime * result + ((getParam2() == null) ? 0 : getParam2().hashCode());
        result = prime * result + ((getExpiretime() == null) ? 0 : getExpiretime().hashCode());
        result = prime * result + ((getRefundsucctime() == null) ? 0 : getRefundsucctime().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}
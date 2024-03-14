/**
 * @author G-bug 2019/12/20
 */
package com.gugpay.service.service;

import com.gugpay.dal.dao.mapper.PayOrderMapper;
import com.gugpay.dal.dao.model.PayOrder;
import com.gugpay.dal.dao.model.PayOrderExample;
import constant.PayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author G-bug
 * @Description 支付订单service
 * @Date 2019/12/20 17:49
 */
@Service
public class PayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    public int createPayOrder(PayOrder payOrder) {
        return payOrderMapper.insertSelective(payOrder);
    }

    public PayOrder selectPayOrder(String payOrderId) {
        return payOrderMapper.selectByPrimaryKey(payOrderId);
    }

    public PayOrder selectByMchIdAndId(String mchId, String payOrderId) {
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMchIdEqualTo(mchId);
        criteria.andPayOrderIdEqualTo(payOrderId);
        List<PayOrder> payOrderList = payOrderMapper.selectByExample(example);
        return CollectionUtils.isEmpty(payOrderList) ? null : payOrderList.get(0);
    }

    public PayOrder selectByMcIdAndMchOrderNo(String mchId, String mchOrderNo) {
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMchIdEqualTo(mchId);
        criteria.andMchOrderNoEqualTo(mchOrderNo);
        List<PayOrder> payOrderList = payOrderMapper.selectByExample(example);
        return CollectionUtils.isEmpty(payOrderList) ? null : payOrderList.get(0);
    }

    public int updateStatusIng(String payOrderId, String channelOrderNo) {
        PayOrder payOrder = new PayOrder();
        payOrder.setStatus(PayConstant.PAY_STATUS_PAYING);
        if (channelOrderNo != null) {
            payOrder.setChannelOrderNo(channelOrderNo);
        }
        payOrder.setPaySuccTime(System.currentTimeMillis());

        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_INIT);

        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    public int updateStatusSuccess(String payOrderId) {
        PayOrder payOrder = new PayOrder();
        payOrder.setPayOrderId(payOrderId);
        payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);
        payOrder.setPaySuccTime(System.currentTimeMillis());

        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_PAYING);
        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    public int updateStatusComplete(String payOrderId) {
        PayOrder payOrder = new PayOrder();
        payOrder.setPayOrderId(payOrderId);
        payOrder.setStatus(PayConstant.PAY_STATUS_COMPLETE);

        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_SUCCESS);
        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    /**
     * 更新通知次数
     */
    public int updateNotify(String payOrderId, byte count) {
        PayOrder newPayOrder = new PayOrder();
        // TODO:并发有问题
        newPayOrder.setNotifyCount(count);
        newPayOrder.setLastNotifyTime(System.currentTimeMillis());
        newPayOrder.setPayOrderId(payOrderId);
        return payOrderMapper.updateByPrimaryKeySelective(newPayOrder);
    }
}

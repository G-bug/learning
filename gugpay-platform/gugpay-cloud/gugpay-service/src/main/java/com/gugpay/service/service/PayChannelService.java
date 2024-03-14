/**
 * @author G-bug 2019/12/17
 */
package com.gugpay.service.service;

import com.gugpay.dal.dao.mapper.PayChannelMapper;
import com.gugpay.dal.dao.model.PayChannel;
import com.gugpay.dal.dao.model.PayChannelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author G-bug
 * @Description 商户-支付渠道 service
 * @Date 2019/12/17 14:56
 */
@Service
public class PayChannelService {

    @Autowired
    private PayChannelMapper payChannelMapper;

    public PayChannel selectPayChannel(String channelId, String mchId) {
        PayChannelExample example = new PayChannelExample();
        PayChannelExample.Criteria criteria = example.createCriteria();
        criteria.andChannelIdEqualTo(channelId);
        criteria.andMchIdEqualTo(mchId);
        List<PayChannel> payChannelList = payChannelMapper.selectByExample(example);
        return CollectionUtils.isEmpty(payChannelList) ? null : payChannelList.get(0);
    }

}

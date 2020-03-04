/**
 * @author G-bug 2019/12/11
 */
package com.gugpay.service.service;

import com.gugpay.dal.dao.mapper.MchInfoMapper;
import com.gugpay.dal.dao.model.MchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author G-bug
 * @Description 商户信息 service
 * @Date 2019/12/11 16:54
 */
@Service
public class MchInfoService {

    @Autowired
    private MchInfoMapper mchInfoMapper;

    public MchInfo selectMchInfo(String mchId) {
        return mchInfoMapper.selectByPrimaryKey(mchId);
    }

}

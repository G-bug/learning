package com.gugpay.dal.dao.mapper;

import com.gugpay.dal.dao.model.PayChannel;
import com.gugpay.dal.dao.model.PayChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayChannelMapper {
    int countByExample(PayChannelExample example);

    int deleteByExample(PayChannelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PayChannel record);

    int insertSelective(PayChannel record);

    List<PayChannel> selectByExample(PayChannelExample example);

    PayChannel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PayChannel record, @Param("example") PayChannelExample example);

    int updateByExample(@Param("record") PayChannel record, @Param("example") PayChannelExample example);

    int updateByPrimaryKeySelective(PayChannel record);

    int updateByPrimaryKey(PayChannel record);
}
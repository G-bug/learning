package com.gugpay.dal.dao.mapper;

import com.gugpay.dal.dao.model.RefundOrder;
import com.gugpay.dal.dao.model.RefundOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefundOrderMapper {
    int countByExample(RefundOrderExample example);

    int deleteByExample(RefundOrderExample example);

    int deleteByPrimaryKey(String refundorderid);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    List<RefundOrder> selectByExample(RefundOrderExample example);

    RefundOrder selectByPrimaryKey(String refundorderid);

    int updateByExampleSelective(@Param("record") RefundOrder record, @Param("example") RefundOrderExample example);

    int updateByExample(@Param("record") RefundOrder record, @Param("example") RefundOrderExample example);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);
}
package com.gugpay.dal.dao.mapper;

import com.gugpay.dal.dao.model.TransOrder;
import com.gugpay.dal.dao.model.TransOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransOrderMapper {
    int countByExample(TransOrderExample example);

    int deleteByExample(TransOrderExample example);

    int deleteByPrimaryKey(String transOrderId);

    int insert(TransOrder record);

    int insertSelective(TransOrder record);

    List<TransOrder> selectByExample(TransOrderExample example);

    TransOrder selectByPrimaryKey(String transOrderId);

    int updateByExampleSelective(@Param("record") TransOrder record, @Param("example") TransOrderExample example);

    int updateByExample(@Param("record") TransOrder record, @Param("example") TransOrderExample example);

    int updateByPrimaryKeySelective(TransOrder record);

    int updateByPrimaryKey(TransOrder record);
}
package com.gugpay.dal.dao.mapper;

import com.gugpay.dal.dao.model.MchInfo;
import com.gugpay.dal.dao.model.MchInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MchInfoMapper {
    int countByExample(MchInfoExample example);

    int deleteByExample(MchInfoExample example);

    int deleteByPrimaryKey(String mchId);

    int insert(MchInfo record);

    int insertSelective(MchInfo record);

    List<MchInfo> selectByExample(MchInfoExample example);

    MchInfo selectByPrimaryKey(String mchId);

    int updateByExampleSelective(@Param("record") MchInfo record, @Param("example") MchInfoExample example);

    int updateByExample(@Param("record") MchInfo record, @Param("example") MchInfoExample example);

    int updateByPrimaryKeySelective(MchInfo record);

    int updateByPrimaryKey(MchInfo record);
}
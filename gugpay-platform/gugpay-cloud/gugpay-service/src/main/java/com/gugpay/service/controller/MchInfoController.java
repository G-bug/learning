/**
 * @author G-bug 2019/12/12
 */
package com.gugpay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gugpay.dal.dao.model.MchInfo;
import com.gugpay.service.service.MchInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import util.CustomerBase64;

/**
 * @author G-bug
 * @Description 商户信息 controller
 * @Date 2019/12/12 16:51
 */
@RestController
public class MchInfoController {

    @Autowired
    private MchInfoService mchInfoService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/mch/{id}")
    public String selectMchInfo(@PathVariable("id") String jsonParam) {
        JSONObject retObj = new JSONObject();
        retObj.put("code", "0000");
        if (StringUtils.isBlank(jsonParam)) {
            retObj.put("code", "0001");
            retObj.put("msg", "缺少参数");
            return retObj.toJSONString();
        }

        JSONObject paramObj = JSON.parseObject(CustomerBase64.decode(jsonParam));
        String mchId = paramObj.getString("mchId");
        MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);

        if (mchInfo == null) {
            retObj.put("code", "0002");
            retObj.put("msg", "数据对象不存在");
            return retObj.toJSONString();
        }

        retObj.put("result", JSON.toJSON(mchInfo));
        log.info("result:{}", retObj.toJSONString());

        return retObj.toJSONString();
    }

}

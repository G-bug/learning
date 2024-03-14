/**
 * @author G-bug 2019/12/27
 */
package util;

import com.alibaba.fastjson.JSON;
import constant.PayConstant;
import enums.ErrCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G-bug
 * @Description
 * @Date 2019/12/27 13:38
 */
public class PlatFormUtils {

    private static final Logger log = LoggerFactory.getLogger(PlatFormUtils.class);

    public static Map<String, Object> makeRetMap(String retCode, String retMsg, String resCode, String errCode, String errCodeDes) {
        Map<String, Object> map = new HashMap<>();
        if (retCode != null) {
            map.put(PayConstant.RETURN_PARAM_RETCODE, retCode);
        }
        if (retMsg != null) {
            map.put(PayConstant.RETURN_PARAM_RETMSG, retMsg);
        }
        if (resCode != null) {
            map.put(PayConstant.RESULT_PARAM_RESCODE, resCode);
        }
        if (errCode != null) {
            map.put(PayConstant.RESULT_PARAM_ERRCODE, errCode);
        }
        if (errCodeDes != null) {
            map.put(PayConstant.RESULT_PARAM_ERRCODEDES, errCodeDes);
        }
        return map;
    }

    public static Map<String, Object> makeRetMap(String retCode, String retMsg, String resCode, ErrCodeEnum errCodeEnum) {
        Map<String, Object> map = new HashMap<>(10);
        if (retCode != null) {
            map.put(PayConstant.RETURN_PARAM_RETCODE, retCode);
        }
        if (retMsg != null) {
            map.put(PayConstant.RETURN_PARAM_RETMSG, retMsg);
        }
        if (resCode != null) {
            map.put(PayConstant.RESULT_PARAM_RESCODE, resCode);
        }
        if (errCodeEnum != null) {
            map.put(PayConstant.RESULT_PARAM_ERRCODE, errCodeEnum.getCode());
            map.put(PayConstant.RESULT_PARAM_ERRCODEDES, errCodeEnum.getMessage());
        }
        return map;
    }

    public static String makeRetData(Map<String, Object> retMap, String resKey) {
        if (retMap.get(PayConstant.RETURN_PARAM_RETCODE).equals(PayConstant.RETURN_VALUE_SUCCESS)) {
            String sign = DigestUtil.getSign(retMap, resKey, "payParams");
            retMap.put(PayConstant.RESULT_PARAM_SIGN, sign);
        }
        log.info("生成响应数据:{}", retMap);
        return JSON.toJSONString(retMap);
    }

    public static String makeRetFail(Map retMap) {
        log.info("生成响应数据:{}", retMap);
        return JSON.toJSONString(retMap);
    }

    public static boolean verifyPaySign(Map<String, Object> params, String key) {
        String sign = (String) params.get("sign");
        params.remove("sign");
        String checkSign = DigestUtil.getSign(params, key);
        return checkSign.equalsIgnoreCase(sign);
    }

    public static boolean verifyPaySign(Map<String, Object> params, String key, String... noSigns) {
        String sign = (String) params.get("sign");
        params.remove("sign");
        if (noSigns != null && noSigns.length > 0) {
            for (String noSign : noSigns) {
                params.remove(noSign);
            }
        }

        String checkSign = DigestUtil.getSign(params, key);
        return checkSign.equalsIgnoreCase(sign);
    }

    public static String toUrlQueryString(Map<String, Object> paramMap) {
        int size;

        if (paramMap == null || (size = paramMap.size()) == 0) {
            return "";
        }

        StringBuilder urlParam = new StringBuilder();
        for (String key : paramMap.keySet()) {
            urlParam.append(key).append("=").append(paramMap.get(key));
            if (--size == 0) {
                break;
            }
            urlParam.append("&");
        }

        return urlParam.toString();
    }

    public static String callPost(String urlStr) {
        try {
            URL url = new URL(urlStr);
            if ("https".equals(url.getProtocol())) {
                return HttpClient.callHttpsPost(urlStr);
            } else if ("http".equals(url.getProtocol())) {
                return HttpClient.callHttpPost(urlStr);
            } else {
                return "";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }

}

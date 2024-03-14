/**
 * @author G-bug 2019/12/23
 */
package constant;

import java.io.File;

/**
 * @author G-bug
 * @Description 支付常量类
 * @Date 2019/12/23 15:45
 */
public class PayConstant {

    /**
     * 微信支付: 公众号 原生扫码 微信app H5
     */
    public final static String PAY_CHANNEL_WX_JSAPI = "WX_JSAPI";
    public final static String PAY_CHANNEL_WX_NATIVE = "WX_NATIVE";
    public final static String PAY_CHANNEL_WX_APP = "WX_APP";
    public final static String PAY_CHANNEL_WX_MWEB = "WX_MWEB";
    /**
     * 苹果应用内支付
     */
    public final static String PAY_CHANNEL_IAP = "IAP";

    /**
     * 支付宝: 移动支付 pc wap支付 当面付-扫码
     */
    public final static String PAY_CHANNEL_ALIPAY_MOBILE = "ALIPAY_MOBILE";
    public final static String PAY_CHANNEL_ALIPAY_PC = "ALIPAY_PC";
    public final static String PAY_CHANNEL_ALIPAY_WAP = "ALIPAY_WAP";
    public final static String PAY_CHANNEL_ALIPAY_QR = "ALIPAY_QR";

    /**
     * 渠道名称
     */
    public final static String CHANNEL_NAME_WX = "WX";
    public final static String CHANNEL_NAME_ALIPAY = "ALIPAY";

    /**
     * 本系统支付状态: 过期 失败 初始 支付中 成功 结束
     */
    public final static byte PAY_STATUS_EXPIRED = -2;
    public final static byte PAY_STATUS_FAILED = -1;
    public final static byte PAY_STATUS_INIT = 0;
    public final static byte PAY_STATUS_PAYING = 1;
    public final static byte PAY_STATUS_SUCCESS = 2;
    public final static byte PAY_STATUS_COMPLETE = 3;

    /**
     * 转帐状态: 初始 转账中 成功 失败 完成
     */
    public final static byte TRANS_STATUS_INIT = 0;
    public final static byte TRANS_STAUTS_TRANTING = 1;
    public final static byte TRANS_STATUS_SUCCESS = 2;
    public final static byte TRANS_STATUS_FAIL = 3;
    public final static byte TRANS_STATUS_COMPLETE = 4;

    /**
     * 转帐结果:
     */
    public final static byte TRANS_RESULT_INIT = 0;
    public final static byte TRANS_RESULT_REFUNDING = 1;
    public final static byte TRANS_REUSLT_SUCCESS = 2;
    public final static byte TRANS_RESULT_FAIL = 3;

    /**
     * 退款状态: 退款初始 退款中 成功 失败 完成
     */
    public final static byte REFUND_STATUS_INIT = 0;
    public final static byte REFUND_STATUS_REFUNDING = 1;
    public final static byte REFUND_STATUS_SUCCESS = 2;
    public final static byte REFUND_STATUS_FAIL = 3;
    public final static byte REFUND_STATUS_COMPLETE = 4;

    /**
     * 退款结果:
     */
    public final static byte REFUND_RESULT_INIT = 0;
    public final static byte REFUND_RESULT_REFUNDING = 1;
    public final static byte REFUND_RESULT_SUCCESS = 2;
    public final static byte REFUND_RESULT_FAIL = 3;

    /**
     * 商户通知类型: 支付订单 转账订单 退款订单
     */
    public final static String MCH_NOTIFY_TYPE_PAY = "1";
    public final static String MCH_NOTIFY_TYPE_TRANS = "2";
    public final static String MCH_NOTIFY_TYPE_REFUND = "3";

    /**
     * 商户通知状态: 通知中 成功 失败
     */
    public final static byte MCH_NOTIFY_STATUS_NOTIFYING = 1;
    public final static byte MCH_NOTIFY_STATUS_SUCCESS = 2;
    public final static byte MCH_NOTIFY_STATUS_FAIL = 3;
    /**
     * 通知使用编码
     */
    public final static String RESP_UTF8 = "UTF-8";

    /**
     * 通信状态
     */
    public static final String RETURN_PARAM_RETCODE = "retCode";
    public static final String RETURN_PARAM_RETMSG = "retMsg";
    /**
     * 真正的处理结果
     */
    public static final String RESULT_PARAM_RESCODE = "resCode";
    public static final String RESULT_PARAM_ERRCODE = "errCode";
    public static final String RESULT_PARAM_ERRCODEDES = "errCodeDes";
    public static final String RESULT_PARAM_SIGN = "sign";

    public static final String RETURN_VALUE_SUCCESS = "SUCCESS";
    public static final String RETURN_VALUE_FAIL = "FAIL";

    /**
     * 支付宝回调的响应
     */
    public static final String RETURN_ALIPAY_VALUE_SUCCESS = "success";
    public static final String RETURN_ALIPAY_VALUE_FAIL = "fail";
    /**
     * 加密格式
     */
    public static final String RETURN_ALIPAY_SIGN_CHARSET = "UTF-8";
    public static final String RETURN_ALIPAY_FORMAT = "json";
    /**
     * 加密类型(只支持RSA和RSA2 推荐RSA2)
     */
    public static final String RETURN_ALIPAY_SIGN_TYPE = "RSA2";

    /**
     * 京东支付配置文件路径
     */
    public static class JdConstant {
        public final static String CONFIG_PATH = "jd" + File.separator + "jd";
    }

    public static class WxConstant {
        public final static String TRADE_TYPE_APP = "APP";
        public final static String TRADE_TYPE_JSPAI = "JSAPI";
        public final static String TRADE_TYPE_NATIVE = "NATIVE";
        public final static String TRADE_TYPE_MWEB = "MWEB";
    }

    /**
     * 苹果应用内支付配置文件
     */
    public static class IapConstant {
        public final static String CONFIG_PATH = "iap" + File.separator + "iap";
    }

    public static class AlipayConstant {
        public final static String CONFIG_PATH = "alipay" + File.separator + "alipay";
        /**
         * 回调中的trade_status(交易状态): 等待付款 交易关闭 交易成功 交易成功已结束
         */
        public final static String TRADE_STATUS_WAIT = "WAIT_BUYER_PAY";
        public final static String TRADE_STATUS_CLOSED = "TRADE_CLOSED";
        public final static String TRADE_STATUS_SUCCESS = "TRAD_SUCCESS";
        public final static String TRADE_STATUS_FINISHED = "TRADE_FINISHED";
    }

    public static final String NOTIFY_BUSI_PAY = "NOTIFY_VV_PAY_RES";
    public static final String NOTIFY_BUSI_TRANS = "NOTIFY_VV_TRANS_RES";

    /**
     * 商户通知成功输出串
     */
    public static final String PAY_NOTIFY_RETURN_STR = "success";

    /**
     * 商户通知最大次数
     */
    public static final int PAY_NOTIFY_MAX_COUNT = 5;
}

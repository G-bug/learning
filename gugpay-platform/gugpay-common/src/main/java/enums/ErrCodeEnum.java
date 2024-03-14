/**
 * @author G-bug 2019/12/27
 */
package enums;

/**
 * @author G-bug
 * @Description
 * @Date 2019/12/27 13:56
 */
public enum ErrCodeEnum {

    // 错误
    ERR_0001("0001", "商户签名异常"),

    ERR_0010("0010", "系统错误"),
    ERR_0011("0011", "请使用post方法"),
    ERR_0012("0012", "post数据为空"),
    ERR_0013("0013", "签名错误"),
    ERR_0014("0014", "参数错误"),
    ERR_0015("0015", "商户不存在"),
    ERR_0110("0110", "第三方超时"),
    ERR_0111("0111", "第三方异常"),
    ERR_0112("0112", "订单不存在"),
    ERR_0113("0113", "订单已支付"),
    ERR_0114("0114", "商品不存在"),
    ERR_0115("0115", "价格不对"),
    ERR_0116("0116", "物品数量不对"),
    ERR_0117("0117", "过程返回255"),
    ERR_0118("0118", "DB错误");

    ErrCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

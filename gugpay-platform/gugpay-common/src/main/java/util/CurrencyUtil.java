/**
 * @author G-bug 2020/1/3
 */
package util;

import java.text.DecimalFormat;
import java.text.FieldPosition;

/**
 * @author G-bug
 * @Description 货币处理
 * @Date 2020/1/3 9:18
 */
public class CurrencyUtil {

    /**
     * 元转分
     */
    public static String dollarToCent(String dollar) {
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuffer sb = df.format(Double.parseDouble(dollar), new StringBuffer(), new FieldPosition(0));

        int idx = sb.toString().indexOf(".");
        sb.deleteCharAt(idx);

        for (; sb.length() != 1; ) {
            if (sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 分转元
     */
    public static String centToDollar(String cent) {
        if (cent == null || "".equals(cent)) {
            return "";
        }

        // 删除 + 号
        if (cent.charAt(0) == '+') {
            cent = cent.substring(1);
        }

        // 负数
        boolean negative = false;
        long l = Long.parseLong(cent);
        if (l < 0) {
            negative = true;
            // 去负号
            l = Math.abs(l);
        }

        cent = Long.toString(l);
        if (cent.length() == 1) {
            cent = "0.0" + cent;
        } else if (cent.length() == 2) {
            cent = "0." + cent;
        } else {
            cent = cent.substring(0, cent.length() - 2) + "." + cent.substring(cent.length() - 2);
        }

        return cent + (negative ? "-" : "");
    }

    /**
     * 分转元: 若没有小数部分则省去
     */
    public static String centToShortDollar(String cent) {
        String dollar = Double.parseDouble(centToDollar(cent)) + "";

        if (dollar.endsWith(".0")) {
            return dollar.substring(0, dollar.length() - 2);
        } else {
            return dollar;
        }
    }
}

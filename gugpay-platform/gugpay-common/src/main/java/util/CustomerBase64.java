/**
 * @author G-bug 2019/12/12
 */
package util;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author G-bug
 * @Description 自定义 base64 处理类
 * @Date 2019/12/13 11:11
 */
public class CustomerBase64 {

    private static final byte CODE_UPPER = 26;
    private static final byte CODE_LOWER = 52;
    private static final byte CODE_PLUS = 62;
    private static final byte CODE_COUNT = 63;
    private static final byte UNIT_BASE64 = 4;
    private static final byte UNIT_ASCII = 3;

    public static String decode(String str) {
        if (str == null) {
            return null;
        }

        if ((str = str.replaceAll("\\s", "")).length() == 0) {
            return "";
        }

        return new String(Base64.getDecoder().decode(str));
    }

    public static String encode(String src) {
        return Base64.getEncoder().encodeToString(src.getBytes());
    }

    /**
     * 如果 startIdx + srcLen > src.length, 则取src.length-startIdx
     */
    public static String encode(byte[] src, int startIdx, int srcLen) {
        if (src == null) {
            return null;
        }
        if (startIdx + srcLen > src.length) {
            srcLen = src.length - startIdx;
        }

        byte[] src2 = new byte[srcLen];
        System.arraycopy(src, startIdx, src2, 0, srcLen);

        return encode(src2);
    }

    public static String encode(byte[] src) {
        return encode(src, src.length);
    }

    public static String encode(byte[] src, int srcLen) {
        if (src == null) {
            return null;
        }

        srcLen = Math.min(srcLen, src.length);
        // 补全3个字节
        byte[] temp = new byte[srcLen + 2];
        System.arraycopy(src, 0, temp, 0, srcLen);

        // 8 * unit_ascii = 6 * unit_base64, & 优先级大于 |
        byte[] dest = new byte[(temp.length / UNIT_ASCII) * UNIT_BASE64];
        for (int i = 0, j = 0; j < srcLen; j += UNIT_ASCII, i += UNIT_BASE64) {
            // x^8 -> 00xxxxxx
            dest[i] = (byte) (temp[j] >>> 2);
            // (x^8 -> 00xx0000) | (x^8 -> 0000xxxx)
            dest[i + 1] = (byte) ((temp[j + 1] >>> 4) | (temp[j] << 4) & 0b111111);
            // x^8 -> xxxxxx00 | x^8 -> 000000xx -> 00xxxxxx
            dest[i + 2] = (byte) ((temp[j + 2] >>> 6) | (temp[j + 1] << 2) & 0b111111);
            // x^8 -> 00xxxxxx
            dest[i + 3] = (byte) (temp[j + 2] & 0b111111);
        }

        // base -> ascii conversion
        for (int i = 0; i < dest.length; i++) {
            if (dest[i] < CODE_UPPER) {
                dest[i] = (byte) (dest[i] + 'A');
            } else if (dest[i] < CODE_LOWER) {
                dest[i] = (byte) (dest[i] - 26 + 'a');
            } else if (dest[i] < CODE_PLUS) {
                dest[i] = (byte) (dest[i] - 52 + '0');
            } else if (dest[i] < CODE_COUNT) {
                dest[i] = (byte) '+';
            } else {
                dest[i] = (byte) '/';
            }
        }

        // 落单字符转为'='
        for (int i = dest.length - 1; i > (srcLen * UNIT_BASE64) / UNIT_ASCII; i--) {
            dest[i] = (byte) '=';
        }

        return new String(dest);
    }

    public static String encode_64(byte[] bin) throws Exception {
        String b64 = encode(bin);
        StringBuilder sb = new StringBuilder();
        for (int offset = 0; offset < b64.length(); offset += 64) {
            int idxBegin = offset;
            int idxEnd = Math.min(offset += 64, b64.length());
            String s = b64.substring(idxBegin, idxEnd);
            sb.append(s).append('\n');
        }

        b64 = sb.toString();
        return b64;
    }

    private static byte[] decode(byte[] src) {
        int tail = src.length;
        // = 去除
        while (src[tail - 1] == '=') {
            tail--;
        }

        byte[] dest = new byte[tail - src.length / 4];
        for (int i = 0; i < src.length; i++) {
            src[i] = base64ToAscii(src[i]);
        }

        int i, j;
        for (i = 0, j = 0; j < dest.length - 2; i += UNIT_BASE64, j += UNIT_ASCII) {
            dest[j] = (byte) (((src[i] << 2) & 0b11111111) | ((src[i + 1] >>> 4) & 3));
            dest[j + 1] = (byte) (((src[i + 1] << 4) & 0b11111111) | ((src[i + 2] >>> 2) & 0b1111));
            dest[j + 2] = (byte) (((src[i + 2] << 6) & 0b11111111) | (src[i + 3] & 0b111111));
        }

        if (j < dest.length) {
            dest[j] = (byte) (((src[i] << 2) & 0b11111111) | ((src[i + 1] >>> 4) & 3));
        }

        if (++j < dest.length) {
            dest[j] = (byte) (((src[i + 1] << 4) & 0b11111111) | ((src[i + 2] >>> 2) & 0b1111));
        }

        return dest;
    }

    private static byte base64ToAscii(byte src) {
        byte dest = 0;
        if (src >= 'A' && src <= 'Z') {
            dest = (byte) (src - 'A');
        } else if (src >= 'a' && src <= 'z') {
            dest = (byte) (src - ('a' - 26));
        } else if (src >= '0' && src <= '9') {
            dest = (byte) (src - ('0' - 52));
        } else if (src == '+') {
            dest = 62;
        } else if (src == '/') {
            dest = 63;
        } else if (src == '=') {
            dest = 0;
        }
        return dest;
    }

    public static void main(String[] args) {
        System.out.print(Base64.getEncoder().encodeToString("{mchId:2}".getBytes()));
    }

}

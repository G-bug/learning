/**
 * @author G-bug 2019/12/26
 */
package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author G-bug
 * @Description
 * @Date 2019/12/26 15:52
 */
public class DigestUtil {

    private static final Logger log = LoggerFactory.getLogger(DigestUtil.class);
    private static final String ENCODING_CHARSET = "UTF-8";

    public static String hmacSign(String aKey, String aValue) {
        byte[] k_ipad = new byte[64];
        byte[] k_opad = new byte[64];
        byte[] keyb;
        byte[] value;
        try {
            keyb = aKey.getBytes(ENCODING_CHARSET);
            value = aValue.getBytes(ENCODING_CHARSET);
        } catch (UnsupportedEncodingException e) {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }

        Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
        Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte) (keyb[i] ^ 0b110110);
            k_opad[i] = (byte) (keyb[i] ^ 0b1011110);
        }

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        md.update(k_ipad);
        md.update(value);
        byte dg[] = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        return toHex(dg);
    }

    public static String toHex(byte input[]) {
        if (input == null) {
            return null;
        }

        StringBuffer output = new StringBuffer(input.length * 2);
        for (byte i : input) {
            int current = i & 0b11111111;
            if (current < 16) {
                output.append("0");
            }
            output.append(Integer.toString(current, 16));
        }
        return output.toString();
    }

    public static String getHmac(String[] args, String key) {
        if (args == null || args.length == 0) {
            return null;
        }
        StringBuffer str = new StringBuffer();
        for (String arg : args) {
            str.append(arg);
        }
        return hmacSign(str.toString(), key);
    }

    public static String digest(String aValue) {
        aValue = aValue.trim();
        byte[] value;
        try {
            value = aValue.getBytes(ENCODING_CHARSET);
        } catch (UnsupportedEncodingException e) {
            value = aValue.getBytes();
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return toHex(md.digest(value));
    }

    public static String md5(String value, String charset) {
        MessageDigest md;
        try {
            byte[] data = value.getBytes(charset);
            md = MessageDigest.getInstance("MD5");
            byte[] digestData = md.digest(data);
            return toHex(digestData);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getSign(Object o, String key) throws IllegalAccessException {
        if (o instanceof Map) {
            return getSign((Map<String, Object>) o, key);
        }

        ArrayList<String> list = new ArrayList<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && !"".equals(f.get(o))) {
                list.add(f.getName() + "=" + f.get(o) + "&");
            }
        }

        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }

        String result = sb.toString();
        result += "key=" + key;
        log.debug("sign before md5:" + result);
        result = Objects.requireNonNull(md5(result, ENCODING_CHARSET)).toUpperCase();
        log.debug("sign result:", result);
        return result;
    }

    public static String getSign(Map<String, Object> map, String key) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null && !"".equals(entry.getValue())) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }

        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (String str : arrayToSort) {
            sb.append(str);
        }
        String result = sb.toString() + "key=" + key;
        log.debug("sign before md5:" + result);
        result = md5(result, ENCODING_CHARSET).toUpperCase();
        log.debug("sign result:" + result);
        return result;
    }

    public static String getSign(Map<String, Object> map, String key, String... notContains) {
        Map<String, Object> newMap = new HashMap<>(32);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            boolean isContain = false;
            for (String str : notContains) {
                if (entry.getKey().equals(str)) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }
        return getSign(newMap, key);
    }

}

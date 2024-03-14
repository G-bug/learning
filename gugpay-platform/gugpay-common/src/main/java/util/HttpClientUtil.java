/**
 * @author G-bug 2019/12/27
 */
package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G-bug
 * @Description 网络模块工具
 * @Date 2019/12/27 17:02
 */
public class HttpClientUtil {

    public static final String SunX509 = "SunX509";
    public static final String JKS = "JKS";
    public static final String PKCS12 = "PKCS12";
    public static final String TLS = "TLS";

    private static final String encoding = "UTF-8";

    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    public static HttpURLConnection getHttpURLConnection(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        return httpURLConnection;
    }

    public static HttpsURLConnection getHttpsURLConnection(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        return httpsURLConnection;
    }

    public static String getURL(String strUrl) {
        if (strUrl != null) {
            int indexOf = strUrl.indexOf("?");
            if (-1 != indexOf) {
                return strUrl.substring(0, indexOf);
            }
            return strUrl;
        }

        return null;
    }

    public static String getQueryString(String strUrl) {
        if (strUrl != null) {
            int indexOf = strUrl.indexOf("?");
            if (indexOf != -1) {
                return strUrl.substring(indexOf + 1, strUrl.length());
            }
            return "";
        }

        return null;
    }

    public static Map queryStringMap(String queryString) {
        if (queryString == null || "".equals(queryString)) {
            return null;
        }

        Map<String, String> map = new HashMap();
        String[] queryArray = queryString.split("&");
        for (String pair : queryArray) {
            if (pair != null && !"".equals(pair)) {
                String[] pairArray = pair.split("=");
                if (pairArray.length == 2) {
                    map.put(pairArray[0], pairArray[1]);
                } else {
                    map.put(pair, "");
                }
            }
        }

        return map;
    }

    public static String bufferedReaderString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\r\n");
        }

        return sb.toString();
    }

    public static void doOutput(OutputStream out, byte[] data, int len) throws IOException {
        int dataLen = data.length;
        int off = 0;

        while (off < data.length) {
            if (len >= dataLen) {
                out.write(data, off, dataLen);
                off += dataLen;
            } else {
                out.write(data, off, len);
                off += len;
                dataLen -= len;
            }

            out.flush();
        }
    }

    public static SSLContext getSSLContext(FileInputStream trustFileInputStream, String trustPassword,
                                           FileInputStream keyFileInputStream, String keyPassword)
            throws NoSuchAlgorithmException, KeyStoreException, CertificateException,
            IOException, UnrecoverableKeyException, KeyManagementException {

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(HttpClientUtil.SunX509);
        KeyStore trustKeyStore = KeyStore.getInstance(HttpClientUtil.SunX509);
        trustKeyStore.load(trustFileInputStream, HttpClientUtil.strCharArray(trustPassword));
        tmf.init(trustKeyStore);

        final char[] kp = HttpClientUtil.strCharArray(keyPassword);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(HttpClientUtil.SunX509);
        KeyStore ks = KeyStore.getInstance(HttpClientUtil.PKCS12);
        ks.load(keyFileInputStream, kp);
        kmf.init(ks, kp);

        SecureRandom rand = new SecureRandom();
        SSLContext ctx = SSLContext.getInstance(HttpClientUtil.TLS);
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);

        return ctx;
    }

    public static java.security.cert.Certificate getCertificate(File caFile) throws IOException, CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream(caFile);
        java.security.cert.Certificate cert = cf.generateCertificate(in);
        in.close();
        return cert;
    }

    public static char[] strCharArray(String str) {
        if (null == str) {
            return null;
        }

        return str.toCharArray();
    }

    public static void storeCACert(Certificate cert, String alias, String password, OutputStream out)
            throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(null, null);
        ks.setCertificateEntry(alias, cert);
        ks.store(out, HttpClientUtil.strCharArray(password));
    }

    public static InputStream stringInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    public static byte[] inputStreamToByte(InputStream in) throws IOException {
        int BUFFER_SIZE = 4096;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;

        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
            outStream.write(data, 0, count);
        }

        data = null;
        byte[] outByte = outStream.toByteArray();
        outStream.close();

        return outByte;
    }

    public static String inputStreamToString(InputStream in, String encoding) throws IOException {
        return new String(inputStreamToByte(in), encoding);
    }
}


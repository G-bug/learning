/**
 * @author G-bug 2019/12/30
 */
package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @auth Administrator
 */
public class HttpClient {
    private static String USER_AGENT_VALUE = "Mozilla/4.0 (compatiable; MISE 6.0; Windows XP)";

    private static final String JKS_CA_FILENAME = "tnpay_cacert.jks";

    private static final String jks_ca_alias = "tenpay";

    private static final String jks_ca_password = "";

    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * CA证书
     */
    private File caFile;

    /**
     * 密钥文件
     */
    private File certFile;

    /**
     * 密钥
     */
    private String certPassword;

    private String reqContent;

    private String resContent;

    private String method;

    private String errInfo;

    private int timeout;

    private int responseCode;

    private String charset;

    private InputStream inputStream;

    public HttpClient() {
        this.caFile = null;
        this.certFile = null;
        this.certPassword = "";

        this.reqContent = "";
        this.resContent = "";
        this.method = "POST";
        this.errInfo = "";
        this.timeout = 30;
        this.responseCode = 0;
        this.charset = "UTF-8";
        this.inputStream = null;
    }

    public HttpClient(String url, String method, int timeout, String charset) {
        this.caFile = null;
        this.certFile = null;
        this.certPassword = "";

        this.reqContent = "";
        this.resContent = "";
        this.method = method;
        this.errInfo = "";
        this.timeout = timeout;
        this.responseCode = 0;
        this.charset = charset;
        this.inputStream = null;
    }

    public void setCertInfo(File certFile, String certPassword) {
        this.certFile = certFile;
        this.certPassword = certPassword;
    }

    public void setCaInfo(File caFile) {
        this.caFile = caFile;
    }

    public void setReqContent(String reqContent) {
        this.reqContent = reqContent;
    }

    public String getResContent() {
        try {
            this.doResponse();
        } catch (IOException e) {
            log.error("resContent error:", e);
            this.errInfo = e.getMessage();
        }
        return this.resContent;
    }

    public boolean call() {
        boolean isRet = false;

        if (null == this.caFile && null == this.certFile) {
            try {
                this.callHttp();
                isRet = true;
            } catch (Exception e) {
                log.error("", e);
                this.errInfo = e.getMessage();
            }
            return isRet;
        }

        return calls();
    }

    public boolean calls() {
        boolean isSuccess = false;
        try {
            this.callHttps();
            isSuccess = true;
        } catch (Exception e) {
            this.errInfo = e.getMessage();
        }
        return isSuccess;
    }

    protected void callHttp() throws IOException {
        if ("POST".equals(this.method.toUpperCase())) {
            String url = HttpClientUtil.getURL(this.reqContent);
            String queryString = HttpClientUtil.getQueryString(this.reqContent);
            byte[] postData = queryString.getBytes(this.charset);
            this.httpPostMethod(url, postData);
            return;
        }
        this.httpGetMethod(this.reqContent);
    }

    protected void callHttps() throws IOException,
            NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, null, new SecureRandom());

        if ("POST".equals(this.method.toUpperCase())) {
            String url = HttpClientUtil.getURL(this.reqContent);
            String queryString = HttpClientUtil.getQueryString(this.reqContent);
            byte[] postData = queryString.getBytes(this.charset);

            this.httpsPostMethod(url, postData, sslContext);
            return;
        }
        this.httpsGetMethod(this.reqContent, sslContext);
    }

    protected void httpPostMethod(String url, byte[] postData) throws IOException {
        HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);
        this.doPost(conn, postData);
    }

    protected void httpGetMethod(String url, byte[] postData) throws IOException {
        HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);
        this.doPost(conn, postData);
    }

    protected void httpGetMethod(String url) throws IOException {
        HttpURLConnection httpConnection = HttpClientUtil.getHttpURLConnection(url);
        this.setHttpRequest(httpConnection);

        httpConnection.setRequestMethod("GET");

        this.responseCode = httpConnection.getResponseCode();
        this.inputStream = httpConnection.getInputStream();
    }

    protected void httpsGetMethod(String url, SSLContext sslContext) throws IOException {
        SSLSocketFactory sf = sslContext.getSocketFactory();
        HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);
        conn.setSSLSocketFactory(sf);

        this.doGet(conn);
    }

    protected void doGet(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod("GET");
        this.setHttpRequest(connection);
        this.responseCode = connection.getResponseCode();
        this.inputStream = connection.getInputStream();
    }

    protected void httpsPostMethod(String url, byte[] postData, SSLContext sslContext) throws IOException {
        SSLSocketFactory sf = sslContext.getSocketFactory();
        HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);
        conn.setSSLSocketFactory(sf);
        this.doPost(conn, postData);
    }

    protected void setHttpRequest(HttpURLConnection httpConnection) {
        httpConnection.setConnectTimeout(this.timeout * 1000);
        httpConnection.setRequestProperty("User-Agent", HttpClient.USER_AGENT_VALUE);
        httpConnection.setUseCaches(false);
        httpConnection.setDoInput(true);
        httpConnection.setDoOutput(true);
    }

    protected void doResponse() throws IOException {
        if (null == this.inputStream) {
            return;
        }
        this.resContent = HttpClientUtil.inputStreamToString(this.inputStream, this.charset);
        this.inputStream.close();
    }

    protected void doPost(HttpURLConnection conn, byte[] postData) throws IOException {
        conn.setRequestMethod("POST");
        this.setHttpRequest(conn);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());

        final int len = 1024;
        HttpClientUtil.doOutput(out, postData, len);
        out.close();

        this.responseCode = conn.getResponseCode();
        this.inputStream = conn.getInputStream();
    }

    public static String callHttpPost(String url) {
        return callHttpPost(url, 60);
    }

    public static String callHttpPost(String url, int connectTimeout) {
        return callHttpPost(url, connectTimeout, "UTF-8");
    }

    public static String callHttpPost(String url, int connectTimeout, String encode) {
        HttpClient client = new HttpClient(url, "POST", connectTimeout, encode);
        client.call();
        return client.getResContent();
    }

    public static String callHttpsPost(String url) {
        HttpClient client = new HttpClient(url, "POST", 60, "UTF-8");
        client.calls();
        return client.getResContent();
    }
}

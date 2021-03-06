package com.gugpay.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author G-bug
 * @Description 路由目标服务返回
 * @Date 2020/1/14 19:11
 */

@Component
public class ServiceFallback implements FallbackProvider {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getRoute() {
        return "gugpay-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

        if (cause != null && cause.getCause() != null) {
            log.error("zuul exception", cause);
        }

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() {

                String body = "The service is unavailable, [" + cause.getMessage() + "]";

                return new ByteArrayInputStream(body.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}

/**
 * @author G-bug 2019/11/13
 */
package com.learn.config;

import org.springframework.http.*;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.tuple.JsonStringToTupleConverter;
import org.springframework.web.client.RestTemplate;

public class RestUtils {

    static RestTemplate template = new RestTemplate();
    static HttpHeaders headers = new HttpHeaders();

    static {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    static HttpEntity<String> entity = new HttpEntity<>(headers);

    private static void getHello() {
        template = new RestTemplate();
        String rep = template.getForObject("http://localhost:8002/hello", String.class);
        System.out.println(rep);
    }

    // 单位个客户端的更新(可配合Webhook使用)
    private static void refreshClient() {
        ResponseEntity response = template.exchange("http://localhost:8002/refresh", HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());  // 有改变 则输出 key, 否则输出 []
    }

    // Config Client端refresh实现全部Client自动更新
    private static void busClientRefresh() {
        ResponseEntity resp = template.exchange("http://localhost:8002/bus-refresh", HttpMethod.POST, entity, String.class);
        System.out.println(resp.getBody());
    }

    // Config Server端refresh实现客端自动更新
    private static void busServerRefresh() {
        ResponseEntity resp = template.exchange("http://localhost:8001/bus-refresh", HttpMethod.POST, entity, String.class);
        System.out.println(resp.getBody());
    }

    // 链路跟踪
    private static void busServerTrace() {
        String resp = template.getForObject("http://localhost:8001/httptrace", String.class);
        System.out.println(resp);
    }

    public static void main(String[] args) {
        busServerTrace();
    }

}

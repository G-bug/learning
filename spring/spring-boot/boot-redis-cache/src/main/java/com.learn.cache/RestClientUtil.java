/**
 * @author G-bug 2019/10/12
 */
package com.learn.cache;

import com.learn.cache.dao.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @auth Administrator
 */
public class RestClientUtil {

    private void getUser(int id) {

        // 请求模板对象
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8080/users/{id}";
        // 建立请求头->设置请求内容类型
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Http实体对头信息包装
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // 请求对象发起请求->对响应实体包装 -> 解析结果
        ResponseEntity<User> resultEntity = restTemplate.exchange(url, HttpMethod.GET, entity, User.class, id);
        User user = resultEntity.getBody();

        System.out.println(user);
    }

    private void addUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8080/users/user";

        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        URI uri = restTemplate.postForLocation(url, entity);

        System.out.println(uri.getPath());
    }

    private void delUser(long id) {
        RestTemplate template = new RestTemplate();
        String url = "http://127.0.0.1:8080/users/del/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        template.exchange(url, HttpMethod.DELETE, entity, Void.class, id);
    }

    private void getUsers() {
        RestTemplate template = new RestTemplate();
        String url = "http://127.0.0.1:8080/users/all";
        ResponseEntity<List> response = template.getForEntity(url, List.class);
        System.out.println();
    }

    public static void main(String[] args) {
        RestClientUtil util = new RestClientUtil();
        //util.getUser(2);
        util.getUsers();
        //User user = new User(6, "admin0", "admin0");util.addUser(user);
    }

}

/**
 * @author G-bug 2019/10/15
 */
package com.learn.hibernte;

import com.learn.hibernte.dao.entity.Article;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * @auth Administrator
 */
public class HibernateUtil {


    private void getArticle(int id) {

        String url = "http://127.0.0.1:8080/article/{id}";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Article> response = template.exchange(url, HttpMethod.GET, entity, Article.class, id);
        System.out.println((response.getBody()).toString());
    }

    public static void main(String[] args) {
        new HibernateUtil().getArticle(1);
    }

}

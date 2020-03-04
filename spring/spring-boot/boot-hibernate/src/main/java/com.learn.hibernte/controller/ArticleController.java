/**
 * @author G-bug 2019/10/15
 */
package com.learn.hibernte.controller;

import com.learn.hibernte.dao.entity.Article;
import com.learn.hibernte.dao.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auth Administrator
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService service;


    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.getArticleById(id), HttpStatus.OK);
    }

}

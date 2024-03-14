/**
 * @author G-bug 2019/10/15
 */
package com.learn.hibernte.dao.service;

import com.learn.hibernte.dao.entity.Article;
import com.learn.hibernte.dao.repository.impl.ImplArticleDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auth Administrator
 */

@Service
public class ArticleService {

    @Resource
    private ImplArticleDao articleDao;

    public Article getArticleById(int articleId) {
        Article obj = articleDao.getArticleById(articleId);
        return obj;
    }

}

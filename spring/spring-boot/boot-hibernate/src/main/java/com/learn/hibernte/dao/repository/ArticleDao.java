/**
 * @author G-bug 2019/10/15
 */
package com.learn.hibernte.dao.repository;

import com.learn.hibernte.dao.entity.Article;

import java.util.List;

/**
 * @auth Administrator
 */
public interface ArticleDao {

    List<Article> getAllArticles();

    Article getArticleById(int articleId);

    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(int articleId);

    boolean articleExists(String title, String category);
}

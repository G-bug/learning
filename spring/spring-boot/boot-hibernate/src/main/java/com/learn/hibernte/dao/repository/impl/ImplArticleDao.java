/**
 * @author G-bug 2019/10/15
 */
package com.learn.hibernte.dao.repository.impl;

import com.learn.hibernte.dao.entity.Article;
import com.learn.hibernte.dao.repository.ArticleDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @auth Administrator
 */
@Repository
public class ImplArticleDao implements ArticleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Article getArticleById(int articleId) {
        return entityManager.find(Article.class, articleId);
    }

    @Override
    public List<Article> getAllArticles() {
        String hq0l = "from Article a order by a.articleId ";
        return (List<Article>) entityManager.createQuery(hq0l).getResultList();
    }

    @Override
    public void addArticle(Article article) {
        entityManager.persist(article);
    }

    @Override
    public void updateArticle(Article article) {
        Article article1 = getArticleById(article.getArticleId());
        article1.setCategory(article.getCategory());
        article1.setTitle(article.getTitle());
        entityManager.flush();
    }

    @Override
    public void deleteArticle(int articleId) {
        entityManager.remove(getArticleById(articleId));
    }

    @Override
    public boolean articleExists(String title, String category) {
        String hql = "FROM Article atcl WHERE atcl.tile = ?1 AND atcl.category = ?2";
        int count = entityManager.createQuery(hql)
                .setParameter(1, title)
                .setParameter(2, category).getResultList().size();
        return count > 0;
    }
}

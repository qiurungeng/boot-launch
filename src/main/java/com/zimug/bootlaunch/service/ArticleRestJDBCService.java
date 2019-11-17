package com.zimug.bootlaunch.service;

import com.zimug.bootlaunch.dao.ArticleJDBCDAO;
import com.zimug.bootlaunch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ArticleRestJDBCService implements ArticleRestService{

    @Resource
    private
    ArticleJDBCDAO articleJDBCDAO;

    @Resource
    private JdbcTemplate primaryJdbcTemplate;

    @Resource
    private JdbcTemplate secondaryJdbcTemplate;

    @Transactional
    public Article saveArticle(Article article) {
        articleJDBCDAO.save(article,primaryJdbcTemplate);
        articleJDBCDAO.save(article,secondaryJdbcTemplate);
        //int a = 2/0；  //人为制造一个异常，用于测试事务
        return article;
    }

    public void deleteArticle(Long id){
        articleJDBCDAO.deleteById(id,primaryJdbcTemplate);
    }

    public void updateArticle(Article article){
        articleJDBCDAO.updateById(article,primaryJdbcTemplate);
    }

    public Article getArticle(Long id){
        return articleJDBCDAO.findById(id,primaryJdbcTemplate);
    }

    public List<Article> getAll(){
        return articleJDBCDAO.findAll(primaryJdbcTemplate);
    }
}

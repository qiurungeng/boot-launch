package com.zimug.bootlaunch.service;


import com.zimug.bootlaunch.jpa.testdb.Article;
import com.zimug.bootlaunch.jpa.testdb.ArticleRepository;
import com.zimug.bootlaunch.jpa.testdb2.Message;
import com.zimug.bootlaunch.jpa.testdb2.MessageRepository;
import com.zimug.bootlaunch.model.ArticleVO;
import com.zimug.bootlaunch.utils.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ArticleRestJPAServiceImpl implements ArticleRestService{

    //将JPA仓库对象注入
    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private MessageRepository messageRepository;

    @Resource
    private Mapper dozerMapper;

    public ArticleVO saveArticle(ArticleVO article) {

        Article articlePO = dozerMapper.map(article,Article.class);
        articleRepository.save(articlePO);    //保存一个对象到数据库，insert
        Message message=new Message();
        message.setName("kobe");
        message.setContent("科比退役了");
        messageRepository.save(message);
        return  article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);   //根据id删除1条数据库记录
    }

    @Override
    public void updateArticle(ArticleVO article) {
        Article articlePO = dozerMapper.map(article,Article.class);
        articleRepository.save(articlePO);   //更新一个对象到数据库，仍然使用save方法
    }

    @Override
    public ArticleVO getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);  //根据id查找一条数据
        //把读者查出来
        ArticleVO articleVO=dozerMapper.map(article.get(),ArticleVO.class);
        return dozerMapper.map(article.get(),ArticleVO.class);
    }

    @Override
    public List<ArticleVO> getAll() {
        List<Article> articleLis = articleRepository.findAll();  //查询article表的所有数据
        return DozerUtils.mapList(articleLis,ArticleVO.class);
    }

}

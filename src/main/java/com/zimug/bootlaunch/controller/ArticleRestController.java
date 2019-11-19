package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.model.AjaxResponse;
import com.zimug.bootlaunch.model.ArticleVO;
import com.zimug.bootlaunch.service.ArticleRestJPAServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@Controller
@RequestMapping("/rest")
public class ArticleRestController {

    @Resource
    ArticleRestJPAServiceImpl articleRestJPAServiceImpl;

 
    //@RequestMapping(value = "/article", method = POST, produces = "application/json")
    @PostMapping("/article")
    public @ResponseBody  AjaxResponse saveArticle(@RequestBody ArticleVO article) {
    /*public @ResponseBody  AjaxResponse saveArticle(@RequestParam String  id,
                                                   @RequestParam String  author) {*/

        log.info("saveArticle：{}",article);

        log.info("articleRestService return :" + articleRestJPAServiceImpl.saveArticle(article));

        return  AjaxResponse.success(article);
    }
 
    //@RequestMapping(value = "/article/{id}", method = DELETE, produces = "application/json")
    @DeleteMapping("/article/{id}")
    public @ResponseBody AjaxResponse deleteArticle(@PathVariable Long id) {

        articleRestJPAServiceImpl.deleteArticle(id);
        return AjaxResponse.success(id);
    }
 
    //@RequestMapping(value = "/article/{id}", method = PUT, produces = "application/json")
    @PutMapping("/article/{id}")
    public @ResponseBody AjaxResponse updateArticle(@PathVariable Long id, @RequestBody ArticleVO article) {
        article.setId(id);

        articleRestJPAServiceImpl.updateArticle(article);
        return AjaxResponse.success(article);
    }
 
    //@RequestMapping(value = "/article/{id}", method = GET, produces = "application/json")
    @GetMapping( "/article/{id}")
    public @ResponseBody  AjaxResponse getArticle(@PathVariable Long id) {
        return AjaxResponse.success(articleRestJPAServiceImpl.getArticle(id));
    }

    @GetMapping( "/article")
    public @ResponseBody  AjaxResponse getAll() {
        return AjaxResponse.success(articleRestJPAServiceImpl.getAll());
    }
}
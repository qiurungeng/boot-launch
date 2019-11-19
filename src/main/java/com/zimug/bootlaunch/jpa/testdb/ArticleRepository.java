package com.zimug.bootlaunch.jpa.testdb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    //注意这个方法的名称，jPA会根据方法名自动生成SQL执行
    List<Article> findByAuthor(String author);
}
package com.visa.prj.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.visa.prj.entity.Article;

public class ArticleClient {

    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("NoDBA",561,"bliki",Arrays.asList("nosql","people","orm")));
        articles.add(new Article("Infodesk",1145,"bliki",Arrays.asList("nosql","writing")));
        articles.add(new Article("OrmHate",1718,"bliki",Arrays.asList("nosql","orm")));
        articles.add(new Article("ruby",1313,"article",Arrays.asList("ruby")));
        articles.add(new Article("DDD_Aggregate",482,"bliki",Arrays.asList("nosql","ddd")));
        
        printTotalWordCount(articles); // 5219
        printArticlesByType(articles); //{bliki:4, article: 1}
        
        // use flatMap, Function.identity and groupingby and Collectors.counting to solve this
        printTagCount(articles); // nosql: 4, ruby : 1, 
    }

    private static void printTagCount(List<Article> articles) {
      //Map<Object, List<String>> =   
    }
        

    private static void printArticlesByType(List<Article> articles) {
         Map<String, List<Article>> catmap= articles.stream().collect(Collectors.groupingBy(p->p.getType()));
         for(Map.Entry<String, List<Article>> entry: catmap.entrySet()) {
             System.out.println(entry.getKey() + " " + entry.getValue().size());
         }
    }

    private static void printTotalWordCount(List<Article> articles) {
        int sum=0;
        for(Article p: articles) {
             sum+= p.getWords();
        }
        System.out.println(sum);
        
    }

}

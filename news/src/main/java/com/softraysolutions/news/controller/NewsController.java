package com.softraysolutions.news.controller;

import com.softraysolutions.news.model.News;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsController {

    @GetMapping("/api/news/{id}")
    public String getNews(@PathVariable long id) {
        return String.valueOf(id);
    }

    @GetMapping("/api/news/admin/{searchParam}")
    public String filterAdminNews(@PathVariable String searchParam) {
        return searchParam;
    }

    @GetMapping("/api/news/public/{searchParam}")
    public String filterAllNews(@PathVariable String searchParam) {
        return searchParam;
    }

    @PostMapping("/api/news")
    public String save(@RequestBody News news){
        return news.toString();
    }

    @PutMapping("/api/news/{id}")
    public String update(@PathVariable long id) {
        return String.valueOf(id);
    }


}

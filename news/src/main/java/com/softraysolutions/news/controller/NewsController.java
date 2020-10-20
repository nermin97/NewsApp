package com.softraysolutions.news.controller;

import com.softraysolutions.news.model.News;
import com.softraysolutions.news.model.User;
import com.softraysolutions.news.service.NewsService;
import com.softraysolutions.news.service.UserService;
import com.softraysolutions.news.util.Helper;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class NewsController {

    private final NewsService newsService;
    private final UserService userService;

    public NewsController (NewsService service, UserService userService) {
        this.newsService = service;
        this.userService = userService;
    }

    @GetMapping("/api/news/{id}")
    public News getNews(@PathVariable long id) {
        return newsService.getById(id);
    }

    @GetMapping("/api/news/admin/{searchParam}")
    public List<News> filterAdminNews(@PathVariable String searchParam) {
        return newsService.searchAdmin((User) userService.loadUserByUsername(Helper.getCurrentLoggedUser()), searchParam);
    }

    @GetMapping("/api/news/public/{searchParam}")
    public List<News> filterAllNews(@PathVariable String searchParam) {
        return newsService.searchPublic(searchParam);
    }

    @PostMapping("/api/news")
    public News save(@RequestBody News news){ ;
        User user = getUser();
        news.setCreatedBy(user);
        news.setEditedBy(user.getUsername());
        news.setCreationDate(new Date());
        return newsService.save(news);
    }

    @PutMapping("/api/news/{id}")
    public News update(@PathVariable long id, @RequestBody News body) {
        User user = getUser();
        body.setId(id);
        body.setEditedBy(user.getUsername());
        return newsService.update(body);
    }

    private User getUser() {
        return (User) userService.loadUserByUsername(Helper.getCurrentLoggedUser());
    }


}

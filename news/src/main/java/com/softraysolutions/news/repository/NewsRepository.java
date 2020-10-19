package com.softraysolutions.news.repository;

import com.softraysolutions.news.model.News;
import com.softraysolutions.news.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    public List<News> getNewsByCreatedByOrEditedBy(User user, String email);
}

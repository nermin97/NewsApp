package com.softraysolutions.hibernate.dao;
import com.softraysolutions.hibernate.entity.News;
import com.softraysolutions.hibernate.entity.User;

import java.util.ArrayList;
import java.util.List;

public class NewsDao extends Dao<News> {

    @SuppressWarnings("unchecked")
    public List<News> searchNews(String searchParameter) {
        openSession();
        List<News> list = new ArrayList<>();
        if (searchParameter.equals("all")) {
            list = session.createQuery("from News").list();
        }
        closeSession();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<News> adminNews(User user, String searchParam) {
        openSession();
        List<News> list = new ArrayList<>();
        if (searchParam.equals("all")) {
            list = session.createQuery("from News where created_by = :userId or edited_by = :userEmail")
                    .setParameter("userId", user.getId())
                    .setParameter("userEmail", user.getEmail())
                    .list();
        }
        closeSession();
        return list;
    }

    public News get(int id) {
        openSession();
        News news = session.get(News.class, id);
        session.close();
        return news;
    }
}

package com.softraysolutions.hibernate.services;

import com.softraysolutions.api.models.NewsData;
import com.softraysolutions.api.models.NewsReturnData;
import com.softraysolutions.hibernate.dao.NewsDao;
import com.softraysolutions.hibernate.dao.UserDao;
import com.softraysolutions.hibernate.entity.News;
import com.softraysolutions.hibernate.entity.User;
import org.jvnet.hk2.annotations.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsService {

    private static NewsDao newsDao;
    private static UserDao userDao;

    public NewsService() {
        newsDao = new NewsDao();
        userDao = new UserDao();
    }

    public NewsReturnData get(int id) {
        News news =  newsDao.get(id);
        return mapToReturnData(news);
    }

    public NewsReturnData save(NewsData newsData, int userId) {
        User editedBy = userDao.getById(userId);
        News news = createNewsFromData(newsData, editedBy);
        int id = newsDao.save(news);
        news =  newsDao.get(id);
        return mapToReturnData(news);
    }

    public NewsReturnData update(int newsId, NewsData newsData, int userId) {
        User editedBy = userDao.getById(userId);
        News news = createNewsFromData(newsData, editedBy);
        news.setId(newsId);
        newsDao.update(news);
        return mapToReturnData(news);
    }

    public List<NewsReturnData> searchNews(String searchParam) {
        List<News> newsList =  newsDao.searchNews(searchParam);
        List<NewsReturnData> returnDataList = new ArrayList<>();
        for (News n : newsList) {
            returnDataList.add(mapToReturnData(n));
        }
        return returnDataList;
    }

    public List<NewsReturnData> adminNews(int userId, String searchParam) {
        List<News> newsList = newsDao.adminNews(userDao.getById(userId), searchParam);
        List<NewsReturnData> returnDataList = new ArrayList<>();
        for (News n : newsList) {
            returnDataList.add(mapToReturnData(n));
        }
        return returnDataList;
    }

    private NewsReturnData mapToReturnData(News news) {
        NewsReturnData returnData = new NewsReturnData(news.getId(), news.getTitle(), news.getDescription(), news.getCreationDate().toString(), news.getCreatedBy().getEmail(), news.getEditedBy());
        return returnData;
    }

    private News createNewsFromData(NewsData newsData, User editedBy) {
        User createdBy = userDao.getByEmail(newsData.getCreatedBy());
        return new News(newsData.getTitle(), newsData.getDescription(), new Date(), createdBy, editedBy.getEmail());
    }
}

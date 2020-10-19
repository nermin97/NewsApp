package com.softraysolutions.hibernate.dao;
import com.softraysolutions.hibernate.entity.News;
import com.softraysolutions.hibernate.entity.User;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class NewsDao extends Dao<News> {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<News> searchNews(String searchParameter) {
        openSession();
        List<News> list;
        if (searchParameter.equals("all")) {
            list = session.createQuery("from News").list();
        } else {
            org.hibernate.query.Query fullTextQuery = getFulTextQuery(searchParameter);
            list = fullTextQuery.list();
        }
        closeSession();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<News> adminNews(User user, String searchParameter) {
        openSession();
        List<News> list;
        if (searchParameter.equals("all")) {
            list = session.createQuery("from News where created_by=?0 or edited_by=?1")
                    .setParameter(0, user.getId())
                    .setParameter(1, user.getEmail())
                    .list();
        } else {
            org.hibernate.query.Query fullTextQuery = getFulTextQuery(searchParameter);
            list = fullTextQuery.list();
            list = list.stream().filter(
                    news -> news.getCreatedBy().getEmail().equals(user.getEmail()) || news.getEditedBy().equals(user.getEmail()))
                    .collect(Collectors.toList());
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

    private org.hibernate.query.Query getFulTextQuery(String searchParameter) {
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        final QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(News.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword()
                .onFields("title", "description")
                .matching(searchParameter)
                .createQuery();
        return fullTextSession.createFullTextQuery(luceneQuery);
    }
}

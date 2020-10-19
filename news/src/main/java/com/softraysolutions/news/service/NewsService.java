package com.softraysolutions.news.service;

import com.softraysolutions.news.model.News;
import com.softraysolutions.news.model.User;
import com.softraysolutions.news.repository.NewsRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService implements ServiceInterface<News> {

    @Autowired
    private NewsRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public News getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public News update(News news) {
        Optional<News> optional = repository.findById(news.getId());
        if (optional.isPresent()) {
            News existing = optional.get();
            existing.setTitle(news.getTitle());
            existing.setDescription(news.getDescription());
            return repository.save(existing);
        }
        return null;
    }

    @Override
    public void delete(News news) {
        repository.delete(news);
    }

    @Override
    public List<News> getAll() {
        return repository.findAll();
    }

    public List<News> searchPublic(String search) {
        return (search.equals("all")) ?
                this.repository.findAll()
                :
                this.getSearchQuery(search).getResultList();
    }

    public List<News> searchAdmin(User user, String search) {
        if (search.equals("all")) {
            return this.repository.getNewsByCreatedByOrEditedBy(user, user.getEmail());
        }
        List<News> result = getSearchQuery(search).getResultList();
        return result.stream().filter(news ->
                news.getCreatedBy().getEmail().equals(user.getEmail())
                        || news.getEditedBy().equals(user.getEmail())).collect(Collectors.toList());
    }

    private FullTextQuery getSearchQuery(String search) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(News.class)
                .get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .wildcard()
                .onFields("title", "description")
                .matching(search)
                .createQuery();
        return fullTextEntityManager.createFullTextQuery(luceneQuery, News.class);
    }
}

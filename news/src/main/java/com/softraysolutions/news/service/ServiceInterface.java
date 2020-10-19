package com.softraysolutions.news.service;

import java.util.List;

public interface ServiceInterface<T> {
    T getById(Long id);
    T save(T t);
    T update(T t);
    void delete(T t);
    List<T> getAll();
}

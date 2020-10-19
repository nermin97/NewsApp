package com.softraysolutions.tests;


import com.softraysolutions.PropertiesReader;
import com.softraysolutions.hibernate.dao.NewsDao;
import com.softraysolutions.hibernate.dao.UserDao;
import com.softraysolutions.hibernate.entity.User;
import com.softraysolutions.hibernate.services.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NewsTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    private NewsDao newsDao;

    @Mock
    private UserDao userDao;

    private final String adminEmail = PropertiesReader.getProperty("administrator.email");

    private User admin;

    @Test
    public void getAllPublicNewsTest() {

    }

    @Test
    public void getAllAdminNewsTest() {

    }
}

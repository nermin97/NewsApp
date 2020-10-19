package com.softraysolutions.tests;


import com.softraysolutions.InitAdmin;
import com.softraysolutions.PropertiesReader;
import com.softraysolutions.api.models.UserCredentials;
import com.softraysolutions.hibernate.dao.UserDao;
import com.softraysolutions.hibernate.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    private UserService userService;

    private final String adminEmail = PropertiesReader.getProperty("administrator.email");
    private final String adminPassword = PropertiesReader.getProperty("administrator.password");

    @Test
    public void loginUserTest() {
        InitAdmin.init();
        UserCredentials uc = new UserCredentials();
        uc.setEmail(adminEmail);
        uc.setPassword(adminPassword);
        Assert.assertNotNull(userService.get(uc));

        Mockito.verify(userService).get(uc);
    }


}

package com.softraysolutions.tests;


import com.softraysolutions.InitAdmin;
import com.softraysolutions.PropertiesReader;
import com.softraysolutions.api.models.UserCredentials;
import com.softraysolutions.hibernate.dao.UserDao;
import com.softraysolutions.hibernate.services.UserService;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() {
        userService = new UserService();
    }

    private final String adminEmmail = PropertiesReader.getProperty("administrator.email");
    private final String adminPassword = PropertiesReader.getProperty("administrator.password");

    @Test
    public void loginUserTest() {
        InitAdmin.init();
        UserCredentials uc = new UserCredentials();
        uc.setEmail(adminEmmail);
        uc.setPassword(adminPassword);
        Assert.assertNotNull(userService.get(uc));
    }


}

package com.softraysolutions;

import com.softraysolutions.api.models.UserCredentials;
import com.softraysolutions.hibernate.entity.enumeration.Enumerations;
import com.softraysolutions.hibernate.services.UserService;

public class InitAdmin {
    public static void initialize() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail(System.getProperty("administrator.email"));
        userCredentials.setPassword(System.getProperty("administrator.password"));
        new UserService().save(userCredentials, Enumerations.UserType.Administrator);
    }
}

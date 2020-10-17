package com.softraysolutions.hibernate.services;

import com.softraysolutions.api.models.UserCredentials;
import com.softraysolutions.hibernate.dao.UserDao;
import com.softraysolutions.hibernate.entity.User;
import com.softraysolutions.hibernate.entity.enumeration.Enumerations;
import com.softraysolutions.hibernate.security.Encrypto;
import org.jvnet.hk2.annotations.Service;

@Service
public class UserService {

    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User save(UserCredentials uc, Enumerations.UserType userType){
        try {
            int id = userDao.save(new User(uc.getEmail(), Encrypto.encrypt(uc.getPassword()), userType));
            return getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User get(UserCredentials uc) {
        User user = userDao.getByEmail(uc.getEmail());
        try {
            if(Encrypto.decrypt(user.getPassword()).equals(uc.getPassword()))
                return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}

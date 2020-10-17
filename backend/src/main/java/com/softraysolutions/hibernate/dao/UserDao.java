package com.softraysolutions.hibernate.dao;
import com.softraysolutions.hibernate.entity.User;

import java.util.List;

public class UserDao extends Dao<User> {

    public User getById(int id) {
        openSession();
        User user = session.get(User.class, id);
        closeSession();
        return user;
    }

    public User getByEmail(String email) {
        openSession();
        List<User> list = session.createQuery("from User where email = :email").setParameter("email", email).list();
        closeSession();
        if(list.isEmpty())
            return null;
        return list.get(0);
    }
}

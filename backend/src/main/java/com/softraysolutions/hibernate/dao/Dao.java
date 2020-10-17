package com.softraysolutions.hibernate.dao;

import com.softraysolutions.hibernate.util.Db;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class Dao<T> {

    protected Session session;

    public int save(T t){
        openSession();
        Transaction tx = session.beginTransaction();
        int id = (int) session.save(t);
        tx.commit();
        closeSession();
        return id;
    }

    public void update(T t){
        openSession();
        Transaction tx = session.beginTransaction();
        session.update(t);
        tx.commit();
        closeSession();
    }
    public void delete(T t) {
        openSession();
        Transaction tx = session.beginTransaction();
        session.delete(t);
        tx.commit();
        closeSession();
    }

    protected Session openSession() {
        session = Db.getSessionFactory().openSession();
        return session;
    }

    protected void closeSession() {
        if (session != null) {
            session.close();
        }
    }
}

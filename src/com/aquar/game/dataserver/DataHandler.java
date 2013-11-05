package com.aquar.game.dataserver;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class DataHandler {
    private static DataHandler instance;
    private SessionFactory sessionFactory;

    private DataHandler() {
        // TODO Auto-generated constructor stub
    }
    
    public static DataHandler getInstance() {
        if (instance == null) {
            instance = new DataHandler();
        }
        return instance;
    }
    
    public void init() {
        // configures settings from hibernate.cfg.xml
        Configuration cfg = new Configuration();
        cfg.configure();
        // A SessionFactory is set up once for an application
        sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry());
    }
    
    public <T> boolean save(List<T> objs) {
        boolean ret = false;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            int count = 0;
            for (T obj : objs) {
                session.save(obj);
                count++;
                if (count == 20) { //20, same as the JDBC batch size
                    //flush a batch of inserts and release memory:
                    session.flush();
                    session.clear();
                    count = 0;
                }
            }
            
            session.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ret;
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> query(T obj) {
        List<T> result = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Criteria crit = session.createCriteria(obj.getClass());
            result = crit.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
        
    }
    
    public <T> T load(T t) {
        Session session = sessionFactory.openSession();
        session.buildLockRequest(LockOptions.NONE).lock(t);
        return t;
    }
    
    public <T> boolean delete(T obj) {
        boolean ret = false;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ret;
    }
    
    public void release() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

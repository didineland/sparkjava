package com.bnc.dao;

import java.util.List;

import com.bnc.entity.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EventDao {
    public void saveStudent(Event student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(student);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List < Event > getEvent() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Event", Event.class).list();
        }
    }
}

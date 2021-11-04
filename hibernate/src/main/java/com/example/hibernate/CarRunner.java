package com.example.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class CarRunner {
    private static SessionFactory sessionFactory;

    public CarRunner() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }


    public void addCar(Car car) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        session.save(car);

        transaction.commit();
        session.close();
    }


    public List<Car> showCars() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Car> carList = session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();

        return carList;
    }

    public void updateCar(Car car) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        session.update(car);

        transaction.commit();
        session.close();
    }

    public void removeCar(Car car) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.delete(car);

        transaction.commit();
        session.close();
    }
}

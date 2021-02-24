package pl.edu.wszib.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.dao.PointOfSaleDAO;
import pl.edu.wszib.model.PointOfSale;

import java.util.List;

@Component
public class PointOfSaleDAOImpl implements PointOfSaleDAO {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    @Autowired
    public PointOfSaleDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public PointOfSale getPointOfSaleById(Long id) {
        session = factory.openSession();
        return (PointOfSale) session.get(PointOfSale.class, id);
    }

    @Override
    public PointOfSale getPointOfSaleByName(String name) {
        session = factory.openSession();
        PointOfSale pointOfSaleTakenByName = (PointOfSale) session.createQuery("FROM pl.edu.wszib.model.PointOfSale WHERE name = '" + name + "'").uniqueResult();
        session.close();
        return pointOfSaleTakenByName;
    }

    @Override
    public PointOfSale getPointOfSaleByLocation(String location) {
        session = factory.openSession();
        PointOfSale pointOfSaleTakenByLocation = (PointOfSale) session.createQuery("FROM pl.edu.wszib.model.PointOfSale WHERE location = '" + location + "'").uniqueResult();
        session.close();
        return pointOfSaleTakenByLocation;
    }

    @Override
    public List<PointOfSale> getAllPointOfSale() {
        session = factory.openSession();
        return session.createQuery("FROM pl.edu.wszib.model.PointOfSale").list();
    }

    @Override
    public PointOfSale addPointOfSale(PointOfSale pointOfSale) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.save(pointOfSale);
            transaction.commit();
            return pointOfSale;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public PointOfSale updatePointOfSale(PointOfSale pointOfSale) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.update(pointOfSale);
            transaction.commit();
            return pointOfSale;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void deletePointOfSale(Long id) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            PointOfSale pointOfSale = (PointOfSale) session.load(PointOfSale.class, id);
            session.delete(pointOfSale);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }
}

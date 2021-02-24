package pl.edu.wszib.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.dao.CouponDAO;
import pl.edu.wszib.model.Coupon;
import pl.edu.wszib.model.CouponType;

import java.util.ArrayList;
import java.util.List;

@Component
public class CouponDAOImpl implements CouponDAO {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;


    @Autowired
    public CouponDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Coupon> findCouponByCouponType(CouponType couponType) {

        List<Coupon> coupons = session.createQuery("FROM pl.edu.wszib.model.Coupon").list();
        List<Coupon> couponsWithSelectedType = new ArrayList<>();

        for (Coupon coupon : coupons) {
            if (couponType.equals(coupon.getCouponType())) {
                couponsWithSelectedType.add(coupon);
            }
        }

        return couponsWithSelectedType;
    }

    @Override
    public Coupon getCouponById(Long id) {
        session = factory.openSession();

        return (Coupon) session.get(Coupon.class, id);

    }

    @Override
    public Coupon getCouponByCouponCode(String couponCode) {
        session = factory.openSession();

        Coupon couponTakenByCouponCode = (Coupon) session.createQuery("FROM pl.edu.wszib.model.Coupon WHERE couponCode = '" + couponCode + "'").uniqueResult();
        session.close();
        return couponTakenByCouponCode;

    }

    @Override
    public List<Coupon> getAllCoupons() {
        session = factory.openSession();
        return session.createQuery("FROM pl.edu.wszib.model.Coupon").list();
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.update(coupon);
            transaction.commit();
            return coupon;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void deleteCoupon(Long id) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            Coupon coupon = (Coupon) session.load(Coupon.class, id);
            session.delete(coupon);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.save(coupon);
            transaction.commit();
            return coupon;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }
}

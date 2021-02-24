package pl.edu.wszib.dao.impl;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.dao.ReportDao;
import pl.edu.wszib.model.Report;

import java.util.List;

@Component
public class ReportDAOImpl implements ReportDao {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    @Autowired
    public ReportDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Report getReportById(Long id) {
        session = factory.openSession();

        return (Report) session.get(Report.class, id);
    }

    @Override
    public Report updateReport(Report report) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.update(report);
            transaction.commit();
            return report;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Report addReport(Report report) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.save(report);
            transaction.commit();
            return report;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void deleteReport(Long id) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            Report report = (Report) session.load(Report.class, id);
            session.delete(report);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Report> getAllReports() {
        session = factory.openSession();
        List<Report> reports = session.createQuery("FROM pl.edu.wszib.model.Report").list();
        session.close();
        return reports;
    }
}

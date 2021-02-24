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
import pl.edu.wszib.dao.UserDAO;
import pl.edu.wszib.model.User;
import pl.edu.wszib.model.UserRole;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    @Autowired
    public UserDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) {
        session = factory.openSession();

        List<User> users = session.createQuery("FROM pl.edu.wszib.model.User").list();
        List<User> usersWithSelectedRole = new ArrayList<>();

        for (User user : users) {
            for (UserRole role : user.getRoles()) {
                if (role.equals(userRole)) {
                    if (usersWithSelectedRole.contains(user)) continue;
                    usersWithSelectedRole.add(user);
                }
            }
        }
        return usersWithSelectedRole;
    }

    @Override
    public User getUserById(Long id) {
        session = factory.openSession();
        return (User) session.get(User.class, id);

    }

    @Override
    public User getUserByLogin(String login) {
        session = factory.openSession();

        User userTakenByName = (User) session.createQuery("FROM pl.edu.wszib.model.User WHERE login = '" + login + "'").uniqueResult();
        session.close();
        return userTakenByName;
    }

    @Override
    public User updateUser(User user) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return user;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public User addUser(User user) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        session = factory.openSession();
        List<User> users = session.createQuery("FROM pl.edu.wszib.model.User").list();
        session.close();
        return users;
    }
}

package ru.oop.logic.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.oop.logic.models.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("ID должен быть null при сохранении нового объекта.");
        }
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        return user;
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("ID не должен быть null при обновлении объекта.");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User updatedUser = session.merge(user);
            transaction.commit();
            return updatedUser;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при обновлении User", e);
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске User с ID: " + id, e);
        }
    }

    @Override
    public User findByTelegramId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User u WHERE u.telegramId = :telegramId";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("telegramId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске User с ID: " + id, e);
        }
    }

    @Override
    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске пользователя с именем: " + username, e);
        }
    }

    public List<User> findByUsernameLike(String usernamePart) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User u WHERE u.username LIKE :usernamePart";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("usernamePart", "%" + usernamePart + "%");
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске пользователей по имени, содержащему: " + usernamePart, e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при удалении User с ID: " + id, e);
        }
    }
}
package ru.oop.logic.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.Wishlist;

public class WishRepositoryImpl implements WishRepository {

    private final SessionFactory sessionFactory;

    public WishRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Wish save(Wish wish) {
        if (wish.getId() != null) {
            throw new IllegalArgumentException("ID должен быть null при сохранении нового объекта.");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(wish);
            transaction.commit();
            return wish;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при сохранении Wish", e);
        }
    }

    @Override
    public Wish update(Wish wish) {
        if (wish.getId() == null) {
            throw new IllegalArgumentException("ID не должен быть null при обновлении объекта.");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Wish updatedWish = (Wish) session.merge(wish);
            transaction.commit();
            return updatedWish;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при обновлении Wish", e);
        }
    }

    @Override
    public Wish findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Wish.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске Wish с ID: " + id, e);
        }
    }

    @Override
    public void deleteById(long id, long wishlistId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Wishlist wishlist = session.get(Wishlist.class, wishlistId);
            Wish wishToRemove = session.get(Wish.class, id);
            wishlist.getWishes().remove(wishToRemove);
            Wish wish = session.get(Wish.class, id);
            if (wish != null) {
                session.delete(wish);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при удалении Wish с ID: " + id, e);
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
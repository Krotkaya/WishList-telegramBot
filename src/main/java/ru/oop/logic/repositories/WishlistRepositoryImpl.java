package ru.oop.logic.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.oop.logic.models.Wishlist;

import java.util.List;

public class WishlistRepositoryImpl implements WishlistRepository {

    private final SessionFactory sessionFactory;

    // Конструктор для инициализации SessionFactory
    public WishlistRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        if (wishlist.getId() != null) {
            throw new IllegalArgumentException("ID должен быть null при сохранении нового объекта.");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(wishlist); // persist для новых объектов
            transaction.commit();
            return wishlist;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при сохранении Wishlist", e);
        }
    }

    @Override
    public Wishlist update(Wishlist wishlist) {
        if (wishlist.getId() == null) {
            throw new IllegalArgumentException("ID не должен быть null при обновлении объекта.");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Wishlist updatedWishlist = (Wishlist) session.merge(wishlist); // merge для обновления
            transaction.commit();
            return updatedWishlist;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при обновлении Wishlist", e);
        }
    }

    @Override
    public Wishlist findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Wishlist.class, id); // Поиск объекта по ID
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске Wishlist с ID: " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Wishlist wishlist = session.get(Wishlist.class, id);
            if (wishlist != null) {
                session.delete(wishlist); // Удаление объекта
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при удалении Wishlist с ID: " + id, e);
        }
    }

    @Override
    public List<Wishlist> findByUsername(long userId) {
        try (Session session = sessionFactory.openSession()) {
            // HQL для получения всех Wishlists по userId
            return session.createQuery("FROM Wishlist WHERE user.id = :userId", Wishlist.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске Wishlist для пользователя с ID: " + userId, e);
        }
    }

    // Закрытие SessionFactory
    public void close() {
        sessionFactory.close();
    }
}
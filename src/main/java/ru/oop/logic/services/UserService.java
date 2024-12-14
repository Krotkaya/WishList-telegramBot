package ru.oop.logic.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.oop.logic.models.User;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.utils.HibernateUtil;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<Long, User> users = new HashMap<>(); // Сохраняем пользователей в памяти для быстрого доступа

    // Поиск пользователя по chatId из базы данных
    public User getUserByChatId(Long chatId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE chatId = :chatId", User.class)
                    .setParameter("chatId", chatId)
                    .uniqueResult();
        }
    }

    // Сохранение нового пользователя в базу данных
    public void saveUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    // Добавление нового списка желаний пользователю
    public void addWishlistToUser(User user, String wishlistName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Создаём новый список желаний
            Wishlist wishlist = new Wishlist();
            wishlist.setName(wishlistName);
            wishlist.setUser(user);

            // Сохраняем список желаний
            session.save(wishlist);
            transaction.commit();
        }
    }

    // Вспомогательные методы для работы с данными в памяти
    public void addUser(User user) {
        users.put(user.getId(), user); // Добавление пользователя в локальную память
    }

    public User getUserById(Long userId) {
        return users.get(userId); // Поиск пользователя по ID из локальной памяти
    }
}

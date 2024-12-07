/*package ru.oop.logic.services;


import ru.oop.logic.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<Long, User> users = new HashMap<>();

    public User getUserByChatId(Long chatId) {
        // Поиск пользователя по chatId
        for (User user : users.values()) {
            if (user.getChatId().equals(chatId)) {
                return user;
            }
        }
        return null; // Если пользователь не найден
    }

    public void addUser(User user) {
        users.put(user.getId(), user); // Добавление пользователя в систему
    }

    public User getUserById(Long userId) {
        return users.get(userId); // Поиск пользователя по id
    }
}*/
package ru.oop.logic.services;

import ru.oop.logic.models.User;
import ru.oop.logic.models.Wishlist;

import java.util.HashMap;
import java.util.Map;
import java.util.List; // Нужно для списка вишлистов
import java.util.ArrayList; // Нужно для пустого списка

public class UserService {
    private final Map<Long, User> users = new HashMap<>();

    public User getUserByChatId(Long chatId) {
        // Поиск пользователя по chatId
        for (User user : users.values()) {
            if (user.getChatId().equals(chatId)) {
                return user;
            }
        }
        return null; // Если пользователь не найден
    }

    public void addUser(User user) {
        users.put(user.getId(), user); // Добавление пользователя в систему
    }

    public User findByUsername(String userId) {
        return users.get(userId); // Поиск пользователя по id
    }

    public void addWishlistToUser(String userId, Wishlist wishlist) {
        User user = findByUsername(userId);
        if (user != null) {
            user.addWishlist(wishlist);
        }
    }

    public List<Wishlist> getWishlistsByUserId(String userId) {
        User user = findByUsername(userId);
        if (user != null) {
            return user.getWishlists();
        }
        return new ArrayList<>();
    }
}

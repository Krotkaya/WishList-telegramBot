package ru.oop.logic.services;


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

    // Другие методы для работы с пользователями, если это необходимо
}

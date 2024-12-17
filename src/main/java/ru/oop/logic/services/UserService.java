package ru.oop.logic.services;

import ru.oop.logic.models.User;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User getUserByTelegramId(Long userId) {
        return userRepository.findByTelegramId(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void addWishlistToUser(User user, Wishlist wishlist) {
        user.getWishlists().add(wishlist);
        userRepository.update(user);
    }

    public void updateUsernameIfChanged(Long telegramId, String newUsername) {
        try {
            User user = userRepository.findByTelegramId(telegramId);

            if (user == null) {
                User newUser = new User();
                newUser.setTelegramId(telegramId);
                newUser.setUsername(newUsername);
                userRepository.save(newUser);
            } else {
                if (!user.getUsername().equals(newUsername)) {
                    user.setUsername(newUsername);
                    userRepository.update(user);
                }
            }
        } catch (Exception e) {
            // Логирование ошибки
            System.err.println("Ошибка при обновлении имени пользователя: " + e.getMessage());
        }
    }



    public User processTelegramUser(Long telegramId, String username) {
        if (telegramId == null) {
            throw new IllegalArgumentException("Telegram ID не может быть null.");
        }

        updateUsernameIfChanged(telegramId, username);

        // Возвращаем обновленного или существующего пользователя
        return userRepository.findByTelegramId(telegramId);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User registerUser(Long telegramId, String username) {
        User existingUser = userRepository.findByTelegramId(telegramId);
        if (existingUser == null) {
            User newUser = new User();
            newUser.setTelegramId(telegramId);
            newUser.setUsername(username);
            return userRepository.save(newUser);
        }
        return existingUser; // Пользователь уже существует.
    }

    public List<User> findUsersByUsername(String usernamePart) {
        return userRepository.findByUsernameLike(usernamePart);
    }
}

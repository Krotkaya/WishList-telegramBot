package ru.oop.logic.services;

import ru.oop.logic.models.User;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.repositories.UserRepository;

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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

package ru.oop.logic.repositories;

import ru.oop.logic.models.User;

public interface UserRepository {
    User save(User user);
    User update(User user);
    User findById(Long id);
    User findByTelegramId(Long id);
    void deleteById(Long id);
    User findByUsername(String username);
}
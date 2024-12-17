package ru.oop.logic.repositories;

import ru.oop.logic.models.User;
import java.util.List;

public interface UserRepository {
    User save(User user);
    User update(User user);
    User findById(Long id);
    User findByTelegramId(Long id);
    void deleteById(Long id);
    User findByUsername(String username);
    List<User> findByUsernameLike(String usernamePart);
}
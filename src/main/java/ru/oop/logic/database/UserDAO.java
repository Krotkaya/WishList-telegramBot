package ru.oop.logic.database;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private Map<Long, User> users = new HashMap<>();

    public User getUser(long id) {
        return users.get(id);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }
}

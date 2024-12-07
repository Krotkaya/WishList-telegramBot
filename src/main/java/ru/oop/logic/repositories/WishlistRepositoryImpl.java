package ru.oop.logic.repositories;

import ru.oop.logic.models.Wishlist;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class WishlistRepositoryImpl implements WishlistRepository {

    private final Map<Long, Wishlist> database = new HashMap<>();
    private long currentId = 1;
    //контструктор для инициализации базы
    //в конструкторе пробегаемся по базе и выбираем максимальный ацдишник и прибавляем 1

    @Override
    public Wishlist save(Wishlist wishlist) {
        if (wishlist.getId() == null) {
            wishlist.setId(currentId++);
        }
        database.put(wishlist.getId(), wishlist);
        return wishlist;
    }

    @Override
    public Wishlist findById(Long id) {
        return database.get(id);
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }

    @Override
    public List<Wishlist> findByUsername(long userId) {
        List<Wishlist> userWishlists = new ArrayList<>();
        for (Wishlist wishlist : database.values()) {
            if (wishlist.getUserId() == userId) {
                userWishlists.add(wishlist);
            }
        }
        return userWishlists;
    }
}

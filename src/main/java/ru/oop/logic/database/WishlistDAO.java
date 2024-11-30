package ru.oop.logic.database;

import ru.oop.logic.models.Wishlist;

import java.util.HashMap;
import java.util.Map;

public class WishlistDAO {
    private Map<Long, Wishlist> userWishlists = new HashMap<>();

    public Wishlist getWishlist(long userId) {
        return userWishlists.getOrDefault(userId, new Wishlist());
    }

    public void createWishlist(long userId) {
        userWishlists.putIfAbsent(userId, new Wishlist());
    }

    public void deleteWishlist(long userId) {
        userWishlists.remove(userId);
    }
}
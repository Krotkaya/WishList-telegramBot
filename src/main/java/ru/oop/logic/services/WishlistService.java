package ru.oop.logic.services;

import ru.oop.logic.models.Wish;
import ru.oop.logic.models.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(long userId, String name);//передаем сразу wishlist

    void addWishToWishlist(long wishlistId, Wish wish);//заменяю long на UUID

    void deleteWishFromWishlist(long wishlistId, long wishId);

    void deleteWishlist(long wishlistId);
}
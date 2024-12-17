package ru.oop.logic.repositories;

import java.util.List;

import ru.oop.logic.models.Wishlist;

//добавить реализацию репрозитории юзера и виша

public interface WishlistRepository extends Repository<Long, Wishlist> {

    List<Wishlist> findByUsername(long userId);
}
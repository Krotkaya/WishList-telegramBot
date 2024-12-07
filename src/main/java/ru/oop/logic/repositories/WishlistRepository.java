package ru.oop.logic.repositories;

import java.util.List;

import ru.oop.logic.models.Wishlist;

public interface WishlistRepository {
    Wishlist save(Wishlist wishlist);//передаю объект, а он сам заполняет айдишник, убрать аргумент айди, разделить save на update и save
    Wishlist findById(Long id);
    void deleteById(Long id);
    List<Wishlist> findByUsername(long userId);
}
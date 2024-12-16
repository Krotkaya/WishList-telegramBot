package ru.oop.logic.repositories;

import java.util.List;

import ru.oop.logic.models.Wishlist;

//добавить реализацию репрозитории юзера и виша

public interface WishlistRepository {
    Wishlist save(Wishlist wishlist); // Для новых объектов
    Wishlist update(Wishlist wishlist); // Для существующих объектов
    Wishlist findById(Long id); // Найти объект по ID
    void deleteById(Long id); // Удалить объект по ID

    List<Wishlist> findByUsername(long userId);
}
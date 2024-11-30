package ru.oop.logic.database;

import ru.oop.logic.models.Wish;

import java.util.List;

public interface WishDAO {
    void addWish(Wish wish);
    void deleteWish(String text);
    List<Wish> getWishes();
}
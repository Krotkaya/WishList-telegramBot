package ru.oop.logic.repositories;
import ru.oop.logic.models.Wish;

import java.util.List;

public interface WishRepository {
    Wish save(Wish wish);
    Wish update(Wish wish);
    Wish findById(Long id);
    void deleteById(Long id);
}
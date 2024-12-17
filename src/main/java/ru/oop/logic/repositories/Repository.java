package ru.oop.logic.repositories;

import ru.oop.logic.models.Wishlist;

public interface Repository<Id, Entity> {
    Entity save(Entity entity); // Для новых объектов
    Entity update(Entity entity); // Для существующих объектов
    Entity findById(Id id); // Найти объект по ID
    void deleteById(Id id); // Удалить объект по ID
}

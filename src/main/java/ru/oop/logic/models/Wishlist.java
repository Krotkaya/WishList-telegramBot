package ru.oop.logic.models;


import java.util.List;

/**
 * Модель для представления списка желаний
 */
public class Wishlist {

    private Long id;             // Уникальный идентификатор списка
    private Long userId;         // Идентификатор пользователя
    private String name;         // Название списка
    private List<Wish> wishes;   // Список желаний

    // Конструктор
    public Wishlist(Long id, Long userId, String name, List<Wish> wishes) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.wishes = wishes;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    // Переопределение toString для удобного отображения
    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", wishes=" + wishes +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Wishlist otherWishList)) {
            return false; // Если obj не является экземпляром Wishlist
        }

        // Сравнение значимых полей
        return this.id != null && this.id.equals(otherWishList.id);

    }
}
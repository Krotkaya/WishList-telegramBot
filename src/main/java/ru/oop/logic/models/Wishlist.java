package ru.oop.logic.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlists")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Связь с таблицей users
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishes = new ArrayList<>();

    // Конструктор по умолчанию
    public Wishlist() {}

    // Конструктор с параметрами
    public Wishlist(User user, String name, List<Wish> wishes) {
        this.user = user;
        this.name = name;
        this.wishes = (wishes != null) ? wishes : new ArrayList<>();
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", wishes=" + wishes +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Wishlist otherWishlist)) {
            return false;
        }
        return id != null && id.equals(otherWishlist.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
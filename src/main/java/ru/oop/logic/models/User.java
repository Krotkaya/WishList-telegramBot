package ru.oop.logic.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "telegramId", nullable = false)
    private long telegramId;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wishlist> wishlists = new ArrayList<>();

    public User() {}

    public User(Long telegramId, String username) {
        this.telegramId = telegramId;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", telegramId='" + telegramId + '\'' +
                ", username='" + username + '\'' +
                ", wishlists=" + wishlists +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User otherUser)) {
            return false;
        }
        return id != null && id.equals(otherUser.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

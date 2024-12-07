package ru.oop.logic.models;
import java.util.List;
import java.util.ArrayList;

public class User {
    private Long id;            // Уникальный идентификатор пользователя
    private String username;    // Имя пользователя
    private Long chatId;        // Идентификатор чата Telegram
    private List<Wishlist> wishlists;    // Добавляем список вишлистов

    public User(Long id, String username, Long chatId) {
        this.id = id;
        this.username = username;
        this.chatId = chatId;
        this.wishlists = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void addWishlist(Wishlist wishlist) {
        this.wishlists.add(wishlist);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", chatId=" + chatId +
                ", wishlists=" + wishlists +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
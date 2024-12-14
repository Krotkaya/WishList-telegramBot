package ru.oop.logic.models;

import jakarta.persistence.*; // Для Hibernate/Jakarta Persistence API

@Entity
@Table(name = "users") // Указывает таблицу в БД
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private Long id; // Уникальный идентификатор пользователя

    @Column(nullable = false) // Поле "username" не может быть NULL
    private String username; // Имя пользователя

    @Column(nullable = false, unique = true) // Поле "chatId" уникально и не может быть NULL
    private Long chatId; // Идентификатор чата Telegram

    // Конструктор без аргументов (обязательно для Hibernate)
    public User() {}

    // Конструктор с параметрами
    public User(Long id, String username, Long chatId) {
        this.id = id;
        this.username = username;
        this.chatId = chatId;
    }

    // Геттеры и сеттеры
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

    // Методы toString, equals и hashCode
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", chatId=" + chatId +
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
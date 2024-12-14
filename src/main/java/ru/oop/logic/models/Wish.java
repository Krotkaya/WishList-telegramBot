package ru.oop.logic.models;

import jakarta.persistence.*;

@Entity
@Table(name = "wishes")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false) // Связь с Wishlist
    private Wishlist wishlist;

    public Wish() {}

    public Wish(String description, Wishlist wishlist) {
        this.description = description;
        this.wishlist = wishlist;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", wishlist=" + wishlist +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Wish otherWish)) {
            return false;
        }
        return id != null && id.equals(otherWish.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
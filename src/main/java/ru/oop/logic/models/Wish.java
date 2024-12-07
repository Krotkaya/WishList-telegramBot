package ru.oop.logic.models;

public class Wish {

    private Long id;            // Уникальный идентификатор желания
    private String description; // Описание желания (пока просто текст)
    private boolean available = true; // По умолчанию доступно

    public Wish(Long id, String description, boolean available) {
        this.id = id;
        this.description = description;
        this.available = available;
    }

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Переопределение toString для удобного отображения
    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Wish)) return false;
        Wish other = (Wish) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
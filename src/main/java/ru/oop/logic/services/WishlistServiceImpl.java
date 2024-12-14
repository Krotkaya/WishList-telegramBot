package ru.oop.logic.services;

import ru.oop.logic.models.Wish;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.repositories.WishlistRepository;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository repository; // Репозиторий для работы с данными
    private final Random random = new Random();

    public WishlistServiceImpl(WishlistRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        if (wishlist.getId() == null) {
            long id = Math.abs(random.nextLong());
            wishlist.setId(id); // Генерация уникального ID
        }
        if (wishlist.getWishes() == null) {
            wishlist.setWishes(new ArrayList<>()); // Инициализация пустого списка желаний
        }
        return repository.save(wishlist); // Сохраняем в репозитории
    }
//на этом этапе у вишлиста еще нет айдишника, но мы уже с ними работаем.
    @Override
    public void addWishToWishlist(long wishlistId, Wish wish) {
        Wishlist wishlist = repository.findById(wishlistId);

        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist с ID " + wishlistId + " не найден.");
        }
        if (wish == null) {
            throw new IllegalArgumentException("Wish не может быть null.");
        }

        // Удаляем старое пожелание, если оно уже существует
        wishlist.getWishes().removeIf(existingWish -> existingWish.getId().equals(wish.getId()));
        wishlist.getWishes().add(wish); // Добавляем новое пожелание

        // Сохраняем изменения
        repository.save(wishlist);
    }

    @Override
    public void deleteWishFromWishlist(long wishlistId, long wishId) {
        Wishlist wishlist = repository.findById(wishlistId);
        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist с ID " + wishlistId + " не найден.");
        }

        // Удаляем подарок из списка
        wishlist.getWishes().removeIf(wish -> wish.getId().equals(wishId));

        // Сохраняем изменения
        repository.save(wishlist);
    }

    @Override
    public void deleteWishlist(long wishlistId) {
        repository.deleteById(wishlistId); // Удаляем вишлист через репозиторий
    }


}
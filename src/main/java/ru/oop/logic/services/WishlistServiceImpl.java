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
    public Wishlist createWishlist(long userId, String name) {
        long id = Math.abs(random.nextLong());
        Wishlist wishlist = new Wishlist(id, userId, name, new ArrayList<>());
        return repository.save(wishlist);
    }

    @Override
    public void addWishToWishlist(long wishlistId, Wish wish) {
        // Получаем вишлист из репозитория

        Wishlist wishlist = repository.findById(wishlistId);
        //Wish wishId = repository.findById(wish.getId());

        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist с ID " + wishlistId + " не найден.");
        }
        if (wish == null) {
            throw new IllegalArgumentException("Wish не может быть null.");
        }
        if (wishlist.getWishes().contains(wish)) {
            wishlist.getWishes().remove(wish); // Удаляем старый подарок
        }

        // Удаляем старое пожелание, если оно уже существует
        wishlist.getWishes().removeIf(existingWish -> existingWish.getId().equals(wish.getId()));
        wishlist.getWishes().add(wish);

        // Сохраняем изменения
        repository.save(wishlist);
    }

    @Override
    public void deleteWishFromWishlist(long wishlistId, long wishId) {
        // Получаем вишлист из репозитория
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
        // Удаляем вишлист через репозиторий
        repository.deleteById(wishlistId);
    }

    @Override
    public List<Wishlist> getWishlistsByUserId(long userId) {
        return repository.findByUsername(userId); // Получаем список вишлистов по userId
    }

    @Override
    public void markItemAsTaken(long wishlistId, long wishId) {
        Wishlist wishlist = repository.findById(wishlistId);
        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist с ID " + wishlistId + " не найден.");
        }

        for (Wish wish : wishlist.getWishes()) {
            if (wish.getId().equals(wishId)) {
                if (!wish.isAvailable()) {
                    throw new IllegalArgumentException("Wish с ID " + wishId + " уже занято.");
                }
                wish.setAvailable(false); // Отметить как занято
                break;
            }
        }
        repository.save(wishlist); // Сохранить изменения
    }
}
package ru.oop.logic.services;

import ru.oop.logic.models.Wish;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.repositories.WishRepository;
import ru.oop.logic.repositories.WishlistRepository;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository repository;
    private final WishRepository wishRepository;

    public WishlistServiceImpl(WishlistRepository repository, WishRepository wishRepository) {
        this.repository = repository;
        this.wishRepository = wishRepository;
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        wishlist.setWishes(new ArrayList<>());

        return repository.save(wishlist);
    }
    @Override
    public void addWishToWishlist(long wishlistId, Wish wish) {

        if (wish == null) {
            throw new IllegalArgumentException("Wish не может быть null.");
        }

        Wishlist wishlist = repository.findById(wishlistId);

        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist с ID " + wishlistId + " не найден.");
        }


        wishlist.getWishes().removeIf(existingWish -> existingWish.getId().equals(wish.getId()));
        wishlist.getWishes().add(wish);
        wish.setWishlist(wishlist);

        wishRepository.save(wish);
//        repository.save(wishlist);
    }

    @Override
    public void deleteWishFromWishlist(long wishlistId, long wishId) {
        Wishlist wishlist = repository.findById(wishlistId);
        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist с ID " + wishlistId + " не найден.");
        }

        wishlist.getWishes().removeIf(wish -> wish.getId().equals(wishId));
        wishRepository.deleteById(wishId, wishlistId);
//        repository.save(wishlist);
    }

    @Override
    public void deleteWishlist(long wishlistId) {
        repository.deleteById(wishlistId);
    }

    @Override
    public List<Wishlist> getWishlistsByUserId(long userId) {
        return List.of();
    }

    @Override
    public void markItemAsTaken(long wishlistId, long itemId) {

    }
}
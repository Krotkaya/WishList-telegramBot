package ru.oop.logic;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.repositories.WishlistRepository;
import ru.oop.logic.repositories.WishlistRepositoryImpl;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.services.WishlistServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WishlistServiceImplTest {
    WishlistRepository repository;
    WishlistService service;

    @Before
    public void setUp() {
        repository = new WishlistRepositoryImpl();
        service = new WishlistServiceImpl(repository);
    }

    @After
    public void tearDown() {
        repository = null;
        service = null;
    }

    @Test
    public void createWishlist() {
        // Создаем объект Wishlist и передаем его в сервис
        Wishlist wishlist = new Wishlist(123L, "For parents", new ArrayList<>());
        Wishlist createdWishlist = service.createWishlist(wishlist);

        assertNotNull(createdWishlist.getId());
        assertEquals(wishlist.getName(), createdWishlist.getName());
        assertEquals(wishlist.getUserId(), createdWishlist.getUserId());
    }

    @Test
    public void addWishToWishlist() {
        // Создаем объект Wishlist
        Wishlist wishlist = new Wishlist(123L, "Car", new ArrayList<>());
        Wishlist createdWishlist = service.createWishlist(wishlist);

        // Добавляем новое желание
        Wish wish = new Wish(1L, "Buy a car");
        service.addWishToWishlist(createdWishlist.getId(), wish);

        assertTrue(createdWishlist.getWishes().contains(wish));
    }

    @Test
    public void addWishWithSameIdToWishlist() {
        // Создаем объект Wishlist
        Wishlist wishlist = new Wishlist(1L, "gelly", new ArrayList<>());
        Wishlist createdWishlist = service.createWishlist(wishlist);

        // Добавляем желания с одинаковым ID
        Wish wish1 = new Wish(123L, "Car");
        Wish wish2 = new Wish(123L, "Wheel");

        service.addWishToWishlist(createdWishlist.getId(), wish1);
        service.addWishToWishlist(createdWishlist.getId(), wish2);

        // Проверяем, что в списке осталась только одна версия желания
        assertEquals(1, createdWishlist.getWishes().size());
        assertEquals("Wheel", createdWishlist.getWishes().get(0).getDescription());
    }

    @Test
    public void deleteWishFromWishlist() {
        // Создаем объект Wishlist
        Wishlist wishlist = new Wishlist(123L, "Tech Wishlist", new ArrayList<>());
        Wishlist createdWishlist = service.createWishlist(wishlist);

        // Добавляем несколько желаний
        Wish wish1 = new Wish(1L, "New Phone");
        Wish wish2 = new Wish(2L, "New Laptop");
        service.addWishToWishlist(createdWishlist.getId(), wish1);
        service.addWishToWishlist(createdWishlist.getId(), wish2);

        // Удаляем одно из желаний
        service.deleteWishFromWishlist(createdWishlist.getId(), wish1.getId());

        assertEquals(1, createdWishlist.getWishes().size());
        assertFalse(createdWishlist.getWishes().contains(wish1));
        assertTrue(createdWishlist.getWishes().contains(wish2));
    }

    @Test
    public void shouldRemoveExistingWishlist() {
        // Создаем объект Wishlist
        Wishlist wishlist = new Wishlist(1L, "Holiday Wishlist", new ArrayList<>());
        Wishlist createdWishlist = service.createWishlist(wishlist);

        // Удаляем вишлист
        service.deleteWishlist(createdWishlist.getId());

        assertNull(repository.findById(createdWishlist.getId()));
    }
}
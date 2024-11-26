package ru.oop.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.repositories.WishlistRepository;
import ru.oop.logic.repositories.WishlistRepositoryImpl;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.services.WishlistServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class WishlistServiceImplTest {
    WishlistRepository repository;
    WishlistService service;


    @BeforeEach
    void setUp() {
        repository = new WishlistRepositoryImpl();
        service = new WishlistServiceImpl(repository);
    }

    @AfterEach
    void tearDown() {
        repository = null;
        service = null;
    }

    @Test
    void createWishlist() {
        long id = 123;
        var name = "For parents";
        Wishlist wishlist = service.createWishlist(id, name);
        assertEquals(name, wishlist.getName());
        assertEquals(id, wishlist.getUserId());
    }

    @Test
    void addWishToWishlist() {
        long id = 123;
        var name = "Car";
        Wishlist wishlist = service.createWishlist(id, name);
        Wish wish = new Wish(id,name);
        service.addWishToWishlist(wishlist.getId(), wish);
        assertTrue( wishlist.getWishes().contains(wish));
    }
    @Test
    void addWishWithSameIdtToWishlist() {
        long Id = 1;
        var name = "gelly";
        long idFirst = 123;
        long idSecond = 123;
        var nameFirst = "Car";
        var nameSecond = "Wheel";
        Wishlist wishlist = service.createWishlist(Id, name);
        Wish wishFirst = new Wish(idFirst,nameFirst);
        Wish wishSecond = new Wish(idSecond,nameSecond);
        service.addWishToWishlist(wishlist.getId(), wishFirst);
        service.addWishToWishlist(wishlist.getId(), wishSecond);
        assertEquals( 1, wishlist.getWishes().size());
    }

    @Test
    void deleteWishFromWishlist() {
        long id = 123;
         var name = "Car";
        Wish wish1 = new Wish(1L, "New Phone");
        Wish wish2 = new Wish(2L, "New Laptop");
        Wishlist wishlist = service.createWishlist(id, name);
        service.addWishToWishlist(wishlist.getId(), wish1);
        service.addWishToWishlist(wishlist.getId(), wish2);

        // Удаляем первый подарок
        service.deleteWishFromWishlist(wishlist.getId(), wish1.getId());

        // Проверяем, что первый подарок удален, а второй остался
        assertEquals(1, wishlist.getWishes().size());
        assertFalse(wishlist.getWishes().contains(wish1));
        assertTrue(wishlist.getWishes().contains(wish2));
    }
//        long id = 123;
//        var name = "Car";
//        Wishlist wishlist = service.createWishlist(id, name);
//        Wish wish = new Wish(id,name);
//        service.addWishToWishlist(wishlist.getId(), wish);
//        service.deleteWishlist(wishlist.getId(), wish);



    @Test
    void shouldRemoveExistingWishlist() {
        long userId = 1L;
        String name = "Holiday Wishlist";
        Wishlist wishlist = service.createWishlist(userId, name);
        service.deleteWishlist(wishlist.getId());

        assertNull(repository.findById(wishlist.getId()));
    }
}
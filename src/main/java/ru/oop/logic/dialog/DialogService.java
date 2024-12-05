package ru.oop.logic.dialog;


/*public class UserService {
    private final WishDAO wishDAO = new WishDAO();
    private final Map<String, DialogState> dialogStates = new HasMap<>();

    public Response processRequest(Request request) {
        String chatId = request.getChatId();
        String message = request.getMessage();

        switch (message) {
            case "/start":
                return new Response("Я Wishlist бот");

            case "/createWishlist":
                userStates.setUserState(chatId, "CREATE_WISHLIST", null);
                return new Response("Начнем создавать Wishlist");

            case "/addWishToWishlist":
                userStates.setUserState(chatId, "ADD_WISH_TO_WISHLIST", null);
                return new Response("Введите название пожелания/виша хз");

            case "/deleteWishlist":
                userStates.setUserState(chatId, "DELETE_WISHLIST", null);
                return new Response("");

            default:
                if
        }
    }
}*/

/*import ru.oop.logic.database.WishlistDAO;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.WishlistService;
public class UserService {
    private WishlistDAO wishlistDAO;

    public UserService(WishlistDAO wishlistDAO) {
        this.wishlistDAO = wishlistDAO;
    }

    public String createWishlist(long userId) {
        wishlistDAO.createWishlist(userId);
        return "Вишлист успешно создан и он пуст.";
    }

    public String addWishToWishlist(long userId, String wishText) {
        Wish wish = new Wish(wishText);
        Wishlist wishlist = wishlistDAO.getWishlist(userId);
        wishlist.addWish(wish);
        return "Пожелание добавлено: " + wishText;
    }

    public String deleteWishFromWishlist(long userId, String wishText) {
        Wishlist wishlist = wishlistDAO.getWishlist(userId);
        wishlist.deleteWish(wishText);
        return "Пожелание удалено: " + wishText;
    }

    public String deleteWishlist(long userId) {
        wishlistDAO.deleteWishlist(userId);
        return "Вишлист удален.";
    }
}*/

import ru.oop.logic.models.Wish;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.services.WishlistService;

public class DialogService {
    private final WishlistService wishlistService;

    public DialogService(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // Создание нового вишлиста
    public String createWishlist(Long userId, String name) {
        // Используем метод из WishlistService для создания вишлиста
        Wishlist wishlist = wishlistService.createWishlist(userId, name);
        return "Вишлист '" + wishlist.getName() + "' успешно создан с ID " + wishlist.getId() + ".";
    }

    // Добавление пожелания в вишлист
    public String addWishToWishlist(Long wishlistId, String wishDescription) {
        // Создаем объект пожелания и добавляем его в вишлист через сервис
        //Wish wish = new Wish(wishText);
        Long wishId = generateWishId(); // Предположим, что у нас есть метод для генерации ID
        Wish wish = new Wish(wishId, wishDescription);
        wishlistService.addWishToWishlist(wishlistId, wish);
        return "Пожелание '" + wish.getDescription() + "' успешно добавлено в вишлист с ID " + wishlistId + ".";
    }

    // Удаление пожелания из вишлиста
    public String deleteWishFromWishlist(Long wishlistId, Long wishId) {
        // Удаляем пожелание через сервис
        wishlistService.deleteWishFromWishlist(wishlistId, wishId);
        return "Пожелание с ID " + wishId + " успешно удалено из вишлиста с ID " + wishlistId + ".";
    }

    // Удаление вишлиста
    public String deleteWishlist(Long wishlistId) {
        // Удаляем вишлист через сервис
        wishlistService.deleteWishlist(wishlistId);
        return "Вишлист с ID " + wishlistId + " успешно удален.";
    }

    private Long generateWishId() {
        // Здесь можно добавить вашу логику генерации ID, например, использовать счетчик или хранилище, чтобы гарантировать уникальность
        return System.currentTimeMillis(); // Или другая логика для генерации уникального ID
    }
}
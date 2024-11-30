package ru.oop.logic.commands;

import ru.oop.logic.other.Request;
import ru.oop.logic.other.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateWishlistCommand implements Command {
    private final WishlistService wishlistService;

    public CreateWishlistCommand(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/createWishlist (.+)"); // Шаблон для команды
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        String name = matched.group(1); // Получаем имя вишлиста из команды
        Long userId = currentUser.getId(); // Получаем userId текущего пользователя

        // Создаем вишлист с помощью сервиса
        Wishlist wishlist = wishlistService.createWishlist(userId, name);

        // Возвращаем созданный ответ
        return new Response("Вишлист '" + wishlist.getName() + "' успешно создан.");
    }
}
/*public class CreateWishlistCommand implements Command {
    private final WishlistService wishlistService;

    public CreateWishlistCommand(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/createWishlist (.+)");
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        String name = matched.group(1);
        Long userId = request.getUserId(); // Получение userId из запроса
        String responseMessage = wishlistService.createWishlist(userId, name).getName();
        return new Response("Вишлист '" + responseMessage + "' успешно создан."); // Паттерн не совпадает
    }
}*/
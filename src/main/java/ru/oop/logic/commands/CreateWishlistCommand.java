package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateWishlistCommand implements Command {
    private final WishlistService wishlistService;

    public CreateWishlistCommand(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public String getCommandPattern() {
        return "/createWishlist (.+)"; // Шаблон в виде строки
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        String name = matched.group(1); // Получаем имя вишлиста из команды
        Long userId = currentUser.getId(); // Получаем userId текущего пользователя

        // Создаем объект Wishlist с пустым id (он будет сгенерирован в репозитории) и пустым списком желаний
        Wishlist newWishlist = new Wishlist();

        // Создаем вишлист с помощью сервиса
        Wishlist createdWishlist = wishlistService.createWishlist(newWishlist);

        // Возвращаем созданный ответ
        return new Response("Вишлист '" + createdWishlist.getName() + "' успешно создан.");
    }
    @Override
    public void execute(String chatId, String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: /createwishlist <name>");
            return;
        }

        String wishlistName = args[0];
        wishlistService.createWishlist(new Wishlist());
        System.out.println("Wishlist created: " + wishlistName);
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
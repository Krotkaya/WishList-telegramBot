/*package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.OutputWriter;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.models.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddImageToWishCommand implements Command {
    private final WishlistService wishlistService;

    public AddImageToWishCommand(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("add image to wish (\\d+) (.+)"); // Регулярное выражение для команды
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        long wishId = Long.parseLong(matched.group(1)); // ID желания
        String imageUrl = matched.group(2); // URL изображения

        try {
            wishlistService.addImageToWish(wishId, imageUrl); // Добавьте метод в WishlistService
            return new Response("Изображение было добавлено к желанию.");
        } catch (Exception e) {
            return new Response("Произошла ошибка при добавлении изображения к желанию: " + e.getMessage());
        }
    }
}*/
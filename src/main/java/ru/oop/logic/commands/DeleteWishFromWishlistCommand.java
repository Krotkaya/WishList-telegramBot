package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteWishFromWishlistCommand implements Command {
    private final WishlistService wishlistService;

    public DeleteWishFromWishlistCommand(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/deleteWishFromWishlist (\\d+) (\\d+)");
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        Long wishlistId = Long.valueOf(matched.group(1)); // ID вишлиста
        Long wishId = Long.valueOf(matched.group(2)); // ID пожелания
        wishlistService.deleteWishFromWishlist(wishlistId, wishId); // Удаление пожелания
        return new Response("Пожелание с ID " + wishId + " успешно удалено из вишлиста с ID " + wishlistId + ".");
    }
}

package ru.oop.logic.commands;

import ru.oop.logic.other.Request;
import ru.oop.logic.other.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteWishlistCommand implements Command {
    private final WishlistService wishlistService;

    public DeleteWishlistCommand(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/deleteWishlist (\\d+)");
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        Long wishlistId = Long.valueOf(matched.group(1)); // ID вишлиста
        wishlistService.deleteWishlist(wishlistId); // Удаление вишлиста
        return new Response("Вишлист с ID " + wishlistId + " успешно удален.");
    }
}
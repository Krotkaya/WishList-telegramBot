package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.services.UserService;
import ru.oop.logic.models.Wishlist;
import ru.oop.logic.models.User;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClaimWishlistItemCommand implements Command {
    private final WishlistService wishlistService;
    private final UserService userService;

    public ClaimWishlistItemCommand(WishlistService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        String targetUsername = matched.group(1);
        long itemId = Long.parseLong(matched.group(2));

        User targetUser = userService.findByUsername(targetUsername);
        if (targetUser == null) {
            return new Response("Пользователь с именем '" + targetUsername + "' не найден.");
        }

        Wishlist wishlist = wishlistService.getWishlistsByUserId(targetUser.getId()).getFirst();
        wishlistService.markItemAsTaken(wishlist.getId(), itemId);

        return new Response("Пожелание с ID " + itemId + " было занято пользователем '" + currentUser.getTelegramId() + "'.");
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/claimItem (.+) (\\d+)");
    }
}

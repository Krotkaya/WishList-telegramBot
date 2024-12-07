package ru.oop.logic.commands;

import ru.oop.logic.models.Wishlist;
import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.services.UserService;
import ru.oop.logic.models.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewWishlistsCommand implements Command {
    private final WishlistService wishlistService;
    private final UserService userService;

    public ViewWishlistsCommand(WishlistService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        String targetUsername = matched.group(1); // Извлекаем имя пользователя
        User targetUser = userService.findByUsername(targetUsername);

        if (targetUser == null) {
            return new Response("Пользователь с именем '" + targetUsername + "' не найден.");
        }

        List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(targetUser.getId());

        // Формирование ответа
        StringBuilder responseBuilder = new StringBuilder("Вишлисты пользователя '" + targetUsername + "':\n");

        if (wishlists.isEmpty()) {
            responseBuilder.append("У пользователя нет вишлистов.");
        } else {
            for (Wishlist wishlist : wishlists) {
                responseBuilder.append("ID: ")
                        .append(wishlist.getId())
                        .append(", Название: ")
                        .append(wishlist.getName())
                        .append("\n");
            }
        }

        return new Response(responseBuilder.toString());
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/viewWishlists (.+)"); // Регулярное выражение для команды
    }
}
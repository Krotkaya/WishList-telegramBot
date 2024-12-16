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
    public Pattern getCommandPattern() {
        return Pattern.compile("/createWishlist (.+)");
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User user) {
        String name = matched.group(1);


        Wishlist newWishlist = new Wishlist();
        newWishlist.setName(name);
        newWishlist.setUser(user);

        Wishlist createdWishlist = wishlistService.createWishlist(newWishlist);

        return new Response("Вишлист '" + createdWishlist.getName() + "' успешно создан.");
    }
}
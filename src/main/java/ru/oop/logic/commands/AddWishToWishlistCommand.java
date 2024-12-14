package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;

public class AddWishToWishlistCommand implements Command {
    private final WishlistService wishlistService;

    public AddWishToWishlistCommand(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public String getCommandPattern() {
        return "add wish (\\d+) (.+)"; // Пример команды: "add wish <wishlistId> <wishDescription>"
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        try {
            // Проверяем, что пользователь существует
            if (currentUser == null) {
                return new Response("Пользователь не найден. Пожалуйста, зарегистрируйтесь.");
            }

            // Извлекаем данные из команды
            long wishlistId = Long.parseLong(matched.group(1)); // ID списка желаний
            String wishDescription = matched.group(2); // Описание желания

            if (wishDescription == null || wishDescription.isBlank()) {
                return new Response("Описание желания не может быть пустым.");
            }

            // Создаем объект желания
            Wish wish = new Wish(wishDescription, null); // Передаём null для Wishlist, если он будет установлен позже

            // Добавляем желание в список через сервис
            wishlistService.addWishToWishlist(wishlistId, wish);

            return new Response("Желание успешно добавлено в список желаний.");
        } catch (NumberFormatException e) {
            return new Response("Неверный формат идентификатора списка желаний.");
        } catch (IllegalArgumentException e) {
            return new Response("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            return new Response("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }

    @Override
    public void execute(String chatId, String[] args) {

    }
}

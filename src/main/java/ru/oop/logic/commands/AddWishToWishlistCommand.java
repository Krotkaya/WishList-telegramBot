package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.services.UserService;
import ru.oop.logic.models.Wish;
import ru.oop.logic.models.User;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddWishToWishlistCommand implements Command{
    private final WishlistService wishlistService;
    private final UserService userService;

    public AddWishToWishlistCommand(WishlistService wishlistService, UserService userService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @Override
    public Pattern getCommandPattern() {
        // Возвращаем регулярное выражение в виде Pattern. Лучше сделать один раз. Поместить компеляцию в класс
        return Pattern.compile("/add wish (\\d+) (.+)");
    }

    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        // Получаем идентификатор списка желаний и описание желания из матчера
        long wishlistId = Long.parseLong(matched.group(1)); // id из первой группы
        String wishDescription = matched.group(2); // описание из второй группы

        // Создаём новый объект Wish
        Wish wish = new Wish(null, wishDescription, true);

        // Добавление желания в список желаний с помощью метода сервиса
        try {
            wishlistService.addWishToWishlist(wishlistId, wish); // Добавляем желание в указанный wishlist
            return new Response("Желание добавлено в список желаний."); // Ответ пользователю
        } catch (Exception e) {
            return new Response("Произошла ошибка при добавлении желания в список желаний: " + e.getMessage());
        }
    }
}




    /*public void execute(Request request, OutputWriter outputWriter, Long chatId) {
        // Получаем текст сообщения из объекта Request
        String messageText = request.getMessage(); // Используем доступный метод getMessage()

        // Получение пользователя по chatId
        User user = userService.getUserByChatId(chatId);

        if (user == null) {
            outputWriter.write("Пользователь не найден. Пожалуйста, сначала зарегистрируйтесь.");
            return;
        }

        // Проверка на наличие текста сообщения
        if (messageText == null || messageText.isEmpty()) {
            outputWriter.write("Сообщение пустое. Пожалуйста, укажите идентификатор списка желаний и описание желания.");
            return;
        }

        // Извлекаем wishlistId из сообщения
        String[] parts = messageText.split(" ");
        if (parts.length < 2) {
            outputWriter.write("Необходимо указать идентификатор списка желаний и описание желания.");
            return;
        }

        long wishlistId;
        try {
            wishlistId = Long.parseLong(parts[0]); // Убираем первый аргумент как wishlistId
        } catch (NumberFormatException e) {
            outputWriter.write("Некорректный идентификатор списка желаний.");
            return;
        }

        // Остальная часть сообщения - это описание желания
        String wishDescription = messageText.substring(parts[0].length()).trim();

        // Создаем объект Wish
        Wish wish = new Wish(null, wishDescription); // null для id, если вы будете генерировать его на стороне сервиса

        // Добавление желания в список желаний с помощью метода сервиса
        try {
            wishlistService.addWishToWishlist(wishlistId, wish); // Добавляем желание в указанный wishlist
            outputWriter.write("Желание добавлено в список желаний."); // Ответ пользователю
        } catch (Exception e) {
            outputWriter.write("Произошла ошибка при добавлении желания в список желаний: " + e.getMessage());
        }
    }
}*/


    /*@Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/addWishToWishlist (.+)");
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        Long wishlistId = request.getWishlistId(); // Получаем wishlistId из запроса
        String wishText = matched.group(1); // Извлекаем текст желания из matched

        wishlistService.addWishToWishlist(wishlistId, new Wish(wishText));
        return new Response("Пожелание '" + wishText + "' успешно добавлено в вишлист с ID " + wishlistId + ".");
        /*Matcher matcher = getCommandPattern().matcher(request.getMessage());
        if (matcher.matches()) {
            Long wishlistId = request.getWishlistId(); // Получение wishlistId из запроса
            String wishText = matcher.group(1);
            wishlistService.addWishToWishlist(wishlistId, new Wish(wishText));
            return new Response("Пожелание '" + wishText + "' успешно добавлено в вишлист с ID " + wishlistId + ".");
        }
        return null;
    }*/

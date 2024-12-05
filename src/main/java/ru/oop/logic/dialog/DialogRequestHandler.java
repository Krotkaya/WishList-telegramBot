package ru.oop.logic.dialog;


/*public class DialogRequestHandler implements RequestHandler {
    private UserService dialogService;

    public DialogRequestHandler(UserService dialogService) {
        this.dialogService = dialogService;
    }

    @Override
    public void handle(Request request, OutputWriter writer) {
        String message = request.getMessage();
        String response;

        if (message.equals("/createWishlist")) {
            response = dialogService.createWishlist(1);
        } else if (message.startsWith("/addWishToWishlist")) {
            String wishText = message.substring(20); // Получаем текст пожелания
            response = dialogService.addWishToWishlist(1, wishText);
        } else if (message.startsWith("/deleteWishFromWishlist")) {
            String wishText = message.substring(23); // Получаем текст пожелания
            response = dialogService.deleteWishFromWishlist(1, wishText);
        } else if (message.equals("/deleteWishlist")) {
            response = dialogService.deleteWishlist(1);
        } else if (message.equals("/start")) {
            response = "Я Wishlist бот";
        } else {
            response = "Неизвестная команда.";
        }

        Response res = new Response(response);
        writer.write(res);
    }
}*/


/*import ru.oop.logic.other.RequestHandler;
import ru.oop.logic.other.Request;
import ru.oop.logic.other.OutputWriter;
import ru.oop.logic.other.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialogRequestHandler implements RequestHandler {
    private final DialogService dialogService;

    public DialogRequestHandler(DialogService dialogService) {
        this.dialogService = dialogService;
    }

    @Override
    public void handle(Request request, OutputWriter writer) {
        String message = request.getMessage();
        String response;

        // Динамически получаем userId, wishlistId и wishId

        Long userId = 1L; // Заменить на реальный userId
        Long wishlistId = 1L; // Заменить на реальный wishlistId
        Long wishId = 1L; // Заменить на реальный wishId (для тестирования)

        if (message.startsWith("/createWishlist")) {
            String name = message.substring("/createWishlist".length()).trim();
            response = dialogService.createWishlist(userId, name);
        } else if (message.startsWith("/addWishToWishlist")) {
            Pattern pattern = Pattern.compile("/addWishToWishlist (.+)");
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                String wishText = matcher.group(1);
                response = dialogService.addWishToWishlist(wishlistId, wishText);
            } else {
                response = "Ошибка: Не указано пожелание.";
            }
        } else if (message.startsWith("/deleteWishFromWishlist")) {// Желаемое ID нужно извлечь из текста команды
            Pattern pattern = Pattern.compile("/deleteWishFromWishlist (\\d+)");
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                wishId = Long.valueOf(matcher.group(1));
                response = dialogService.deleteWishFromWishlist(wishlistId, wishId);
            } else {
                response = "Ошибка: Не указано ID пожелания.";
            }
        } else if (message.startsWith("/deleteWishlist")) {
            // ID вишлиста извлекается
            response = dialogService.deleteWishlist(wishlistId);
        } else {
            response = "Неизвестная команда.";
        }

        Response res = new Response(response);
        writer.write(res);
    }
}*/



import ru.oop.logic.commands.Command;
import ru.oop.logic.other.RequestHandler;
import ru.oop.logic.other.Request;
import ru.oop.logic.other.OutputWriter;
import ru.oop.logic.other.Response;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;
import java.util.List;

public class DialogRequestHandler implements RequestHandler {
    private final List<Command> commands;

    public DialogRequestHandler(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void handle(Request request, OutputWriter writer) {
        Response response = null;

        // Сначала извлекаем сообщение и команды
        String message = request.getMessage();

        for (Command command : commands) {
            Matcher matcher = command.getCommandPattern().matcher(message);
            if (matcher.matches()) {
                // Если нашло совпадение, обновим response
                response = command.executeCommand(request, matcher, null);
                break; // Прекращаем цикл, если команда найдена
            }
        }

        if (response == null) {
            response = new Response("Неизвестная команда.");
        }

        writer.write(response.getMessage());
    }
}

        /*Response response = null;

        for (Command command : commands) {
            response = command.executeCommand(request);
            if (response != null) {
                break;
            }
        }

        if (response == null) {
            response = new Response("Неизвестная команда.");
        }

        writer.write(response);*/
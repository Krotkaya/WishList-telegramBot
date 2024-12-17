package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.services.UserService;
import ru.oop.logic.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartCommand implements Command {
    private final UserService userService;

    public StartCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/start(?:\\s+(\\w+))?");
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        try {
            Long telegramId = currentUser.getTelegramId();
            String argument = matched.group(1);

            if (argument != null) {
                userService.updateUsernameIfChanged(telegramId, argument);
                return new Response("Имя пользователя обновлено на: " + argument);
            } else if (currentUser != null) {
                String actualUsername = currentUser.getUsername();
                if (actualUsername != null && !actualUsername.isEmpty()) {
                    userService.updateUsernameIfChanged(telegramId, actualUsername);
                }
                return new Response("Добро пожаловать, @" + currentUser.getUsername() + "!");
            }


            return new Response("Добро пожаловать, @" + (currentUser != null ? currentUser.getUsername() : "новый пользователь") + "!");
        } catch (Exception e) {
            return new Response("Произошла ошибка: " + e.getMessage());
        }
    }
}

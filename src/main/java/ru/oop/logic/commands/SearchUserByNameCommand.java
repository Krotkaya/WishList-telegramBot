package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.services.UserService;
import ru.oop.logic.models.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchUserByNameCommand implements Command {
    private final UserService userService;

    public SearchUserByNameCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/searchUser (.+)");
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        try {
            if (currentUser == null) {
                return new Response("Пользователь не найден. Пожалуйста, зарегистрируйтесь.");
            }

            String usernamePart = matched.group(1).trim();

            if (usernamePart.isEmpty()) {
                return new Response("Имя для поиска не может быть пустым.");
            }

            List<User> matchingUsers = userService.findUsersByUsername(usernamePart);

            if (matchingUsers.isEmpty()) {
                return new Response("Пользователи с именем, содержащим '" + usernamePart + "', не найдены.");
            }

            StringBuilder responseMessage = new StringBuilder("Найдены следующие пользователи:\n");
            for (User user : matchingUsers) {
                responseMessage.append("- ").append(user.getUsername()).append("\n");
            }

            return new Response(responseMessage.toString());
        } catch (Exception e) {
            return new Response("Произошла ошибка при поиске пользователей: " + e.getMessage());
        }
    }
}

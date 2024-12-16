package ru.oop.platforms.console;

import ru.oop.logic.OutputWriter;
import ru.oop.logic.Request;
import ru.oop.logic.handlers.RequestHandler;
import ru.oop.logic.models.User;
import ru.oop.logic.services.UserService;
import ru.oop.platforms.InputReader;

public class ConsoleBot implements ru.oop.platforms.Bot {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final RequestHandler requestHandler;
    private final UserService userService;

    public ConsoleBot(InputReader inputReader, OutputWriter outputWriter, RequestHandler requestHandler, UserService userService) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.requestHandler = requestHandler;
        this.userService = userService;
    }

    @Override
    public void startBot() {
        System.out.println("Добро пожаловать в консольного бота! Введите ваше сообщение.");
        while (true) {
            Request request = inputReader.read();

            User user = userService.getUserById((long) -1);
            if (user == null) {
                user = new User();
                user.setTelegramId((long) -1);
                user.setUsername("Console");
                userService.saveUser(user);
            }

            requestHandler.handle(request, outputWriter, user);
        }
    }
}
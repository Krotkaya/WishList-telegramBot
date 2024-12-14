package ru.oop.platforms.telegram;
import ru.oop.logic.commands.SetReminderCommand;
import ru.oop.logic.commands.CommandRegistry;
import ru.oop.logic.commands.Command;
import ru.oop.logic.DialogRequestHandler;
import ru.oop.logic.services.UserService;

public class BotHandler {
    private final DialogRequestHandler dialogRequestHandler;
    private final UserService userService;
    private final TelegramBot telegramBot;

    public BotHandler(DialogRequestHandler dialogRequestHandler, UserService userService) {
        this.dialogRequestHandler = dialogRequestHandler;
        this.userService = userService;
        this.telegramBot = new TelegramBot(dialogRequestHandler, userService);
    }

    public void initialize() {
        // Регистрация команды
        CommandRegistry.register("reminder", new SetReminderCommand(telegramBot));
    }

    public String handleMessage(String message, String chatId) {
        String[] parts = message.split(" ", 2); // Разделяем команду и аргументы
        String commandName = parts[0].replace("/", ""); // Убираем слэш
        String[] args = parts.length > 1 ? parts[1].split(" ") : new String[0];

        if (CommandRegistry.hasCommand(commandName)) {
            Command command = CommandRegistry.getCommand(commandName);
            command.execute(chatId, args); // Передаём реальный chatId
            return "Command executed successfully.";
        } else {
            return "Unknown command.";
        }
    }
}
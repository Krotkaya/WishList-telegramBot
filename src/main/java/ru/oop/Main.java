package ru.oop;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.oop.logic.OutputWriter;
import ru.oop.logic.handlers.ConsoleRequestHandler;
import ru.oop.logic.commands.*;
import ru.oop.logic.repositories.*;
import ru.oop.logic.services.ReminderService;
import ru.oop.logic.services.UserService;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.services.ReminderService;
import ru.oop.logic.services.WishlistServiceImpl;
import ru.oop.logic.handlers.TelegramRequestHandler;
import ru.oop.platforms.console.ConsoleBot;
import ru.oop.platforms.console.ConsoleInputReader;
import ru.oop.platforms.console.ConsoleOutputWriter;
import ru.oop.platforms.telegram.TelegramBot;
import ru.oop.platforms.telegram.TelegramOutputWriter;

public class Main {
    private static char[] chatId;

    public static void main(String[] args) {
        WishlistRepository wishlistRepository = new WishlistRepositoryImpl();
        WishRepository wishRepository = new WishRepositoryImpl();
        WishlistService wishlistService = new WishlistServiceImpl(wishlistRepository, wishRepository);
        ReminderService reminderService = new ReminderService();

        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserService(userRepository);

        Command[] commands = {
                new AddWishToWishlistCommand(wishlistService),
                new ClaimWishlistItemCommand(wishlistService, userService),
                new CreateWishlistCommand(wishlistService),
                new DeleteWishFromWishlistCommand(wishlistService),
                new DeleteWishlistCommand(wishlistService),
                new SetReminderCommand(reminderService),
                new ViewWishlistsCommand(wishlistService, userService)
        };


        TelegramRequestHandler telegramRequestHandler = new TelegramRequestHandler();
        telegramRequestHandler.setCommands(commands);

        TelegramOutputWriter outputWriter = new TelegramOutputWriter();
        TelegramBot telegramBot = new TelegramBot(telegramRequestHandler, outputWriter, userService);
        outputWriter.setBot(telegramBot);
        reminderService.setOutputWriter(outputWriter);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
            System.out.println("Бот успешно запущен!");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ConsoleRequestHandler consoleRequestHandler = new ConsoleRequestHandler();
//        consoleRequestHandler.setCommands(commands);
//
//        ConsoleOutputWriter consoleOutputWriter = new ConsoleOutputWriter();
//        ConsoleBot consoleBot = new ConsoleBot(new ConsoleInputReader(), consoleOutputWriter, consoleRequestHandler, userService);
//        reminderService.setOutputWriter(consoleOutputWriter);
//        consoleBot.startBot();

    }
}

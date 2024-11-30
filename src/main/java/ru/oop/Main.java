package ru.oop;

import ru.oop.logic.other.ConsoleRequestHandler;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.oop.logic.commands.*;
import ru.oop.logic.services.UserService;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.services.WishlistServiceImpl;
import ru.oop.logic.repositories.WishlistRepository;
import ru.oop.logic.repositories.WishlistRepositoryImpl;
import ru.oop.logic.dialog.DialogRequestHandler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Сначала создаем экземпляр WishListService
        //WishlistService wishlistService = new WishlistServiceImpl(...); // передать необходимые параметры

        //UserService userService = new UserService(); // Предполагается, что у вас пустой конструктор
        // Инициализируем список команд

        WishlistRepository wishlistRepository = new WishlistRepositoryImpl(); // Создание конкретной реализации

        // Создаем экземпляр WishListService, передавая репозиторий
        WishlistService wishlistService = new WishlistServiceImpl(wishlistRepository);

        // Создаем экземпляр UserService
        UserService userService = new UserService();
        List<Command> commands = List.of(
                new CreateWishlistCommand(wishlistService),
                new AddWishToWishlistCommand(wishlistService, userService),
                new DeleteWishFromWishlistCommand(wishlistService),
                new DeleteWishlistCommand(wishlistService)
        );

        // Создаем обработчик запросов и передаем ему список команд
        DialogRequestHandler requestHandler = new DialogRequestHandler(commands);

        // Теперь создаем объект бота
        String botToken = "7920073441:AAGqiifhrFOHl-qAKS7VcxsbIof6Fcj9VxA"; // Замените вашим токеном
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ru.oop.platforms.telegram.TelegramBot(requestHandler, userService));
            System.out.println("Бот успешно запущен!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*package ru.oop;

import ru.oop.logic.other.ConsoleRequestHandler;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.oop.logic.commands.*;
import ru.oop.logic.WishlistService;
import ru.oop.logic.WishlistServiceImpl; // реализация
import ru.oop.logic.dialog.DialogRequestHandler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleRequestHandler requestHandler = new ConsoleRequestHandler(); // Создаем обработчик запросов
        String botToken = "7920073441:AAGqiifhrFOHl-qAKS7VcxsbIof6Fcj9VxA";
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ru.oop.platforms.telegram.TelegramBot(requestHandler, botToken));
            System.out.println("Бот успешно запущен!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Сначала создаем экземпляр WishListService
        WishlistService wishlistService = new WishlistServiceImpl(...); // передать необходимые параметры

        // Инициализируем список команд
        List<Command> commands = List.of(
                new CreateWishlistCommand(wishlistService),
                new AddWishToWishlistCommand(wishlistService),
                new DeleteWishFromWishlistCommand(wishlistService),
                new DeleteWishlistCommand(wishlistService)
        );

        // Создаем обработчик запросов и передаем ему список команд
        DialogRequestHandler requestHandler = new DialogRequestHandler(commands);
    }
}
*/








/*package platforms.telegram;

import logic.ConsoleRequestHandler;

public class Main {
    public static void main(String[] args) {
        ConsoleRequestHandler requestHandler = new ConsoleRequestHandler(); // Создаем обработчик запросов
        String botToken = "YourBotToken";
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());
            System.out.println("Бот успешно запущен!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TelegramBot bot = new TelegramBot(requestHandler, botToken);  Создаем объект бота
    }
}*/


/*import logic.ConsoleRequestHandler;
import platforms.console.ConsoleBot;
import platforms.console.ConsoleInputReader;
import platforms.console.ConsoleOutputWriter;
import platforms.telegram.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());
            System.out.println("Бот успешно запущен!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/

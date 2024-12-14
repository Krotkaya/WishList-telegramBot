package ru.oop;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.oop.logic.commands.*;
import ru.oop.logic.services.UserService;
import ru.oop.logic.services.WishlistService;
import ru.oop.logic.services.WishlistServiceImpl;
import ru.oop.logic.repositories.WishlistRepository;
import ru.oop.logic.repositories.WishlistRepositoryImpl;
import ru.oop.logic.DialogRequestHandler;

import java.util.List;

import ru.oop.platforms.telegram.TelegramBot;

//public class Main {
//    public static void main(String[] args) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            // Идентификатор пользователя (chatId)
//            Long chatId = 123456789L;
//
//            // Поиск пользователя по chatId
//            User user = session.createQuery("FROM User WHERE chatId = :chatId", User.class)
//                    .setParameter("chatId", chatId)
//                    .uniqueResult();
//
//            if (user == null) {
//                // Если пользователь не найден, создаем нового
//                user = new User();
//                user.setUsername("John Doe");
//                user.setChatId(chatId);
//                session.save(user);
//                System.out.println("Новый пользователь создан: " + user.getUsername());
//            } else {
//                System.out.println("Пользователь найден: " + user.getUsername());
//            }
//
//            // Создаем новый список желаний
//            Wishlist newWishlist = new Wishlist();
//            newWishlist.setName("Another Wish List");
//            newWishlist.setUser(user); // Устанавливаем связь с пользователем
//            session.save(newWishlist);
//
//            System.out.println("Список желаний добавлен: " + newWishlist.getName());
//
//            transaction.commit();
//            System.out.println("Данные успешно сохранены!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateUtil.getSessionFactory().close(); // Закрываем SessionFactory
//        }
//    }
//}

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр репозитория для работы со списками желаний
        WishlistRepository wishlistRepository = new WishlistRepositoryImpl();

        // Создаем экземпляр сервиса для работы со списками желаний
        WishlistService wishlistService = new WishlistServiceImpl(wishlistRepository);

        // Создаем экземпляр сервиса для работы с пользователями
        UserService userService = new UserService(); // Если нужен репозиторий, добавьте его в конструктор

        TelegramBot telegramBot = new TelegramBot(null, userService);

        // Создаем список команд, которые бот может обрабатывать
        List<Command> commands = List.of(
                new CreateWishlistCommand(wishlistService),
                new AddWishToWishlistCommand(wishlistService),
                new DeleteWishFromWishlistCommand(wishlistService),
                new DeleteWishlistCommand(wishlistService),
                new SetReminderCommand(telegramBot) // Команда для напоминаний
        );

        DialogRequestHandler dialogRequestHandler = new DialogRequestHandler(commands);
        telegramBot.setDialogRequestHandler(dialogRequestHandler);

        // Создаем обработчик запросов для управления командами
        DialogRequestHandler requestHandler = new DialogRequestHandler(commands);

        // Создаем и регистрируем Telegram бота
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ru.oop.platforms.telegram.TelegramBot(requestHandler, userService));
            System.out.println("Бот успешно запущен!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

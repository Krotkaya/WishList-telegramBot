package ru.oop;

import ru.oop.logic.ConsoleRequestHandler;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        ConsoleRequestHandler requestHandler = new ConsoleRequestHandler(); // Создаем обработчик запросов
        String botToken = "7920073441:AAGqiifhrFOHl-qAKS7VcxsbIof6Fcj9VxA"; // Замените на действительный токен вашего бота
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ru.oop.platforms.telegram.TelegramBot(requestHandler, botToken));
            System.out.println("Бот успешно запущен!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


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

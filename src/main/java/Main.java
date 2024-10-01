import logic.EchoRequestHandler;
import platforms.console.ConsoleBot;
import platforms.console.ConsoleInputReader;
import platforms.console.ConsoleOutputWriter;
import platforms.telegram.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // Запуск Telegram бота
        Runnable telegramBotTask = () -> {
            try {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(new TelegramBot());
                System.out.println("Telegram бот успешно запущен!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Запуск консольного бота
        Runnable consoleBotTask = () -> {
            ConsoleInputReader inputReader = new ConsoleInputReader();
            ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
            EchoRequestHandler requestHandler = new EchoRequestHandler();
            ConsoleBot consoleBot = new ConsoleBot(inputReader, outputWriter, requestHandler);

            consoleBot.startBot();
        };

        // Запуск потоков
        executorService.submit(telegramBotTask);
        executorService.submit(consoleBotTask);

        // Остановка ExecutorService корректно после завершения задач
        executorService.shutdown();
    }
}


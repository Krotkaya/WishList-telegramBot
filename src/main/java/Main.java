import logic.ConsoleRequestHandler;
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
}

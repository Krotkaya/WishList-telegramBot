package platforms.telegram;

import logic.*;
import platforms.Bot;
import platforms.InputReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

public class TelegramBot extends TelegramLongPollingBot implements Bot {
    private final String botToken;  // Переменная для хранения токена

    // Конструктор загружает токен из .env файла
    public TelegramBot() {
        Dotenv dotenv = Dotenv.load();
        botToken = dotenv.get("TELEGRAM_BOT_TOKEN");
    }

    @Override
    public void startBot() {
        // Реализация метода для консольного запуска бота
        System.out.println("Telegram бот успешно запущен!");
    }

    @Override
    public String getBotUsername() {
        return "YourBestWishListBot";  // Укажите имя своего бота
    }

    @Override
    public String getBotToken() {
        return botToken;  // Возвращаем токен из .env
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();  // Текст сообщения
            long chatId = update.getMessage().getChatId();       // Идентификатор чата

            String responseText = BotHandler.handleMessage(messageText);  // Логика обработки сообщения

            SendMessage message = new SendMessage();
            message.setChatId(Long.toString(chatId));  // Устанавливаем идентификатор чата
            message.setText(responseText);  // Устанавливаем текст ответа

            try {
                execute(message);  // Отправляем сообщение пользователю
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

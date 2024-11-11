package ru.oop.platforms.telegram;
import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.RequestHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


public class TelegramBot extends TelegramLongPollingBot {
    private final RequestHandler requestHandler;
    private final String botToken;

    public TelegramBot(RequestHandler requestHandler, String botToken) {
        this.requestHandler = requestHandler; // сохраняем объект RequestHandler
        this.botToken = botToken; // сохраняем токен бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) { // проверяем, что сообщение есть и оно текстовое
            String incomingMessage = update.getMessage().getText();
            Request request = new Request(incomingMessage);

            String chatId = String.valueOf(update.getMessage().getChatId());
            TelegramOutputWriter telegramOutputWriter = new TelegramOutputWriter(chatId, this);

            // Обработка запроса
            requestHandler.handle(request, telegramOutputWriter);
        }
    }

    @Override
    public String getBotUsername() {
        return "YourBotUsername";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
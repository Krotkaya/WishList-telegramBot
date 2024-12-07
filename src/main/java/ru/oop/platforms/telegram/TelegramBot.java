package ru.oop.platforms.telegram;
import ru.oop.logic.Request;
import ru.oop.logic.OutputWriter;
import ru.oop.logic.dialog.DialogRequestHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.oop.logic.models.User;
import ru.oop.logic.services.UserService;


public class TelegramBot extends TelegramLongPollingBot {
    private final DialogRequestHandler dialogRequestHandler;
    private final UserService userService;
    private final String botToken;

    public TelegramBot(DialogRequestHandler dialogRequestHandler, UserService userService, String botToken) {
        this.dialogRequestHandler = dialogRequestHandler;
        this.userService = userService;
        this.botToken = botToken; // Инициализируем токен
    }

    @Override
    public String getBotUsername() {
        return "YourBotUsername";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            User currentUser = userService.getUserByChatId(chatId);

            // Обрабатываем входящее сообщение
            Request request = new Request(messageText);
            OutputWriter outputWriter = new TelegramOutputWriter(String.valueOf(chatId), this);
            dialogRequestHandler.handle(request, outputWriter);
        }
    }


}



/*public class TelegramBot extends TelegramLongPollingBot {
    private final RequestHandler requestHandler;
    private final String botToken;

    public TelegramBot(RequestHandler requestHandler, String botToken) {
        this.requestHandler = requestHandler; // сохраняем объект RequestHandler
        this.botToken = botToken; // сохраняем токен бота
    }*/


    /*public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) { // проверяем, что сообщение есть и оно текстовое
            String incomingMessage = update.getMessage().getText();
            Request request = new Request(incomingMessage);

            String chatId = String.valueOf(update.getMessage().getChatId());
            TelegramOutputWriter telegramOutputWriter = new TelegramOutputWriter(chatId, this);

            // Обработка запроса
            requestHandler.handle(request, telegramOutputWriter);
        }
    }*/

package ru.oop.platforms.telegram;
import io.github.cdimascio.dotenv.Dotenv;
import ru.oop.logic.Request;
import ru.oop.logic.OutputWriter;
import ru.oop.logic.handlers.TelegramRequestHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.oop.logic.models.User;
import ru.oop.logic.services.ReminderService;
import ru.oop.logic.services.UserService;


public class TelegramBot extends TelegramLongPollingBot {
    private final OutputWriter outputWriter;
    private final TelegramRequestHandler telegramRequestHandler;
    private final UserService userService;
    private final String botToken; // Добавьте это поле

    public TelegramBot(TelegramRequestHandler telegramRequestHandler, OutputWriter outputWriter, UserService userService) {
        this.telegramRequestHandler = telegramRequestHandler;
        this.userService = userService;
        this.outputWriter = outputWriter;
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TELEGRAM_BOT_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("TELEGRAM_BOT_TOKEN is not set in the environment variables.");
        }
        this.botToken = token; // Устанавливаем токен
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
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        String messageText = update.getMessage().getText();
        long telegramId = update.getMessage().getFrom().getId();
        long chatId = update.getMessage().getChatId();


        // Получаем пользователя по chatId или создаём нового
        User user = userService.getUserByTelegramId(telegramId);
        if (user == null) {
            user = new User();
            user.setTelegramId(telegramId);
            user.setUsername(update.getMessage().getFrom().getUserName());
            userService.saveUser(user);
        }

        Request request = new Request(messageText);
        ((TelegramOutputWriter) outputWriter).setChatId(String.valueOf(chatId));
        telegramRequestHandler.handle(request, outputWriter, user);
    }
}
package ru.oop.platforms.telegram;
import io.github.cdimascio.dotenv.Dotenv;
import ru.oop.logic.Request;
import ru.oop.logic.OutputWriter;
import ru.oop.logic.DialogRequestHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.oop.logic.models.User;
import ru.oop.logic.services.UserService;
import ru.oop.logic.scheduler.ReminderScheduler;


public class TelegramBot extends TelegramLongPollingBot {
    private DialogRequestHandler dialogRequestHandler;
    private final UserService userService;
    private final String botToken; // Добавьте это поле
    private final ReminderScheduler scheduler = new ReminderScheduler();

    public void sendReminder(String chatId, String message, long delayInMillis) {
        scheduler.scheduleReminder(delayInMillis, () -> sendMessage(chatId, message));
    }

    public void sendMessage(String chatId, String message) {
        // Реализация отправки сообщения через Telegram API
        System.out.printf("Sending to %s: %s%n", chatId, message);
    }


    public TelegramBot(DialogRequestHandler dialogRequestHandler, UserService userService) {
        this.dialogRequestHandler = dialogRequestHandler;
        this.userService = userService;
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

    public void setDialogRequestHandler(DialogRequestHandler requestHandler) {
        this.dialogRequestHandler = requestHandler;
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

            // Получаем пользователя по chatId или создаём нового
            User currentUser = userService.getUserByChatId(chatId);
            if (currentUser == null) {
                currentUser = new User();
                currentUser.setChatId(chatId);
                currentUser.setUsername(update.getMessage().getFrom().getUserName()); // Telegram Username
                userService.saveUser(currentUser);
            }//по уму сделать сервис которые реализует этот if

            // Обрабатываем команды
            if (messageText.startsWith("/newlist ")) {
                String wishlistName = messageText.substring(9); // Имя нового списка
                userService.addWishlistToUser(currentUser, wishlistName);

                // Ответ пользователю
                OutputWriter outputWriter = new TelegramOutputWriter(String.valueOf(chatId), this);
                outputWriter.write("Новый список желаний \"" + wishlistName + "\" добавлен!");
            } else {
                // Передаём сообщение в диалоговый обработчик
                Request request = new Request(messageText);
                OutputWriter outputWriter = new TelegramOutputWriter(String.valueOf(chatId), this);
                dialogRequestHandler.handle(request, outputWriter);
            }
        }
    }
//Одна и та же версия проекта, в основной ветке был итоговый код нашего проекта, в репрозиториях был юзер и виш, и все работало

}

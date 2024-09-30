package platforms.telegram;

import logic.*;
import platforms.InputReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot implements platforms.Bot {
//    private final InputReader inputReader;
//    private final OutputWriter outputWriter;
//    private final RequestHandler requestHandler;

//    public TelegramBot(InputReader inputReader, OutputWriter outputWriter, RequestHandler requestHandler) {
//        this.inputReader = inputReader;
//        this.outputWriter = outputWriter;
//        this.requestHandler = requestHandler;
//    }

    public TelegramBot() {

    }

    @Override
    public void startBot() {
    }

    @Override
    public String getBotUsername() {
        return "YourBestWishListBot"; // Укажи имя своего бота
    }

    @Override
    public String getBotToken() {
        return " "; // Укажи токен, полученный от BotFather
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText(); // Объявляем один раз
            long chatId = update.getMessage().getChatId();       // Объявляем один раз

            String responseText = BotHandler.handleMessage(messageText);

            SendMessage message = new SendMessage();
            message.setChatId(Long.toString(chatId)); // Преобразуем chatId в строку
            message.setText(responseText);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

package ru.oop.platforms.telegram;

import ru.oop.logic.OutputWriter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramOutputWriter implements OutputWriter {
    private String chatId;
    private TelegramBot bot;

    @Override
    public void write(String response) {
        if (chatId == null) {
            return;
        }
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(response)
                .build();

        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setBot(TelegramBot bot) {
        this.bot = bot;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}

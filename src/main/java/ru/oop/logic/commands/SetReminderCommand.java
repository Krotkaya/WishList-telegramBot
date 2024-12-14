package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.models.User;
import ru.oop.platforms.telegram.TelegramBot;

import java.util.regex.Matcher;

public class SetReminderCommand implements Command {
    private final TelegramBot telegramBot;

    public SetReminderCommand(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void execute(String chatId, String[] args) {
        if (args.length < 2) {
            telegramBot.sendMessage(chatId, "Usage: /reminder <time_in_minutes> <message>");
            return;
        }

        try {
            long delay = Long.parseLong(args[0]) * 60 * 1000;
            String reminderMessage = args[1];
            telegramBot.sendReminder(chatId, reminderMessage, delay);
            telegramBot.sendMessage(chatId, "Reminder set!");
        } catch (NumberFormatException e) {
            telegramBot.sendMessage(chatId, "Invalid time format. Please provide time in minutes.");
        }
    }
    @Override
    public String getCommandPattern() {
        return "/reminder (\\d+) (.+)";
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {

        if (currentUser == null) {
            return new Response("Ошибка: Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь, чтобы использовать бот.");
        }

        if (matched.group(1) == null || matched.group(2) == null) {
            return new Response("Usage: /reminder <time_in_minutes> <message>");
        }


        String chatId = currentUser.getChatId().toString();
        try {
            long delay = Long.parseLong(matched.group(1)) * 60 * 1000;
            String reminderMessage = matched.group(2);

            telegramBot.sendReminder(chatId, reminderMessage, delay);

            return new Response("Напоминание установлено на " + matched.group(1) + " минут: " + reminderMessage);
        } catch (NumberFormatException e) {
            return new Response("Ошибка: Неверный формат времени. Пожалуйста, укажите время в минутах.");
        }
    }
}
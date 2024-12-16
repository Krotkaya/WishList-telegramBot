package ru.oop.logic.commands;

import ru.oop.logic.Request;
import ru.oop.logic.Response;
import ru.oop.logic.models.User;
import ru.oop.logic.services.ReminderService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetReminderCommand implements Command {
    private final ReminderService reminderService;

    public SetReminderCommand(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @Override
    public Pattern getCommandPattern() {
        return Pattern.compile("/reminder (\\d+) (.+)");
    }

    @Override
    public Response executeCommand(Request request, Matcher matched, User currentUser) {
        if (currentUser == null) {
            return new Response("Ошибка: Вы не зарегистрированы. Пожалуйста, зарегистрируйтесь, чтобы использовать бот.");
        }

        if (matched.group(1) == null || matched.group(2) == null) {
            return new Response("Usage: /reminder <time_in_minutes> <message>");
        }

        try {
            long delay = Long.parseLong(matched.group(1)) * 60 * 1000;
            String reminderMessage = matched.group(2);

            reminderService.scheduleReminder(delay, reminderMessage, currentUser);

            return new Response("Напоминание установлено на " + matched.group(1) + " минут(ы): " + reminderMessage);
        } catch (NumberFormatException e) {
            return new Response("Ошибка: Неверный формат времени. Пожалуйста, укажите время в минутах.");
        }
    }
}

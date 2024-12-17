package ru.oop.logic.services;

import ru.oop.logic.OutputWriter;
import ru.oop.logic.models.User;
import ru.oop.platforms.telegram.TelegramOutputWriter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReminderService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private OutputWriter outputWriter;

    public void scheduleReminder(long delayInMillis, String reminderMessage, User user) {
        Runnable reminderTask = () -> {

            long chatId = user.getTelegramId();
            ((TelegramOutputWriter) outputWriter).setChatId(String.valueOf(chatId));
            outputWriter.write(reminderMessage);
        };

        scheduler.schedule(reminderTask, delayInMillis, TimeUnit.MILLISECONDS);
    }

    public void setOutputWriter(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }
}

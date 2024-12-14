package ru.oop.logic.scheduler;

import ru.oop.platforms.telegram.TelegramBot;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Работает сам механизм,

public class ReminderService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final TelegramBot telegramBot;

    public ReminderService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void scheduleDailyReminder(String chatId, String message) {
        long initialDelay = calculateInitialDelay(13, 0);
        long period = TimeUnit.DAYS.toMillis(1);

        scheduler.scheduleAtFixedRate(() -> {
            telegramBot.sendMessage(chatId, "Напоминание: " + message);
        }, initialDelay, period, TimeUnit.MILLISECONDS);

        System.out.println("Ежедневное напоминание установлено для чата " + chatId);
    }

    private long calculateInitialDelay(int targetHour, int targetMinute) {
        LocalTime now = LocalTime.now();
        LocalTime targetTime = LocalTime.of(targetHour, targetMinute);

        if (now.isAfter(targetTime)) {
            targetTime = targetTime.plusNanos(1);
        }

        return Duration.between(now, targetTime).toMillis();
    }

    public void cancelAllReminders() {
        scheduler.shutdownNow();
    }
}

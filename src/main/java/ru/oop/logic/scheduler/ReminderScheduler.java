package ru.oop.logic.scheduler;

import java.util.Timer;
import java.util.TimerTask;

public class ReminderScheduler {

    private final Timer timer = new Timer(true);

    public void scheduleReminder(long delay, Runnable task) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay);
    }
}
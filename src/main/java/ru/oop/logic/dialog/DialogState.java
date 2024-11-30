package ru.oop.logic.dialog;

public class DialogState {
    private long userId;

    public DialogState(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
package ru.oop.logic;
//здесь завести поле, которое будет отвечать за reminder, потом учу TGoutputwriter, проверять если это поле не null то устанавливаем напоминание
public class Response {
    private final String message;
    public Response(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

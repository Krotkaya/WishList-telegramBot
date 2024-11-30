package ru.oop.platforms.console;

import ru.oop.logic.other.OutputWriter;
import ru.oop.logic.other.Request;
import ru.oop.logic.other.RequestHandler;
import ru.oop.platforms.InputReader;

public class ConsoleBot implements ru.oop.platforms.Bot {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final RequestHandler requestHandler;

    public ConsoleBot(InputReader inputReader, OutputWriter outputWriter, RequestHandler requestHandler) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.requestHandler = requestHandler;
    }

    @Override
    public void startBot() {
        System.out.println("Добро пожаловать в консольного бота! Введите ваше сообщение.");
        while (true) {
            Request request = inputReader.read();
            requestHandler.handle(request, outputWriter);
        }
    }
}
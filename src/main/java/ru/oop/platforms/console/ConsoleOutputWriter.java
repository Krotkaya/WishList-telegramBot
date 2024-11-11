package ru.oop.platforms.console;

import ru.oop.logic.Response;
import ru.oop.logic.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {//отвечает за вывод ответов в консоль
    @Override
    public void write(Response response) {
        System.out.println("Ответ: " + response.getMessage());
    }
}
package ru.oop.platforms.console;

import ru.oop.logic.other.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {//отвечает за вывод ответов в консоль
    @Override
    public void write(String response) {
        System.out.println("Ответ: " + response);
    }
}
package ru.oop.logic.other;

/**
 *
 */
public class ConsoleRequestHandler implements RequestHandler {//создание класса, который будет содержать логику для обработки запросов. Ключевое слово implements указывает на то, что этот класс обязан реализовать все методы, определенные в интерфейсе RequestHandler.

    /**
     * @param request
     * @param writer
     */
//Это аннотация, которая указывает, что метод, который следует за ней, переопределяет метод из интерфейса RequestHandler


    @Override
    public void handle(Request request, OutputWriter writer) {
        Response response = new Response(request.getMessage());
        writer.write(String.valueOf(response));
    }
}

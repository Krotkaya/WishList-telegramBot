package ConsoleBot.logic;

/**
 *
 */
public class ConsoleRequestHandler implements RequestHandler {//создание класса, который будет содержать логику для обработки запросов. Ключевое слово implements указывает на то, что этот класс обязан реализовать все методы, определенные в интерфейсе RequestHandler.

    /**
     * @param request
     * @param writer
     */
    @Override//Это аннотация, которая указывает, что метод, который следует за ней, переопределяет метод из интерфейса RequestHandler
    public void handle(Request request, OutputWriter writer) {
        // Просто возвращаем запрос как ответ
        Response response = new Response(request.getMessage());
        writer.write(response);
    }
}

package logic;

public interface RequestHandler {
    void handle(Request request, OutputWriter writer);
}
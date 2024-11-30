package ru.oop.logic.other;

public interface RequestHandler {
    void handle(Request request, OutputWriter writer);
}
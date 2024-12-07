package ru.oop.logic;

public interface RequestHandler {
    void handle(Request request, OutputWriter writer);
}
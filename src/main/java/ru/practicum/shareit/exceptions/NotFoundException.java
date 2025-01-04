package ru.practicum.shareit.exceptions;

// Класс NotFoundException наследуется от RuntimeException, что делает его непроверяемым исключением.
public class NotFoundException extends RuntimeException {

    // Конструктор класса, который принимает сообщение (String message) и передает его в конструктор родительского класса RuntimeException.
    public NotFoundException(String message) {
        super(message); // Вызов конструктора родительского класса с переданным сообщением.
    }
}
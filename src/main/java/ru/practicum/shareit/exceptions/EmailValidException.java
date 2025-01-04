package ru.practicum.shareit.exceptions;

// Класс EmailValidException наследуется от RuntimeException, что делает его непроверяемым исключением.
public class EmailValidException extends RuntimeException {

    // Конструктор класса, который принимает сообщение (String message) и передает его в конструктор родительского класса RuntimeException.
    public EmailValidException(String message) {
        super(message); // Вызов конструктора родительского класса с переданным сообщением.
    }
}
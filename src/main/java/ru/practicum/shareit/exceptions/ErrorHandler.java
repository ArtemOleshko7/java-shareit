package ru.practicum.shareit.exceptions;

import jakarta.validation.ValidationException; // Исключение валидации
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler; // Аннотация для обработки исключений
import org.springframework.web.bind.annotation.ResponseStatus; // Аннотация для установки статуса ответа
import org.springframework.web.bind.annotation.RestControllerAdvice; // Аннотация для глобального обработчика исключений

// Аннотация RestControllerAdvice для обработки исключений на уровне контроллеров
@RestControllerAdvice
public class ErrorHandler {

    // Метод для обработки исключений NotFoundException
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        return new ErrorResponse(e.getMessage()); // Возвращаем новое сообщение об ошибке
    }

    // Метод для обработки исключений ValidationException
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(ValidationException e) {
        return new ErrorResponse(e.getMessage()); // Возвращаем новое сообщение об ошибке
    }

    // Метод для обработки исключений EmailValidException
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT) // Устанавливаем статус ответа 409 Conflict
    public ErrorResponse handleEmailValidException(EmailValidException e) {
        return new ErrorResponse(e.getMessage()); // Возвращаем новое сообщение об ошибке
    }

    // Метод для обработки исключений IllegalArgumentException
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Устанавливаем статус ответа 400 Bad Request
    public ErrorResponse handleEmptyFieldException(IllegalArgumentException e) {
        return new ErrorResponse(e.getMessage()); // Возвращаем новое сообщение об ошибке
    }

    // Внутренний класс (record) для формата ответа об ошибке
    public record ErrorResponse(String error) {
    }
}
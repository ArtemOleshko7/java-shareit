package ru.practicum.shareit.user.controller;

import jakarta.validation.Valid; // Импортирует аннотацию для проверки входных данных
import lombok.RequiredArgsConstructor; // Импортирует аннотацию для автоматической генерации конструктора с параметрами
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*; // Импортирует аннотации для работы с REST-контроллерами
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService; // Импортирует интерфейс UserService для доступа к бизнес-логике

@RestController // Указывает, что данный класс является контроллером и обрабатывает HTTP-запросы
@RequestMapping(path = "/users") // Определяет базовый путь для всех методов контроллера
@RequiredArgsConstructor //Генерирует конструктор,принимающий все final-поля, для автоматического внедрения зависимостей
public class UserController {

    private final UserService userService; // Сервис для управления пользователями

    @PostMapping // Обрабатывает HTTP-запросы типа POST по пути "/users"
    @ResponseStatus(HttpStatus.CREATED) // Устанавливает статус ответа на 201 CREATED
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        // Вызывает метод сервиса для создания пользователя и возвращает объект UserDto
        return userService.createUser(userDto);
    }

    @GetMapping("/{userId}") // Обрабатывает HTTP-запросы типа GET по пути "/users/{userId}"
    @ResponseStatus(HttpStatus.OK) // Устанавливает статус ответа на 200 OK
    public UserDto getUser(@PathVariable Long userId) { // Метод для получения информации о пользователе по его id
        // Вызывает метод сервиса для получения пользователя и возвращает объект UserDto
        return userService.getUser(userId);
    }

    @PatchMapping("/{userId}") // Обрабатывает HTTP-запросы типа PATCH по пути "/users/{userId}"
    @ResponseStatus(HttpStatus.OK) // Устанавливает статус ответа на 200 OK
    public UserDto updateUser(@PathVariable Long userId, // Метод для обновления информации о пользователе
                              @RequestBody UserDto userDto) {
        // Вызывает метод сервиса для обновления пользователя и возвращает обновленный объект UserDto
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{userId}") // Обрабатывает HTTP-запросы типа DELETE по пути "/users/{userId}"
    @ResponseStatus(HttpStatus.NO_CONTENT) // Устанавливает статус ответа на 204 NO CONTENT
    public void deleteUser(@PathVariable Long userId) { // Метод для удаления пользователя по его идентификатору
        userService.deleteUser(userId); // Вызывает метод сервиса для удаления пользователя
    }
}
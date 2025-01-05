package ru.practicum.shareit.user.model;

import lombok.Data;
import lombok.RequiredArgsConstructor; // Импортирует аннотацию RequiredArgsConstructor из библиотеки Lombok.

@Data
@RequiredArgsConstructor // Генерирует конструктор, который принимает все финальные поля класса
public class User {

    private Long id; // Идентификатор пользователя, может быть null для новых пользователей.
    private String name; // Имя пользователя
    private String email; // Email пользователя
} // Конец класса
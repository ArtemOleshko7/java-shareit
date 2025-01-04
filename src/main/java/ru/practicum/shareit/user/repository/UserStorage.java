package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

public interface UserStorage {

    // Метод для создания нового пользователя. Принимает объект User и возвращает созданный объект.
    User createUser(User userDto);

    // Метод для получения пользователя по его ID. Принимает ID пользователя и возвращает объект User.
    User getUser(Long userId);

    // Метод для обновления данных пользователя. Принимает ID пользователя и объект User с обновленными данными.
    User updateUser(Long userId, User userDto);

    // Метод для удаления пользователя по его ID. Не возвращает никаких значений.
    void deleteUser(Long userId);
}
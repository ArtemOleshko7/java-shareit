package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

public interface UserService { // Определяет интерфейс UserService для работы с пользователями.

    // Метод для получения пользователя по его уникальному идентификатору (ID). Возвращает объект UserDto.
    UserDto getUser(Long userId);

    // Метод для создания нового пользователя. Принимает UserDto с данными нового пользователя и возвращает UserDto.
    UserDto createUser(UserDto userDto);

    // Принимает ID пользователя и UserDto с обновленными данными, возвращает обновленный объект UserDto.
    UserDto updateUser(Long userId, UserDto userDto);

    // Метод для удаления пользователя по его уникальному идентификатору (ID). Не возвращает значения.
    void deleteUser(Long userId);
}
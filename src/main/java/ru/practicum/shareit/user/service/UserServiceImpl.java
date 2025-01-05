package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor; // Импортирует аннотацию Lombok для автоматического создания конструктора.
import org.springframework.stereotype.Service; // Импортирует аннотацию Spring для обозначения компонента сервиса.
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.repository.UserStorage;

@RequiredArgsConstructor // Генерирует конструктор с аргументами для всех финальных полей.
@Service // Указывает, что этот класс является сервисом в Spring-контейнере.
public class UserServiceImpl implements UserService {

    private final UserStorage userStorage; // Хранилище пользователей, инжектируемое через конструктор.

    // Метод для получения пользователя по его уникальному идентификатору (ID).
    @Override
    public UserDto getUser(Long userId) {
        return UserMapper.toUserDto(userStorage.getUser(userId)); //Получает User из хранилища,преобразует его в UserDto
    }

    // Метод для создания нового пользователя.
    @Override
    public UserDto createUser(UserDto userDto) {
        // Преобразует UserDto в User, создает его в хранилище и возвращает в виде UserDto.
        return UserMapper.toUserDto(userStorage.createUser(UserMapper.toUser(userDto)));
    }

    // Метод для обновления данных существующего пользователя.
    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        // Обновляет пользователя в хранилище и возвращает обновленный UserDto.
        return UserMapper.toUserDto(userStorage.updateUser(userId, UserMapper.toUser(userDto)));
    }

    // Метод для удаления пользователя по его уникальному идентификатору (ID).
    @Override
    public void deleteUser(Long userId) {
        userStorage.deleteUser(userId); // Удаляет пользователя из хранилища.
    }
}
package ru.practicum.shareit.user.mapper;

import ru.practicum.shareit.user.dto.UserDto; //Импортирует класс для работы с объектами передачи данных о пользователях
import ru.practicum.shareit.user.model.User; // Импортирует класс User, который представляет собой модель пользователя.

public class UserMapper {

    public static UserDto toUserDto(User user) { // Метод преобразования объекта User в UserDto
        return UserDto.builder() // Использует шаблон Builder для создания экземпляра UserDto
                .id(user.getId()) // Устанавливает идентификатор пользователя
                .name(user.getName()) // Устанавливает имя пользователя
                .email(user.getEmail()) // Устанавливает email пользователя
                .build(); // Создает экземпляр UserDto
    }

    public static User toUser(UserDto userDto) { // Метод преобразования объекта UserDto в User
        User user = new User(); // Создает новый объект User
        user.setId(userDto.getId()); // Устанавливает идентификатор пользователя
        user.setName(userDto.getName()); // Устанавливает имя пользователя
        user.setEmail(userDto.getEmail()); // Устанавливает email пользователя
        return user; // Возвращает созданный объект User
    }
} // Конец класса
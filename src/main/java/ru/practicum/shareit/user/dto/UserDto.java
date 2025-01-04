package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email; // Импортирует аннотацию для проверки корректности email.
import jakarta.validation.constraints.NotBlank; // Импортирует аннотацию для проверки на непустое значение.
import lombok.*; // Импортирует аннотации библиотеки Lombok для упрощения кода.

@Data
@Builder // Позволяет создавать объекты UserDto через паттерн проектирования Builder.
@AllArgsConstructor // Генерирует конструктор с параметрами для всех полей класса.
@NoArgsConstructor // Генерирует конструктор без параметров.
public class UserDto {

    private Long id; // Идентификатор пользователя, может быть null, если пользователь еще не создан.

    @NotBlank // Обеспечивает, что поле не может быть пустым
    private String name; // Имя пользователя

    @Email // Проверяет, что значение поля соответствует формату email
    @NotBlank // Обеспечивает, что поле не может быть пустым
    private String email; // Email пользователя
}
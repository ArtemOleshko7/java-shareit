package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank; // Импортирует аннотацию для проверки на пустые строки.
import jakarta.validation.constraints.NotNull; // Импортирует аннотацию для проверки на null значения.
import lombok.*; // Импортирует аннотации библиотеки Lombok для генерации методов и конструкторов.
import ru.practicum.shareit.user.model.User;

// Аннотация @Data генерирует геттеры, сеттеры, toString, equals и hashCode методы для всех полей класса.
@Data
// Аннотация @RequiredArgsConstructor генерирует конструктор с обязательными параметрами (финальные поля).
@RequiredArgsConstructor
// Аннотация @Builder позволяет использовать паттерн Builder для создания экземпляров данного класса.
@Builder
// Аннотация @AllArgsConstructor генерирует конструктор с параметрами для всех полей класса.
@AllArgsConstructor
public class ItemDto {
    private Long id; // Уникальный идентификатор предмета.

    @NotBlank // Проверка, что поле не пустое и не содержит только пробелы.
    private String name; // Название предмета.

    @NotBlank
    private String description; // Описание предмета.

    private User owner; // Владелец предмета (представленный классом User).

    @NotNull // Проверка, что поле не равно null.
    private Boolean available; // Доступность предмета (true - доступен, false - недоступен).

    private Long requestId; // Идентификатор запроса, если предмет связан с запросом.
}
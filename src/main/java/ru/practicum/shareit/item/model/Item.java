package ru.practicum.shareit.item.model;

import lombok.Data; // Импортирует аннотацию Lombok для генерации методов.
import lombok.RequiredArgsConstructor; // Импортирует аннотацию для создания конструктора.
import ru.practicum.shareit.user.model.User;

@Data
@RequiredArgsConstructor // Аннотация, которая генерирует конструктор для всех финальных полей класса.
public class Item {
    private Long id; // Уникальный идентификатор предмета.
    private String name; // Название предмета.
    private String description; // Описание предмета.
    private Boolean available; // Доступность предмета (true - доступен, false - недоступен).
    private User owner; // Владелец предмета (объект типа User).
    private Long requestId; // Идентификатор запроса, если предмет связан с запросом.
}
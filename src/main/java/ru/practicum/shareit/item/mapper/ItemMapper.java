package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

public class ItemMapper {

    // Метод, который преобразует объект типа Item в объект типа ItemDto
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder() // Использует паттерн Строитель для создания объекта ItemDto
                .id(item.getId()) // Устанавливает идентификатор предмета
                .name(item.getName()) // Устанавливает название предмета
                .description(item.getDescription()) // Устанавливает описание предмета
                .owner(item.getOwner()) // Устанавливает владельца предмета
                .available(item.getAvailable()) // Устанавливает доступность предмета
                // Устанавливает идентификатор запроса (если есть)
                .requestId(item.getRequestId() != null ? item.getRequestId() : null)
                .build(); // Создает и возвращает объект ItemDto
    }

    // Метод, который преобразует объект типа ItemDto в объект типа Item
    public static Item toItem(ItemDto itemDto, User user) {
        Item item = new Item(); // Создает новый объект Item
        item.setId(itemDto.getId()); // Устанавливает идентификатор предмета
        item.setName(itemDto.getName()); // Устанавливает название предмета
        item.setDescription(itemDto.getDescription()); // Устанавливает описание предмета
        item.setAvailable(itemDto.getAvailable()); // Устанавливает доступность предмета
        item.setOwner(user); // Устанавливает владельца предмета
        return item; // Возвращает объект Item
    }
}
package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

// Определение интерфейса ItemService, который задает методы для работы с предметами.
public interface ItemService {

    // Метод для создания нового предмета.
    // Принимает идентификатор пользователя и объект ItemDto, представляющий данные нового предмета.
    ItemDto createItem(Long userId, ItemDto itemDto);

    // Метод для обновления информации о предмете.
    // Принимает идентификаторы пользователя и предмета, а также новый объект ItemDto с обновлёнными данными.
    ItemDto updateItem(Long userId, Long itemId, ItemDto itemDto);

    // Метод для получения предмета по его идентификатору.
    // Проверяет, является ли запрашивающий пользователь владельцем предмета, и возвращает объект ItemDto.
    ItemDto getItem(Long userId, Long itemId);

    // Метод для удаления предмета по его идентификатору.
    // Проверяет, что пользователь является владельцем предмета, прежде чем удалить его.
    void deleteItem(Long userId, Long itemId);

    // Метод для получения всех предметов, принадлежащих указанному пользователю.
    // Возвращает список объектов ItemDto, представляющих все предметы пользователя.
    List<ItemDto> getAllItems(Long userId);

    // Метод для поиска предметов по текстовому запросу.
    // Возвращает список предметов, которые соответствуют критериям поиска и принадлежат указанному пользователю.
    List<ItemDto> searchItems(Long userId, String text);
}
package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage { // Определение интерфейса ItemStorage, который задает методы для работы с предметами.

    // Метод для создания нового предмета. Принимает идентификатор пользователя и объект Item.
    Item createItem(Long userId, Item item);

    // Метод для получения предмета по его идентификатору и идентификатору владельца (пользователя).
    Item getItem(Long userId, Long itemId);

    // Метод для обновления информации о предмете. Принимает идентификаторы пользователя и предмета и новый объект Item.
    Item updateItem(Long userId, Long itemId, Item item);

    // Метод для удаления предмета по его идентификатору. Проверяет, что пользователь является владельцем.
    void deleteItem(Long userId, Long itemId);

    // Метод для получения всех предметов, принадлежащих указанному пользователю.
    List<Item> getAllItems(Long userId);

    // Метод для поиска предметов по текстовому запросу. Возвращает список предметов, соответствующих запросу.
    List<Item> searchItems(Long userId, String text);
}
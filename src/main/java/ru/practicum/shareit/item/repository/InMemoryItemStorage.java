package ru.practicum.shareit.item.repository;

import lombok.RequiredArgsConstructor; // Импортирует аннотацию Lombok для генерации конструктора.
import org.springframework.stereotype.Repository; // Импортирует аннотацию Spring для обозначения класса как репозитория.
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.repository.UserStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor // Аннотация, которая генерирует конструктор с обязательными аргументами.
@Repository // Обозначает, что этот класс является хранилищем данных (репозиторием).
public class InMemoryItemStorage implements ItemStorage { // Класс реализует интерфейс ItemStorage
    // Хранилище предметов, где ключ - идентификатор предмета, значение - объект Item.
    private final Map<Long, Item> items = new HashMap<>();
    private Long id = 1L; // Уникальный идентификатор для новых предметов.
    private final UserStorage userStorage; // Внедрение зависимости UserStorage для работы с пользователями.


    @Override
    public Item createItem(Long userId, Item item) { // Метод для создания нового предмета
        if (userStorage.getUser(userId) == null) { // Проверка, существует ли пользователь
            throw new NotFoundException("Пользователя с таким id не существует");
        }
        item.setOwner(userStorage.getUser(userId)); // Установка владельца предмета
        // Проверка на наличие имени и описания предмета
        if ((item.getName() == null || item.getName().isBlank())
                || (item.getDescription() == null || item.getDescription().isBlank())) {
            throw new IllegalArgumentException("Поля name и description не могут быть пустыми");
        }
        item.setId(id); // Установка идентификатора предмета
        items.put(item.getId(), item); // Сохранение предмета в хранилище
        id++; // Инкремент идентификатора для следующего предмета
        return item; // Возвращаем созданный объект Item
    }

    @Override
    public Item getItem(Long userId, Long itemId) { // Метод для получения предмета по идентификатору
        if (!items.containsKey(itemId)) { // Проверка, существует ли предмет с данным идентификатором
            throw new NotFoundException("Предмет с таким id не найден");
        }
        // Проверка, является ли пользователь владельцем предмета
        if (!items.get(itemId).getOwner().getId().equals(userId)) {
            throw new NotFoundException("Пользователя с таким id не существует");
        }
        return items.get(itemId); // Возвращаем найденный предмет
    }

    @Override
    public Item updateItem(Long userId, Long itemId, Item item) { // Метод для обновления информации о предмете
        if (!items.containsKey(itemId)) { // Проверка, существует ли предмет
            throw new NotFoundException("Предмет с таким id не найден");
        }
        // Проверка, является ли пользователь владельцем предмета
        if (!items.get(itemId).getOwner().getId().equals(userId)) {
            throw new NotFoundException("Редактировать предмет может только его владелец");
        }
        Item updatedItem = items.get(itemId); // Получаем текущий предмет для обновления
        if (item.getName() != null) { // Проверка на наличие нового имени
            updatedItem.setName(item.getName()); // Обновляем имя предмета
        }
        if (item.getDescription() != null) { // Проверка на наличие нового описания
            updatedItem.setDescription(item.getDescription()); // Обновляем описание предмета
        }
        if (item.getAvailable() != null) { // Проверка на наличие нового статуса доступности
            updatedItem.setAvailable(item.getAvailable()); // Обновляем доступность предмета
        }
        items.put(itemId, updatedItem); // Сохраняем обновлённый предмет
        return item; // Возвращаем обновлённый объект Item
    }

    @Override
    public void deleteItem(Long userId, Long itemId) { // Метод для удаления предмета
        if (!items.containsKey(itemId)) { // Проверка, существует ли предмет
            throw new NotFoundException("Предмет с таким id не найден");
        }
        // Проверка, является ли пользователь владельцем предмета
        if (!items.get(itemId).getOwner().getId().equals(userId)) {
            throw new NotFoundException("Удалить предмет может только его владелец");
        }
        items.remove(itemId); // Удаление предмета из хранилища
    }

    @Override
    public List<Item> getAllItems(Long userId) { // Метод для получения всех предметов владельца
        return items.values().stream() // Переходим ко всем предметам
                .filter(item -> item.getOwner().getId().equals(userId)) // Фильтруем по владельцу
                .toList(); // Возвращаем список найденных предметов
    }

    @Override
    public List<Item> searchItems(Long userId, String text) { // Метод для поиска предметов по тексту
        if (text == null || text.isBlank()) { // Проверка, пуст ли текст
            return List.of(); // Возвращаем пустой список
        }
        String textToLowerCase = text.toLowerCase(); // Приводим текст к нижнему регистру для удобства поиска
        return items.values().stream() // Перебираем все предметы
                .filter(item -> (item.getDescription().toLowerCase().contains(textToLowerCase) // Сравниваем с описанием
                        || item.getName().toLowerCase().contains(textToLowerCase)) // Сравниваем с названием
                        && item.getOwner().getId().equals(userId) // Проверка владельца
                        && item.getAvailable().booleanValue()) // Проверка доступности
                .toList(); // Возвращаем список найденных предметов
    }
}
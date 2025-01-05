package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor; // Импортирует аннотацию для автоматической генерации конструктора.
import org.springframework.stereotype.Service; //Импортирует аннотацию, указывающую, что класс является сервисом Spring.
import ru.practicum.shareit.item.dto.ItemDto; //Импортирует класс ItemDto, модель данных для передачи предметов.
import ru.practicum.shareit.item.mapper.ItemMapper; //Импортирует класс ItemMapper, преобразования между Item и ItemDto.
import ru.practicum.shareit.item.repository.ItemStorage;
import ru.practicum.shareit.user.repository.UserStorage;

import java.util.List;

@Service // Помечает класс как сервис внутри Spring, его можно внедрять в другие компоненты.
@RequiredArgsConstructor //Генерирует конструктор, принимающий все final, для автоматического внедрения зависимостей.
public class ItemServiceImpl implements ItemService {

    private final ItemStorage itemStorage; // Хранилище для предметов.
    private final UserStorage userStorage; // Хранилище для пользователей.

    @Override
    public ItemDto createItem(Long userId, ItemDto itemDto) {
        // Создает предмет, используя данные из ItemDto и возвращает его в виде ItemDto.
        return ItemMapper.toItemDto(
                itemStorage.createItem(userId, ItemMapper.toItem(itemDto, userStorage.getUser(userId))));
    }

    @Override
    public ItemDto updateItem(Long userId, Long itemId, ItemDto itemDto) {
        // Обновляет существующий предмет и возвращает обновленный объект ItemDto.
        return ItemMapper.toItemDto(
                itemStorage.updateItem(userId, itemId, ItemMapper.toItem(itemDto, userStorage.getUser(userId))));
    }

    @Override
    public ItemDto getItem(Long userId, Long itemId) {
        // Получает предмет по его идентификатору и возвращает его в виде ItemDto.
        return ItemMapper.toItemDto(itemStorage.getItem(userId, itemId));
    }

    @Override
    public void deleteItem(Long userId, Long itemId) {
        // Удаляет предмет по его идентификатору.
        itemStorage.deleteItem(userId, itemId);
    }

    @Override
    public List<ItemDto> getAllItems(Long userId) {
        // Получает все предметы, принадлежащие пользователю, и возвращает их в виде списка ItemDto.
        return itemStorage.getAllItems(userId).stream()
                .map(ItemMapper::toItemDto) // Преобразует каждого предмета из Item в ItemDto.
                .toList(); // Собирает результаты в список.
    }

    @Override
    public List<ItemDto> searchItems(Long userId, String text) {
        // Выполняет поиск предметов по текстовому запросу и возвращает их в виде списка ItemDto.
        return itemStorage.searchItems(userId, text).stream()
                .map(ItemMapper::toItemDto) // Преобразует каждого предмета из Item в ItemDto.
                .toList(); // Собирает результаты в список.
    }
}
package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid; // Для валидации входящих данных
import lombok.RequiredArgsConstructor; // Для автоматической генерации конструктора с обязательными параметрами
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*; // Для аннотаций Spring MVC
import ru.practicum.shareit.item.dto.ItemDto; // DTO-класс для передачи данных о предмете
import ru.practicum.shareit.item.service.ItemService; // Сервис для бизнес-логики предметов

import java.util.List;

// Аннотация, которая обозначает данный класс как контроллер REST
@RestController
// Аннотация, задающая базовый URL для всех методов этого контроллера
@RequestMapping("/items")
// Аннотация, которая позволяет автоматически генерировать конструктор с обязательными параметрами (itemService)
@RequiredArgsConstructor
public class ItemController {

    // Создаем экземпляр сервиса предметов, который будет использован в методах контроллера
    private final ItemService itemService;
    // Константа для заголовка, содержащего идентификатор пользователя
    private static final String HEADER_USER_ID = "X-Sharer-User-Id";

    // Метод для добавления нового предмета
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Указываем, что на успешное выполнение метода будет ответ с кодом 201(Created)
    public ItemDto addItem(@RequestHeader(HEADER_USER_ID) Long userId,
                           @Valid @RequestBody ItemDto itemDto) {
        // Вызываем метод сервиса для создания предмета и возвращаем созданный предмет
        return itemService.createItem(userId, itemDto);
    }

    // Метод для получения предмета по его идентификатору
    @GetMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK) // Указываем, что на успешное выполнение метода будет ответ с кодом 200 (OK)
    public ItemDto getItem(@RequestHeader(HEADER_USER_ID) Long userId,
                           @PathVariable Long itemId) {
        // Получаем предмет через сервис и возвращаем его
        return itemService.getItem(userId, itemId);
    }

    // Метод для обновления существующего предмета
    @PatchMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK) // Указываем, что на успешное выполнение метода будет ответ с кодом 200 (OK)
    public ItemDto updateItem(@RequestHeader(HEADER_USER_ID) Long userId,
                              @PathVariable @Valid Long itemId,
                              @RequestBody ItemDto itemDto) {
        // Вызываем метод сервиса для обновления предмета и возвращаем обновленный предмет
        return itemService.updateItem(userId, itemId, itemDto);
    }

    // Метод для удаления предмета
    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Указываем, что на успешное выполнение метода будет ответ с кодом 204 (No Content)
    public void deleteItem(@RequestHeader(HEADER_USER_ID) Long userId,
                           @PathVariable Long itemId) {
        // Вызываем метод сервиса для удаления предмета
        itemService.deleteItem(userId, itemId);
    }

    // Метод для получения всех предметов пользователя
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // Указываем, что на успешное выполнение метода будет ответ с кодом 200 (OK)
    public List<ItemDto> getAllItems(@RequestHeader(HEADER_USER_ID) Long userId) {
        // Запрашиваем у сервиса все предметы пользователя и возвращаем их
        return itemService.getAllItems(userId);
    }

    // Метод для поиска предметов по текстовому запросу
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK) // Указываем, что на успешное выполнение метода будет ответ с кодом 200 (OK)
    public List<ItemDto> searchItems(@RequestHeader(HEADER_USER_ID) Long userId,
                                     @RequestParam(required = false) String text) {
        // Запрашиваем у сервиса список предметов, соответствующих текстовому запросу, и возвращаем их
        return itemService.searchItems(userId, text);
    }
}
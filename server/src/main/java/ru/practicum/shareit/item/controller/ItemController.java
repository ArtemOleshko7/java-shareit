package ru.practicum.shareit.item.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.api.RequestHttpHeaders;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentSaveDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemSaveDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.Collection;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto addItem(@RequestHeader(value = RequestHttpHeaders.USER_ID) int userId,
                           @RequestBody ItemSaveDto itemSaveDto) {
        return itemService.addItem(userId, itemSaveDto);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComment(@RequestHeader(value = RequestHttpHeaders.USER_ID) int userId,
                                 @PathVariable int itemId,
                                 @RequestBody CommentSaveDto commentSaveDto) {
        return itemService.addComment(userId, itemId, commentSaveDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader(value = RequestHttpHeaders.USER_ID) int userId,
                              @PathVariable int itemId,
                              @RequestBody ItemSaveDto itemSaveDto) {
        return itemService.updateItem(userId, itemId, itemSaveDto);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable int itemId) {
        return itemService.getItem(itemId);
    }

    @GetMapping
    public Collection<ItemDto> getAllOwnerItems(@RequestHeader(value = RequestHttpHeaders.USER_ID) int userId) {
        return itemService.getAllOwnerItems(userId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> searchItems(@RequestParam String text) {
        return itemService.searchItems(text);
    }
}
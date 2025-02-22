package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.model.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO create(ItemDTO item);

    ItemDTO update(ItemDTO item);

    ItemDTO findById(int id);

    List<ItemDTO> findByUserId(int userId);

    List<ItemDTO> findByText(String text);
}
package ru.practicum.shareit.user.repository;

import lombok.RequiredArgsConstructor; // Импортирует аннотацию RequiredArgsConstructor из библиотеки Lombok.
import org.springframework.stereotype.Component; // Импортирует аннотацию Component для интеграции с Spring.
import ru.practicum.shareit.exceptions.EmailValidException; // Импортирует пользовательское исключение
import ru.practicum.shareit.exceptions.NotFoundException; // Импортирует пользовательское исключение
import ru.practicum.shareit.user.model.User;

import java.util.HashMap; // Импортирует класс HashMap для хранения пользователей.
import java.util.HashSet; // Импортирует класс HashSet для хранения уникальных email.
import java.util.Map; // Импортирует интерфейс Map.
import java.util.Set; // Импортирует интерфейс Set.

@RequiredArgsConstructor // Генерирует конструктор, принимающий все финальные поля класса.
@Component //Указывает,что класс является компонентом Spring и может быть автоматически обнаружен в контексте приложения
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> users = new HashMap<>(); // Хранит пользователей, используя их ID как ключ.

    // Используется для хранения уникальных email пользователей для быстрого поиска.
    private final Set<String> emails = new HashSet<>();

    private Long id = 1L; // Служит для генерации уникальных идентификаторов пользователей.

    @Override
    public User createUser(User user) {
        // Проверяем, существует ли уже пользователь с данным email.
        if (emails.contains(user.getEmail())) {
            throw new EmailValidException("Пользователь с таким email уже существует");
        }
        emails.add(user.getEmail()); // Добавляем email в набор уникальных адресов.
        user.setId(id); // Устанавливаем уникальный ID для нового пользователя.
        users.put(user.getId(), user); // Сохраняем пользователя в мапу.
        id++; // Увеличиваем ID для следующего пользователя.
        return user; // Возвращаем созданного пользователя.
    }

    @Override
    public User getUser(Long userId) { // Метод для получения пользователя по ID.
        // Проверяем, существует ли пользователь с данным ID.
        if (!users.containsKey(userId)) {
            throw new NotFoundException("Пользователь с таким id не найден");
        }
        return users.get(userId); // Возвращаем найденного пользователя.
    }

    @Override
    public User updateUser(Long userId, User user) { // Метод для обновления данных пользователя.
        // Проверяем, существует ли пользователь с данным ID.
        if (!users.containsKey(userId)) {
            throw new NotFoundException("Пользователь с таким id не найден");
        }

        User updatedUser = users.get(userId); // Получаем существующего пользователя для обновления.
        if (user.getName() != null) { // Если имя не null, обновляем его.
            updatedUser.setName(user.getName());
        }
        // Проверяем, существует ли уже другой пользователь с таким email.
        if (emails.contains(user.getEmail())) {
            throw new EmailValidException("Пользователь с таким email уже существует");
        }
        if (user.getEmail() != null) { // Если email не null, обновляем его.
            emails.remove(users.get(userId).getEmail()); // Удаляем старый email из набора.
            emails.add(user.getEmail()); // Добавляем новый email в набор.
            updatedUser.setEmail(user.getEmail()); // Устанавливаем новый email для обновляемого пользователя.
        }
        users.put(userId, updatedUser); // Сохраняем обновленного пользователя в мапу.
        return updatedUser; // Возвращаем обновленного пользователя.
    }

    @Override
    public void deleteUser(Long userId) { // Метод для удаления пользователя по ID.
        // Проверяем, существует ли пользователь с данным ID.
        if (!users.containsKey(userId)) {
            throw new NotFoundException("Пользователь с таким id не найден");
        }
        // Удаляем email пользователя из набора уникальных адресов.
        emails.remove(users.get(userId).getEmail());
        users.remove(userId); // Удаляем пользователя из мапы.
    }
}
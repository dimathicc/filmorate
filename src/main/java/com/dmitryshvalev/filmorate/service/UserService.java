package com.dmitryshvalev.filmorate.service;

import com.dmitryshvalev.filmorate.exception.UserNotFoundException;
import com.dmitryshvalev.filmorate.model.User;
import com.dmitryshvalev.filmorate.storage.FriendStorage;
import com.dmitryshvalev.filmorate.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserStorage userStorage;
    private final FriendStorage friendStorage;

    public List<User> findAll() {
        return userStorage.findAll();
    }

    public User create(User user) {
        validate(user);
        return userStorage.create(user);
    }

    public User update(User user) {
        validate(user);
        if (userStorage.findUserById(user.getId()).isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден.");
        }
        return userStorage.update(user);
    }

    public User findUserById(int id) {
        return userStorage.findUserById(id).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    public void addFriend(int id, int friendId) {
        if (userStorage.findUserById(id).isEmpty() || userStorage.findUserById(friendId).isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден.");
        }
        if (id < 0 || friendId < 0) {
            throw new UserNotFoundException("Пользователь не найден.");
        }
        friendStorage.addFriend(id, friendId);
    }

    public List<User> findAllFriends(int id) {
        return friendStorage.findAllFriends(id);
    }

    public List<User> findCommonFriends(int id, int otherId) {
        return friendStorage.findCommonFriends(id, otherId);
    }

    public void removeFriend(int id, int friendId) {
        if (userStorage.findUserById(id).isEmpty() || userStorage.findUserById(friendId).isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден.");
        }
        friendStorage.removeFriend(id, friendId);
    }

    private void validate(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }
}

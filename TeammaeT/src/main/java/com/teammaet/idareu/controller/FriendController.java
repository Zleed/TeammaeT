package com.teammaet.idareu.controller;

import com.teammaet.idareu.model.Friend;
import com.teammaet.idareu.model.User;
import com.teammaet.idareu.service.UserStorage;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/user/{userId}/friend")
public class FriendController {
    private UserStorage userStorage;

    public FriendController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @GetMapping
    public Set<Friend> getFriendList(@PathVariable("userId") int userId){
        User user = userStorage.getUserById(userId);
        return user.getFriendList();
    }

    @PostMapping("/{id}")
    public Friend addFriend(@PathVariable("userId") int userId, @PathVariable("id") int friendId) {
        User user = userStorage.getUserById(userId);
        Friend friend = userStorage.getUserById(friendId);
        return user.addFriend(friend);
    }

    @DeleteMapping("/{id}")
    public String deleteFriend(@PathVariable("userId") int userId, @PathVariable("id") int id) {
        User user = userStorage.getUserById(userId);
        Friend friend = user.getById(id);
        return user.deleteFriend(friend);
    }

}

package com.teammaet.idareu.controller;


import com.teammaet.idareu.model.Dare;
import com.teammaet.idareu.model.Friend;
import com.teammaet.idareu.model.SentDareInformation;
import com.teammaet.idareu.model.User;
import com.teammaet.idareu.service.DareStorage;
import com.teammaet.idareu.service.UserStorage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/user/{userId}/dare")
public class DareController {

    private UserStorage userStorage;

    public DareController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @GetMapping("/received")
    public List<Dare> getReceivedDares(@PathVariable("userId") int userId) throws NullPointerException {
        User user = userStorage.getUserById(userId);
        return user.getDareStorage().getDares();
    }

    @GetMapping("/sent")
    public List<Dare> getSentDares(@PathVariable("userId") int userId) throws NullPointerException {
        User user = userStorage.getUserById(userId);
        return user.getDareStorage().getDares();
    }

    @GetMapping
    public List<Dare> getDares(@PathVariable("userId") int userId) throws NullPointerException {
        User user = userStorage.getUserById(userId);
        return user.getDareStorage().getAllDare();
    }

    @PostMapping
    public Dare createDare(@PathVariable("userId") int userId,
                           @RequestBody SentDareInformation sentDareInformation) {

        User user = userStorage.getUserById(userId);
        Set<Friend> friends = userStorage.getFriends(sentDareInformation.getFriendSet());
        Dare dare = sentDareInformation.getDare();
        user.send(dare, friends);
        return dare;
    }

    @GetMapping("/{id}")
    public Dare getDare(@PathVariable("userId") int userId,
                        @PathVariable("id") int id) {
        User user = userStorage.getUserById(userId);
        DareStorage dareStorage = user.getDareStorage();
        return dareStorage.getDareBy(id, dareStorage.getAllDare());
    }

    @PutMapping("/{id}")
    public Dare updateDare(@PathVariable("userId") int userId,
                           @PathVariable("id") int id) {
        User user = userStorage.getUserById(userId);
        DareStorage dareStorage = user.getDareStorage();
        Dare dare = dareStorage.getDareBy(id, dareStorage.getDares());
        dareStorage.update(dare, user);
        return dare;
    }

    @DeleteMapping("/{id}")
    public String deleteDare(@PathVariable("userId") int userId,
                             @PathVariable("id") int id) throws NullPointerException {
        User user = userStorage.getUserById(userId);
        DareStorage dareStorage = user.getDareStorage();
        Dare dare = dareStorage.getDareBy(id, dareStorage.getAllDare());
        dareStorage.delete(dare);
        return "User was deleted";
    }
}

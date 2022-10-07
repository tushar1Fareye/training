package com.fareye.training.service;

import com.fareye.training.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public User getUser(String email) {
        return users.stream().filter(User -> email.equals(User.getEmail())).findFirst().orElse(null);
    }

    public List<User> addUser(User user) {
        users.add(user);
        return users;
    }
}

package com.fareye.training.service;

import com.fareye.training.model.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public User get(String email) {

        List<User> filteredUsers = users.stream().filter(User -> email.equals(User.getEmail()))
                .collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(filteredUsers)) {
            return filteredUsers.get(0);
        }

        return null;

    }

    public Boolean add(User user) {

        User filteredUser = get(user.getEmail());

        if(filteredUser == null) {
            users.add(user);
            TodoService.userToTodosMap.put(user.getEmail(), new ArrayList<>());
            return true;
        }

        return false;

    }

    public Boolean delete(String email) {

        List<User> filteredUsers = users.stream().filter(User -> !email.equals(User.getEmail()))
                .collect(Collectors.toList());

        if(users.size() != filteredUsers.size()) {
            TodoService.userToTodosMap.remove(email);
            users = filteredUsers;
            return true;
        }

        return false;
    }

    public Boolean update(User userModifications) throws InvocationTargetException, IllegalAccessException {

        for(User user: users) {
            if(user.getEmail().equals(userModifications.getEmail())) {
                BeanUtils.copyProperties(user, userModifications);
                return true;
            }
        }

        return false;
    }

}

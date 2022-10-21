package com.fareye.training.service;

import com.fareye.training.model.User;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {


    @Test
    void getUserShouldReturnNull() {
        UserService userService = new UserService();
        assertEquals(userService.getUser("wrong@gmail.com"), null);
    }

    @Test
    void getUsersShouldReturnEmptyList() {
        UserService userService = new UserService();
        System.out.println(userService.getUsers());
        assertEquals(userService.getUsers(), new ArrayList<>());
    }

    @Test
    void addShouldReturnTrue() throws ParseException {
        UserService userService = new UserService();
        User user= new User();
        user.setEmail("test@gmail.com");
        assertEquals(userService.add(user), true);
    }

    @Test
    void addShouldReturnFalse() throws ParseException {
        UserService userService = new UserService();
        User user= new User();
        user.setEmail("test1@gmail.com");
        userService.add(user);
        assertEquals(userService.add(user), false);
    }

    @Test
    void getUserShouldReturnValidOutput() throws ParseException {
        UserService userService = new UserService();
        User user= new User();
        user.setEmail("test2@gmail.com");
        user.setGithubUserName("tushar1Fareye");
        user.setGithubToken("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");
        userService.add(user);
        assertEquals(userService.getUser("test2@gmail.com").getGithubToken(),
                "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");
    }

    @Test
    void getUsersShouldReturnValidOutput() throws ParseException {
        UserService userService = new UserService();
        User user= new User();
        user.setEmail("test3@gmail.com");
        userService.add(user);

        assertEquals(userService.getUsers().get(0).getEmail(), "test3@gmail.com");

    }

    @Test
    void deleteShouldReturnTrue() throws ParseException {
        UserService userService = new UserService();
        User user= new User();
        user.setEmail("test4@gmail.com");
        userService.add(user);
        assertEquals(userService.delete("test4@gmail.com"),true);

    }

    @Test
    void deleteShouldReturnFalse() {
        UserService userService = new UserService();
        assertEquals(userService.delete("wrong1@gmail.com"),false);

    }

    @Test
    void updateShouldReturnTrue() throws ParseException, InvocationTargetException, IllegalAccessException {
        UserService userService = new UserService();
        User user= new User();
        user.setEmail("test5@gmail.com");
        userService.add(user);

        User modificationUser= new User();
        modificationUser.setEmail("test5@gmail.com");
        modificationUser.setPassword("abcd");

        assertEquals(userService.update(modificationUser),true);

    }

    @Test
    void updateShouldReturnFalse() throws ParseException, InvocationTargetException, IllegalAccessException {
        UserService userService = new UserService();

        User modificationUser= new User();
        modificationUser.setEmail("test6@gmail.com");

        assertEquals(userService.update(modificationUser),false);

    }


}
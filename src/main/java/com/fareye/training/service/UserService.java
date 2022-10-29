package com.fareye.training.service;

import com.fareye.training.model.User;
import com.fareye.training.repository.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String email) {

        List<User> userList = userRepository.findByEmail(email);

        if(!userList.isEmpty()) {
            return userList.get(0);
        }

        return null;

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Boolean add(User user) throws ParseException {

        if(getUser(user.getEmail()) == null) {
            user.setCreated(LocalDateTime.now());
            user.setGithubPhoto(getGithubPhotoUrl(user));
            userRepository.save(user);
            return true;
        }

        return false;

    }

    public Boolean delete(String email) {

        User user = getUser(email);

        if(user != null) {
            userRepository.deleteById(user.getId());
            return true;
        }

        return false;
    }

    public Boolean update(User userModifications) throws InvocationTargetException, IllegalAccessException, ParseException {

        User user = getUser(userModifications.getEmail());
        if(user != null) {
            user.setModified(LocalDateTime.now());
            user.setFirstName(userModifications.getFirstName());
            user.setLastName(userModifications.getLastName());
            user.setPassword(userModifications.getPassword());
            user.setRole(userModifications.getRole());
            user.setGithubUserName(userModifications.getGithubUserName());
            user.setGithubToken(userModifications.getGithubToken());
            user.setGithubPhoto(getGithubPhotoUrl(user));
            userRepository.save(user);
            return true;
        }

        return false;
    }

    private String getGithubPhotoUrl(User user) throws ParseException {

        try {
            if (user.getGithubUserName() != null && user.getGithubToken() != null) {

                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", "application/vnd.github+json");
                headers.set("Authorization", "Bearer " + user.getGithubToken());
                String resourceUrl
                        = "https://api.github.com/users/" + user.getGithubUserName();
                HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
                ResponseEntity<String> response = restTemplate.exchange(
                        resourceUrl, HttpMethod.GET, requestEntity, String.class);

                String body = response.getBody();
                JSONParser jsonParser = new JSONParser();
                Object jsonObj = jsonParser.parse(body);
                JSONObject jsonObject = (JSONObject) jsonObj;
                return (String) jsonObject.get("avatar_url");

            }
        } catch (Exception e) {
            System.out.println("Github Api fetch fail");
        }
        return null;
    }


}

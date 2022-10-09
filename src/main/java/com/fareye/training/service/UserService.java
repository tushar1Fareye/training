package com.fareye.training.service;

import com.fareye.training.model.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    public Boolean add(User user) throws ParseException {

        User filteredUser = get(user.getEmail());

        if(filteredUser == null) {
            user.setGithubPhoto(getGithubPhotoUrl(user));
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

    private String getGithubPhotoUrl(User user) throws ParseException {

        if(user.getGithubUserName() != null && user.getGithubToken() != null) {

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

        return null;
    }


}

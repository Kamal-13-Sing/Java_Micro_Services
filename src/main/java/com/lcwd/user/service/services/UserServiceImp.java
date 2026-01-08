package com.lcwd.user.service.services;

import com.lcwd.user.service.dtos.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserServices {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();

        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        //get user from database using user repo
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found in server !! " + userId));

        //fetch rating of the above user from rating services
        // http://localhost:8083/ratings/users/65c55d01-dea9-4f83-8489-698a8bf4712d

        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), ArrayList.class);
        logger.info("{}", ratingOfUser);

        user.setRatings(ratingOfUser);
        return user;
    }
}

package com.lcwd.user.service.services;

import com.lcwd.user.service.dtos.HotelDto;
import com.lcwd.user.service.dtos.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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

        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();


        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel

            //http://localhost:8082/hotels/6cd69eec-18df-4667-a8b5-a217f4716fd5
            ResponseEntity<HotelDto> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), HotelDto.class);
            HotelDto hotel = forEntity.getBody();
            logger.info("response status code: {}", forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);
            //return the tating
            return rating;
        }).toList();

        //setting value of rating
        user.setRatings(ratingList);
        return user;
    }
}

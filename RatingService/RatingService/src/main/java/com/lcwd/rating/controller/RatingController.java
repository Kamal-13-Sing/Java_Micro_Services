package com.lcwd.rating.controller;


import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    //create
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating ratingCreated = ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingCreated);
    }


    //get single
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getSingleRating(@PathVariable String ratingId) {
        Rating rating = ratingService.getRating(ratingId);
        return ResponseEntity.ok(rating);
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating() {
        List<Rating> ratings = ratingService.getAll();
        return ResponseEntity.ok(ratings);
    }

    //get all by user id
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userId) {
        List<Rating> ratings = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(ratings);
    }


    //get all by hotel id
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId) {
        List<Rating> ratings = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratings);
    }

}

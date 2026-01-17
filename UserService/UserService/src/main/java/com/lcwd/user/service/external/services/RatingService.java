package com.lcwd.user.service.external.services;

import com.lcwd.user.service.dtos.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;
import java.util.Objects;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    //get

    //post
    @PostMapping("/ratings")
    public Rating createRating(Rating rating);

    //put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);


    //delete
    @DeleteMapping("ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}

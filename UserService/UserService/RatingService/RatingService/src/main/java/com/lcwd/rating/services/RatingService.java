package com.lcwd.rating.services;


import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create

    public Rating create(Rating rating);

    //get all
    List<Rating> getAll();

    //get single
    Rating getRating(String id);

    //get all by userid
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);

}

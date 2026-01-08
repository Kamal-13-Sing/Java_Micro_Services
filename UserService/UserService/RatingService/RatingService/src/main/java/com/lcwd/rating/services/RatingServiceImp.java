package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.exceptions.ResourceNotFoundException;
import com.lcwd.rating.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImp implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        String randomUserId = UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRating(String id) {
        return ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating with given id not found in server !! " + id));
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}

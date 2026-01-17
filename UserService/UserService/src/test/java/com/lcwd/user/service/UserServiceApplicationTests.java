package com.lcwd.user.service;

import com.lcwd.user.service.dtos.Rating;
import com.lcwd.user.service.external.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	//create rating test

//	@Test
//	void createRating(){
//		Rating rating = Rating.builder()
//				.ratings(10)
//				.userId("")
//				.hotelId("")
//				.feedback("this is created using feing client")
//				.build();
//	 Rating savedRating = ratingService.createRating(rating);
//		System.out.println("new rating creating: "+savedRating);
//	}
}

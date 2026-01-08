package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create

    public Hotel create(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Hotel getHotel(String id);
}

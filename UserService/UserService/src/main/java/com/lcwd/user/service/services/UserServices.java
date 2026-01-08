package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserServices {

    public User saveUser(User user);

    public List<User> getAllUser();

    public User getUser(String userId);
}

package com.hami.service;

import com.hami.entity.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);
    public User updateUser(User user);
    public User findUserId(String userId);
    public List<User> findAllUsers();
    public void deleteUserById(String userId);
    public User displayUserMetaDate(String userId);
}

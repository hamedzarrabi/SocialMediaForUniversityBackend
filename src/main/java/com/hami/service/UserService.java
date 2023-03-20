package com.hami.service;

import com.hami.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User createUser(User user);
    public User updateUser(User user);
    public Optional<User> findUserId(Long userId);
    public List<User> findAllUsers();
    public void deleteUserById(Long userId);
}

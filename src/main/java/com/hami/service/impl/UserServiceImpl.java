package com.hami.service.impl;

import com.hami.entity.User;
import com.hami.repository.UserRepository;
import com.hami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(String userId) {

    }

    @Override
    public User displayUserMetaDate(String userId) {
        return userRepository.findByUserId(userId);
    }


}

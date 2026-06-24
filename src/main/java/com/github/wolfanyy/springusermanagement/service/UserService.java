package com.github.wolfanyy.springusermanagement.service;

import com.github.wolfanyy.springusermanagement.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User findById(Long id);

    List<User> findAll();

    User update(User user);

    void deleteById(Long id);
}
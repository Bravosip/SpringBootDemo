package com.example.demo.service;

import com.example.demo.dto.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    public List<User> getUserList();
    public int add(User user);
    public int update(Integer id, User user);
    public int delete(Integer id);

}

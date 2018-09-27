package com.example.demo.dao;

import com.example.demo.dto.User;

import java.util.List;

public interface UserDao {
    User getUserById(Integer id);
    public List<User> getUserList();
    public int add(User user);
    public int update(Integer id, User user);
    public int delete(Integer id);
}

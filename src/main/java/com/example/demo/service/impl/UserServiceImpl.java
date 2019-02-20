package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        log.info("userService根据ID查询用户"+id);
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int update(Integer id, User user) {
        return userDao.update(id, user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }
}

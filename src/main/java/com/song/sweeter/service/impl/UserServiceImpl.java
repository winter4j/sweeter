package com.song.sweeter.service.impl;

import com.song.sweeter.entity.User;
import com.song.sweeter.repository.UserRepository;
import com.song.sweeter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional != null ? optional.get() : null;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public int updateLastLogin(Date date, Long id) {
        return userRepository.updateLastLogin(date, id);
    }

    @Override
    public int changeXing(Long userId, int delta) {
        return userRepository.changeXing(delta, userId);
    }

    @Override
    public int changeDong(Long userId, int delta) {
        return userRepository.changeDong(delta, userId);
    }
}

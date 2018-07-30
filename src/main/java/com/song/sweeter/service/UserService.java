package com.song.sweeter.service;

import com.song.sweeter.entity.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> getUserList();

    User findUserById(long id);

    User findByUserName(String userName);

    void save(User user);

    void delete(long id);

    int updateLastLogin(Date date, Long id);

    int changeXing(Long userId, int delta);

    int changeDong(Long userId, int delta);

}

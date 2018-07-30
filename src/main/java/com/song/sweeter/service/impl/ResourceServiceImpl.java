package com.song.sweeter.service.impl;

import com.song.sweeter.repository.UserRepository;
import com.song.sweeter.service.ResourceService;
import com.song.sweeter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService{
    @Autowired
    private UserService userService;

    @Override
    public UserService getUserService() {
        return userService;
    }
}

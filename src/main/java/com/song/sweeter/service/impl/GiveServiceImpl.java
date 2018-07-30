package com.song.sweeter.service.impl;

import com.song.sweeter.entity.Give;
import com.song.sweeter.repository.GiveRepository;
import com.song.sweeter.service.GiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiveServiceImpl implements GiveService {
    @Autowired
    private GiveRepository giveRepository;

    @Override
    public List<Give> findAll() {
        return giveRepository.findAll();
    }

    @Override
    public void save(Give give) {
        giveRepository.save(give);
    }
}

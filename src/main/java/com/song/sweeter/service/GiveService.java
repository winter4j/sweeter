package com.song.sweeter.service;

import com.song.sweeter.entity.Give;

import java.util.List;

public interface GiveService {
    List<Give> findAll();

    void save(Give give);
}

package com.song.sweeter.service;

import com.song.sweeter.comm.StateEnum;
import com.song.sweeter.entity.Consume;

import java.util.List;

public interface ConsumeService {
    List<Consume> findAll();

    void save(Consume use);

    Consume findById(Long id);

    int updateState(StateEnum state, Long id);
}

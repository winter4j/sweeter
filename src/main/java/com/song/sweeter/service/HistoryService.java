package com.song.sweeter.service;

import com.song.sweeter.comm.StateEnum;
import com.song.sweeter.entity.History;

import java.util.List;

public interface HistoryService {
    List<History> findAll();

    History findById(Long id);

    int updateState(StateEnum state, Long id);

    void save(History history);

}

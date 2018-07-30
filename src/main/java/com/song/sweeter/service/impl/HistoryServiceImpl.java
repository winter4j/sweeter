package com.song.sweeter.service.impl;

import com.song.sweeter.comm.StateEnum;
import com.song.sweeter.entity.History;
import com.song.sweeter.repository.HistoryRepository;
import com.song.sweeter.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private HistoryRepository historyRepository;


    @Override
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    @Override
    public History findById(Long id) {
        Optional<History> optional = historyRepository.findById(id);
        return optional != null ? optional.get() : null;
    }

    @Override
    public int updateState(StateEnum state, Long id) {
        return historyRepository.updateState(state, id);
    }

    @Override
    public void save(History history) {
        historyRepository.save(history);
    }
}

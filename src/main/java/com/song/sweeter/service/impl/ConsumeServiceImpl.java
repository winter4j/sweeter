package com.song.sweeter.service.impl;

import com.song.sweeter.comm.StateEnum;
import com.song.sweeter.entity.Consume;
import com.song.sweeter.repository.ConsumeRepository;
import com.song.sweeter.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumeServiceImpl implements ConsumeService {
    @Autowired
    private ConsumeRepository consumeRepository;

    @Override
    public List<Consume> findAll() {
        return consumeRepository.findAll();
    }

    @Override
    public void save(Consume use) {
        consumeRepository.save(use);
    }

    @Override
    public Consume findById(Long id) {
        Optional<Consume> optional = consumeRepository.findById(id);
        return optional != null ? optional.get() : null;
    }

    @Override
    public int updateState(StateEnum state, Long id) {
        return consumeRepository.updateState(state, id);
    }
}

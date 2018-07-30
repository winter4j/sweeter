package com.song.sweeter.repository;

import com.song.sweeter.comm.StateEnum;
import com.song.sweeter.entity.Consume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ConsumeRepository extends JpaRepository<Consume, Long> {
    @Transactional
    @Modifying
    @Query("update Consume set state = ?1 where id = ?2")
    int updateState(StateEnum state, Long id);
}

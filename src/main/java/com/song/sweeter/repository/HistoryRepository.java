package com.song.sweeter.repository;

import com.song.sweeter.comm.StateEnum;
import com.song.sweeter.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Transactional
    @Modifying
    @Query("update History set state = ?1 where id = ?2")
    int updateState(StateEnum state, Long id);
}

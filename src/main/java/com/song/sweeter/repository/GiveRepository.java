package com.song.sweeter.repository;

import com.song.sweeter.entity.Give;
import com.song.sweeter.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiveRepository extends JpaRepository<Give, Long> {
}

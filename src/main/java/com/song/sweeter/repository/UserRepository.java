package com.song.sweeter.repository;

import com.song.sweeter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUserName(String userName);

    @Transactional
    @Modifying
    @Query("update User set lastLogin = ?1 where id = ?2")
    int updateLastLogin(Date date, Long id);

    @Transactional
    @Modifying
    @Query("update User set xing = xing + ?1 where id = ?2")
    int changeXing(int delta, Long userId);

    @Transactional
    @Modifying
    @Query("update User set dong = dong + ?1 where id = ?2")
    int changeDong(int delta, Long userId);
}

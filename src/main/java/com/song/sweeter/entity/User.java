package com.song.sweeter.entity;

import com.song.sweeter.comm.SexEnum;
import com.song.sweeter.comm.dto.UserDto;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName = "";
    @Column(nullable = false)
    private String password = "";
    @Column(nullable = false)
    private Integer xing = 0;
    @Column(nullable = false)
    private Integer dong = 0;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SexEnum sex = SexEnum.MALE;
    private Date lastLogin;
    @Column(nullable = false)
    private Long half = 0L;
    @Column(nullable = false)
    private int egg = 0;

    private transient String halfName;

    public UserDto genUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setUserName(this.userName);
        return userDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getXing() {
        return xing;
    }

    public void setXing(Integer xing) {
        this.xing = xing;
    }

    public Integer getDong() {
        return dong;
    }

    public void setDong(Integer dong) {
        this.dong = dong;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getHalf() {
        return half;
    }

    public void setHalf(Long half) {
        this.half = half;
    }

    public String getHalfName() {
        return halfName;
    }

    public void setHalfName(String halfName) {
        this.halfName = halfName;
    }

    public int getEgg() {
        return egg;
    }

    public void setEgg(int egg) {
        this.egg = egg;
    }
}
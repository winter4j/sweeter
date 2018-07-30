package com.song.sweeter.entity;

import com.song.sweeter.comm.ResEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Give {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Date date = new Date();
    @Column(nullable = false)
    private Long fromUser;
    @Column(nullable = false)
    private Long toUser;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Integer xing;
    @Column(nullable = false)
    private Integer dong;

    private transient String fromUserName;
    private transient String toUserName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public void setFromUser(Long fromUser) {
        this.fromUser = fromUser;
    }

    public Long getToUser() {
        return toUser;
    }

    public void setToUser(Long toUser) {
        this.toUser = toUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getXingName(){
       return ResEnum.XING.getName();
    }

    public String getDongName(){
        return ResEnum.DONG.getName();
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}

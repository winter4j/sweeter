package com.song.sweeter.entity;

import com.song.sweeter.comm.HistoryEnum;
import com.song.sweeter.comm.StateEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
public class History {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Date date = new Date();
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HistoryEnum type;
    @Column(nullable = false)
    private Long fromUser;
    @Column(nullable = false)
    private Long toUser;
    @Column(nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StateEnum state = StateEnum.ING;


    private transient String fromUserName;
    private transient String toUserName;
    private transient boolean closable;

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

    public HistoryEnum getType() {
        return type;
    }

    public void setType(HistoryEnum type) {
        this.type = type;
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

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
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

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }
}

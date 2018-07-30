package com.song.sweeter.comm.dto;

public class UseDto {
    private String key; //所使用的资源
    private String content; //说明

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

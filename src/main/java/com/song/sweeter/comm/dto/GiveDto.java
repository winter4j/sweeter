package com.song.sweeter.comm.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

public class GiveDto {
    @Range(min = 0, message = "不能为负数")
    private int xing;
    @Range(min = 0, message = "不能为负数")
    private int dong;
    @NotEmpty
    private String content;

    public int getXing() {
        return xing;
    }

    public void setXing(int xing) {
        this.xing = xing;
    }

    public int getDong() {
        return dong;
    }

    public void setDong(int dong) {
        this.dong = dong;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

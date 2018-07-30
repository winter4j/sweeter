package com.song.sweeter.comm.dto;

import com.song.sweeter.comm.ResEnum;

public class ResDto {
    private ResEnum resEnum;
    private int num;

    public ResDto(ResEnum resEnum, int num) {
        this.resEnum = resEnum;
        this.num = num;
    }

    public ResEnum getResEnum() {
        return resEnum;
    }

    public void setResEnum(ResEnum resEnum) {
        this.resEnum = resEnum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getType(){
        return this.resEnum.getType();
    }

    public String getName(){
        return this.resEnum.getName();
    }

    public String getKey(){
        return this.resEnum.toString();
    }
}

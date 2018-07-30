package com.song.sweeter.comm;

public class Result<T> {
    private int code;
    private boolean succ;
    private T data;
    private String errorMsg;

    public Result<T> buildCode(int code){
        this.code = code;
        return this;
    }

    public Result<T> buildSucc(boolean succ){
        this.succ = succ;
        return this;
    }

    public Result<T> buildData(T data){
        this.data = data;
        return this;
    }

    public Result<T> buildErrorMsg(String errorMsg){
        this.errorMsg = errorMsg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public boolean isSucc() {
        return succ;
    }

    public T getData() {
        return data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

package com.hzdz.ls.common;

public class ResultDetail<T> extends Result{

    public final T data;

    public ResultDetail(int code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public ResultDetail(T data) {
        super(0, "请求成功");
        this.data = data;
    }


}

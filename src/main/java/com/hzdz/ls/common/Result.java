package com.hzdz.ls.common;

public class Result {
    public final int code;
    public final String msg;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
        code = 0;
        msg = "请求成功";
    }

    public static final Result SUCCESS = new Result();
    public static final Result FAILURE = new Result(-1, "操作异常");
}

package com.hzdz.ls.common;

public enum UploadState {
    UPLOAD_SUCCESS(0, "上传文件成功！"),
    UPLOAD_FAILURE(1, "上传文件失败！"),
    UPLOAD_TYPE_ERROR(2, "上传文件的类型错误！"),
    UPLOAD_OVERSIZE(3, "上传文件过大！"),
    UPLOAD_ZEROSIZE(4, "上传文件为空！"),
    UPLOAD_NOTFOUND(5, "上传文件路径错误！");

    private String state;
    private int flag;

    public String getState() {
        return state;
    }

    public int getFlag() {
        return flag;
    }

    UploadState(int flag, String state) {
        this.state = state;
        this.flag = flag;
    }
}

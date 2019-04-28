package com.smtjava.demo.base;


public class BaseRes<T> {
    private int code;
    private String msg;
    private T data;

    public BaseRes() {

    }

    public BaseRes(int code) {
        setCode(code);
        if (code == -1) {
            if (getMsg() != null) {
                setMsg("操作失败");
            }
        } else {
            if (getMsg() != null) {
                setMsg("请求成功");
            }
        }
    }

    public BaseRes(int code, String msg) {
        setMsg(msg);
        setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public BaseRes setData(T data) {
        this.data = data;
        if (getCode()==0) {
            setMsg("操作成功");
        }
        return this;
    }
}

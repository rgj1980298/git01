package com.group.five.exceptions;

public class AuthException extends RuntimeException{
    private Integer code = 300;
    private  String msg = "该角色无此权限";

    public AuthException() {
    }

    public AuthException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AuthException(Integer code) {
        this.code = code;
    }

    public AuthException(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

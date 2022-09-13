package com.group.five.query;

import com.group.five.base.BaseQuery;

public class ScoreQuery extends BaseQuery {
    private String username;
    private   String state;
    private   String clazzname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClazzname() {
        return clazzname;
    }

    public void setClazzname(String clazzname) {
        this.clazzname = clazzname;
    }
}

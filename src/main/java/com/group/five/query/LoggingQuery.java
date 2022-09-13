package com.group.five.query;

import com.group.five.base.BaseQuery;

public class LoggingQuery extends BaseQuery {
    private String userName;
    private String moduleName;
    private Boolean status = true;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

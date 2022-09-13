package com.group.five.query;


import com.group.five.base.BaseQuery;

/**
 * 多条件查询条件
 */
public class IdeaQuery extends BaseQuery {


  private String customerName; // 班级名称
    private String linkMan; // 班长
    private String state; // 分配状态


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

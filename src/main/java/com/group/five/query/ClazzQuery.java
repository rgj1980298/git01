package com.group.five.query;

import com.group.five.base.BaseQuery;

/**
 * @Author Dcz
 * @DateCreated 2022/9/1 17:15
 */
public class ClazzQuery extends BaseQuery {

    private Integer id;
    private String clazzName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }
}

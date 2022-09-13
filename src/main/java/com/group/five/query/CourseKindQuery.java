package com.group.five.query;

import com.group.five.base.BaseQuery;

/**
 * @BelongsProject: SVN1
 * @BelongsPackage: com.group.five.query
 * @Author: sisyphus
 * @CreateTime: 2022-09-02  17:20
 * @Description: TODO
 * @Version: 1.0
 */
public class CourseKindQuery extends BaseQuery {

    private String KindName;

    public String getKindName() {
        return KindName;
    }

    public void setKindName(String kindName) {
        KindName = kindName;
    }
}

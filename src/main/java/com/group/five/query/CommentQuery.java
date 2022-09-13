package com.group.five.query;

import com.group.five.base.BaseQuery;

public class CommentQuery extends BaseQuery {
    private String studentName;
    private String teacherName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

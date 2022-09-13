package com.group.five.query;

import com.group.five.base.BaseQuery;

/**
 * @BelongsProject: SVN1
 * @BelongsPackage: com.group.five.query
 * @Author: sisyphus
 * @CreateTime: 2022-09-01  15:16
 * @Description: TODO
 * @Version: 1.0
 */
public class AllQueryForCourse extends BaseQuery {

    private String CourseKind;//课程种类

    private Integer CourseKindId;//课程种类Id

    private String CourseName;//课程名

    private Integer MinTotalCount;//课时

    private Integer MaxTotalCount;//课时

    private Integer TeacherId;//授课教师姓名

    private String TeacherName;//授课教师姓名

    private Integer ClassId;//班级Id

    private String ClassName;//班级Name


    public Integer getCourseKindId() {
        return CourseKindId;
    }

    public void setCourseKindId(Integer courseKindId) {
        CourseKindId = courseKindId;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public Integer getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(Integer teacherId) {
        TeacherId = teacherId;
    }

    public Integer getClassId() {
        return ClassId;
    }

    public void setClassId(Integer classId) {
        ClassId = classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getCourseKind() {
        return CourseKind;
    }

    public void setCourseKind(String courseKind) {
        CourseKind = courseKind;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public Integer getMinTotalCount() {
        return MinTotalCount;
    }

    public void setMinTotalCount(Integer minTotalCount) {
        MinTotalCount = minTotalCount;
    }

    public Integer getMaxTotalCount() {
        return MaxTotalCount;
    }

    public void setMaxTotalCount(Integer maxTotalCount) {
        MaxTotalCount = maxTotalCount;
    }

    @Override
    public String toString() {
        return "CourseQuery{" +
                "CourseKind='" + CourseKind + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", MinTotalCount=" + MinTotalCount +
                ", MaxTotalCount=" + MaxTotalCount +
                ", TeacherName='" + TeacherName + '\'' +
                '}';
    }
}

package cn.edu.swpu.kotlintest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 65403 on 2018/5/7.
 */
@Entity
public class CourseDB {
    private String courseId;
    private String courseName;
    private String courseNum;
    private String credit;
    private String properties;
    private String type;
    private String teacher;
    private String StudyMode;
    private String state;
    private String week;
    private String weekday;
    private String startTime;
    private String duration;
    private String campus;
    private String teachingBuilding;
    private String classroom;
    @Generated(hash = 1804619125)
    public CourseDB(String courseId, String courseName, String courseNum,
            String credit, String properties, String type, String teacher,
            String StudyMode, String state, String week, String weekday,
            String startTime, String duration, String campus,
            String teachingBuilding, String classroom) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNum = courseNum;
        this.credit = credit;
        this.properties = properties;
        this.type = type;
        this.teacher = teacher;
        this.StudyMode = StudyMode;
        this.state = state;
        this.week = week;
        this.weekday = weekday;
        this.startTime = startTime;
        this.duration = duration;
        this.campus = campus;
        this.teachingBuilding = teachingBuilding;
        this.classroom = classroom;
    }
    @Generated(hash = 1140258734)
    public CourseDB() {
    }
    public String getCourseId() {
        return this.courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return this.courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseNum() {
        return this.courseNum;
    }
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }
    public String getCredit() {
        return this.credit;
    }
    public void setCredit(String credit) {
        this.credit = credit;
    }
    public String getProperties() {
        return this.properties;
    }
    public void setProperties(String properties) {
        this.properties = properties;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTeacher() {
        return this.teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getStudyMode() {
        return this.StudyMode;
    }
    public void setStudyMode(String StudyMode) {
        this.StudyMode = StudyMode;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getWeek() {
        return this.week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getWeekday() {
        return this.weekday;
    }
    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getDuration() {
        return this.duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getCampus() {
        return this.campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }
    public String getTeachingBuilding() {
        return this.teachingBuilding;
    }
    public void setTeachingBuilding(String teachingBuilding) {
        this.teachingBuilding = teachingBuilding;
    }
    public String getClassroom() {
        return this.classroom;
    }
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

}

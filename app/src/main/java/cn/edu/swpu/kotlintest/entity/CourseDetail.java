package cn.edu.swpu.kotlintest.entity;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * Created by 65403 on 2018/6/10.
 */

@SmartTable(name="考试成绩")
public class CourseDetail {
    public CourseDetail(String courseNum, String lessonNum, String courseName,
                        String courseEnName, String credits, String attribute,
                        String maxGrade, String minGrade, String averageGrade,
                        String grade, String rank, String reason) {
        this.courseNum = courseNum;
        this.lessonNum = lessonNum;
        this.courseName = courseName;
        this.courseEnName = courseEnName;
        this.credits = credits;
        this.attribute = attribute;
        this.maxGrade = maxGrade;
        this.minGrade = minGrade;
        this.averageGrade = averageGrade;
        this.grade = grade;
        this.rank = rank;
        this.reason = reason;
    }

    @SmartColumn(id = 1, name = "课程号")
    private String courseNum;
    @SmartColumn(id = 2, name = "课序号")
    private String lessonNum;
    @SmartColumn(id = 3, name = "课程名")
    private String courseName;
    @SmartColumn(id = 4, name = "英文课程名")
    private String courseEnName;
    @SmartColumn(id = 5, name = "学分")
    private String credits;
    @SmartColumn(id = 6, name = "课程属性")
    private String attribute;
    @SmartColumn(id = 7, name = "课程最高分")
    private String maxGrade;
    @SmartColumn(id = 8, name = "课程最低分")
    private String minGrade;
    @SmartColumn(id = 9, name = "课程平均分")
    private String averageGrade;
    @SmartColumn(id = 10, name = "成绩")
    private String grade;
    @SmartColumn(id = 11, name = "名次")
    private String rank;
    @SmartColumn(id = 12, name = "特殊原因")
    private String reason;

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(String lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseEnName() {
        return courseEnName;
    }

    public void setCourseEnName(String courseEnName) {
        this.courseEnName = courseEnName;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(String maxGrade) {
        this.maxGrade = maxGrade;
    }

    public String getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
    }

    public String getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    //    @SmartColumn(id = 13, name = "明细")
//    private String detail;

}

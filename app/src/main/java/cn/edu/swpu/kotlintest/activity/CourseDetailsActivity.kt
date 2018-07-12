package cn.edu.swpu.kotlintest.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cn.edu.swpu.kotlintest.R
import cn.edu.swpu.kotlintest.db.CourseDaoOpe
import kotlinx.android.synthetic.main.activity_course_details.*

class CourseDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        val intent = intent
        val weekday = intent.getStringExtra("weekday")
        val start = intent.getStringExtra("start")
        val duration = intent.getStringExtra("duration")

        val cdo = CourseDaoOpe()

        for (j in start.toInt() until start.toInt()+duration.toInt()) {
            val courseList = cdo.queryForStartTime(this, weekday, j.toString())
            if (courseList != null)
                for (i in 0 until courseList.size) {
                    val textName = TextView(this)
                    val textWeekday = TextView(this)
                    val textTeachingBuilding = TextView(this)
                    val textClassroom = TextView(this)
                    val textStartTime = TextView(this)
                    val textDuration = TextView(this)
                    val textTeacher = TextView(this)

                    textName.text = "课程名：" + courseList[i].courseName
                    textClassroom.text = "教室：" + courseList[i].classroom
                    textWeekday.text = "周几：周" + courseList[i].weekday
                    textTeachingBuilding.text = "教学楼：" + courseList[i].teachingBuilding
                    textTeacher.text = "老师：" + courseList[i].teacher.replace("*", "")
                    textStartTime.text = "开始时间：" + courseList[i].startTime
                    textDuration.text = "持续时间：" + courseList[i].duration + "\n\n\n\n\n"

                    course_detail.addView(textName)
                    course_detail.addView(textTeacher)
                    course_detail.addView(textTeachingBuilding)
                    course_detail.addView(textClassroom)
                    course_detail.addView(textWeekday)
                    course_detail.addView(textStartTime)
                    course_detail.addView(textDuration)

                }
        }
        print("hello")
    }
}

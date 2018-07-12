package cn.edu.swpu.kotlintest.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.edu.swpu.kotlintest.R
import cn.edu.swpu.kotlintest.entity.CourseDetail
import cn.edu.swpu.kotlintest.tools.ParseHtml
import com.bin.david.form.core.SmartTable
import com.bin.david.form.data.Column
import com.bin.david.form.data.table.TableData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class GradeViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade_view)

        val holeCookie = intent.getStringExtra("cookies")
        val cookies = holeCookie?.split(";")
        val jsessionId = cookies!![0].split("=")[1].replace(";", "")
        val serverId = "jwxtc"
        doAsync {
            val gradeDetails = ParseHtml.getDetails(jsessionId, serverId)
            if (gradeDetails.size != 0) {
                val table = findViewById<SmartTable<CourseDetail>>(R.id.table)
                val courseNum = Column<String>("课程号", "courseNum")
                val lessonNum = Column<String>("课序号", "lessonNum")
                val courseName = Column<String>("课程名", "courseName")
                courseName.isFixed = true
                val courseEnName = Column<String>("英文课程名", "courseEnName")
                val credits = Column<String>("学分", "credits")
                val attribute = Column<String>("课程属性", "attribute")
                val maxGrade = Column<String>("课程最高分", "maxGrade")
                val minGrade = Column<String>("课程最低分", "minGrade")
                val averageGrade = Column<String>("课程平均分", "averageGrade")
                val grade = Column<String>("成绩", "grade")
                val rank = Column<String>("名次", "rank")
                val reason = Column<String>("特殊原因", "reason")
                val tableData = TableData("考试成绩", gradeDetails, courseNum, lessonNum, courseName,
                        courseEnName, credits, attribute, maxGrade, minGrade, averageGrade, grade,
                        rank, reason)
                table?.setTableData(tableData)
            }else {
                uiThread { toast("发生了一些小错误，请登录教务网站检查您的成绩能否查看") }
            }
        }
    }
}

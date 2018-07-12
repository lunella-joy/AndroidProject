package cn.edu.swpu.kotlintest.entity

/**
 * Created by 65403 on 2018/5/7.
 */
data class Course(val courseId: String, val courseName: String, val courseNum: String,
                  val credit: String, val properties: String, val type: String,
                  val teacher: String, val StudyMode: String, val state: String,
                  val week: String, val weekday: String, val startTime: String,
                  val duration: String, val campus: String, val teachingBuilding: String,
                  val classroom: String)
// 课程号 	 课程名 	 课序号 	 学分 	 课程属性 	 考试类型 	 教师 	 修读方式 	 选课状态 	 周次 	 星期 	 节次 	 节数 	 校区 	 教学楼 	 教室

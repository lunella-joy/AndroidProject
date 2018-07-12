package cn.edu.swpu.kotlintest.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import cn.edu.swpu.kotlintest.activity.CourseDetailsActivity
import cn.edu.swpu.kotlintest.activity.LoginActivity
import cn.edu.swpu.kotlintest.R
import cn.edu.swpu.kotlintest.db.CourseDaoOpe
import cn.edu.swpu.kotlintest.entity.CourseDB
import cn.edu.swpu.kotlintest.tools.tools
import kotlinx.android.synthetic.main.activity_schedule.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.support.v4.toast
import java.util.*

/**
 * Created by 65403 on 2018/6/8.
 */
class CourseDetailsFragment : Fragment() {

    private var inflater1: LayoutInflater? = null
    private var container1: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater1 = inflater
        container1 = container
        val view = inflater.inflate(R.layout.activity_schedule, container, false)
        view.fab.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
//            activity.onBackPressed();//销毁自己
//            activity.finish()
            onHiddenChanged(true)
            startActivity(intent)
        }
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val weekday = if(calendar.get(Calendar.DAY_OF_WEEK) - 1 != 0)
            calendar.get(Calendar.DAY_OF_WEEK) - 1 else 7
        val year = calendar.get(Calendar.YEAR)
        val context = activity

        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dm)

        for(i in 0..7) {
            val text = TextView(context)
            val monthInsert = if (day - weekday + 1 > 0)  month else if(month == 1) 12 else month - 1
            val mondayInsert = tools.getMondaysDay(year, month, day, weekday)
            val flag = mondayInsert > day  //判断星期一是不是在上个月
            text.text = when (i) {
                0 -> "${monthInsert}月"
                1 -> "周一\n${mondayInsert}日"
                2 -> if (tools.AfterAddDay(year, month, mondayInsert, 1) == 1) "周二\n${month + 1}月"
                else "周二\n${tools.AfterAddDay(year, month, mondayInsert, 1)}日"
                3 -> if (tools.AfterAddDay(year, month, mondayInsert, 2) == 1) "周三\n${month + 1}月"
                else "周三\n${tools.AfterAddDay(year, month, mondayInsert, 2)}日"
                4 -> if (tools.AfterAddDay(year, month, mondayInsert, 3) == 1) "周四\n${month + 1}月"
                else "周四\n${tools.AfterAddDay(year, month, mondayInsert, 3)}日"
                5 -> if (tools.AfterAddDay(year, month, mondayInsert, 4) == 1) "周五\n${month + 1}月"
                else "周五\n${tools.AfterAddDay(year, month, mondayInsert, 4)}日"
                6 -> if (tools.AfterAddDay(year, month, mondayInsert, 5) == 1) "周六\n${month + 1}月"
                else "周六\n${tools.AfterAddDay(year, month, mondayInsert, 5)}日"
                7 -> if (tools.AfterAddDay(year, month, mondayInsert, 6) == 1) "周日\n${month + 1}月"
                else "周日\n${tools.AfterAddDay(year, month, mondayInsert, 6)}日"
                else -> "error"
            }
            if (mondayInsert + i - 1 == day) {  //判断当前添加的这一栏是否是当天，是则将这一栏背景变为蓝色
                text.backgroundColor = Color.BLUE
            }
            text.gravity = Gravity.CENTER_HORIZONTAL
            view.day_of_week.addView(text)
            val params = LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
            text.layoutParams = params
        }

        for (i in 1..12) {  //添加左边的节数
            val text = TextView(context)
            text.text = i.toString()
            view.time.addView(text)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    0)
            params.gravity = Gravity.CENTER
            params.height = dm.widthPixels / 6
            text.layoutParams = params
        }
        val cdo = CourseDaoOpe()
        val courses = cdo.queryAll(context)?.toList()

        val schedule = listOf(view.week_1, view.week_2, view.week_3, view.week_4,
                view.week_5, view.week_6, view.week_7) //建立一个list将序号与布局文件联系起来
        //把课程按照上课周次时间分到7个数组中
        val week1 = mutableListOf<CourseDB>()
        val week2 = mutableListOf<CourseDB>()
        val week3 = mutableListOf<CourseDB>()
        val week4 = mutableListOf<CourseDB>()
        val week5 = mutableListOf<CourseDB>()
        val week6 = mutableListOf<CourseDB>()
        val week7 = mutableListOf<CourseDB>()
        if (courses != null){
            for (course in courses){
                val weekday = course.weekday.trim().toInt()
                when(weekday) {
                    1 -> week1.add(course)
                    2 -> week2.add(course)
                    3 -> week3.add(course)
                    4 -> week4.add(course)
                    5 -> week5.add(course)
                    6 -> week6.add(course)
                    7 -> week7.add(course)
                    else -> print("unknow")
                }
            }
        }
        for (i in 0 until schedule.size) {  //把每天的课添加进课程表
            val week = when(i) {
                0 -> week1
                1 -> week2
                2 -> week3
                3 -> week4
                4 -> week5
                5 -> week6
                6 -> week7
                else -> null
            }
            val courseList = mutableMapOf<Int, List<CourseDB>>()
            val lesson1 = mutableListOf<CourseDB>()
            val lesson2 = mutableListOf<CourseDB>()
            val lesson3 = mutableListOf<CourseDB>()
            val lesson4 = mutableListOf<CourseDB>()
            val lesson5 = mutableListOf<CourseDB>()
            val lesson6 = mutableListOf<CourseDB>()
            val lesson7 = mutableListOf<CourseDB>()
            val lesson8 = mutableListOf<CourseDB>()
            val lesson9 = mutableListOf<CourseDB>()
            val lesson10 = mutableListOf<CourseDB>()
            val lesson11 = mutableListOf<CourseDB>()
            val lesson12 = mutableListOf<CourseDB>()
            if (week != null) {
                for (course in week) {
                    val startTime = course.startTime.trim().toInt()
                    val duration = course.duration.trim().toInt()
                    for (j in startTime until startTime+duration){
                        when (j){
                            1 -> lesson1.add(course)
                            2 -> lesson2.add(course)
                            3 -> lesson3.add(course)
                            4 -> lesson4.add(course)
                            5 -> lesson5.add(course)
                            6 -> lesson6.add(course)
                            7 -> lesson7.add(course)
                            8 -> lesson8.add(course)
                            9 -> lesson9.add(course)
                            10 -> lesson10.add(course)
                            11 -> lesson11.add(course)
                            12 -> lesson12.add(course)
                        }
                    }
                }
            }
            for (j in 1..12) {
                courseList[j] = when(j) {
                    1 -> lesson1.toList()
                    2 -> lesson2.toList()
                    3 -> lesson3.toList()
                    4 -> lesson4.toList()
                    5 -> lesson5.toList()
                    6 -> lesson6.toList()
                    7 -> lesson7.toList()
                    8 -> lesson8.toList()
                    9 -> lesson9.toList()
                    10 -> lesson10.toList()
                    11 -> lesson11.toList()
                    12 -> lesson12.toList()
                    else -> lesson1.toList()
                }
            }

            var lesson = 1  //在循环中判断当前进行到哪节课了
            var flag = 0  //在循环中判断当前相同的课已经累计了几节
            while (lesson < 13) {  //把单天的课添加进课程表
                if (courseList[lesson]?.isEmpty()!!) {
                    while (lesson<13&&courseList[lesson]?.isEmpty()!!) { //为没有课程的地方添加透明按钮
                        flag++
                        lesson++
                    }
                    val button = Button(context)
                    button.background.alpha = 0  //设置透明度
                    schedule[i]?.addView(button)
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            0)
                    params.gravity = Gravity.CENTER
                    params.height = dm.widthPixels / 6 * flag
                    button.layoutParams = params
                    flag = 0 //将flag重置为0，方便下次再使用flag做连续上课节数的判断
                }else {
                    val name = courseList[lesson]?.get(0)?.courseName
                    val teachingBuilding = courseList[lesson]?.get(0)?.teachingBuilding
                    val classroom = courseList[lesson]?.get(0)?.classroom
                    while (lesson<13 && courseList[lesson]?.isNotEmpty()!!
                            && courseList[lesson]?.get(0)?.courseName.equals(name)) run {
                        flag++
                        lesson++
                    }
                    val button = Button(context)
                    button.text = name?.trim() + "\n@" + teachingBuilding?.trim() + classroom?.trim()
                    button.textSize = 10f
                    schedule[i]?.addView(button)
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            0)
                    params.gravity = Gravity.CENTER
                    params.height = dm.widthPixels / 6 * flag
                    button.layoutParams = params

                    val intent = Intent(context, CourseDetailsActivity::class.java)
                    intent.putExtra("weekday", (i+1).toString())
                    intent.putExtra("start", (lesson-flag).toString())
                    intent.putExtra("duration", flag.toString())
                    button.setOnClickListener { startActivity(intent) }
                    flag = 0 //将flag重置为0，方便下次再使用flag做连续上课节数的判断
                }
            }
        }
        return view

    }

}
package cn.edu.swpu.kotlintest.tools

import android.util.Log
import cn.edu.swpu.kotlintest.entity.CourseDB
import cn.edu.swpu.kotlintest.entity.CourseDetail
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup
import java.util.regex.Pattern

/**
 * Created by 65403 on 2018/5/6.
 */
object ParseHtml {
    /**
     * 将登陆成功的cookies发送给服务器用于请求学生成绩页面，并将解析到的学生成绩返回为一个列表
     * *
     * @param String 传入的JsessionId,用于构建发送给服务器的cookies
     * @param string 传入的ServerId,用于构建发送给服务器的cookies
     * *
     * @return  解析之后的成绩信息的列表
     */
    fun getDetails(JsessionId: String, ServerId: String): MutableList<CourseDetail> {
        val url = "http://jwxt.swpu.edu.cn/bxqcjcxAction.do"
        val courses = mutableListOf<CourseDetail>()
        try{
            val doc = Jsoup.connect(url).cookie("JSESSIONID", JsessionId).cookie("SERVERID", ServerId).get()
            val table = doc.select("table[id=user]")[0]
            val tbody = table.select("thead")[0]
            val trs = tbody.select("tr")
            for (i in 1 until trs.size) {
                val tds = trs[i].select("td")
                var detail: String
                try {
                    detail = "http://jwxt.swpu.edu.cn${tds[12].select("img").attr("onclick").replace("cjmx('", "").replace("');", "")}"
                }
                catch (e: Exception) {
                    detail = ""
                    e.printStackTrace()
                }
                val courseDetail = CourseDetail(tds[0].text().trim(), tds[1].text().trim(), tds[2].text().trim(),
                        tds[3].text().trim(), tds[4].text().trim(), tds[5].text().trim(), tds[6].text().trim(),
                        tds[7].text().trim(), tds[8].text().trim(), tds[9].text().trim(), tds[10].text().trim(),
                        tds[11].text().trim())
                courses.add(courseDetail)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return courses
    }

    /**
     * 因为现在的教务处课程表页面为空，故不采取之前的getCourses方法，改用这个方法
     * *
     * @param String 传入的JsessionId,用于构建发送给服务器的cookies
     * @param string 传入的ServerId,用于构建发送给服务器的cookies
     * *
     * @return  解析之后的课程信息的列表
     */
    fun getCourses2(JsessionId: String, ServerId: String): MutableList<CourseDB> {
        val url = "http://jwxt.swpu.edu.cn/lnkbcxAction.do?zxjxjhh=2017-2018-2-2"
        val courses = mutableListOf<CourseDB>()
//        val pattern = "rowspan=\"\\d\""
        try {
            var flag = 0
            val doc = Jsoup.connect(url).cookie("JSESSIONID", JsessionId).cookie("SERVERID", ServerId).get()
            val table = doc.select("table[id=user]")[1]
            val tbody = table.select("tbody")
            val trs = tbody.select("tr")
            while (flag < trs.size) {
                val tds = trs[flag].select("td")
                var courseId = 0 //用作courses的计数器
                val rowspan = tds[0].attr("rowspan")
//                val r = Pattern.compile(pattern)
//                val m = r.matcher(tds[0].toString())
                val td = tds[tds.size-1].text().trim().replace(" ", "")
                    if (td.count() != 0) {
//                    val str = m.group().toString()
//                    val pattern1 = "\\d"  //匹配rowspan="1"中的数字
//                    val r1 = Pattern.compile(pattern1)
//                    val m1 = r1.matcher(str)
                        val num = rowspan.toInt()
//                    if (m1.find()) {
//                        num = m1.group().toInt() //得到这个课一周上几节
//                    }
                        val course = CourseDB(tds[1].text().toString().trim(),
                                tds[2].text().toString().trim(), tds[3].text().toString().trim(),
                                tds[4].text().toString().trim(), tds[5].text().toString().trim(),
                                tds[6].text().toString().trim(), tds[7].text().toString().trim(),
                                tds[8].text().toString().trim(),
                                tds[9].text().toString().trim(), splitWeek(tds[10].text().toString()).trim(),
                                tds[11].text().toString().trim(), tds[12].text().toString().trim(),
                                tds[13].text().toString().trim(), tds[14].text().toString().trim(),
                                tds[15].text().toString().trim(), tds[16].text().toString().trim())
                        courses.add(courseId++, course)  //将课程的属性录入
                        var i = 1
                        while (i < num) {
                            val course1 = CourseDB(tds[1].text().toString().trim(),
                                    tds[2].text().toString().trim(), tds[3].text().toString().trim(),
                                    tds[4].text().toString().trim(), tds[5].text().toString().trim(),
                                    tds[6].text().toString().trim(), tds[7].text().toString().trim(),
                                    tds[8].text().toString().trim(), tds[9].text().toString().trim(),
                                    splitWeek(trs[flag + i].select("td")[0].text().toString().trim()),
                                    trs[flag + i].select("td")[1].text().toString().trim(),
                                    trs[flag + i].select("td")[2].text().toString().trim(),
                                    trs[flag + i].select("td")[3].text().toString().trim(),
                                    trs[flag + i].select("td")[4].text().toString().trim(),
                                    trs[flag + i].select("td")[5].text().toString().trim(),
                                    trs[flag + i].select("td")[6].text().toString().trim())
                            courses.add(courseId++, course1)  //将课程的属性录入
                            i++

                        }
                        flag += num  //根据当前课程一周上的节数判断下一课程在哪行
                    } else {

                        flag++  //是实践课做跳过处理，不显示到课表上
                    }
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }
        return courses
    }

    /**
     * 将登陆成功的cookies发送给服务器用于请求学生课程表页面，并将解析到的课程返回为一个列表
     * *
     * @param String 传入的JsessionId,用于构建发送给服务器的cookies
     * @param string 传入的ServerId,用于构建发送给服务器的cookies
     * *
     * @return  解析之后的课程信息的列表
     */
    fun getCourses(JsessionId: String, ServerId: String): MutableList<CourseDB> {
        val url = "http://jwxt.swpu.edu.cn/xkAction.do?actionType=6"
        val pattern = "rowspan=\"\\d\""
        val courses = mutableListOf<CourseDB>()

        try {
            var flag = 0
            val doc = Jsoup.connect(url).cookie("JSESSIONID", JsessionId).cookie("SERVERID", ServerId).get()
            val table = doc.select("table[id=user]")[1]
            val tbody = table.select("tbody")
            val trs = tbody.select("tr")
            while (flag < trs.size) {
                val tds = trs[flag].select("td")
                val r = Pattern.compile(pattern)
                val m = r.matcher(tds[0].toString())
                var courseId = 0 //用作courses的计数器
                if (m.find()) {  //判断是不是实践课
                    val str = m.group().toString()
                    val pattern1 = "\\d"  //匹配rowspan="1"中的数字
                    val r1 = Pattern.compile(pattern1)
                    val m1 = r1.matcher(str)
                    var num = 1
                    if (m1.find()) {
                        num = m1.group().toInt() //得到这个课一周上几节
                    }
                    val course = CourseDB(tds[0].text().toString().trim(), tds[1].text().toString().trim(),
                            tds[2].text().toString().trim(), tds[3].text().toString().trim(),
                            tds[4].text().toString().trim(), tds[5].text().toString().trim(),
                            tds[6].text().toString().trim(), tds[8].text().toString().trim(),
                            tds[9].text().toString().trim(), splitWeek(tds[10].text().toString()).trim(),
                            tds[11].text().toString().trim(), tds[12].text().toString().trim(),
                            tds[13].text().toString().trim(), tds[14].text().toString().trim(),
                            tds[15].text().toString().trim(), tds[16].text().toString().trim())
                    courses.add(courseId++, course)  //将课程的属性录入

                    var i = 1
                    while (i < num) {
                        val course1 = CourseDB(tds[0].text().toString().trim(), tds[1].text().toString().trim(),
                                tds[2].text().toString().trim(), tds[3].text().toString().trim(),
                                tds[4].text().toString().trim(), tds[5].text().toString().trim(),
                                tds[6].text().toString().trim(), tds[8].text().toString().trim(),
                                tds[9].text().toString().trim(),
                                splitWeek(trs[flag + i].select("td")[0].text().toString().trim()),
                                trs[flag + i].select("td")[1].text().toString().trim(),
                                trs[flag + i].select("td")[2].text().toString().trim(),
                                trs[flag + i].select("td")[3].text().toString().trim(),
                                trs[flag + i].select("td")[4].text().toString().trim(),
                                trs[flag + i].select("td")[5].text().toString().trim(),
                                trs[flag + i].select("td")[6].text().toString().trim())
                        courses.add(courseId++, course1)  //将课程的属性录入
                        i++
                    }

                    flag += num  //根据当前课程一周上的节数判断下一课程在哪行
                } else {

                    flag++  //是实践课做跳过处理，不显示到课表上
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return courses
    }

    /**
     * 将周次信息拆分为更加易于辨认的字符串

     * @param string 传入的周次字符串
     * *
     * @return  返回的拆分后字符串
     */
    fun splitWeek(string: String): String {
        try {
            val week = string.replace("周", "").trim().split("-")
            val start = week[0]
            val end = week[1]
            var weeks = ""
            for (w in start.toInt()..end.toInt()) {
                weeks = weeks + w + ","
            }
            return weeks
        }catch (e: Exception) {
            return string.replace("周", "").trim()
        }
    }
}

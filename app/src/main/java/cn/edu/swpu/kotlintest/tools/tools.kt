package cn.edu.swpu.kotlintest.tools

/**
 * Created by 65403 on 2018/5/18.
 */
object tools {
    /**
     * 根据传入的日期信息判断传入日期周的周一是几号
     * *
     * @param Int 传入日期的年份
     * @param Int 传入日期的月份
     * @param Int 传入日期的日
     * @param Int 传入日期的星期几
     * *
     * @return  返回周一的日期
     */
    fun getMondaysDay(year: Int, month: Int, day: Int, weekday: Int): Int {
        val bigMonth = listOf(1, 3, 5, 7, 8, 10, 12)

        if (day - weekday + 1 <= 0) {  //判断周一是否在本月
            if (month - 1 > 0) {  //判断上个月是否在本年
                return if (month - 1 !in bigMonth) {  //判断上个月是否为小月
                    if (month - 1 == 2) {
                        if (year % 4 ==0) {  //判断是不是闰年
                            29 + day - weekday + 1
                        } else {
                            28 + day - weekday + 1
                        }
                    } else {
                        30 + day - weekday + 1
                    }
                } else {
                    31 + day - weekday + 1
                }
            } else {
                return 31 + day - weekday + 1
            }
        } else {
            return day - weekday + 1
        }
    }

    /**
     * 将根据传入的日期和添加的天数判断添加天数后的日期
     * *
     * @param Int 传入日期的年份
     * @param Int 传入日期的月份
     * @param Int 传入日期的日
     * @param Int 传入添加的天数
     * *
     * @return  返回计算后的日期
     */
    fun AfterAddDay(year: Int, month: Int, day: Int, addDays: Int): Int {
        val bigMonth = listOf(1, 3, 5, 7, 8, 10, 12)
        if (month !in bigMonth){  //判断是否是小月
            if (month == 2) {  //判断是否二月
                if (year % 4 == 0){
                    return if (day + addDays > 29) {
                        day + addDays - 29
                    } else {
                        day + addDays
                    }
                } else {
                    return if (day + addDays > 28) {
                        day + addDays - 28
                    } else {
                        day + addDays
                    }
                }
            } else {
                return if (day + addDays > 30) {
                    day + addDays - 30
                } else {
                    day + addDays
                }
            }
        } else {
            return if (day + addDays > 31) {
                day + addDays - 31
            } else {
                day + addDays
            }
        }
    }
}
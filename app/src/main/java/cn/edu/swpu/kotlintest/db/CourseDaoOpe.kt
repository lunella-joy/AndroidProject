package cn.edu.swpu.kotlintest.db

/**
 * Created by 65403 on 2018/5/7.
 */
import android.content.Context
import cn.edu.swpu.kotlintest.dao.CourseDBDao
import cn.edu.swpu.kotlintest.entity.CourseDB


class CourseDaoOpe {

    private object mHolder {
        val instance = CourseDaoOpe()
    }

    companion object {
        fun getInstance(): CourseDaoOpe {
            return mHolder.instance
        }
    }

    /**
     * 添加数据至数据库

     * @param context
     * *
     * @param stu
     */
    fun insertData(context: Context?, stu: CourseDB) {

        DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.insert(stu)
    }


    /**
     * 将数据实体通过事务添加至数据库

     * @param context
     * *
     * @param list
     */
    fun insertData(context: Context?, list: List<CourseDB>?) {
        if (null == list || list.size <= 0) {
            return
        }
        DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.insertInTx(list)
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；

     * @param context
     * *
     * @param student
     */
    fun saveData(context: Context?, student: CourseDB) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.save(student)
    }

    /**
     * 删除数据至数据库

     * @param context
     * *
     * @param student 删除具体内容
     */
    fun deleteData(context: Context?, student: CourseDB) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.delete(student)
    }

//    /**
//     * 根据id删除数据至数据库
//
//     * @param context
//     * *
//     * @param id      删除具体内容
//     */
//    fun deleteByKeyData(context: Context?, id: String) {
//        DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.deleteByKey(id)
//    }

    /**
     * 删除全部数据

     * @param context
     */
    fun deleteAllData(context: Context?) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.deleteAll()
    }

    /**
     * 更新数据库

     * @param context
     * *
     * @param student
     */
    fun updateData(context: Context?, student: CourseDB) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.update(student)
    }


    /**
     * 查询所有数据

     * @param context
     * *
     * @return
     */
    fun queryAll(context: Context?): MutableList<CourseDB>? {
        val builder = DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.queryBuilder()
        return builder?.build()?.list()
    }

    /**
     * 根据id，其他的字段类似

     * @param context
     * *
     * @param id
     * *
     * @return
     */
    fun queryForId(context: Context?, id: String): MutableList<CourseDB>? {
        val builder = DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.queryBuilder()
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(StudentDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();

         */
        // Query<Student> build = builder.where(StudentDao.Properties.Id.eq(id)).build();
        // List<Student> list = build.list();
        return builder?.where(CourseDBDao.Properties.CourseId.eq(id))?.list()
    }

    /**
     * 根据startTime
     * @param context
     * @param String
     **
     * @return 返回查询到的list
     */
    fun queryForStartTime(context: Context?,weekDay: String, startTime: String): MutableList<CourseDB>? {
        val builder = DbManager.getInstance(context!!)?.getDaoSession(context)?.courseDBDao?.queryBuilder()
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(StudentDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();

         */
        // Query<Student> build = builder.where(StudentDao.Properties.Id.eq(id)).build();
        // List<Student> list = build.list();
        return builder?.where(CourseDBDao.Properties.Weekday.eq(weekDay))?.where(CourseDBDao.Properties.StartTime.eq(startTime))?.list()
    }

}
package cn.edu.swpu.kotlintest.db

/**
 * Created by 65403 on 2018/5/7.
 */
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import cn.edu.swpu.kotlintest.dao.DaoMaster
import cn.edu.swpu.kotlintest.dao.DaoSession


class DbManager private constructor(mContext: Context) {

    private val DB_NAME = "test.db"
    private var mDevOpenHelper: DaoMaster.DevOpenHelper? = null
    private var mDaoMaster: DaoMaster? = null
    private var mDaoSession: DaoSession? = null

    init {
        // 初始化数据库信息
        mDevOpenHelper = DaoMaster.DevOpenHelper(mContext, DB_NAME)
        getDaoMaster(mContext)
        getDaoSession(mContext)
    }

    companion object {
        @Volatile
        var instance: DbManager? = null

        fun getInstance(mContext: Context): DbManager? {
            if (instance == null) {
                synchronized(DbManager::class) {
                    if (instance == null) {
                        instance = DbManager(mContext)
                    }
                }
            }
            return instance
        }
    }

    /**
     * 获取可读数据库
     *
     * @param context
     * @return
     */
    fun getReadableDatabase(context: Context): SQLiteDatabase? {
        if (null == mDevOpenHelper) {
            getInstance(context)
        }
        return mDevOpenHelper?.getReadableDatabase()
    }

    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    fun getWritableDatabase(context: Context): SQLiteDatabase? {
        if (null == mDevOpenHelper) {
            getInstance(context)
        }

        return mDevOpenHelper?.getWritableDatabase()
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    fun getDaoMaster(context: Context): DaoMaster? {
        if (null == mDaoMaster) {
            synchronized(DbManager::class.java) {
                if (null == mDaoMaster) {
                    mDaoMaster = DaoMaster(getWritableDatabase(context))
                }
            }
        }
        return mDaoMaster
    }

    /**
     * 获取DaoSession
     *
     * @param context
     * @return
     */
    fun getDaoSession(context: Context): DaoSession? {
        if (null == mDaoSession) {
            synchronized(DbManager::class.java) {
                mDaoSession = getDaoMaster(context)?.newSession()
            }
        }

        return mDaoSession
    }
}
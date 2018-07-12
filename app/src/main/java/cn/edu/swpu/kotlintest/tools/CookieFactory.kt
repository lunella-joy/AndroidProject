package cn.edu.swpu.kotlintest.tools

import android.content.Context
import android.webkit.CookieManager
import java.util.*
import android.webkit.CookieSyncManager



/**
 * Created by 65403 on 2018/5/5.
 */
object CookieFactory{

    /**
     * 得到一个符合教务处cookies格式的cookies
     * *
     * @return  返回的cookies字符串
     */
    fun getCookie(): String{
        val data = StringBuilder()
        for (i in 'a'..'z'){
            data.append(i)
        }
        for (i in 'A'..'Z')
            data.append(i)
        data.append('_')
        val sb = StringBuffer("JSESSIONID=abc")
        while (sb.length < 32) {
            val random = Random()
            val flag = random.nextInt(53)
            sb.append(data[flag])
        }
        sb.append(";SERVERID=jwxtc")
        return sb.toString()
    }


}
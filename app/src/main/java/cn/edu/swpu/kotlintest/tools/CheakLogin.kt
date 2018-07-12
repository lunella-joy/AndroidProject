package cn.edu.swpu.kotlintest.tools

import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import org.jsoup.Jsoup

/**
 * Created by 65403 on 2018/5/7.
 */
object CheakLogin {

    /**
     * 使用用户输入的信息尝试登陆，并得到登陆结果
     * *
     * @param string 登陆账号
     * @param String 登陆密码
     * @param String 登陆验证码
     * @param String 登陆cookies
     * *
     * @return  返回的登陆结果
     */
    fun cheakLogin(zjh: String, mm: String, v_yzm: String, cookie: String): Boolean{
        try {
            val client = OkHttpClient()
            val requestBodyPost = FormBody.Builder()
                    .add("zjh", zjh)
                    .add("mm", mm)
                    .add("v_yzm", v_yzm)
                    .build()
            val request = okhttp3.Request.Builder()
                    .url("http://jwxt.swpu.edu.cn/loginAction.do")
                    .header("Cookie", cookie)
                    .post(requestBodyPost)
                    .build()
            val text = client.newCall(request).execute().body().string()
            val html = Jsoup.parse(text)
            val title = html.select("title")[0].text()
            return title != "西南石油大学教务系统 - 登录"
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
    }
}


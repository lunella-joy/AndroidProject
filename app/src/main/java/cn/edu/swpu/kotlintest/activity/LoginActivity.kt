package cn.edu.swpu.kotlintest.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.view.inputmethod.InputMethodManager
import cn.edu.swpu.kotlintest.R
import cn.edu.swpu.kotlintest.db.CourseDaoOpe
import cn.edu.swpu.kotlintest.entity.CourseDB
import cn.edu.swpu.kotlintest.tools.CheakLogin
import cn.edu.swpu.kotlintest.tools.CookieFactory
import cn.edu.swpu.kotlintest.tools.ParseHtml
import com.bumptech.glide.Glide
import org.jetbrains.anko.sdk25.coroutines.onTouch


class LoginActivity : AppCompatActivity() {

    companion object{
        val action: String = "KotlinTest.broadcast.action"
    }

    val cookie=CookieFactory.getCookie() //得到一个符合教务处cookie的字符串

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        flash()

        all.onTouch { v, event ->
            if (null != this@LoginActivity.getCurrentFocus()) {
                /**
                 * 点击空白位置 隐藏软键盘
                 */
                val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mInputMethodManager!!.hideSoftInputFromWindow(this@LoginActivity.getCurrentFocus().getWindowToken(), 0)
                zjh.clearFocus()
                mm.clearFocus()
                v_yzm.clearFocus()
            }
            false
        }

        part.onTouch { v, event ->
            if (null != this@LoginActivity.getCurrentFocus()) {
                /**
                 * 点击空白位置 隐藏软键盘
                 */
                val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mInputMethodManager!!.hideSoftInputFromWindow(this@LoginActivity.getCurrentFocus().getWindowToken(), 0)
                zjh.clearFocus()
                mm.clearFocus()
                v_yzm.clearFocus()
            }
            false
        }

        my_image.setOnClickListener{
            flash()
        }

        login.setOnClickListener{
            doAsync{
                val state = CheakLogin.cheakLogin(zjh.text.toString(), mm.text.toString(),
                        v_yzm.text.toString(), cookie)
                if (state){
                    val cdo = CourseDaoOpe()
                    val cookies = cookie.split(";")
                    val JsessionId = cookies[0].split("=")[1].replace(";", "")
                    val ServerId = "jwxtc"
                    var courses: MutableList<CourseDB>? = null
                    doAsync {  //在子线程中执行对于课表的请求
                        courses = ParseHtml.getCourses2(JsessionId, ServerId)
                        if (courses?.size != 0) {
                            cdo.deleteAllData(context)
                            cdo.insertData(context, courses?.toList())
//                            uiThread { toast("课表获取成功") }
                            val intent = Intent(action)
                            sendBroadcast(intent)
//                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            finish()
//                            startActivity(intent)
                        } else {
                            uiThread { toast("发生了一些小错误，请登录教务网站检查您的课表能否查看") }
                        }
                    }
                }
                else{
                    uiThread { toast("登录失败") }
                }
            }
        }

        button.setOnClickListener({
            flash()
        })
    }

    fun flash(){
        doAsync {
            var bitmap: Bitmap? = null
            try {
                val client = OkHttpClient()
                val request = okhttp3.Request.Builder()
                        .url("http://jwxt.swpu.edu.cn/validateCodeAction.do?random=0.40023523333389743")
                        .header("Cookie", cookie)
                        .build()
                val body = client.newCall(request).execute().body()
                val imgin = body.byteStream()
                //转化为bitmap
                bitmap = BitmapFactory.decodeStream(imgin)
                uiThread {
                    my_image.setImageBitmap(bitmap)
                }
            }catch (e: Exception){
                uiThread {
                    toast("请使用学校cmcc-edu网络")
                    Glide.with(this@LoginActivity).load(R.drawable.img).into(my_image)
                }
                e.printStackTrace()
            }
        }
    }
}



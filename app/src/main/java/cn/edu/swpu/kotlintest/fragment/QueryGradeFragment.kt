package cn.edu.swpu.kotlintest.fragment


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import cn.edu.swpu.kotlintest.R
import cn.edu.swpu.kotlintest.activity.GradeViewActivity
import cn.edu.swpu.kotlintest.tools.CheakLogin
import cn.edu.swpu.kotlintest.tools.CookieFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onTouch
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread


/**
 * A simple [Fragment] subclass.
 */
class QueryGradeFragment : Fragment() {

    val cookie= CookieFactory.getCookie() //得到一个符合教务处cookie的字符串

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.activity_main, container, false)
        val my_image = view?.findViewById<ImageView>(R.id.my_image)

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
                    my_image?.setImageBitmap(bitmap)
                }
            }catch (e: Exception){
                uiThread {
                    toast("请使用学校cmcc-edu网络")
                    Glide.with(context).load(R.drawable.img).into(my_image)
                }
                e.printStackTrace()
            }
        }
        val content = activity

        val all = view?.findViewById<LinearLayout>(R.id.all)
        all?.onTouch { v, event ->
            if (null != content.currentFocus) {
                /**
                 * 点击空白位置 隐藏软键盘
                 */
                val mInputMethodManager = content.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mInputMethodManager.hideSoftInputFromWindow(content.currentFocus.windowToken, 0)
                zjh.clearFocus()
                mm.clearFocus()
                v_yzm.clearFocus()
            }
            false
        }

        val part = view?.findViewById<LinearLayout>(R.id.part)
        part?.onTouch { v, event ->
            if (null != content.currentFocus) {
                /**
                 * 点击空白位置 隐藏软键盘
                 */
                val mInputMethodManager = content.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mInputMethodManager.hideSoftInputFromWindow(content.currentFocus.windowToken, 0)
                zjh.clearFocus()
                mm.clearFocus()
                v_yzm.clearFocus()
            }
            false
        }

        my_image?.setOnClickListener{
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
                        Glide.with(context).load(R.drawable.img).into(my_image)
                    }
                    e.printStackTrace()
                }
            }
        }

        val login = view?.findViewById<Button>(R.id.login)
        login?.setOnClickListener{
            doAsync{
                val state = CheakLogin.cheakLogin(zjh.text.toString(), mm.text.toString(),
                        v_yzm.text.toString(), cookie)
                if (state){
                    val intent = Intent(activity, GradeViewActivity::class.java)
                    intent.putExtra("cookies", cookie)
                    startActivity(intent)
                }
                else{
                    uiThread { toast("登录失败") }
                }
            }
        }

        val button = view?.findViewById<Button>(R.id.button)
        button?.setOnClickListener({
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
                        my_image?.setImageBitmap(bitmap)
                    }
                }catch (e: Exception){
                    uiThread {
                        toast("请使用学校cmcc-edu网络")
                        Glide.with(context).load(R.drawable.img).into(my_image)
                    }
                    e.printStackTrace()
                }
            }
        })
        return view
    }
}


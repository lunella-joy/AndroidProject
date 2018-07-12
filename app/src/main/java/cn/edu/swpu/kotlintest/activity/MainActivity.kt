package cn.edu.swpu.kotlintest.activity

import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import cn.edu.swpu.kotlintest.R
import cn.edu.swpu.kotlintest.fragment.CourseDetailsFragment
import cn.edu.swpu.kotlintest.fragment.NewsFragment
import cn.edu.swpu.kotlintest.fragment.QueryGradeFragment
import cn.edu.swpu.kotlintest.tools.BottomNavigationViewHelper
import android.support.v4.app.NotificationCompat.getExtras
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.support.annotation.UiThread
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    private val fragment1: NewsFragment = NewsFragment()
    private val fragment2: CourseDetailsFragment = CourseDetailsFragment()
    private val fragment3: QueryGradeFragment = QueryGradeFragment()
    private var fragments: MutableList<Fragment>? = null
    var lastShowFragment = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.item_news -> {
                if (lastShowFragment != 0) {
                    switchFragment(lastShowFragment, 0)
                    lastShowFragment = 0
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.item_courses -> {
                if (lastShowFragment != 1) {
                    switchFragment(lastShowFragment, 1)
                    lastShowFragment = 1
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.item_grade -> {
                if (lastShowFragment != 2) {
                    switchFragment(lastShowFragment, 2)
                    lastShowFragment = 2
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(lastIndex: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment, fragments!![index]).commit()
        if (!fragments!![index].isAdded) {
            transaction.hide(fragments!![lastIndex])
                    .add(R.id.fragment, fragments!![index])
                    .commit()
        } else {
            transaction.hide(fragments!![lastIndex])
                    .show(fragments!![index])
                    .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        initFragments()
    }


    override fun onRestart() {
        super.onRestart()
        if (lastShowFragment == 1) {
            fragments!!.remove(fragment2)
            val fragment = CourseDetailsFragment()
            fragments!![1] = fragment
            fragments!!.add(fragment3)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(fragment2).hide(fragment2).add(R.id.fragment, fragment).commit()

        }
    }


    private fun initFragments() {
        val transaction = supportFragmentManager.beginTransaction()
        fragments = arrayOf(fragment1, fragment2, fragment3).toMutableList()
        transaction.replace(R.id.fragment, fragments!![0]).commit()
        lastShowFragment = 0
    }


}

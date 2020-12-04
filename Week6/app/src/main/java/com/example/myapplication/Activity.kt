package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_.*
import kotlin.properties.Delegates

class Activity : AppCompatActivity() {
    private lateinit var viewpagerAdapter2 : ViewPagerAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_)

        val fragment1 = Fragment1()

        viewpagerAdapter2 = ViewPagerAdapter2(supportFragmentManager)
        viewpagerAdapter2.fragments = listOf(
            Fragment1(),
            RecyclerFragment(),
            SearchFragment()
        )

        sample_viewpager.adapter = viewpagerAdapter2

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment1).commit()

        sample_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_brush -> index = 0
                R.id.menu_camera -> index = 1
                R.id.menu_checkbox -> index = 2
            }
            sample_viewpager.currentItem = index
            true
        }

        sample_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}
            // ViewPager의 페이지 중 하나가 선택된 경우
            override fun onPageSelected(position: Int) {
                sample_bottom_navi.menu.getItem(position).isChecked = true
            }
        })
    }
}
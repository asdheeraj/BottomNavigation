package com.mindorks.example.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mindorks.example.R
import com.mindorks.example.R.layout
import com.mindorks.example.ui.fragment.MindOrksFragment
import com.mindorks.example.ui.fragment.UserFragment
import com.mindorks.example.ui.fragment.AfterAcademyFragment
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView

class MainActivity : AppCompatActivity() {

    private val mindOrksFragment = MindOrksFragment()
    private val afterAcademyFragment = AfterAcademyFragment()
    private val userFragment = UserFragment()
    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = mindOrksFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        fragmentManager.beginTransaction().apply {
            add(R.id.container, userFragment, getString(R.string.user)).hide(userFragment)
            add(R.id.container, afterAcademyFragment, getString(R.string.after_academy)).hide(afterAcademyFragment)
            add(R.id.container, mindOrksFragment, getString(R.string.mindOrks))
        }.commit()
        initListeners()
        bottomNavigationView.itemIconTintList = null
    }

    private fun initListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            return@setOnNavigationItemSelectedListener when (menuItem.itemId) {
                R.id.navigation_mindorks -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(mindOrksFragment).commit()
                    activeFragment = mindOrksFragment
                    true
                }

                R.id.navigation_afterAcademy -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(afterAcademyFragment).commit()
                    activeFragment = afterAcademyFragment
                    true
                }

                R.id.navigation_user -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(userFragment).commit()
                    activeFragment = userFragment
                    true
                }

                else -> false
            }
        }
    }
}

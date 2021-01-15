package com.example.week6assignment1

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.week6assignment1.fragments.AboutUsFragment
import com.example.week6assignment1.fragments.AddStudentFragment
import com.example.week6assignment1.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class homeActivity : AppCompatActivity() {
    private lateinit var bnvNavigation: BottomNavigationView
    private lateinit var llFragment: LinearLayout
    private var storage = Storage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llFragment = findViewById(R.id.llFragment)
        bnvNavigation = findViewById(R.id.bnvNavigation)

        val aboutUsFragment = AboutUsFragment()
        val homeFragment = HomeFragment()
        val addStudentFragment = AddStudentFragment()

        loadDefaultUsers()

        makeFragment(homeFragment)
        bnvNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.iAboutUs -> makeFragment(aboutUsFragment)
                R.id.iHome -> makeFragment(homeFragment)
                R.id.iAddStudent -> makeFragment(addStudentFragment)
            }
            true
        }

    }

    private fun loadDefaultUsers() {
        storage.loadDefault()
    }

    private fun makeFragment(f: Fragment): Boolean {
        supportFragmentManager.beginTransaction().apply {
            this.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out
            );
            replace(R.id.llFragment, f)
            commit()
        }
        return true
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout?")

        builder.setMessage("Do you want to logout?")

        builder.setIcon(android.R.drawable.ic_dialog_info)

        builder.setPositiveButton("YES") { _, _ ->
            super.onBackPressed()
        }
        builder.setNegativeButton("No") { _, _ ->

        }

        val alert: AlertDialog = builder.create()
        alert.setCancelable(true)
        alert.show()
    }
}

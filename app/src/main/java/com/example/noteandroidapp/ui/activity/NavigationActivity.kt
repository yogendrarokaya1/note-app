package com.example.noteandroidapp.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.noteandroidapp.R
import com.example.noteandroidapp.databinding.ActivityNavigationBinding
import com.example.noteandroidapp.ui.fragment.HomeFragment
import com.example.noteandroidapp.ui.fragment.ProfileFragment

class NavigationActivity : AppCompatActivity() {
    lateinit var navigationBinding: ActivityNavigationBinding

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        navigationBinding = ActivityNavigationBinding.inflate(layoutInflater)

        setContentView(navigationBinding.root)

        replaceFragment(HomeFragment())
        navigationBinding.buttomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> replaceFragment(HomeFragment())
                R.id.menuProfile -> replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }
}
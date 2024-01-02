package com.example.crud_roomdatabase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.crud_roomdatabase.R
import com.example.crud_roomdatabase.databinding.ActivityMainBinding
import com.example.crud_roomdatabase.view.fragment.BookmarkFragment
import com.example.crud_roomdatabase.view.fragment.HomeFragment
import com.example.crud_roomdatabase.view.fragment.RecentFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
        setBottomNavigation()
    }

    private fun setNavigation(){
        val host = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction().replace(R.id.navigationHost, host)
            .setPrimaryNavigationFragment(host).commit()
    }

    private fun setBottomNavigation(){
        val homeFragment = HomeFragment()
        val bookmarkFragment = BookmarkFragment()
        val recentFragment = RecentFragment()

        binding.bottomNavigationView.setOnItemSelectedListener  {
            when(it.itemId){
                R.id.home->setCurrentFragment(homeFragment)
                R.id.bookmark->setCurrentFragment(bookmarkFragment)
                R.id.recent->setCurrentFragment(recentFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navigationHost,fragment)
            commit()
        }
}
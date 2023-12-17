package com.example.crud_roomdatabase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud_roomdatabase.R
import com.example.crud_roomdatabase.databinding.ActivityMainBinding
import com.example.crud_roomdatabase.view.fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        setContentView(binding.root)
    }
}
package com.example.appmusic

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appmusic.databinding.ActivityMainBinding
import com.example.appmusic.databinding.HeaderMenuBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var headerBinding: HeaderMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //trỏ tới drawer có id là menu
        val drawer = findViewById<DrawerLayout>(R.id.menu)
        val accname = findViewById<TextView>(R.id.menu_username)
        val accpass = findViewById<TextView>(R.id.menu_usermail)

        binding.btnOpenDrawer.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        // Lấy dữ liệu người dùng từ Intent
        val userName = intent.getStringExtra("user_name")
        val userEmail = intent.getStringExtra("user_email")

        // Gán headerLayoutBinding cho navigationView menu
        val navigationView = binding.navView
        val headerLayoutBinding = HeaderMenuBinding.bind(navigationView.getHeaderView(0))

        // Gán dữ liệu người dùng vào các TextView
        headerLayoutBinding.menuUsername.text = userName
        headerLayoutBinding.menuUsermail.text = userEmail
    }
}
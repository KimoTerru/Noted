package com.kimoterru.noted.presenter

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupMainScreen()
    }

    private fun setupMainScreen() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(getColor(R.color.my_bar_color)))
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        setupActionBarWithNavController(navHostFragment.navController)
    }
}
package com.kimoterru.noted.presenter

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupMainScreen()
    }

    private fun setupMainScreen() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_home -> setupActionBar(getString(R.string.your_notes))
                R.id.fragment_new_note -> setupActionBar(getString(R.string.add_new_note))
                R.id.fragment_detail_note -> setupActionBar(getString(R.string.change_note))
            }
        }
    }

    private fun setupActionBar(title: String) {
        supportActionBar?.apply {
            setTitle(title)
            setBackgroundDrawable(ColorDrawable(getColor(R.color.dynamic_color_for_theme)))
        }
    }
}
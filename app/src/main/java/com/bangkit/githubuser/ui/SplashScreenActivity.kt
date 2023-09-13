package com.bangkit.githubuser.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.bangkit.githubuser.R
import com.bangkit.githubuser.ui.preference.SettingPreferences
import com.bangkit.githubuser.ui.preference.dataStore

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var mainActivityStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val viewModel = ViewModelProvider(
            this,
            SplashScreenViewModel.Factory(SettingPreferences.getInstance(application.dataStore))
        )[SplashScreenViewModel::class.java]

        viewModel.getThemeSetting().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        if (!mainActivityStarted) {
            mainActivityStarted = true
            @Suppress("DEPRECATION") val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000L)
        }
    }
}
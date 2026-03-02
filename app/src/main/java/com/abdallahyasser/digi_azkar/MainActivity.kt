package com.abdallahyasser.digi_azkar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abdallahyasser.digi_azkar.databinding.ActivityMainBinding
import com.abdallahyasser.digi_azkar.presentation.azkar.AzkarFragment
import com.abdallahyasser.digi_azkar.presentation.home.HomeFragment
import com.abdallahyasser.digi_azkar.presentation.prayer.PrayerFragment
import com.abdallahyasser.digi_azkar.presentation.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val homeFragment = HomeFragment.newInstance("","")
        val azkarFragment= AzkarFragment.newInstance("","")
        val prayerFragment= PrayerFragment.newInstance()
        val settingFragment= SettingFragment.newInstance()


        supportFragmentManager.beginTransaction().replace(binding.fragmentContainerView.id, homeFragment).commit()


        binding.azkarNavItem.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.fragmentContainerView.id, azkarFragment).commit()
        }

        binding.prayerNavItem.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.fragmentContainerView.id, prayerFragment).commit()
        }

        binding.navHomeItem.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.fragmentContainerView.id, homeFragment).commit()
        }

        binding.settingsNavItem.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.fragmentContainerView.id, settingFragment).commit()
        }


    }
}
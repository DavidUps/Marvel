package com.davidups.marvel.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.davidups.marvel.marvel.R
import com.davidups.marvel.marvel.databinding.NavigationActivityBinding
import kotlinx.android.synthetic.main.navigation_activity.toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: NavigationActivityBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NavigationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListeners()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        navController.addOnDestinationChangedListener { _, destination, _ -> }
    }
}

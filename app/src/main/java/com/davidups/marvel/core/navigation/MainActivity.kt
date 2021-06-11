package com.davidups.marvel.core.navigation

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.davidups.marvel.R
import com.davidups.marvel.databinding.NavigationActivityBinding
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
        binding.container.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.visibility = when (destination.id) {
                R.id.characterFragment -> View.VISIBLE
                else -> View.GONE
            }
        }
    }
}

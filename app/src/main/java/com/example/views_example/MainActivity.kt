package com.example.views_example

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.views_example.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navHostController: NavController
    private lateinit var bottomNavigationView: NavigationBarView
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var menuIcon: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostController = navHostFragment.navController
        setSupportActionBar(binding.topBarMain)
        navHostController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.tvTopBarText.text = destination.label
        }
        menuIcon = findViewById(R.id.menuIcon)
        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navHostController)
        menuIcon.setOnClickListener {
            lifecycleScope.launch {
                binding.mainDrawerLayout.open()
            }
        }


    }
}
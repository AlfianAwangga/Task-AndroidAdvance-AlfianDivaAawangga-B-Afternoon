package com.example.task_androidadvance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.task_androidadvance.R
import com.example.task_androidadvance.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setBottomNavigation()
        setNavigationDrawer()
    }

    private fun setToolbar() {
        binding.toolbar.title = "Mobile App"
        binding.toolbar.setTitleTextColor(getColor(R.color.white))
        setSupportActionBar(binding.toolbar)
    }

    private fun setBottomNavigation() {
        val navController = Navigation.findNavController(this@MainActivity, R.id.fragment_container)
        binding.bottomNav.setupWithNavController(navController)
    }

    private fun setNavigationDrawer() {
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navDrawer.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.favorite -> {
                Toast.makeText(this, "Menu Favorite Di-klik", Toast.LENGTH_SHORT).show()
            }

            R.id.bookmark -> {
                Toast.makeText(this, "Menu Bookmark Di-klik", Toast.LENGTH_SHORT).show()
            }

            R.id.share -> {
                Toast.makeText(this, "Menu Share Di-klik", Toast.LENGTH_SHORT).show()
            }

            R.id.logout -> {
                Toast.makeText(this, "Menu Log Out Di-klik", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                Toast.makeText(this, "Menu Favorite Di-klik", Toast.LENGTH_SHORT).show()
            }

            R.id.bookmark -> {
                Toast.makeText(this, "Menu Bookmark Di-klik", Toast.LENGTH_SHORT).show()
            }

            R.id.share -> {
                Toast.makeText(this, "Menu Share Di-klik", Toast.LENGTH_SHORT).show()
            }

            R.id.logout -> {
                Toast.makeText(this, "Menu Log Out Di-klik", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}
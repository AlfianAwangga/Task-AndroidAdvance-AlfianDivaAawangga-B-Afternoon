package com.example.task_androidadvance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.task_androidadvance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
    }

    private fun setToolbar() {
        binding.toolbar.title = "Mobile App"
        binding.toolbar.setTitleTextColor(getColor(R.color.white))
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
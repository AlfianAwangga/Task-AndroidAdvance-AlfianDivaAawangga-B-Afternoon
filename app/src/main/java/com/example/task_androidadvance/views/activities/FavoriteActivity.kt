package com.example.task_androidadvance.views.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_androidadvance.R
import com.example.task_androidadvance.database.FavoriteDatabase
import com.example.task_androidadvance.databinding.ActivityFavoriteBinding
import com.example.task_androidadvance.repositories.UserRepository
import com.example.task_androidadvance.viewmodels.UserViewModel
import com.example.task_androidadvance.viewmodels.UserViewModelFactory
import com.example.task_androidadvance.views.adapters.FavoriteAdapter


class FavoriteActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        getData()

        binding.ivBack.setOnClickListener(this)
    }

    private fun init(){
        val repository = UserRepository(FavoriteDatabase.invoke(this))
        val viewModelFactory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]

        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.setHasFixedSize(true)

        adapter = FavoriteAdapter(this, arrayListOf()){ item ->
            viewModel.deleteFromFavorite(item.id)
            getData()
        }
        adapter.notifyDataSetChanged()
    }

    private fun getData(){
        viewModel.favorites.observe(this, Observer { data->
            adapter.setData(data)
            binding.rvFavorite.adapter = adapter
        })
        viewModel.getAllFavorites()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.iv_back -> {
                setResult(Activity.RESULT_OK, Intent())
                finish()
            }
        }
    }
}
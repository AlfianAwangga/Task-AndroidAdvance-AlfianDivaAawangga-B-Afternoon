package com.example.task_androidadvance.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_androidadvance.database.FavoriteDatabase
import com.example.task_androidadvance.databinding.FragmentHomeBinding
import com.example.task_androidadvance.repositories.UserRepository
import com.example.task_androidadvance.viewmodels.UserViewModel
import com.example.task_androidadvance.viewmodels.UserViewModelFactory
import com.example.task_androidadvance.views.adapters.UserAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        init()
        setAdapter()
        getData()

        return binding.root
    }

    private fun init() {
        val repository = UserRepository(FavoriteDatabase.invoke(requireContext()))
        val viewModelFactory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]

        binding.rvUser.layoutManager = LinearLayoutManager(activity)
        binding.rvUser.setHasFixedSize(true)
    }

    private fun setAdapter(){
        adapter = UserAdapter(activity, arrayListOf()) { position, isChecked, item ->
            if (isChecked) {
                viewModel.insertToFavorite(item)
                Toast.makeText(activity, "Ditambahkan ke favorit", Toast.LENGTH_SHORT).show()

            }else{
                viewModel.deleteFromFavorite(item.id)
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun getData() {
        viewModel.users.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                adapter.setData(response.body()!!)
                binding.rvUser.adapter = adapter
            }
        })
        viewModel.getUsers()
    }
}
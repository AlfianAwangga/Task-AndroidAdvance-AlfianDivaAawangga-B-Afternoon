package com.example.task_androidadvance.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_androidadvance.api.ApiClient
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
        getData()

        return binding.root
    }

    private fun init() {
        val repository = UserRepository(ApiClient.apiService)
        val viewModelFactory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]

        binding.rvUser.layoutManager = LinearLayoutManager(activity)
        binding.rvUser.setHasFixedSize(true)

        adapter = UserAdapter(activity, arrayListOf())
        adapter.notifyDataSetChanged()
    }

    private fun getData() {
        viewModel.getUsers()
        viewModel.users.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                adapter.setData(response.body()!!)
                binding.rvUser.adapter = adapter
            }
        })
    }

}
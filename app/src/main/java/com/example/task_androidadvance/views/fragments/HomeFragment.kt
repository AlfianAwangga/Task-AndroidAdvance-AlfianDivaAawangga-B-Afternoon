package com.example.task_androidadvance.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_androidadvance.api.ApiClient
import com.example.task_androidadvance.views.adapters.UserAdapter
import com.example.task_androidadvance.databinding.FragmentHomeBinding
import com.example.task_androidadvance.models.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRv()
        remoteGetUsers()

        return binding.root
    }

    private fun initRv() {
        binding.rvUser.layoutManager = LinearLayoutManager(activity)
        binding.rvUser.setHasFixedSize(true)
    }

    private fun remoteGetUsers() {
        ApiClient.apiService.getUsers().enqueue(object: Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if(response.isSuccessful){
                    val data = response.body()
                    setRvAdapter(data!!)
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("Error", t.stackTraceToString())
            }

        })
    }

    private fun setRvAdapter(data: Users){
        adapter = UserAdapter(activity, arrayListOf())
        adapter.setData(data)
        binding.rvUser.adapter = adapter
    }
}
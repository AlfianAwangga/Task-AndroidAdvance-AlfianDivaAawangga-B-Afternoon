package com.example.task_androidadvance.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_androidadvance.R
import com.example.task_androidadvance.views.adapters.UserAdapter
import com.example.task_androidadvance.databinding.FragmentHomeBinding
import com.example.task_androidadvance.models.UserModel

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
        setRvAdapter()

        return binding.root
    }

    private fun initRv() {
        binding.rvUser.layoutManager = LinearLayoutManager(activity)
    }

    private fun setRvAdapter() {
        val dataUser = mutableListOf<UserModel>()

        namaUser().forEachIndexed { index, nama ->
            dataUser.add(
                UserModel(
                    nama,
                    usernameUser()[index],
                    emailUser()[index],
                    phoneUser()[index]
                )
            )
        }
        adapter = UserAdapter(activity, dataUser)
        binding.rvUser.adapter = adapter

    }

    private fun namaUser(): Array<String> {
        return resources.getStringArray(R.array.nama)
    }
    private fun usernameUser(): Array<String> {
        return resources.getStringArray(R.array.username)
    }
    private fun emailUser(): Array<String> {
        return resources.getStringArray(R.array.email)
    }
    private fun phoneUser(): Array<String> {
        return resources.getStringArray(R.array.phone)
    }
}
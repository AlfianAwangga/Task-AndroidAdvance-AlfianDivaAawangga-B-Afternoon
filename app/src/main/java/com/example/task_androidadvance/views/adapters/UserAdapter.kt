package com.example.task_androidadvance.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_androidadvance.databinding.ListUserBinding
import com.example.task_androidadvance.models.UserItem
import com.example.task_androidadvance.models.Users

class UserAdapter(private val context: Context?, private val list: MutableList<UserItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    class ViewHolder(val binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvUserNama.text = this.name
                binding.tvUserUsername.text = this.username
                binding.tvUserEmail.text = this.email
                binding.tvUserPhone.text = this.phone
            }
        }
    }

    fun setData(data: Users) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


}
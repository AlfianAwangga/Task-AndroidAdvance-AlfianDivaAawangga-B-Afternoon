package com.example.task_androidadvance.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_androidadvance.databinding.ListFavoriteBinding
import com.example.task_androidadvance.models.UserItem

class FavoriteAdapter(
    private val context: Context?, private val list: MutableList<UserItem>,
    private val onItemClickListener: (UserItem) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvFavNama.text = this.name
                binding.tvFavUsername.text = this.username

                binding.btnDelete.setOnClickListener{
                    onItemClickListener.invoke(list[position])
                }
            }
        }
    }

    fun setData(data: List<UserItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


}
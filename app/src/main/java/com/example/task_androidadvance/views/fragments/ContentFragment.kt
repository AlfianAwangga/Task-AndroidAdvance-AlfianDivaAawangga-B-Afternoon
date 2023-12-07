package com.example.task_androidadvance.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task_androidadvance.views.adapters.ViewPagerAdapter
import com.example.task_androidadvance.databinding.FragmentContentBinding

class ContentFragment : Fragment() {
    private lateinit var binding: FragmentContentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContentBinding.inflate(inflater, container, false)

        binding.viewpagerContent.adapter = ViewPagerAdapter(childFragmentManager)
        binding.tabsContent.setupWithViewPager(binding.viewpagerContent)

        return binding.root
    }
}
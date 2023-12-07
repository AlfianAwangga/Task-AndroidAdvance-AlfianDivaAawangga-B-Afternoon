package com.example.task_androidadvance.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.task_androidadvance.views.fragments.AlarmFragment
import com.example.task_androidadvance.views.fragments.BrainTrainerFragment

class ViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AlarmFragment()
            }
            else -> {
                BrainTrainerFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Alarm Manager"
            else -> {
                return "Brain Trainer"
            }
        }
    }
}
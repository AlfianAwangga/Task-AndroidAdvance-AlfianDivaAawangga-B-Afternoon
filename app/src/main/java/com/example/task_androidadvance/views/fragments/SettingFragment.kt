package com.example.task_androidadvance.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import com.example.task_androidadvance.R
import com.example.task_androidadvance.databinding.FragmentSettingBinding
import com.example.task_androidadvance.utils.SharedPreference

class SettingFragment : Fragment(), CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var prefs: SharedPreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        prefs = SharedPreference(requireContext())
        binding.switchAudio.setOnCheckedChangeListener(this)
        binding.switchSfx.setOnCheckedChangeListener(this)
        binding.switchNotif.setOnCheckedChangeListener(this)
        statusCheck()

        return binding.root
    }

    private fun statusCheck() {
        binding.switchAudio.isChecked = prefs.getAudioSetting() == 1
        binding.switchSfx.isChecked = prefs.getSfxSetting() == 1
        binding.switchNotif.isChecked = prefs.getNotifSetting() == 1
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        val message: String
        when (buttonView.id) {
            R.id.switch_audio -> {
                if (isChecked) {
                    prefs.saveAudioSetting(1)
                    message = "Audio is ON"
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

                } else {
                    prefs.saveAudioSetting(0)
                    message = "Audio is OFF"
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                }
            }

            R.id.switch_sfx -> {
                if (isChecked) {
                    prefs.saveSfxSetting(1)
                    message = "Sound Effect is ON"
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

                } else {
                    prefs.saveSfxSetting(0)
                    message = "Sound Effect is OFF"
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                }
            }

            R.id.switch_notif -> {
                if (isChecked) {
                    prefs.saveNotifSetting(1)
                    message = "Notification is ON"
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

                } else {
                    prefs.saveNotifSetting(0)
                    message = "Sound Effect is ON"
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
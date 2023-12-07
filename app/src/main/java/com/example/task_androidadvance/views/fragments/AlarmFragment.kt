package com.example.task_androidadvance.views.fragments

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.task_androidadvance.R
import com.example.task_androidadvance.databinding.FragmentAlarmBinding
import com.example.task_androidadvance.utils.AlarmReceiver
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

class AlarmFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAlarmBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container, false)

        createNotificationChannel()
        binding.btnSetAlarm.setOnClickListener(this)
        binding.btnCancelAlarm.setOnClickListener(this)
        binding.btnSelectTime.setOnClickListener(this)

        return binding.root
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nama: CharSequence = "Alfian Diva Awangga"
            val deskripsi = "Channel AlarmManager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("Alfian", nama, importance)
            channel.description = deskripsi

            val notificationManager = context?.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_select_time -> {
                showTimePicker()
            }

            R.id.btn_set_alarm -> {
                setAlarm()
            }

            R.id.btn_cancel_alarm -> {
                cancelAlarm()
            }
        }
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(requireActivity().supportFragmentManager, "Alfian")

        picker.addOnPositiveButtonClickListener {
            binding.tvTimeSelected.text =
                String.format("%02d", picker.hour) + " : " + String.format("%02d", picker.minute)
            binding.tvTimeSelected.setTypeface(Typeface.DEFAULT_BOLD)
            binding.tvTimeSelected.textSize = 48f

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }

    private fun setAlarm() {
        val intent = Intent(activity, AlarmReceiver::class.java)
        alarmManager = context?.getSystemService(ALARM_SERVICE) as AlarmManager
        pendingIntent =
            PendingIntent.getBroadcast(activity, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        Toast.makeText(activity, "Alarm is set successfully", Toast.LENGTH_SHORT).show()
    }

    private fun cancelAlarm() {
        val intent = Intent(activity, AlarmReceiver::class.java)
        alarmManager = context?.getSystemService(ALARM_SERVICE) as AlarmManager
        pendingIntent =
            PendingIntent.getBroadcast(activity, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.cancel(pendingIntent)

        Toast.makeText(activity, "Alarm is cancelled", Toast.LENGTH_SHORT).show()
    }
}
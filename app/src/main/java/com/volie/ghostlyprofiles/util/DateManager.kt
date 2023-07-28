package com.volie.ghostlyprofiles.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateManager {

    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date: Date? = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }
}
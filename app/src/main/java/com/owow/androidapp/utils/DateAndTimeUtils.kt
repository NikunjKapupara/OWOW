package com.owow.androidapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateAndTimeUtils {
    companion object {

        fun getDateinDD_MM_YYYY(dateFromService: String): String {

            if (ValidationUtils.isStringEmpty(dateFromService))
                return ""

            return try {
                val defaultLocale = Locale("en")
                Locale.setDefault(defaultLocale)
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val output = SimpleDateFormat("dd/MM/yyyy")
                val d = inputFormat.parse(dateFromService)
                output.format(d)
            } catch (e: Exception) {
                e.printStackTrace()
                "NA"
            }
        }
    }
}
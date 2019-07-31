package com.andreamw96.moviecatalogue.utils

import androidx.room.TypeConverter


object MyTypeConverters {

    @TypeConverter
    @JvmStatic
    fun stringToIntList(data: String?): List<Int>? {
        return data?.let { it ->
            it.split(",").map {
                try {
                    it.toInt()
                } catch (ex: NumberFormatException) {
                    loge("Cannot convert $it to number")
                    null
                }
            }
        }?.filterNotNull()
    }

    @TypeConverter
    @JvmStatic
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString(",")
    }

    @TypeConverter
    @JvmStatic
    fun stringToStringList(data: String?): List<String>? {
        return data?.let { it ->
            it.split(",").map {
                try {
                    it
                } catch (ex: NumberFormatException) {
                    loge("Cannot convert $it to string")
                    null
                }
            }
        }?.filterNotNull()
    }

    @TypeConverter
    @JvmStatic
    fun stringListToString(strings: List<String>?): String? {
        return strings?.joinToString(",")
    }
}
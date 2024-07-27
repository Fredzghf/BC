package com.example.bc.utils

import android.content.Context

class ResourceManager(private val context: Context) {
    fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}
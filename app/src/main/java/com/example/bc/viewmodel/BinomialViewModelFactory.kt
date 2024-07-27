package com.example.bc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bc.utils.ResourceManager

class BinomialViewModelFactory(private val resourceManager: ResourceManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BinomialViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BinomialViewModel(resourceManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

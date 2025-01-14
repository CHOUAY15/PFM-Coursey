package com.example.firstaidfront.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.firstaidfront.config.TokenManager
import com.example.firstaidfront.models.Enrollment

import com.example.firstaidfront.repository.EnrollmentRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class LearningModuleViewModel(private val context: Context) : ViewModel() {
    private val enrollmentRepository = EnrollmentRepository(context)

    private val _enrollment = MutableStateFlow<Enrollment?>(null)
    val enrollment: StateFlow<Enrollment?> = _enrollment.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadEnrollment(enrollmentId: Int) {
        _enrollment.value = null
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = enrollmentRepository.getEnrollmentById(enrollmentId)
                _enrollment.value = result
            } catch (e: Exception) {
                _error.value = "Failed to load enrollment: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    class Factory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LearningModuleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LearningModuleViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
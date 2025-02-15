package com.example.doctorappointment.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.doctorappointment.Model.DoctorsModel
import com.example.doctorappointment.Repository.MainRepository

class MainViewModel():ViewModel() {
    private val repository = MainRepository()

    fun loadDoctors(): LiveData<MutableList<DoctorsModel>> {
        return repository.load()
    }
}